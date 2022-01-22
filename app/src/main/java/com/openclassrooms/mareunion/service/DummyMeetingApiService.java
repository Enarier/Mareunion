package com.openclassrooms.mareunion.service;

import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
    private List<Room> mRooms = DummyMeetingGenerator.generateRooms();
    private List<String> mParticipantsEmails = DummyMeetingGenerator.generateParticipantsEmail();

    private List<Meeting> mMeetingsFilteredByDate = new ArrayList<>();
    private List<Meeting> mMeetingsFilteredByRoom = new ArrayList<>();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public List<Room> getRooms() {
        return mRooms;
    }

    @Override
    public List<String> getParticipantsEmail() {
        return mParticipantsEmails;
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
    public List<Meeting> filterMeetingsByDate(String date) {
        mMeetingsFilteredByDate.clear();
        for (Meeting mMeeting : mMeetings) {
            if (mMeeting.getDate().equals(date)) {
                mMeetingsFilteredByDate.add(mMeeting);
            }
        }
        return mMeetingsFilteredByDate;
    }

    @Override
    public List<Meeting> filterMeetingsByRoom(Room room) {
        mMeetingsFilteredByRoom.clear();
        for (Room mRoom : mRooms) {
            if (mRoom.equals(room)) {
                for (Meeting mMeeting : mMeetings) {
                    if (mMeeting.getRoom().equals(room)) {
                        mMeetingsFilteredByRoom.add(mMeeting);
                    }
                }
            }
        }
        return mMeetingsFilteredByRoom;
    }
}
