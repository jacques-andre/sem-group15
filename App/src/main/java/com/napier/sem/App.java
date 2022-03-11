package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class App {
  public static void main(String[] args) {
    largestPopulationToSmallest();
  }

  public static void largestPopulationToSmallest() {
    List<Country> all = Country.getCountries();

    // sort
    Collections.sort(all, new Comparator<Country>() {
      public int compare(Country o1, Country o2) {
        return o1.Population - o2.Population;
      }
    });
    for (int i = all.size() - 1; i >= 0; i--) {
      // current iteration
      Country currentCountry = all.get(i);
      String out = String.format("Name:%s,Population:%d", currentCountry.Name, currentCountry.Population);

      System.out.println(out);
    }

  }
}
