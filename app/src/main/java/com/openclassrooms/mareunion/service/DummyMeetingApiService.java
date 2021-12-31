package com.openclassrooms.mareunion.service;

import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();

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
    public void sortMeeting(Meeting meeting) {

    }

    @Override
    public List<Room> availableRoom() {
        return null;
    }
}
