package team31.cs2340.gatech.sheltermap;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.Matchers.both;
import static org.junit.Assert.assertEquals;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
/**
 * Created by benpusey on 4/16/18.
 */

public class SearchActivityTest {
    @Rule
    public ActivityTestRule<SearchActivity> lActivityRule =
            new ActivityTestRule<>(SearchActivity.class);
    @Test
    public void testGender() {
        onView(withId(R.id.GenderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Male"))).perform(click());
        onView(withId(R.id.Search)).perform(click());
        boolean worked = true;
        ArrayList<Shelter> filtered = (ArrayList<Shelter>) SearchActivity.getFiltered();
        for (int i = 0; i < filtered.size(); i++) {
            if (filtered.get(i).getRestriction().equals("Women/Children")) {
                worked = false;
            }
        }
        assertEquals(true, worked);
    }

    @Test
    public void testAge() {
        onView(withId(R.id.AgeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Children"))).perform(click());
        onView(withId(R.id.Search)).perform(click());
        boolean worked = true;
        ArrayList<Shelter> filtered = (ArrayList<Shelter>) SearchActivity.getFiltered();
        for (int i = 0; i < filtered.size(); i++) {
            if (!(filtered.get(i).getRestriction().equals("Women/Children")
                    || filtered.get(i).getRestriction().equals("Families " +
                    "w/ Children under 5")
                    || filtered.get(i).getRestriction().equals("Childrens/Young " +
                    "adults ")) && worked) {
                worked = false;
            }
        }
        assertEquals(true, worked);
    }

    @Test
    public void testText() {
        onView(withId(R.id.ShelterName)).perform(typeText("My Sister's House"), closeSoftKeyboard());
        onView(withId(R.id.Search)).perform(click());
        boolean worked = true;
        ArrayList<Shelter> filtered = (ArrayList<Shelter>) SearchActivity.getFiltered();
        for (int i = 0; i < filtered.size(); i++) {
            if (!(filtered.get(i).getName().equals("My Sister's House")) && worked) {
                worked = false;
            }
        }
        assertEquals(true, worked);
    }

    @Test
    public void testBadText() {
        onView(withId(R.id.ShelterName)).perform(typeText("adfsadfaw"), closeSoftKeyboard());
        onView(withId(R.id.Search)).perform(click());
        ArrayList<Shelter> filtered = (ArrayList<Shelter>) SearchActivity.getFiltered();
        assertEquals(0, filtered.size());
    }
}
