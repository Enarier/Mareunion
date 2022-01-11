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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private Calendar c = Calendar.getInstance();

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

        mActivityCreateMeetingBinding.editTextEndTime.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String pickedDateString = simpleDateFormat.format(c.getTime());
        mActivityCreateMeetingBinding.editTextDate.setText(pickedDateString);
    }

    private void showTimePickerDialog() {
        DialogFragment mTimePickerFragment = new TimePickerFragment();
        mTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        DateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String pickedDateString = simpleDateFormat.format(c.getTime());
        mActivityCreateMeetingBinding.editTextStartingTime.setText(pickedDateString);
    }

    private void createMeeting() {
//        Meeting mMeeting = new Meeting(
//
//        );
//        mApiService.createMeeting(mMeeting);
//        finish();

    }



}