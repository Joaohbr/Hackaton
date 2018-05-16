package br.com.zup.hackatontimesheet.refund_entry.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;
import br.com.zup.hackatontimesheet.commons.adapters.SimpleSpinnerAdapter;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;
import br.com.zup.hackatontimesheet.utils.generic_activities.LoggedActivity;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundEntryActivity extends LoggedActivity implements RefundEntryContract.View {

    private static final String ENTRY = "entry";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_LOAD_IMAGE = 2;
    public static final String REFUND_ENTRY = "refund_entry";

    private Bitmap currentImage = null;

    @Inject
    RefundEntryContract.Presenter mPresenter;
    private Calendar mCalendar;

    private SimpleSpinnerAdapter spinnerAdapter;

    private Spinner categorySpinner;
    private EditText datePicker;
    private Button saveButton;
    private ImageView receipt, takePicture, openGallery;
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
        receipt = findViewById(R.id.iv_receipt);
        takePicture = findViewById(R.id.iv_take_picture);
        openGallery = findViewById(R.id.iv_open_gallery);

        if(!isLogged()) {
            onUserNotLogged();
            return;
        } else {

            ((TimesheetApplication) getApplication())
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

            takePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });

            openGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openGallery = new Intent(Intent.ACTION_PICK);//, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    openGallery.setType("image/*");
                    startActivityForResult(openGallery, REQUEST_LOAD_IMAGE);
                }
            });

            mPresenter.fetchData((RefundEntry) getIntent().getExtras().getParcelable(ENTRY));
        }
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
            categorySpinner.setSelection(entry.getCategoryPosition()+1);
        }
        datePicker.setText(entry.getDate());
        description.setText(entry.getDescription());
        value.setText(entry.getValue());
        currentImage = decodeFromBase64(entry.getImageBase64());
        receipt.setImageBitmap(currentImage);
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
        if(currentImage == null) {
            showErrorPictureDialog(R.string.label_picture_required);
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
        entry.setImageBase64(encodeToBase64(currentImage));
        return entry;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            currentImage = (Bitmap) extras.get("data");
            receipt.setImageBitmap(currentImage);
        } else if (requestCode == REQUEST_LOAD_IMAGE && resultCode == RESULT_OK) {

            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                currentImage = BitmapFactory.decodeStream(imageStream);
                receipt.setImageBitmap(currentImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                showErrorPictureDialog(R.string.label_picture_problem);
            }

        }
    }

    void showErrorPictureDialog(int messageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton(R.string.action_ack, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setTitle(R.string.title_oops);
        builder.setMessage(messageId);
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private String encodeToBase64(Bitmap image)
    {
        if(image == null)
            return null;

        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return imageEncoded;
    }

    private Bitmap decodeFromBase64(String base64) {
        byte[] bytes =  Base64.decode(base64, Base64.DEFAULT);;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
