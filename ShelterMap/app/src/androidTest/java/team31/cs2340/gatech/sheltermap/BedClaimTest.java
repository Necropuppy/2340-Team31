package team31.cs2340.gatech.sheltermap;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;


@RunWith(AndroidJUnit4.class)

public class BedClaimTest {
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
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testClaimUnclaim() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.shelter_list)).perform(click());
        onView(withId(R.id.checkout)).perform(click());
        Shelter shelter = Shelter.shelters.get(5);
        int openSpots = shelter.getCap() - shelter.getPop();
        int origOpen = shelter.getCap() - shelter.getPop();
        onView(withId(R.id.familyNum)).perform(typeText(Integer.toString(openSpots)), closeSoftKeyboard());
        onView(withId(R.id.checkin)).perform(click());
        openSpots = shelter.getCap() - shelter.getPop();
        assertEquals(0, openSpots);
        onView(withId(R.id.checkout)).perform(click());
        openSpots = shelter.getCap() - shelter.getPop();
        assertEquals(openSpots, origOpen);

    }

    @Test
    public void testClaimTooMany() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.shelter_list)).perform(click());
        onView(withId(R.id.checkout)).perform(click());
        Shelter shelter = Shelter.shelters.get(5);
        int openSpots = shelter.getCap() - shelter.getPop();
        int origOpen = shelter.getCap() - shelter.getPop();
        onView(withId(R.id.familyNum)).perform(typeText(Integer.toString(openSpots + 1)), closeSoftKeyboard());
        onView(withId(R.id.checkin)).perform(click());
        openSpots = shelter.getCap() - shelter.getPop();
        assertEquals(openSpots, origOpen);
        onView(withId(R.id.checkout)).perform(click());

    }

    @Test
    public void testClaimClaim() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.shelter_list)).perform(click());
        onView(withId(R.id.checkout)).perform(click());
        Shelter shelter = Shelter.shelters.get(5);
        int openSpots = shelter.getCap() - shelter.getPop();
        int origOpen = shelter.getCap() - shelter.getPop();
        onView(withId(R.id.familyNum)).perform(typeText(Integer.toString(openSpots - 1)), closeSoftKeyboard());
        onView(withId(R.id.checkin)).perform(click());
        onView(withId(R.id.familyNum)).perform(clearText());
        onView(withId(R.id.familyNum)).perform(typeText(Integer.toString(1)), closeSoftKeyboard());
        onView(withId(R.id.checkin)).perform(click());
        openSpots = shelter.getCap() - shelter.getPop();
        assertEquals(openSpots, 1);
        onView(withId(R.id.checkout)).perform(click());

    }
@Test
    public void Unclaim() {
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.shelter_list)).perform(click());
        Shelter shelter = Shelter.shelters.get(5);
        int openSpots = shelter.getCap() - shelter.getPop();
        int origOpen = shelter.getCap() - shelter.getPop();
        onView(withId(R.id.checkout)).perform(click());
        openSpots = shelter.getCap() - shelter.getPop();
        assertEquals(openSpots, origOpen);
    }







}
