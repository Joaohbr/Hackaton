package br.com.zup.hackatontimesheet.refund_report.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.commons.adapters.SimpleSpinnerAdapter;
import br.com.zup.hackatontimesheet.refund_report.di.RefundReportComponent;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;
import br.com.zup.hackatontimesheet.utils.generic_activities.LoggedActivity;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundReportActivity extends LoggedActivity implements RefundReportContract.View {

    private static final String PROJECT_INDEX = "projectIndex";

    private Spinner currencySpinner;
    private TextView totalTextView, projectNameTextView, totalTitleTextView;
    private TextInputEditText advance;

    @Inject
    RefundReportContract.Presenter mPresenter;

    public static Intent newIntent(Context context, int selectedProjectIndex) {
        Intent intent = new Intent(context, RefundReportActivity.class);
        intent.putExtra(PROJECT_INDEX, selectedProjectIndex);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_report);

        if(!isLogged()) {
            onUserNotLogged();
            return;
        } else {

            int selectedProjectIndex = getIntent().getExtras().getInt(PROJECT_INDEX, 0);

            RefundReportContract.ChildView childView = (RefundReportContract.ChildView) getSupportFragmentManager().findFragmentById(R.id.refund_list_fragment);

            RefundReportComponent mComponent = ((TimesheetApplication) getApplication())
                    .getUserComponent()
                    .getRefundReportComponentBuilder()
                    .childView(childView)
                    .projectIndex(selectedProjectIndex)
                    .view(this)
                    .build();

            mComponent.inject(this);
            mComponent.inject((RefundListFragment) childView);

            childView.bindPresenter(mPresenter);

            currencySpinner = findViewById(R.id.currency_spinner);
            totalTextView = findViewById(R.id.total_value);
            advance = findViewById(R.id.advance_edit_text);
            projectNameTextView = findViewById(R.id.project_name);
            totalTitleTextView = findViewById(R.id.total_title);

            advance.addTextChangedListener(getTextChangedListener());

            currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mPresenter.onCurrencySelected(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mPresenter.fetchData();
        }
    }

    @Override
    public void setupAdvance(String advance) {
        this.advance.setText(advance);
    }

    @Override
    public void setupCurrencySpinner(String[] currencies) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(this, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);
    }

    @Override
    public void setupProjectName(String projectName) {
        projectNameTextView.setText(projectName);
    }

    @Override
    public void updateTotalValue(String total, String title, boolean isNegative) {
        totalTextView.setTextColor(isNegative ?
                getResources().getColor(R.color.colorRed) : getResources().getColor(R.color.colorPrimaryLegacy));
        totalTextView.setText(total);
        totalTitleTextView.setText(title);
    }

    @Override
    public void showEmptyError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setTitle(R.string.title_oops);
        builder.setMessage(R.string.label_empty_report);
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.action_ack, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                currencySpinner.setSelection(0);
                advance.setText("0");
            }
        });
        builder.setTitle(R.string.title_success);
        builder.setMessage(R.string.label_refund_report_success);
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refund_report_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            if(validateFields())
                mPresenter.onSendRefundReport();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private TextWatcher getTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.onAdvanceValueChanged(s.toString());
            }
        };
    }

    private boolean validateFields() {
        boolean areFieldsValid = true;

        if(currencySpinner.getSelectedItemPosition() == 0) {
            ((TextView)currencySpinner.getSelectedView()).setError(getString(R.string.label_required_field));
            areFieldsValid = false;
        }
        if(advance.getText().toString() == null || advance.getText().toString().isEmpty()) {
            advance.setError(getString(R.string.label_required_field));
            areFieldsValid = false;
        }
        return areFieldsValid;
    }
}
