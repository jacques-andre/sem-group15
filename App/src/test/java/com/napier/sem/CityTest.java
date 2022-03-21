package com.napier.sem;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for City class.
 */
public class CityTest {

  /**
   * Test if City.getCities() matches the same number of,
   * cities found in mysql
   */
  @Test
  public void testGetCities() {
    System.out.println("Running testGetCities");

    // total rows in the city table
    int expectedLen = 4079;

    ArrayList<City> cities = City.getCities();
    int gotLen = cities.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }

  /**
   * Test if City.getCitiesByCountryCode() returns the same number of,
   * cities with the countryCode found in mysql
   */
  @Test
  public void testGetCitiesByCountryCode() {
    System.out.println("Runking testGetCitiesByCountryCode");

    String exampleCode = "AUS";

    // rows in the city table with exampleCode
    int expectedLen = 14;

    ArrayList<City> citiesInCode = City.getCitiesByCountryCode("AUS");
    int gotLen = citiesInCode.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }
}
