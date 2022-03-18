package com.napier.sem;

import java.util.*;

public class App {
  public static void main(String[] args) {
    // largestPopulationToSmallest();
    // continentOrgnaisedLargestSmallest();
    populationInCitiesAndNot();

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
    List<Country> countriesInContinent = Country.getCountriesByContinent("Africa");

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
  /*
   * "The population of people, people living in cities, and people not living in cities in each country"
   */
  public static void populationInCitiesAndNot(){
    List<Country> allCountries = Country.getCountries();

    for(int i = 0; i < allCountries.size(); i++){
      // current iteration
      Country currentCountry = allCountries.get(i);

      int countryPopulation = currentCountry.Population;
      int cityPopulation = 0;

      // gets population of all cities in current country
      List<City> citiesInCode = City.getCitiesByCountryCode(currentCountry.Code);
      for (City city : citiesInCode) {
        cityPopulation += city.Population;
      }

      
      int outsideCity = countryPopulation - cityPopulation;
      double cityPercentage = (double)cityPopulation / countryPopulation;
      double outsideCityPercentage = (double)outsideCity / countryPopulation;
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
