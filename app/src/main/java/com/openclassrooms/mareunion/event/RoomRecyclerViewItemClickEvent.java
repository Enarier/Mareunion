package com.openclassrooms.mareunion.event;

import com.openclassrooms.mareunion.model.Room;

public class RoomRecyclerViewItemClickEvent {

    public Room mRoom;

    public RoomRecyclerViewItemClickEvent(Room room) {
        mRoom = room;
    }
}
