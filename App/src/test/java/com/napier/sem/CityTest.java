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
    System.out.println("Testing:testGetCities");

    // total rows in the city table
    int expectedLen = 4079;

    ArrayList<City> cities = City.getCities();
    int gotLen = cities.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }

  /**
   * Test if City.getCities() returns the same number of,
   * cities with the countryCode found in mysql
   */
  @Test
  public void testGetCitiesByCountryName() {
    System.out.println("Testing:testGetCitiesByCountryName");

    String exampleCountry = "Australia";

    // rows in the city table with exampleCountry
    int expectedLen = 14;

    ArrayList<City> citiesInCountry = City.getCitiesByCountryName(exampleCountry);
    int gotLen = citiesInCountry.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }

  /**
   * Test if City.getCitiesByDistrict() returns the same number of,
   * cities with the district found in mysql
   */
  @Test
  public void getCitiesByDistrict() {
    System.out.println("Testing:getCitiesByDistrict");

    String exampleDistrict = "Zhejiang";

    // rows in the city table with exampleDistrict
    int expectedLen = 16;

    ArrayList<City> citiesInDistrict = City.getCitiesByDistrict(exampleDistrict);
    int gotLen = citiesInDistrict.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }
}
