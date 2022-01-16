package com.openclassrooms.mareunion.ui;

import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ListRoomItemBinding;
import com.openclassrooms.mareunion.databinding.ToastBinding;
import com.openclassrooms.mareunion.event.RoomRecyclerViewItemClickEvent;
import com.openclassrooms.mareunion.model.Room;
import com.openclassrooms.mareunion.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.RoomViewHolder> {

    ListRoomItemBinding mListRoomItemBinding;

    private List<Room> mRoomList;

    public RoomRecyclerViewAdapter(List<Room> roomList) {
        mRoomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mListRoomItemBinding = ListRoomItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new RoomViewHolder(mListRoomItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room mRoom = mRoomList.get(position);

        holder.mListRoomItemBinding.imageViewRoomColor.setBackgroundResource(R.drawable.circle);
        GradientDrawable drawable = (GradientDrawable) holder.mListRoomItemBinding.imageViewRoomColor.getBackground();
        drawable.setColor(ContextCompat.getColor(holder.mListRoomItemBinding.imageViewRoomColor.getContext(), mRoom.getColor()));

        holder.mListRoomItemBinding.textViewRoomName.setText(mRoom.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RoomRecyclerViewItemClickEvent(mRoom));
                Toast.makeText(holder.itemView.getContext(), mRoom.getName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {

        ListRoomItemBinding mListRoomItemBinding;

        public RoomViewHolder(@NonNull ListRoomItemBinding listRoomItemBinding) {
            super(listRoomItemBinding.getRoot());
            mListRoomItemBinding = listRoomItemBinding;
        }
    }



}
