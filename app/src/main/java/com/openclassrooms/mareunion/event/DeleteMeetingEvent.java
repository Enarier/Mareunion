package com.openclassrooms.mareunion.event;



import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteMeetingEvent {

    public Meeting mMeeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        mMeeting = meeting;
    }
}

