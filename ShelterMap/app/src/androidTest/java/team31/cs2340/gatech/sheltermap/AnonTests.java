package team31.cs2340.gatech.sheltermap;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Marcus on 4/25/2018.
 */

public class AnonTests {
    @Rule
    public ActivityTestRule<WelcomeActivity> lActivityRule =
            new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void claimAnon() {
        onView(withId(R.id.skip)).perform(click());
        onView(withId(R.id.shelter_list)).perform(click());
        Shelter shelter = Shelter.shelters.get(5);
        int openSpots = shelter.getCap() - shelter.getPop();
        int origOpen = shelter.getCap() - shelter.getPop();
        onView(withId(R.id.familyNum)).perform(typeText(Integer.toString(openSpots - 1)), closeSoftKeyboard());
        onView(withId(R.id.checkin)).perform(click());
        openSpots = shelter.getCap() - shelter.getPop();
        assertEquals(openSpots, origOpen);
    }
}
