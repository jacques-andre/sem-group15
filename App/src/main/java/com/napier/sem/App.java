package com.napier.sem;

import java.util.*;

public class App {
  public static void main(String[] args) {
    // largestPopulationToSmallest();
    continentOrgnaisedLargestSmallest();

  }
  /*
   * "All the countries in the world organised by largest population to smallest."
  */
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
  /*
   * "All the countries in a continent organised by largest population to smallest."
  */
  public static void continentOrgnaisedLargestSmallest(){
    Continent Africa = new Continent("Africa");
    List<Country> countriesInContinent = Country.getCountriesByContinent(Africa);

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country o1, Country o2) {
        return o1.Population - o2.Population;
      }
    });

    for (int i = countriesInContinent.size() - 1; i >= 0; i--) {
      // current iteration
      Country currentCountry = countriesInContinent.get(i);
      String out = String.format("Name:%s,Population:%d", currentCountry.Name, currentCountry.Population);

      System.out.println(out);
    }

  }
}
