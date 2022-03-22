package com.napier.sem;

import java.util.*;

public class App {
  public static void main(String[] args) {
    System.out.println(
        String.format("DB_HOST:%s", System.getenv("DB_HOST")));

    ArrayList<Report> allReports = new ArrayList<Report>();

    allReports.add(countryLargestPopulationToSmallest());
    allReports.add(countryContinentOrgnaisedLargestSmallest());
    allReports.add(countryRegionOrgnaisedLargestSmallest());

    allReports.add(topNPopulatedCountriesInWorld(5));
    allReports.add(topNPopulatedContinentCountries(5));
    allReports.add(topNPopulatedRegionCountries(5));

    allReports.add(citiesLargestPopulationToSmallest());

    allReports.add(populationInCitiesAndNot());

    // for (Report r : allReports) {
    // r.outputReport();
    // System.out.println("----");
    // }
    Report.toHMTL(allReports);
  }

  /*
   * "All the countries in the world organised by largest population to smallest."
   */
  public static Report countryLargestPopulationToSmallest() {
    ArrayList<Country> allCountries = Country.getCountries();

    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(allCountries, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : allCountries) {
      output.add(c.toString());
    }

    Report report = new Report("countryLargestPopulationToSmallest", output);
    return report;
  }

  /*
   * "All the countries in a continent organised by largest population to smallest."
   */
  public static Report countryContinentOrgnaisedLargestSmallest() {
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent("Africa");
    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : countriesInContinent) {
      output.add(c.toString());
    }

    Report report = new Report("countryContinentOrgnaisedLargestSmallest", output);
    return report;
  }

  /*
   * "All the countries in a region organised by largest population to smallest."
   */
  public static Report countryRegionOrgnaisedLargestSmallest() {
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion("Caribbean");
    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(countriesInRegion, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : countriesInRegion) {
      output.add(c.toString());
    }

    Report report = new Report("countryRegionOrgnaisedLargestSmallest", output);
    return report;
  }

  /*
   * The top N populated countries in the world where N is provided by the user.
   */
  public static Report topNPopulatedCountriesInWorld(int n) {
    ArrayList<Country> allCountries = Country.getCountries();
    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(allCountries, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = allCountries.get(i);
      output.add(currentCountry.toString());
    }
    Report report = new Report("topNPopulatedCountriesInWorld", output);
    return report;
  }

  /*
   * The top N populated countries in a continent where N is provided by the user.
   */
  public static Report topNPopulatedContinentCountries(int n) {
    String continent = "Asia";
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);
    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = countriesInContinent.get(i);
      output.add(currentCountry.toString());
    }
    Report report = new Report("topNPopulatedContinentCountries", output);
    return report;
  }

  /*
   * The top N populated countries in a region where N is provided by the user.
   */
  public static Report topNPopulatedRegionCountries(int n) {
    String region = "Eastern Europe";
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(countriesInRegion, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = countriesInRegion.get(i);
      output.add(currentCountry.toString());
    }
    Report report = new Report("topNPopulatedRegionCountries", output);
    return report;
  }

  /*
   * All the cities in the world organised by largest population to smallest.
   */
  public static Report citiesLargestPopulationToSmallest() {
    ArrayList<City> allCities = City.getCities();
    ArrayList<String> output = new ArrayList<String>();

    // sort
    Collections.sort(allCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    for (City c : allCities) {
      output.add(c.toString());
    }

    Report report = new Report("citiesLargestPopulationToSmallest", output);
    return report;
  }

  /*
   * "The population of people, people living in cities, and people not living in cities in each country"
   */
  public static Report populationInCitiesAndNot() {
    List<Country> allCountries = Country.getCountries();
    ArrayList<String> output = new ArrayList<String>();

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

      String outputString = String.format(
          "Country:%s,Country-Population:%d,City-Population:%d,City-Percentage:%f%%,Outside-city:%d,outsideCityPercentage:%f%%",
          currentCountry.Name, countryPopulation, cityPopulation, cityPercentage, outsideCity, outsideCityPercentage);

      output.add(outputString);
    }
    Report report = new Report("populationInCitiesAndNot", output);
    return report;
  }
}
