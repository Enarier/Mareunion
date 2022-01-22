package com.openclassrooms.mareunion.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.openclassrooms.mareunion.R;

import com.openclassrooms.mareunion.model.Room;

import java.util.ArrayList;

public class RoomSpinnerAdapter extends ArrayAdapter<Room> {

    public RoomSpinnerAdapter(Context context, ArrayList<Room> roomList) {
        super (context,0, roomList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_room_item, parent, false
            );
        }
        View viewRoomColor = convertView.findViewById(R.id.imageView_roomColor);
        TextView textViewRoomName = convertView.findViewById(R.id.textView_roomName);

        Room mRoom = getItem(position);

        if (mRoom != null) {
            viewRoomColor.setBackgroundResource(R.drawable.circle);
            GradientDrawable drawable = (GradientDrawable) viewRoomColor.getBackground();
            drawable.setColor(ContextCompat.getColor(viewRoomColor.getContext(), mRoom.getColor()));

//              this doesnt work ! -> I want to make elements of spinner smaller
//            drawable.setSize(25,25);

            textViewRoomName.setText(mRoom.getName());
        }
        return convertView;
    }
}
