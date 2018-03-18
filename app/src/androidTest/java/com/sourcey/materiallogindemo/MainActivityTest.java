package com.sourcey.materiallogindemo;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void caseLoginComplete() {
        String email = "abc@gmail.com";
        String password = "1234";
        String expectString = "Hello world!";
        registerAccount("Chanarthip", "801/56", email, "0869981996", password, password);
        onView(allOf(withId(R.id.btn_logout), withText("Logout"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed())).perform(click());
        onView(withId(R.id.input_email)).perform(typeText("abc@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("1234"));
        onView(withId(R.id.btn_login)).perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withText("Hello world!"), isDisplayed())).check(matches(withText(expectString)));
    }

    @Test
    public void caseCreateAccountComplete() {
        String expectString = "Hello world!";
        registerAccount("Chanarthip", "801/56", "abc@gmail.com", "0869981996", "1234", "1234");
        onView(allOf(withText("Hello world!"), isDisplayed())).check(matches(withText(expectString)));
    }

    private void registerAccount(String name, String address, String email, String mobileNumber, String password, String rePassword) {
        onView(withId(R.id.link_signup)).perform(scrollTo(), click());
        onView(withId(R.id.input_name)).perform(typeText(name));
        onView(withId(R.id.input_address)).perform(typeText(address));
        onView(withId(R.id.input_email)).perform(typeText(email));
        onView(withId(R.id.input_mobile)).perform(replaceText(mobileNumber));
        onView(withId(R.id.input_password)).perform(scrollTo(), replaceText(password));
        onView(withId(R.id.input_reEnterPassword)).perform(scrollTo(), replaceText(rePassword));
        onView(withId(R.id.btn_signup)).perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
