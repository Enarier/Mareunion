package com.openclassrooms.mareunion.ui;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ListMeetingItemBinding;
import com.openclassrooms.mareunion.event.DeleteMeetingEvent;
import com.openclassrooms.mareunion.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder>{

    private final List<Meeting> mMeetingList;

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetingList = items;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        ListMeetingItemBinding mListMeetingItemBinding = ListMeetingItemBinding.inflate(mLayoutInflater, parent, false);

        return new MeetingViewHolder(mListMeetingItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting mMeeting = mMeetingList.get(position);

        holder.mListMeetingItemBinding.meetingSubject.setText(mMeeting.getSubject() + " - ");
        holder.mListMeetingItemBinding.startingTime.setText(mMeeting.getStartingTime() + " - ");
        holder.mListMeetingItemBinding.textViewRoomName.setText(mMeeting.getRoom().getName());

        holder.mListMeetingItemBinding.imageViewRoomColor.setBackgroundResource(R.drawable.circle);
        GradientDrawable drawable = (GradientDrawable) holder.mListMeetingItemBinding.imageViewRoomColor.getBackground();
        drawable.setColor(ContextCompat.getColor(holder.mListMeetingItemBinding.imageViewRoomColor.getContext(), mMeeting.getRoom().getColor()));

        String emails = "";
        for(int i = 0; i < mMeeting.getParticipantEmail().size(); i++) {
            emails += mMeeting.getParticipantEmail().get(i);
            if (i + 1 < mMeeting.getParticipantEmail().size()) {
                emails += ", ";
            }
        }
        holder.mListMeetingItemBinding.email.setText(emails);

        holder.mListMeetingItemBinding.deleteButton.setOnClickListener(new View.OnClickListener() {
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

        ListMeetingItemBinding mListMeetingItemBinding;

        public MeetingViewHolder(@NonNull ListMeetingItemBinding listMeetingItemBinding) {
            super(listMeetingItemBinding.getRoot());
            mListMeetingItemBinding = listMeetingItemBinding;
        }
    }

}
