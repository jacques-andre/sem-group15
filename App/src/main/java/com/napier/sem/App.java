package com.napier.sem;

import java.util.*;

public class App {
  public static void main(String[] args) {
    System.out.println(
        String.format("DB_HOST:%s", System.getenv("DB_HOST")));

    countryLargestPopulationToSmallest();
    countryContinentOrgnaisedLargestSmallest();
    countryRegionOrgnaisedLargestSmallest();
    topNPopulatedCountriesInWorld(5);
    topNPopulatedContinentCountries(5);
    populationInCitiesAndNot();
  }

  /*
   * "All the countries in the world organised by largest population to smallest."
   */
  public static void countryLargestPopulationToSmallest() {
    System.out.println("---countryLargestPopulationToSmallest---");
    ArrayList<Country> allCountries = Country.getCountries();

    // sort
    Collections.sort(allCountries, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });
    for (Country c : allCountries) {
      System.out.println(c);
    }
  }

  /*
   * "All the countries in a continent organised by largest population to smallest."
   */
  public static void countryContinentOrgnaisedLargestSmallest() {
    System.out.println("---countryContinentOrgnaisedLargestSmallest---");
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent("Africa");

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : countriesInContinent) {
      System.out.println(c);
    }

  }

  /*
   * "All the countries in a region organised by largest population to smallest."
   */
  public static void countryRegionOrgnaisedLargestSmallest() {
    System.out.println("---countryRegionOrgnaisedLargestSmallest---");
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion("Caribbean");

    // sort
    Collections.sort(countriesInRegion, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : countriesInRegion) {
      System.out.println(c);
    }

  }

  /*
   * The top N populated countries in the world where N is provided by the user.
   */
  public static void topNPopulatedCountriesInWorld(int n) {
    System.out.println("---topNPopulatedCountriesInWorld---");
    ArrayList<Country> allCountries = Country.getCountries();

    // sort
    Collections.sort(allCountries, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = allCountries.get(i);
      System.out.println(currentCountry);
    }
  }

  /*
   * The top N populated countries in a continent where N is provided by the user.
   */
  public static void topNPopulatedContinentCountries(int n) {
    System.out.println("---topNPopulatedContinentCountries---");
    String continent = "Asia";
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = countriesInContinent.get(i);
      System.out.println(currentCountry);
    }
  }

  /*
   * "The population of people, people living in cities, and people not living in cities in each country"
   */
  public static void populationInCitiesAndNot() {
    System.out.println("---populationInCitiesAndNot---");
    List<Country> allCountries = Country.getCountries();

    for (Country currentCountry : allCountries) {
      int countryPopulation = currentCountry.Population;
      int cityPopulation = 0;

      // gets population of all cities in current country
      List<City> citiesInCode = City.getCitiesByCountryCode(currentCountry.Code);
      for (City city : citiesInCode) {
        cityPopulation += city.Population;
      }

      int outsideCity = countryPopulation - cityPopulation;
      double cityPercentage = (double) cityPopulation / countryPopulation;
      double outsideCityPercentage = (double) outsideCity / countryPopulation;
      cityPercentage *= 100;
      outsideCityPercentage *= 100;

      System.out.println(String.format("Country:%s", currentCountry.Name));
      System.out.println(String.format("Country Population:%d", countryPopulation));
      System.out.println(String.format("City Population:%d", cityPopulation));
      System.out.println(String.format("City Percentage:%f%%", cityPercentage));
      System.out.println(String.format("Outside City:%d", outsideCity));
      System.out.println(String.format("Outside City Percentage:%f%%", outsideCityPercentage));
    }
  }
}
