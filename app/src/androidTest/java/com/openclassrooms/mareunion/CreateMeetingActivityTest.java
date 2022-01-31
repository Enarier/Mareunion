package com.openclassrooms.mareunion;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.service.MeetingApiService;
import com.openclassrooms.mareunion.ui.CreateMeetingActivity;
import com.openclassrooms.mareunion.ui.MainActivity;
import com.openclassrooms.mareunion.utils.ToastMatcher;

import org.junit.Before;
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

    //Put this in MainActivityTest

    @Test
    public void createMeetingWithSuccess() {
        onView(withId(R.id.textInputEditText_subject)).perform(typeText("Test A"));
        closeSoftKeyboard();
        onView(withId(R.id.spinner_room)).perform(click());
        onData(anything()).atPosition(5).perform(click());
        onView(withId(R.id.textInputEditText_date)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022,2,23));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.textInputEditText_startingTime)).perform(click());
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(21,30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.textInputEditText_finishingTime)).perform(click());
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(22,30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.textInputEditText_email)).perform(typeText("test1@mail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.button_email_ok)).perform(click());
        onView(withId(R.id.textInputEditText_email)).perform(clearText());
        onView(withId(R.id.textInputEditText_email)).perform(typeText("test2@mail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.button_email_ok)).perform(click());
        onView(withId(R.id.textInputEditText_email)).perform(clearText());
        onView(withId(R.id.button_createMeeting)).perform(click());

//         How to check the RV which is located in another activity ?
//        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(MEETING_ITEMS_COUNT + 1));
    }

    @Test
    public void createMeeting_TimeLogic() {
        onView(withId(R.id.textInputEditText_startingTime)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.textInputEditText_finishingTime)).perform(click());
        onView(withText("OK")).perform(click());
//        How to test toast message ?
        onView(withText("Finishing Time can't be set earlier than Starting Time")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

    }




}
