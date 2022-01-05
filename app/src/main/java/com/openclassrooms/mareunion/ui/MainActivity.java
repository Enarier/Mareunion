package com.openclassrooms.mareunion.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ActivityMainBinding;
import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.event.DeleteMeetingEvent;
import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Menu mMenu;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MeetingApiService mApiService = DI.getMeetingApiService();
    private List<Meeting> mMeetingsList = mApiService.getMeetings();

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mActivityMainBinding.getRoot();
        setContentView(view);

        initRecyclerView();

        mActivityMainBinding.createMeetingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
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
            case R.id.menu_date:
                showDatePickerDialog();
                break;

            case R.id.menu_room:


            case R.id.menu_default:


        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
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

    private void showDatePickerDialog() {
        DialogFragment mDatePickerFragment = new DatePickerFragment();
        mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
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
        initRecyclerView();
    }
}