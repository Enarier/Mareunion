package com.openclassrooms.mareunion.service;

import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    void sortMeeting(Meeting meeting);

    List<Room> availableRoom();
}
