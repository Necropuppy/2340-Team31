package team31.cs2340.gatech.sheltermap;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.not;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Before
    public void setUp() {
        User siddu = new User("Anunoy Dussa", "siddudussa@gmail.com", "Hello123!", "850-544-8516");
    }

    /** this line is a preferred way to hook up to activity */
    @Rule
    public ActivityTestRule<LoginActivity> lActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testWrongUsernameWrongPassword() {
        // enter an incorrect username
        onView(withId(R.id.email)).perform(typeText("incorrect email"), closeSoftKeyboard());

        // enter an incorrect password
        onView(withId(R.id.password)).perform(typeText("incorrect password"), closeSoftKeyboard());

        // result
        onView(withId(R.id.textView2)).check(matches(not(withText("Login Failed"))));
    }

    @Test
    public void testRightUsernameWrongPassword() {
        // enter a correct username
        onView(withId(R.id.email)).perform(typeText("siddudussa@gmail.com"), closeSoftKeyboard());

        // enter an incorrect correct password
        onView(withId(R.id.password)).perform(typeText("incorrect password"), closeSoftKeyboard());

        // result
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.textView2)).check(matches(not(withText("Login Failed"))));
    }

    @Test
    public void testWrongUsernameRightPassword() {

                // enter an incorrect username
        onView(withId(R.id.email)).perform(typeText("incorrect email"), closeSoftKeyboard());

        // enter a correct password
        onView(withId(R.id.password)).perform(typeText("Hello123!"), closeSoftKeyboard());


        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.textView2)).check(matches(not(withText("Login Failed"))));
    }

    @Test
    public void testRightUsernameRightPassword() {
        // enter a correct username
        onView(withId(R.id.email)).perform(typeText("siddudussa@gmail.com"), closeSoftKeyboard());

        // enter a correct password
        onView(withId(R.id.password)).perform(typeText("Hello123!"), closeSoftKeyboard());

        // result
        onView(withId(R.id.submit)).perform(click());

        onView(withText("My Sister's House")).check(matches(withText("My Sister's House")));
        Espresso.pressBack();
    }


}
