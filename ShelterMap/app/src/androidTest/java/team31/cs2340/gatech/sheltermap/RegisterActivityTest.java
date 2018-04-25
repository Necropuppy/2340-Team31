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


import static junit.framework.Assert.fail;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)

public class RegisterActivityTest {
    @Before
    public void setUp() {
        User siddu = new User("Anunoy Dussa", "siddudussa@gmail.com", "Hello123!", "850-544-8516");
    }

    @Rule
    public ActivityTestRule<RegisterActivity> lActivityRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testUniqueEmail() {
        // enter an incorrect username
        onView(withId(R.id.email)).perform(typeText("some email"), closeSoftKeyboard());

        // enter an incorrect password
        onView(withId(R.id.password)).perform(typeText("some password"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        // result
        onView(withId(R.id.login)).check(matches(isDisplayed()));

    }

    @Test
    public void testTakenEmail() {
        // enter an incorrect username
        onView(withId(R.id.email)).perform(typeText("siddudussa@gmail.com"), closeSoftKeyboard());

        // enter an incorrect password
        onView(withId(R.id.password)).perform(typeText("anypassword"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        // result
        onView(withId(R.id.email)).check(matches(withText("siddudussa@gmail.com")));
    }

    @Test
    public void testNoEmail() {

        // enter an incorrect password
        onView(withId(R.id.password)).perform(typeText("anypassword"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        // result
        onView(withId(R.id.email)).check(matches(withText("")));
    }


}
