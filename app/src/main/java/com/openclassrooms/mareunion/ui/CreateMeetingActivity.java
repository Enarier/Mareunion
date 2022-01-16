package com.openclassrooms.mareunion.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.mareunion.databinding.ActivityCreateMeetingBinding;

import com.openclassrooms.mareunion.service.MeetingApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private ActivityCreateMeetingBinding mActivityCreateMeetingBinding;

    private Calendar c = Calendar.getInstance();

    private MeetingApiService mApiService;

    private TextInputEditText actualTextInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCreateMeetingBinding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = mActivityCreateMeetingBinding.getRoot();
        setContentView(view);

        actualTextInputEditText = mActivityCreateMeetingBinding.textInputEditTextStartingTime;

        mActivityCreateMeetingBinding.textInputEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        mActivityCreateMeetingBinding.textInputEditTextStartingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualTextInputEditText = mActivityCreateMeetingBinding.textInputEditTextStartingTime;
                showTimePickerDialog();
            }
        });

        mActivityCreateMeetingBinding.textInputEditTextFinishingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualTextInputEditText = mActivityCreateMeetingBinding.textInputEditTextFinishingTime;
                showTimePickerDialog();
            }
        });

        mActivityCreateMeetingBinding.buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }

    private void showDatePickerDialog() {
        DialogFragment mDatePickerFragment = new DatePickerFragment();
        mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String pickedDateString = simpleDateFormat.format(c.getTime());
        mActivityCreateMeetingBinding.textInputEditTextDate.setText(pickedDateString);
    }

    private void showTimePickerDialog() {
        DialogFragment mTimePickerFragment = new TimePickerFragment();
        mTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        DateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String pickedTimeString = simpleDateFormat.format(c.getTime());

        if (actualTextInputEditText == mActivityCreateMeetingBinding.textInputEditTextStartingTime) {
            mActivityCreateMeetingBinding.textInputEditTextStartingTime.setText(pickedTimeString);
        } else {
            mActivityCreateMeetingBinding.textInputEditTextFinishingTime.setText(pickedTimeString);

            if ( mActivityCreateMeetingBinding.textInputEditTextFinishingTime.getText().toString().
                    compareTo(mActivityCreateMeetingBinding.textInputEditTextStartingTime.getText().toString()) <= 0 ) {
                Toast.makeText(this, "Finishing Time can't be set earlier than Starting Time", Toast.LENGTH_LONG).show();
                mActivityCreateMeetingBinding.textInputEditTextFinishingTime.setText("");
            }
        }
    }

    private void createMeeting() {
//        Meeting mMeeting = new Meeting(
//
//        );
//        mApiService.createMeeting(mMeeting);
//        finish();

    }



}