package com.openclassrooms.mareunion.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ActivityCreateMeetingBinding;
import com.openclassrooms.mareunion.databinding.FragmentRoomDialogBinding;
import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.model.Room;
import com.openclassrooms.mareunion.service.MeetingApiService;

import java.util.List;

public class RoomDialogFragment extends DialogFragment {

    FragmentRoomDialogBinding mFragmentRoomDialogBinding;
    private RoomRecyclerViewAdapter mAdapter;

    private MeetingApiService mApiService;

    private List<Room> mRoomList;

    public RoomDialogFragment() {

    }

    public static RoomDialogFragment newInstance() {
        RoomDialogFragment fragment = new RoomDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentRoomDialogBinding = FragmentRoomDialogBinding.inflate(inflater, container, false);

        mApiService = DI.getMeetingApiService();
        mRoomList = mApiService.getRooms();

        mFragmentRoomDialogBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mFragmentRoomDialogBinding.getRoot().getContext()));
        mFragmentRoomDialogBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mRoomList);

        return mFragmentRoomDialogBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
//        initList();
    }

    private void initList() {
        mRoomList = mApiService.getRooms();
        mFragmentRoomDialogBinding.recyclerView.setAdapter(new RoomRecyclerViewAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentRoomDialogBinding = null;
    }
}
