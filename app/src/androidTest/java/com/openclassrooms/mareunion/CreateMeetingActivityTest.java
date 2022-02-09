package com.openclassrooms.mareunion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.service.MeetingApiService;
import com.openclassrooms.mareunion.ui.CreateMeetingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

@RunWith(AndroidJUnit4.class)
public class CreateMeetingActivityTest {

    private MeetingApiService mApiService = DI.getMeetingApiService();
    private final Calendar mCalendar = Calendar.getInstance();
    //Fixed amount of item of the meeting List
    private static int MEETING_ITEMS_COUNT = 5;

    @Rule
    public ActivityScenarioRule<CreateMeetingActivity> mCreateMeetingActivityScenarioRule =
            new ActivityScenarioRule<>(CreateMeetingActivity.class);

    @Test
    public void createMeetingActivity_isCorrectlyDisplayed_BeforeAnyInput() {
        onView(withId(R.id.textInputLayout_subject)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.spinner_room)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.textInputLayout_date)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.textInputLayout_startingTime)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.textInputLayout_finishingTime)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.textInputLayout_email)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.button_email_ok)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.button_createMeeting)).check(matches(isCompletelyDisplayed()));
    }

}
