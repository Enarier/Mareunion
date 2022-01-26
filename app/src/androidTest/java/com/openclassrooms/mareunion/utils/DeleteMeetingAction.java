package com.openclassrooms.mareunion.utils;

import android.view.View;
import android.widget.Button;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.openclassrooms.mareunion.R;

import org.hamcrest.Matcher;

public class DeleteMeetingAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Clicked on delete imageView button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete_button);
        button.performClick();
    }
}
