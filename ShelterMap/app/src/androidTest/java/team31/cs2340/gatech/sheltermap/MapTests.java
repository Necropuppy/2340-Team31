package team31.cs2340.gatech.sheltermap;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.util.EnumSet.allOf;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by Marcus on 4/25/2018.
 */

public class MapTests {
    private String name = "Tester1";
    private String email = "Tester1@gmail.com";
    private String pass = "Hello123!";
    private String phone = "123-456-7890";
    @Before
    public void setUp() {
        User tester1 = new User("Tester1", "Tester1@gmail.com", "Hello123!", "123-456-7890");
    }

    @Rule
    public ActivityTestRule<LoginActivity> lActivityRule =
            new ActivityTestRule<>(LoginActivity.class,true);
@Test
    public void testNavigateTo() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.map2)).perform(click());
        onView(withId(R.id.filter)).check(matches(isDisplayed()));

    }
    @Test
    public void testFilterMale() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.map2)).perform(click());
        onView(withId(R.id.filter)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.update)).perform(click());
        onView(withId(R.id.filter)).check(matches(isDisplayed()));

    }

    @Test
    public void testFilterChildren() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.map2)).perform(click());
        onView(withId(R.id.filter)).perform(click());
        onData(anything()).atPosition(3).perform(click());
        onView(withId(R.id.update)).perform(click());
        onView(withId(R.id.filter)).check(matches(isDisplayed()));
    }


}
