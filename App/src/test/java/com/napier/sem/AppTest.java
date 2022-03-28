package com.napier.sem;

import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for App class.
 */
public class AppTest {

  /**
   * Test if App.countryLargestPopulationToSmallest() returns correct number of
   * countries,
   */
  @Test
  public void testCountryLargestPopulationToSmallest() {
    System.out.println("Running testCountryLargestPopulationToSmallest");

    // total rows in the country table
    int expectedLen = 239;

    Report result = App.countryLargestPopulationToSmallest();
    int gotLen = result.QuestionOutput.size();

    assertEquals(expectedLen, gotLen);

    System.out.printf("Expected:%d,Got:%d \n", expectedLen, gotLen);
  }

}
