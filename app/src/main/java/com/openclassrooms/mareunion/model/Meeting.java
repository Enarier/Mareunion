package com.openclassrooms.mareunion.model;

import java.util.Comparator;
import java.util.List;

public class Meeting {

    private Room mRoom;
    private String mSubject;
    private String mDate;
    private String mStartingTime;
    private String mFinishingTime;
    private List<String> mParticipantEmail;

    public Meeting(Room room, String subject, String date, String startingTime, String finishingTime, List<String> participantEmail) {
        mRoom = room;
        mSubject = subject;
        mDate = date;
        mStartingTime = startingTime;
        mFinishingTime = finishingTime;
        mParticipantEmail = participantEmail;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        mRoom = room;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getStartingTime() {
        return mStartingTime;
    }

    public void setStartingTime(String startingTime) {
        mStartingTime = startingTime;
    }

    public String getFinishingTime() {
        return mFinishingTime;
    }

    public void setFinishingTime(String finishingTime) {
        mFinishingTime = finishingTime;
    }

    public List<String> getParticipantEmail() {
        return mParticipantEmail;
    }

    public void setParticipantEmail(List<String> participantEmail) {
        mParticipantEmail = participantEmail;
    }
}
