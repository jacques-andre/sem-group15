package com.napier.sem;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for Country class.
 */
public class CountryTest {

  /**
   * Test if Country.getCountries() matches the same number of,
   * countries found in mysql
   */
  @Test
  public void testGetCountries() {
    System.out.println("Running testGetCountries");

    // total rows in the country table
    int expectedLen = 239;

    ArrayList<Country> countries = Country.getCountries();
    int gotLen = countries.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }

  /**
   * Test if Country.getCountriesByContinent() matches the same number,
   * as sql
   */
  @Test
  public void testGetCountriesByContinent() {
    System.out.println("Running testGetCountriesByContinent");

    String continent = "Asia";
    // There are 51 countries with continent of "Asia"
    int expectedLen = 51;

    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);
    int gotLen = countriesInContinent.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }

  /**
   * Test if Country.getCountriesByRegion() matches the same number,
   * as sql
   */
  @Test
  public void testGetCountriesByRegion() {
    System.out.println("Running testGetCountriesByRegion");

    String region = "Caribbean";
    // There are 24 countries with region of "Asia"
    int expectedLen = 24;

    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    int gotLen = countriesInRegion.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }
}
