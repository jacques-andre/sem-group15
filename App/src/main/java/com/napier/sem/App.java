package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class App {
  public static void main(String[] args) {

  }

  public static void largestPopulationToSmallest() {
    List<Country> all = Country.getCountries();

    for (Country country : all) {
      // loop through list, uses ToString
      System.out.println(country);
    }

  }
}
