package team31.cs2340.gatech.sheltermap;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import static java.sql.DriverManager.println;
import static junit.framework.Assert.fail;


@RunWith(AndroidJUnit4.class)

public class WelcomeActivityTest {
    @Before
    public void setUp() {
        //User siddu = new User("Anunoy Dussa", "siddudussa@gmail.com", "Hello123!", "850-544-8516");
    }

    @Rule
    public ActivityTestRule<WelcomeActivity> lActivityRule =
            new ActivityTestRule<>(WelcomeActivity.class);



    @Test
    public void testLogin() {
        //click Login Button
        onView(withId(R.id.login)).perform(click());

        // result

        try {
            onView(withId(R.id.error)).toString();
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSignup() {
        //click Signup Button
        onView(withId(R.id.register)).perform(click());

        // result

        try {
            onView(withId(R.id.phone)).perform(typeText("some email"), closeSoftKeyboard());
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCancel() {

        onView(withId(R.id.login)).perform(click());
        // enter a correct username
        onView(withId(R.id.email)).perform(typeText("siddudussa@gmail.com"), closeSoftKeyboard());

        // enter a correct password
        onView(withId(R.id.password)).perform(typeText("Hello123!"), closeSoftKeyboard());

        // result
        onView(withId(R.id.cancel)).perform(click());
        onView(withId(R.id.login)).check(matches(isDisplayed()));

    }

    @Test
    public void testWelcomeReturn() {
        onView(withId(R.id.register)).perform(click());

        // enter an incorrect password
        onView(withId(R.id.password)).perform(typeText("anypassword"), closeSoftKeyboard());

        onView(withId(R.id.cancel)).perform(click());

        // result
        onView(withId(R.id.login)).check(matches(isDisplayed()));

    }

}
