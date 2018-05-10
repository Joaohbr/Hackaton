package br.com.zup.hackatontimesheet.refund_entry.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;
import br.com.zup.hackatontimesheet.commons.adapters.SimpleSpinnerAdapter;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundEntryActivity extends BaseActivity implements RefundEntryContract.View {

    private static final String ENTRY = "entry";
    public static final String REFUND_ENTRY = "refund_entry";

    @Inject
    RefundEntryContract.Presenter mPresenter;
    private Calendar mCalendar;

    private SimpleSpinnerAdapter spinnerAdapter;

    private Spinner categorySpinner;
    private EditText datePicker;
    private Button saveButton;
    private TextInputEditText description, value;

    public static Intent newIntent(Context context, RefundEntry entry) {
        Intent intent = new Intent(context, RefundEntryActivity.class);
        intent.putExtra(ENTRY,entry);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_entry);

        categorySpinner = findViewById(R.id.category_spinner);
        datePicker = findViewById(R.id.date_picker);
        saveButton = findViewById(R.id.action_save);
        description = findViewById(R.id.refund_description);
        value = findViewById(R.id.refund_value);


        ((TimesheetApplication)getApplication())
                .getUserComponent()
                .getRefundEntryComponentBuilder()
                .view(this)
                .build()
                .inject(this);


        mCalendar = Calendar.getInstance();

        saveButton.setOnClickListener(getSaveClickListener());

        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(RefundEntryActivity.this, getDateSetListener(), mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mPresenter.fetchData((RefundEntry)getIntent().getExtras().getParcelable(ENTRY));
    }

    @Override
    public void setupCategorySpinner(String[] categories) {
        spinnerAdapter = new SimpleSpinnerAdapter(this, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void updateSelectedDate(String date) {
        datePicker.setError(null);
        datePicker.setText(date);
    }

    @Override
    public void finish(RefundEntry entry) {
        Intent result = new Intent();
        result.putExtra(REFUND_ENTRY, entry);
        setResult(Activity.RESULT_OK,result);
        finish();
    }

    @Override
    public void showSelectedEntry(RefundEntry entry) {

        if(spinnerAdapter.getItem(entry.getCategoryPosition()) == null) {
            Toast.makeText(this,R.string.toast_category_not_found,Toast.LENGTH_SHORT);
        } else {
            categorySpinner.setSelection(entry.getCategoryPosition());
        }
        datePicker.setText(entry.getDate());
        description.setText(entry.getDescription());
        value.setText(entry.getValue());
    }

    private DatePickerDialog.OnDateSetListener getDateSetListener() {
        return new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mPresenter.onSelectedDate(mCalendar);
            }

        };
    }

    private View.OnClickListener getSaveClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields())
                    mPresenter.onSaveReport(getInputedEntry());
            }
        };
    }

    private boolean validateFields() {
        boolean areFieldsValid = true;

        if(categorySpinner.getSelectedItemPosition() == 0) {
            ((TextView)categorySpinner.getSelectedView()).setError(getString(R.string.label_required_field));
            areFieldsValid = false;
        }
        if(datePicker.getText().toString() == null || datePicker.getText().toString().isEmpty()) {
            datePicker.setError(getString(R.string.label_required_field));
            areFieldsValid = false;
        }
        if(description.getText().toString() == null || description.getText().toString().isEmpty()) {
            description.setError(getString(R.string.label_required_field));
            areFieldsValid = false;
        }
        if(value.getText().toString() == null || value.getText().toString().isEmpty()) {
            value.setError(getString(R.string.label_required_field));
            areFieldsValid = false;
        }

        return areFieldsValid;
    }

    private RefundEntry getInputedEntry() {
        RefundEntry entry = new RefundEntry();
        entry.setCategoryPosition(categorySpinner.getSelectedItemPosition()-1);
        entry.setDate(datePicker.getText().toString());
        entry.setDescription(description.getText().toString());
        entry.setValue(value.getText().toString());
        return entry;
    }

}
