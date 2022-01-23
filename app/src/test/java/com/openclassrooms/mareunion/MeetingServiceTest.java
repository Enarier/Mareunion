package com.openclassrooms.mareunion;

import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.model.Meeting;
import com.openclassrooms.mareunion.model.Room;
import com.openclassrooms.mareunion.service.DummyMeetingGenerator;
import com.openclassrooms.mareunion.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.util.List;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService mMeetingApiService;

    @Before
    public void setUp() {
        mMeetingApiService = DI.getMeetingApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetingList = mMeetingApiService.getMeetings();
        List<Meeting> expectedMeetingList = DummyMeetingGenerator.DUMMY_MEETINGS;
        Assert.assertThat(meetingList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetingList.toArray()));
    }

    @Test
    public void getRoomsWithSuccess() {
        List<Room> roomList = mMeetingApiService.getRooms();
        List<Room> expectedRoomList = DummyMeetingGenerator.DUMMY_ROOMS;
        Assert.assertThat(roomList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedRoomList.toArray()));
    }

    @Test
    public void getParticipantsEmailsWithSuccess() {
        List<String> participantsEmailList = mMeetingApiService.getParticipantsEmail();
        List<String> expectedParticipantsEmailList = DummyMeetingGenerator.PARTICIPANTS_EMAIL;
        Assert.assertThat(participantsEmailList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedParticipantsEmailList.toArray()));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting newMeeting = new Meeting(DummyMeetingGenerator.DUMMY_ROOMS.get(6),"Subject Z", "29/01/2022", "14:00", "15:00", DummyMeetingGenerator.PARTICIPANTS_EMAIL);
        mMeetingApiService.createMeeting(newMeeting);
        Assert.assertTrue(mMeetingApiService.getMeetings().contains(newMeeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting mMeeting = mMeetingApiService.getMeetings().get(0);
        Meeting mMeeting2 = mMeetingApiService.getMeetings().get(1);

        Assert.assertTrue(mMeetingApiService.getMeetings().contains(mMeeting));
        Assert.assertTrue(mMeetingApiService.getMeetings().contains(mMeeting2));
        mMeetingApiService.deleteMeeting(mMeeting);
        mMeetingApiService.deleteMeeting(mMeeting2);
        Assert.assertFalse(mMeetingApiService.getMeetings().contains(mMeeting));
        Assert.assertFalse(mMeetingApiService.getMeetings().contains(mMeeting2));
    }

    @Test
    public void filterMeetingsByDateWithSuccess() {
        Meeting firstMeeting = mMeetingApiService.getMeetings().get(0);

        List<Meeting> filteredMeetingList = mMeetingApiService.filterMeetingsByDate(firstMeeting.getDate());
        Assert.assertTrue(filteredMeetingList.contains(firstMeeting));
        Assert.assertTrue(filteredMeetingList.size() == 1);
    }

    @Test
    public void filterMeetingsByRoomWithSuccess() {
        Meeting firstMeeting = mMeetingApiService.getMeetings().get(0);

        List<Meeting> filteredMeetingList = mMeetingApiService.filterMeetingsByRoom(firstMeeting.getRoom());
        Assert.assertTrue(filteredMeetingList.contains(firstMeeting));
        Assert.assertTrue(filteredMeetingList.size() == 1);
    }
}
