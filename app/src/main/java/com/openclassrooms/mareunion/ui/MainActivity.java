package com.openclassrooms.mareunion.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ActivityMainBinding;
import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.event.DeleteMeetingEvent;
import com.openclassrooms.mareunion.event.RoomRecyclerViewItemClickEvent;
import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Menu mMenu;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MeetingApiService mApiService;
    private List<Meeting> mMeetingsList;

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mActivityMainBinding.getRoot();
        setContentView(view);

        init();
        setUpRecyclerView();

    }

    private void init() {
        mApiService = DI.getMeetingApiService();
        mMeetingsList = mApiService.getMeetings();

        mActivityMainBinding.createMeetingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView = mActivityMainBinding.meetingRecyclerView;
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MeetingRecyclerViewAdapter(mMeetingsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createMeeting() {
        Intent intent = new Intent(this, CreateMeetingActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem_date:
                showDatePickerDialog();
                return true;

            case R.id.menuItem_room:
                showRoomDialog();
                return true;

            case R.id.menuItem_reset:
                mRecyclerView.setAdapter(mAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * DatePickerDialog codes
     */
    private void showDatePickerDialog() {
        DialogFragment mDatePickerFragment = new DatePickerFragment();
        mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String pickedDateString = simpleDateFormat.format(c.getTime());

        List<Meeting> mMeetingsListFiltered =  mApiService.filterMeetingsByDate(pickedDateString);
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetingsListFiltered));
    }

    /**
     * RoomDialog codes
     */
    private void showRoomDialog() {
        DialogFragment mRoomDialogFragment = new RoomDialogFragment();
        mRoomDialogFragment.show(getSupportFragmentManager(), "roomDialog");
    }

    /**
     * Eventbus codes
     */
    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        setUpRecyclerView();
    }

    @Subscribe
    public void recyclerViewItemClicked(RoomRecyclerViewItemClickEvent event) {
        mApiService.filterMeetingsByRoom(event.mRoom);
        List<Meeting> mMeetingsListFiltered =  mApiService.filterMeetingsByRoom(event.mRoom);
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetingsListFiltered));

    }
}