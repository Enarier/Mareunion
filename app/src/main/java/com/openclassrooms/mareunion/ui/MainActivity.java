package com.openclassrooms.mareunion.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ActivityMainBinding;
import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.service.MeetingApiService;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Menu mMenu;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MeetingApiService mApiService;


    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mActivityMainBinding.getRoot();
        setContentView(view);

        mApiService = DI.getMeetingApiService();

        initRecyclerView();
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
            case R.id.menu_dateAsc:
                Collections.sort(mApiService.getMeetings(), Meeting.MeetingDateAscendingComparator);
                Toast.makeText(this, "Sort by date ascending", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.menu_timeAsc:
                Collections.sort(mApiService.getMeetings(), Meeting.MeetingStartingTimeAscendingComparator);
                Toast.makeText(this, "Sort by starting time ascending", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.menu_roomAtoZ:
                Collections.sort(mApiService.getMeetings(), Meeting.MeetingRoomAZComparator);
                Toast.makeText(this, "Sort by room A to Z", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initRecyclerView() {
        mRecyclerView = mActivityMainBinding.meetingRecyclerView;
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MeetingRecyclerViewAdapter(mApiService.getMeetings());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}