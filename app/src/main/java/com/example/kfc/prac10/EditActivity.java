package com.example.kfc.prac10;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.kfc.prac10.datamodels.Task;
import com.example.kfc.prac10.helpers.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    public static int OK_RESULT_CODE = 1;

    Button buttonSave;
    TextView textViewDateDue;
    EditText editTextDescription;
    RadioButton radioButtonGreen;
    RadioButton radioButtonRed;
    RadioButton radioButtonBlue;

    Task task = null;

    // TODO:
    // Create a new method called updateUI() and copy a subset of the code that
    // displays the task in the UI from the onCreate method.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();

        // TODO:
        // Retrieve the id of the Task to load from Firebase.
        // If empty, instantiate a new Task object instead.

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        textViewDateDue = findViewById(R.id.textViewDateDue);
        textViewDateDue.setText(dateFormat.format(task.getDateDue()));
        textViewDateDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We want to show a date picker dialog here
                //
                Date dateDue = task.getDateDue();
                DateHelper currentDateDue = DateHelper.convertDateToYearMonthDay(dateDue);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        // year, month, day parameters store the date selected by the user!
                        //
                        Date newDateDue = DateHelper.convertYearMonthDayToDate(year, month, day);
                        task.setDateDue(newDateDue);

                        textViewDateDue.setText(dateFormat.format(task.getDateDue()));
                    }
                }, currentDateDue.year, currentDateDue.month, currentDateDue.day);

                // Remember to show the date picker dialog after creating it.
                //
                datePickerDialog.show();

            }
        });

        radioButtonGreen = findViewById(R.id.radioButtonGreen);
        radioButtonBlue = findViewById(R.id.radioButtonBlue);
        radioButtonRed = findViewById(R.id.radioButtonRed);

        if (task.getColour().equals("G"))
            radioButtonGreen.setChecked(true);
        if (task.getColour().equals("B"))
            radioButtonBlue.setChecked(true);
        if (task.getColour().equals("R"))
            radioButtonRed.setChecked(true);

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDescription.setText(task.getDescription());

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When the user clicks on the save button, let's perform
                // some validation first.
                //
                if (editTextDescription.getText().toString().equals(""))
                {
                    editTextDescription.setError("Please enter a description");
                    return;
                }

                // Then we retrieve all values from the UI widgets
                //
                task.setDescription(editTextDescription.getText().toString());

                if (radioButtonGreen.isChecked())
                    task.setColour("G");
                if (radioButtonBlue.isChecked())
                    task.setColour("B");
                if (radioButtonRed.isChecked())
                    task.setColour("R");

                // This segment here saves the task to the database
                //

                // TODO:
                // Integrate to Firebase to add a new Task object
                // or update an existing Task object.

            }
        });
    }
}
