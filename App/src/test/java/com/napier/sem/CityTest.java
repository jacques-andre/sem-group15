package com.napier.sem;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for City class.
 */
public class CityTest {

  /**
   * Test if City.getCities() returns the same number of,
   * cities as there are in the City table.
   */
  @Test
  public void testGetCities() {
    System.out.println("Running testGetCities");
    // This is how many rows are in the city table
    int expectedLen = 4079;

    ArrayList<City> cities = City.getCities();
    int gotLen = cities.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }
}
