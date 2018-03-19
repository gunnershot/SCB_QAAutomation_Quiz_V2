package com.sourcey.materiallogindemo;

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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    Activity activity = new Activity();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void caseLoginComplete() {
        String email = "abc@gmail.com";
        String password = "1234";
        String expectString = "Hello world!";
        activity.registerAccount("Chanarthip", "801/56", email, "0869981996", password, password);
        onView(allOf(withId(R.id.btn_logout), withText("Logout"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed())).perform(click());
        activity.login(email, password);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(matches(withText(expectString)));
        onView(allOf(withId(R.id.btn_logout), isDisplayed())).check(matches(withText("Logout")));
    }

    @Test
    public void caseNotInputEmail() {
        activity.login("", "1234");
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void caseNotInputPassword() {
        activity.login("abc@gmail.com", "");
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    @Test
    public void caseNotInputEmailAndPassword() {
        activity.login("", "");
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(hasErrorText("enter a valid email address")));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    @Test
    public void caseInvalidInputEmail() {
        activity.login("abc", "1234");
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void caseInvalidPassword() {
        activity.login("abc@gmail.com", "111");
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    @Test
    public void caseEmailHasNotRegister() {
        activity.login("aasdsd@gmail.com", "1234");
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(hasErrorText("enter a valid email address or password")));
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
