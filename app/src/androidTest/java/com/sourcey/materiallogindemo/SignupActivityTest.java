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
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignupActivityTest {

    Activity activity = new Activity();
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void caseCreateAccountComplete() {
        String expectString = "Hello world!";
        activity.registerAccount("Chanarthip", "801/56", "abc@gmail.com", "0869981996", "1234", "1234");
        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(matches(withText(expectString)));
        onView(allOf(withId(R.id.btn_logout), isDisplayed())).check(matches(withText("Logout")));
    }

    @Test
    public void caseNotInputAll() {
        activity.registerAccount("", "", "", "", "", "");
        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(hasErrorText("at least 3 characters")));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(hasErrorText("Enter Valid Address")));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(hasErrorText("enter a valid email address")));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(hasErrorText("Enter Valid Mobile Number")));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(hasErrorText("Password Do not match")));


        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());
    }

    @Test
    public void caseInvalidInputName() {
        activity.registerAccount("aa", "801/56", "abc@gmail.com", "0899999999", "1234", "1234");

        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(hasErrorText("at least 3 characters")));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Address"))));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(not(hasErrorText("enter a valid email address"))));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Mobile Number"))));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(not(hasErrorText("between 4 and 10 alphanumeric characters"))));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(not(hasErrorText("Password Do not match"))));


        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(doesNotExist());
        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());
    }

    @Test
    public void caseInvalidInputAddress() {
        activity.registerAccount("abc", "", "abc@gmail.com", "0899999999", "1234", "1234");

        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(not(hasErrorText("at least 3 characters"))));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(hasErrorText("Enter Valid Address")));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(not(hasErrorText("enter a valid email address"))));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Mobile Number"))));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(not(hasErrorText("between 4 and 10 alphanumeric characters"))));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(not(hasErrorText("Password Do not match"))));

        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(doesNotExist());
        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());
    }

    @Test
    public void caseInvalidInputEmail() {
        activity.registerAccount("abc", "801/56", "abc", "0899999999", "1234", "1234");


        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(not(hasErrorText("at least 3 characters"))));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Address"))));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(hasErrorText("enter a valid email address")));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Mobile Number"))));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(not(hasErrorText("between 4 and 10 alphanumeric characters"))));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(not(hasErrorText("Password Do not match"))));

        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(doesNotExist());
        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());
    }

    @Test
    public void caseInvalidInputMobileNumber() {
        activity.registerAccount("abc", "801/56", "abc@gmail.com", "1234", "1234", "1234");

        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(not(hasErrorText("at least 3 characters"))));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Address"))));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(not(hasErrorText("enter a valid email address"))));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(hasErrorText("Enter Valid Mobile Number")));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(not(hasErrorText("between 4 and 10 alphanumeric characters"))));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(not(hasErrorText("Password Do not match"))));

        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(doesNotExist());
        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());

    }

    @Test
    public void caseInvalidInputPassword() {
        activity.registerAccount("abc", "801/56", "abc@gmail.com", "0899999999", "123", "123");

        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(not(hasErrorText("at least 3 characters"))));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Address"))));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(not(hasErrorText("enter a valid email address"))));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(hasErrorText("Enter Valid Mobile Number")));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(not(hasErrorText("Password Do not match"))));

        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(doesNotExist());
        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());

    }

    @Test
    public void caseInvalidInputPasswordNotSame() {
        activity.registerAccount("abc", "801/56", "abc@gmail.com", "0899999999", "1234", "5678");

        onView(allOf(withId(R.id.input_name), isDisplayed())).check(matches(not(hasErrorText("at least 3 characters"))));
        onView(allOf(withId(R.id.input_address), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Address"))));
        onView(allOf(withId(R.id.input_email), isDisplayed())).check(matches(not(hasErrorText("enter a valid email address"))));
        onView(allOf(withId(R.id.input_mobile), isDisplayed())).check(matches(not(hasErrorText("Enter Valid Mobile Number"))));
        onView(allOf(withId(R.id.input_password), isDisplayed())).check(matches(not(hasErrorText("between 4 and 10 alphanumeric characters"))));
        onView(allOf(withId(R.id.input_reEnterPassword), isDisplayed())).check(matches(hasErrorText("Password Do not match")));

        onView(allOf(withText(R.string.hello_world), isDisplayed())).check(doesNotExist());
        onView(allOf(withId(R.id.btn_logout))).check(doesNotExist());

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
