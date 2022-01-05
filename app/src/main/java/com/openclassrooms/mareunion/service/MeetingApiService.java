package com.openclassrooms.mareunion.service;

import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

//  1 method filter by date and 1 method filter by room
    void filterByDate(String date);

    void filterByRoom(Room room);

    List<Room> availableRoom();
}
