package com.openclassrooms.mareunion.ui;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.databinding.ListRoomItemBinding;
import com.openclassrooms.mareunion.model.Room;

import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.RoomViewHolder> {

    private List<Room> mRoomList;

//    public RoomRecyclerViewAdapter(List<Room> roomList) {
//        mRoomList = roomList;
//    }
    public void setData(List<Room> roomList) {
        mRoomList = roomList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        ListRoomItemBinding mListRoomItemBinding = ListRoomItemBinding.inflate(mLayoutInflater, parent, false);

        return new RoomViewHolder(mListRoomItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room mRoom = mRoomList.get(position);

        holder.mListRoomItemBinding.imageViewRoomColor.setBackgroundResource(R.drawable.circle);
        GradientDrawable drawable = (GradientDrawable) holder.mListRoomItemBinding.imageViewRoomColor.getBackground();
        drawable.setColor(ContextCompat.getColor(holder.mListRoomItemBinding.imageViewRoomColor.getContext(), mRoom.getColor()));

        holder.mListRoomItemBinding.textViewRoomName.setText(mRoom.getName());

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
