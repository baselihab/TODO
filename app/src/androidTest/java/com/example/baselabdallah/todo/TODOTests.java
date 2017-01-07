package com.example.baselabdallah.todo;

import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by baselabdallah on 1/7/17.
 */

@RunWith(AndroidJUnit4.class)
public class TODOTests {
    @Rule
    public ActivityTestRule<TODOMain> mActivityRule = new ActivityTestRule(TODOMain.class);


    @Test
    public void ItemDeleted() {
        onView(withText("Test")).check(doesNotExist());
        onView(withId(R.id.editText1)).perform(typeText("Test"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.editText1)).perform(typeText("Test2"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.editText1)).perform(typeText("Test3"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.editText1)).perform(typeText("Test4"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withText("Test")).check(matches(withText("Test")));
        onView(withText("Test")).perform(longClick());
        onView(withText("Test")).check(doesNotExist());
    }
    @Test
    public void ItemAdded() {
        onView(withText("Test")).check(doesNotExist());
        onView(withId(R.id.editText1)).perform(typeText("Test"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.editText1)).perform(typeText("Test2"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.editText1)).perform(typeText("Test3"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.editText1)).perform(typeText("Test4"));
        onView(withId(R.id.imageView1)).perform(click());
        onView(withText("Test")).check(matches(withText("Test")));
    }


}
