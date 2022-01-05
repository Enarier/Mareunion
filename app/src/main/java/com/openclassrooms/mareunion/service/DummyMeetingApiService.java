package com.openclassrooms.mareunion.service;

import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
    private List<Meeting> mMeetingsDate = new ArrayList<>(mMeetings);

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void filterByDate(String date) {

    }

    @Override
    public void filterByRoom(Room room) {

    }

    @Override
    public List<Room> availableRoom() {
        return null;
    }
}
