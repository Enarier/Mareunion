package com.openclassrooms.mareunion.ui;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ListItemBinding;
import com.openclassrooms.mareunion.event.DeleteMeetingEvent;
import com.openclassrooms.mareunion.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final List<Meeting> mMeetingList;

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetingList = items;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        ListItemBinding mListItemBinding = ListItemBinding.inflate(mLayoutInflater, parent, false);

        return new MeetingViewHolder(mListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting mMeeting = mMeetingList.get(position);

        holder.mListItemBinding.roomSubject.setText(mMeeting.getSubject() + " - ");
        holder.mListItemBinding.startingTime.setText(mMeeting.getStartingTime() + " - ");
        holder.mListItemBinding.roomName.setText(mMeeting.getRoom().getName());
//        holder.mListItemBinding.roomColor.setBackgroundResource(mMeeting.getRoom().getColor());

        String emails = "";
        for(int i = 0; i < mMeeting.getParticipantEmail().size(); i++) {
            emails = emails + mMeeting.getParticipantEmail().get(i) + ", ";
        }
        holder.mListItemBinding.email.setText(emails);

        holder.mListItemBinding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding mListItemBinding;

        public MeetingViewHolder(@NonNull ListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            mListItemBinding = listItemBinding;
        }
    }
}
