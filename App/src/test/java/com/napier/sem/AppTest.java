package com.napier.sem;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import com.napier.sem.Reports.CityReport;
import com.napier.sem.Reports.CountryReport;

/**
 * Unit test for App class.
 */
public class AppTest {

  /**
   * Test if App.countryLargestPopulationToSmallest() returns correct number of
   * countries, and is in correct order (population descending)
   */
  @Test
  public void testCountryLargestPopulationToSmallest() throws Exception {
    System.out.println("Testing:testCountryLargestPopulationToSmallest");

    // total rows in the country table
    int expectedLen = 239;

    // Check if len of output matches rows in db
    CountryReport result = App.countryLargestPopulationToSmallest();
    int gotLen = result.countryOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through countryOutput, check if descending
    for (int i = 0; i < result.countryOutput.size() - 1; i++) {
      Country currentCountry = result.countryOutput.get(i);
      Country nextCountry = result.countryOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCountry.Population >= nextCountry.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.countryContinentOrgnaisedLargestSmallest() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testCountryContinentOrgnaisedLargestSmallest() throws Exception {
    System.out.println("Testing:testCountryContinentOrgnaisedLargestSmallest");

    // total rows in the country table with continent "Asia"
    int expectedLen = 51;

    CountryReport result = App.countryContinentOrgnaisedLargestSmallest("Asia");
    int gotLen = result.countryOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through countryOutput, check if descending
    for (int i = 0; i < result.countryOutput.size() - 1; i++) {
      Country currentCountry = result.countryOutput.get(i);
      Country nextCountry = result.countryOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCountry.Population >= nextCountry.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.countryRegionOrgnaisedLargestSmallest() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testCountryRegionOrgnaisedLargestSmallest() throws Exception {
    System.out.println("Testing:testCountryRegionOrgnaisedLargestSmallest");

    String exampleRegion = "Caribbean";

    // total rows in the country table with exampleRegion
    int expectedLen = 24;

    CountryReport result = App.countryRegionOrgnaisedLargestSmallest(exampleRegion);
    int gotLen = result.countryOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through countryOutput, check if descending
    for (int i = 0; i < result.countryOutput.size() - 1; i++) {
      Country currentCountry = result.countryOutput.get(i);
      Country nextCountry = result.countryOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCountry.Population >= nextCountry.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.topNPopulatedCountriesInWorld() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testTopNPopulatedCountriesInWorld() throws Exception {
    System.out.println("Testing:testTopNPopulatedCountriesInWorld");

    int n = 5;

    // Expecting to get n number of countries
    int expectedLen = n;

    CountryReport result = App.topNPopulatedCountriesInWorld(n);
    int gotLen = result.countryOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through countryOutput, check if descending
    for (int i = 0; i < result.countryOutput.size() - 1; i++) {
      Country currentCountry = result.countryOutput.get(i);
      Country nextCountry = result.countryOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCountry.Population >= nextCountry.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.topNPopulatedContinentCountries() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testTopNPopulatedContinentCountries() throws Exception {
    System.out.println("Testing:testTopNPopulatedContinentCountries");

    int n = 5;
    String exampleContinent = "Africa";

    // Expecting to get n number of countries
    int expectedLen = n;

    CountryReport result = App.topNPopulatedContinentCountries(n, exampleContinent);
    int gotLen = result.countryOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through countryOutput, check if descending
    for (int i = 0; i < result.countryOutput.size() - 1; i++) {
      Country currentCountry = result.countryOutput.get(i);
      Country nextCountry = result.countryOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCountry.Population >= nextCountry.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.topNPopulatedRegionCountries() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testTopNPopulatedRegionCountries() throws Exception {
    System.out.println("Testing:testTopNPopulatedRegionCountries");

    int n = 5;
    String exampleRegion = "Caribbean";

    // Expecting to get n number of countries
    int expectedLen = n;

    CountryReport result = App.topNPopulatedRegionCountries(n, exampleRegion);
    int gotLen = result.countryOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through countryOutput, check if descending
    for (int i = 0; i < result.countryOutput.size() - 1; i++) {
      Country currentCountry = result.countryOutput.get(i);
      Country nextCountry = result.countryOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCountry.Population >= nextCountry.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.citiesLargestPopulationToSmallest() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testCitiesLargestPopulationToSmallest() throws Exception {
    System.out.println("Testing:testCitiesLargestPopulationToSmallest");

    // This matches rows found in sql
    int expectedLen = 4079;

    CityReport result = App.citiesLargestPopulationToSmallest();
    int gotLen = result.cityOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through cityOutput, check if descending
    for (int i = 0; i < result.cityOutput.size() - 1; i++) {
      City currentCity = result.cityOutput.get(i);
      City nextCity = result.cityOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCity.Population >= nextCity.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }

  /**
   * Test if App.citiesInContinentLargestPopulationToSmallest() returns correct
   * number of countries, and is in correct order (population descending)
   */
  @Test
  public void testCitiesInContinentLargestPopulationToSmallest() throws Exception {
    System.out.println("Testing:testCitiesInContinentLargestPopulationToSmallest");

    String exampleContinent = "Africa";
    // This matches rows found in sql
    int expectedLen = 366;

    CityReport result = App.citiesInContinentLargestPopulationToSmallest(exampleContinent);
    int gotLen = result.cityOutput.size();

    assertEquals(expectedLen, gotLen);
    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);

    // Loop through cityOutput, check if descending
    for (int i = 0; i < result.cityOutput.size() - 1; i++) {
      City currentCity = result.cityOutput.get(i);
      City nextCity = result.cityOutput.get(i + 1);

      // check if current iteration (i) population is not greater than i+1,
      // if so throw err
      if (!(currentCity.Population >= nextCity.Population)) {
        throw new Exception("Population is not descending!");
      }
    }
  }
}
