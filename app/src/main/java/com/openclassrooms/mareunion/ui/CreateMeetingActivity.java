package com.openclassrooms.mareunion.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.openclassrooms.mareunion.databinding.ActivityCreateMeetingBinding;

import com.openclassrooms.mareunion.service.MeetingApiService;

import java.util.Calendar;

public class CreateMeetingActivity extends AppCompatActivity {

    private Calendar mCalendar = Calendar.getInstance();

    private MeetingApiService mApiService;

    private ActivityCreateMeetingBinding mActivityCreateMeetingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCreateMeetingBinding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = mActivityCreateMeetingBinding.getRoot();
        setContentView(view);

        mActivityCreateMeetingBinding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        mActivityCreateMeetingBinding.editTextStartingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void showTimePickerDialog() {
        DialogFragment mTimePickerFragment = new TimePickerFragment();
        mTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void createMeeting() {
//        Meeting mMeeting = new Meeting(
//
//        );
//        mApiService.createMeeting(mMeeting);
//        finish();

    }

}