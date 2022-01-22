package com.openclassrooms.mareunion.service;

import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    List<Room> getRooms();

    List<String> getParticipantsEmail();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> filterMeetingsByDate(String date);

    List<Meeting> filterMeetingsByRoom(Room room);


}
