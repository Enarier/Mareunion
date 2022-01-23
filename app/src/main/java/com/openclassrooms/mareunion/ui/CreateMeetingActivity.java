package com.openclassrooms.mareunion.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ActivityCreateMeetingBinding;

import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;
import com.openclassrooms.mareunion.service.MeetingApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private ActivityCreateMeetingBinding mActivityCreateMeetingBinding;
    private TextInputEditText actualTextInputEditText;

    private final Calendar c = Calendar.getInstance();

    private MeetingApiService mApiService;

    private List<String> mChosenParticipants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCreateMeetingBinding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        View view = mActivityCreateMeetingBinding.getRoot();
        setContentView(view);

        mApiService = DI.getMeetingApiService();
        mChosenParticipants = new ArrayList<>();

        setUpSpinner();

        actualTextInputEditText = mActivityCreateMeetingBinding.textInputEditTextStartingTime;
        setUpEditTextDate();
        setUpTwoEditTextTime();

        setUpChipGroup();

        setUpButtonCreateMeeting();
    }

    /**
     * Spinner
     */
    private void setUpSpinner() {
        List<Room> roomList = mApiService.getRooms();

        mActivityCreateMeetingBinding.spinnerRoom.setAdapter(new RoomSpinnerAdapter(this, (ArrayList<Room>) roomList));
        mActivityCreateMeetingBinding.spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Room clickedRoom = (Room) parent.getItemAtPosition(position);
                String clickedRoomName = clickedRoom.getName();
                Toast.makeText(CreateMeetingActivity.this, clickedRoomName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Edit Text Date
     */
    private void setUpEditTextDate() {
        mActivityCreateMeetingBinding.textInputEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
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

    /**
     * 2 Edit Text Time
     */
    private void setUpTwoEditTextTime() {
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

            String chosenStartingTime = mActivityCreateMeetingBinding.textInputEditTextStartingTime.getText().toString();
            String chosenFinishingTime = mActivityCreateMeetingBinding.textInputEditTextFinishingTime.getText().toString();

            if ( chosenFinishingTime.compareTo(chosenStartingTime) <= 0 ) {
                Toast.makeText(this, "Finishing Time can't be set earlier than Starting Time", Toast.LENGTH_SHORT).show();
                mActivityCreateMeetingBinding.textInputEditTextFinishingTime.setText("");
            }
        }
    }

    /**
     * Chip
     */
    private void setUpChipGroup() {
        ChipGroup mChipGroup = mActivityCreateMeetingBinding.chipGroupParticipantsEmail;
        EditText editTextEmail = mActivityCreateMeetingBinding.textInputEditTextEmail;
        Button buttonOK = mActivityCreateMeetingBinding.buttonEmailOk;

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = editTextEmail.getText().toString();

                if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {

                    Chip mChip = new Chip(CreateMeetingActivity.this);
                    mChip.setText(emailInput);
                    mChip.setCloseIconVisible(true);
                    String participants = mChip.getText().toString();
                    mChosenParticipants.add(participants);

                    mChip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mChipGroup.removeView(mChip);
                            mChosenParticipants.remove(participants);
                        }
                    });
                    mChipGroup.addView(mChip);
                } else {
                    Toast.makeText(CreateMeetingActivity.this, "Invalid Email address form", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Button Create Meeting
     */

    private void setUpButtonCreateMeeting()  {
        mActivityCreateMeetingBinding.buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }

    private void createMeeting() {
        String subject = mActivityCreateMeetingBinding.textInputEditTextSubject.getText().toString();
        String chosenDate = mActivityCreateMeetingBinding.textInputEditTextDate.getText().toString();
        String chosenStartingTime = mActivityCreateMeetingBinding.textInputEditTextStartingTime.getText().toString();
        String chosenFinishingTime = mActivityCreateMeetingBinding.textInputEditTextFinishingTime.getText().toString();
        Room mRoom = (Room) mActivityCreateMeetingBinding.spinnerRoom.getSelectedItem();

        // 1 method apart boolean to do this
        if(inputCheck()) {
            mApiService.createMeeting(new Meeting(mRoom, subject, chosenDate, chosenStartingTime, chosenFinishingTime, mChosenParticipants));
            finish();
        } else {
            Toast.makeText(this, "Should fill in all the required filed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean inputCheck() {
        String subject = mActivityCreateMeetingBinding.textInputEditTextSubject.getText().toString();
        String chosenDate = mActivityCreateMeetingBinding.textInputEditTextDate.getText().toString();
        String chosenStartingTime = mActivityCreateMeetingBinding.textInputEditTextStartingTime.getText().toString();
        String chosenFinishingTime = mActivityCreateMeetingBinding.textInputEditTextFinishingTime.getText().toString();

        return !subject.isEmpty() && !chosenDate.isEmpty() && !chosenStartingTime.isEmpty() && !chosenFinishingTime.isEmpty();
    }

}