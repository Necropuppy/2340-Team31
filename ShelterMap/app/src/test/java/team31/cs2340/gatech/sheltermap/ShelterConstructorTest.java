package team31.cs2340.gatech.sheltermap;

/*
 * Created by Elmer
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Shelter.java constructor
 */
public class ShelterConstructorTest {
    User siddu;
    ArrayList<User> users;

    /**
     * Sets up necessary objects for test
     */
    @Before
    public void setUp() {
        users = new ArrayList<>();
        siddu = new User("Anunoy Dussa", "siddudussa@gmail.com", "Hello123!", "850-544-8516", 0, 2, 0);
        User.users.clear();
        User.users.add(siddu);
    }

    /**
     * Tests whether creation of shelter properly accounts for any reservations.
     * Shelter population should be adjusted accordingly if user has reserved.
     */
    @Test
    public void testCorrectShelterPopulationAdjust() {
        Shelter testShelter = new Shelter("Test Shelter", 0, 2, "Test Restriction", 0.0, 0.0, "Test Address"); //shelter of capacity 2 is created
        assertEquals(2, testShelter.getPop()); //current population of shelter is 2
    }

    /**
     * Tests whether creation of shelter properly accounts for any reservations.
     * Shelter population should not be adjusted if no user has reserved.
     */
    @Test
    public void testCorrectShelterPopulation() {
        Shelter testShelter = new Shelter("Test Shelter", 1, 2, "Test Restriction", 0.0, 0.0, "Test Address"); //shelter of capacity 2 is created
        assertEquals(0, testShelter.getPop()); //current population of shelter is 0
    }

    /**
     * Tests whether RuntimeException is thrown due to population being greater than capacity.
     */
    @Test(expected = RuntimeException.class)
    public void testRuntimeExceptionThrown() {
        Shelter testShelter = new Shelter("Test Shelter", 0, 1, "Test Restriction", 0.0, 0.0, "Test Address"); //shelter of capacity 2 is created
    }

    /**
     * Tests whether creation of shelter properly skips updating population given empty Users list.
     * Shelter population should not be adjusted if no user exists.
     */
    @Test
    public void testCheckUsersReserveSkipped() {
        Shelter testShelter = new Shelter("Test Shelter", 1, 2, "Test Restriction", 0.0, 0.0, "Test Address"); //shelter of capacity 2 is created
        User.users.clear(); //clear Users list
        assertEquals(0, testShelter.getPop()); //current population of shelter is 0
    }
}