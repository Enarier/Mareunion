package com.openclassrooms.mareunion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.service.MeetingApiService;
import com.openclassrooms.mareunion.ui.MainActivity;
import com.openclassrooms.mareunion.utils.DeleteMeetingAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MeetingApiService mApiService = DI.getMeetingApiService();
    //Fixed amount of item of the meeting List
    private static int MEETING_ITEMS_COUNT = 5;
    //Fixed amount of item of the room List
    private static int ROOM_ITEMS_COUNT = 10;

    @Rule
    public ActivityScenarioRule<MainActivity> mMainActivityActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void meetingList_isDisplayed() {
        onView(withId(R.id.meeting_recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.meeting_recyclerView)).check(matches(hasMinimumChildCount(MEETING_ITEMS_COUNT)));
    }

    @Test
    public void meetingList_deleteAction() {
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(MEETING_ITEMS_COUNT));
        onView(withId(R.id.meeting_recyclerView)).perform(actionOnItemAtPosition(1,new DeleteMeetingAction()));
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(MEETING_ITEMS_COUNT-1));
    }

    @Test
    public void filter_MeetingList_ByDate() {
        onView(withId(R.id.meeting_recyclerView)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.menu_filterIcon))).check(matches(isDisplayed())).perform(click());
        onView(anyOf(withId(R.id.menuItem_date), withText(R.string.date))).perform(click());
//        How to set Date ??
//        onView(withText(R.string.date)).perform(PickerActions.setDate(2022,1,5));

    }

    @Test
    public void filter_MeetingList_ByRoom() {
        onView(allOf(withId(R.id.menu_filterIcon))).check(matches(isDisplayed())).perform(click());
        onView(anyOf(withId(R.id.menuItem_room), withText(R.string.room))).perform(click());
        onView(withId(R.id.recyclerView_roomDialogFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView_roomDialogFragment)).check(withItemCount(ROOM_ITEMS_COUNT));
        onView(withId(R.id.recyclerView_roomDialogFragment)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.meeting_recyclerView)).check(matches(hasMinimumChildCount(1)));

        onView(allOf(withId(R.id.menu_filterIcon))).check(matches(isDisplayed())).perform(click());
        onView(anyOf(withId(R.id.menuItem_room), withText(R.string.room))).perform(click());
        onView(withId(R.id.recyclerView_roomDialogFragment)).perform(actionOnItemAtPosition(6, click()));
        onView(withId(R.id.meeting_recyclerView)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void menu_ResetButton() {
        onView(allOf(withId(R.id.menu_filterIcon))).check(matches(isDisplayed())).perform(click());
        onView(anyOf(withId(R.id.menuItem_room), withText(R.string.room))).perform(click());
        onView(withId(R.id.recyclerView_roomDialogFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView_roomDialogFragment)).check(withItemCount(ROOM_ITEMS_COUNT));
        onView(withId(R.id.recyclerView_roomDialogFragment)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.meeting_recyclerView)).check(matches(hasMinimumChildCount(1)));

        onView(allOf(withId(R.id.menu_filterIcon))).check(matches(isDisplayed())).perform(click());
        onView(anyOf(withId(R.id.menuItem_reset), withText(R.string.reset))).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(MEETING_ITEMS_COUNT));
    }

}