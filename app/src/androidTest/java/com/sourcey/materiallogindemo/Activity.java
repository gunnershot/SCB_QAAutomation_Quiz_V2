package com.sourcey.materiallogindemo;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
/**
 * Created by sinch on 3/20/2018.
 */

public class Activity {

    public void registerAccount(String name, String address, String email, String mobileNumber, String password, String rePassword) {
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


    public void login(String email, String password) {
        onView(withId(R.id.input_email)).perform(typeText(email));
        onView(withId(R.id.input_password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(scrollTo(), click());
    }

}
