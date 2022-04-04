package com.napier.sem;

import java.util.*;

import com.napier.sem.Reports.CityReport;
import com.napier.sem.Reports.CountryReport;
import com.napier.sem.Reports.Report;

public class App {
  public static void main(String[] args) {
    System.out.println(
        String.format("DB_HOST:%s", System.getenv("DB_HOST")));

    ArrayList<Report> allReports = new ArrayList<Report>();
    ArrayList<CountryReport> countryReports = new ArrayList<CountryReport>();
    ArrayList<CityReport> cityReports = new ArrayList<CityReport>();

    countryReports.add(countryLargestPopulationToSmallest());
    countryReports.add(countryContinentOrgnaisedLargestSmallest("Africa"));
    countryReports.add(countryRegionOrgnaisedLargestSmallest("Caribbean"));
    countryReports.add(topNPopulatedCountriesInWorld(5));
    countryReports.add(topNPopulatedContinentCountries(5, "Africa"));
    countryReports.add(topNPopulatedRegionCountries(5, "Eastern Europe"));

    cityReports.add(citiesLargestPopulationToSmallest());

    allReports.addAll(countryReports);
    allReports.addAll(cityReports);

    for (Report r : allReports) {
      r.outputReport();
      System.out.println("----");
    }

    Report.toHMTL(allReports, countryReports);
  }

  /*
   * "All the countries in the world organised by largest population to smallest."
   */
  public static CountryReport countryLargestPopulationToSmallest() {
    ArrayList<Country> allCountries = Country.getAllCountries();

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<Country> outputCountry = new ArrayList<Country>();

    // sort by country population
    Collections.sort(allCountries, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through all countries (sorted) append to output
    for (Country c : allCountries) {
      outputStr.add(c.toString());
      outputCountry.add(c);
    }

    // Create a new report with outputs
    CountryReport report = new CountryReport(
        "All the countries in the world organised by largest population to smallest.", outputStr, "", outputCountry);

    return report;
  }

  /*
   * "All the countries in a continent organised by largest population to smallest."
   */
  public static CountryReport countryContinentOrgnaisedLargestSmallest(String continent) {
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<Country> outputCountry = new ArrayList<Country>();

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : countriesInContinent) {
      outputStr.add(c.toString());
      outputCountry.add(c);
    }

    String comment = String.format("Using %s as continent", continent);

    CountryReport report = new CountryReport(
        "All the countries in a continent organised by largest population to smallest.", outputStr, comment,
        outputCountry);
    return report;
  }

  /*
   * "All the countries in a region organised by largest population to smallest."
   */
  public static CountryReport countryRegionOrgnaisedLargestSmallest(String region) {
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<Country> outputCountry = new ArrayList<Country>();

    // sort
    Collections.sort(countriesInRegion, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (Country c : countriesInRegion) {
      outputStr.add(c.toString());
      outputCountry.add(c);
    }

    String comment = String.format("Using %s as region", region);

    CountryReport report = new CountryReport(
        "All the countries in a region organised by largest population to smallest", outputStr, comment, outputCountry);
    return report;
  }

  /*
   * "The top N populated countries in the world where N is provided by the user"
   */
  public static CountryReport topNPopulatedCountriesInWorld(int n) {
    ArrayList<Country> allCountries = Country.getAllCountries();
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<Country> outputCountry = new ArrayList<Country>();

    // sort
    Collections.sort(allCountries, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = allCountries.get(i);
      outputStr.add(currentCountry.toString());
      outputCountry.add(currentCountry);
    }

    String reportComment = String.format("Using %d as N", n);

    CountryReport report = new CountryReport(
        "The top N populated countries in the world where N is provided by the user", outputStr,
        reportComment, outputCountry);
    return report;
  }

  /*
   * "The top N populated countries in a continent where N is provided by the user"
   */
  public static CountryReport topNPopulatedContinentCountries(int n, String continent) {
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<Country> outputCountry = new ArrayList<Country>();

    // sort
    Collections.sort(countriesInContinent, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = countriesInContinent.get(i);
      outputStr.add(currentCountry.toString());
      outputCountry.add(currentCountry);
    }

    String reportComment = String.format("Using %d as N, Using %s as continent", n, continent);

    CountryReport report = new CountryReport(
        "The top N populated countries in a continent where N is provided by the user",
        outputStr, reportComment, outputCountry);
    return report;
  }

  /*
   * "The top N populated countries in a region where N is provided by the user."
   */
  public static CountryReport topNPopulatedRegionCountries(int n, String region) {
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<Country> outputCountry = new ArrayList<Country>();

    // sort
    Collections.sort(countriesInRegion, new Comparator<Country>() {
      public int compare(Country c1, Country c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      Country currentCountry = countriesInRegion.get(i);
      outputStr.add(currentCountry.toString());
      outputCountry.add(currentCountry);
    }

    String reportComment = String.format("Using %d as N, Using %s as region", n, region);

    CountryReport report = new CountryReport(
        "The top N populated countries in a region where N is provided by the user", outputStr,
        reportComment, outputCountry);
    return report;
  }

  /*
   * "All the cities in the world organised by largest population to smallest."
   */
  public static CityReport citiesLargestPopulationToSmallest() {
    ArrayList<City> allCities = City.getCities();
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCity = new ArrayList<City>();

    // sort
    Collections.sort(allCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    for (City c : allCities) {
      outputStr.add(c.toString());
      outputCity.add(c);
    }

    CityReport report = new CityReport("All the cities in the world organised by largest population to smallest.",
        outputStr, "", outputCity);
    return report;
  }

  // /*
  // * "The population of people, people living in cities, and people not living
  // in cities in each country"
  // */
  // public static Report populationInCitiesAndNot() {
  // List<Country> allCountries = Country.getAllCountries();
  // ArrayList<String> output = new ArrayList<String>();

  // for (Country currentCountry : allCountries) {
  // int countryPopulation = currentCountry.Population;
  // int cityPopulation = 0;

  // // gets population of all cities in current country
  // List<City> citiesInCode = City.getCitiesByCountryCode(currentCountry.Code);
  // for (City city : citiesInCode) {
  // cityPopulation += city.Population;
  // }

  // int outsideCity = countryPopulation - cityPopulation;
  // double cityPercentage = (double) cityPopulation / countryPopulation;
  // double outsideCityPercentage = (double) outsideCity / countryPopulation;
  // cityPercentage *= 100;
  // outsideCityPercentage *= 100;

  // String outputString = String.format(
  // "Country:%s,Country-Population:%d,City-Population:%d,City-Percentage:%f%%,Outside-city:%d,outsideCityPercentage:%f%%",
  // currentCountry.Name, countryPopulation, cityPopulation, cityPercentage,
  // outsideCity, outsideCityPercentage);

  // output.add(outputString);
  // }
  // Report report = new Report("populationInCitiesAndNot", output);
  // return report;
  // }

  /*
   * "The top N populated cities in the world where N is provided by the user."
   */
  public static CityReport topNPopulatedCitiesInWorld(int n) {
    ArrayList<City> allCities = City.getCities();
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCity = new ArrayList<City>();

    // sort
    Collections.sort(allCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    for (int i = 0; i < n; i++) {
      City currentCity = allCities.get(i);
      outputStr.add(currentCity.toString());
      outputCity.add(currentCity);
    }

    String comment = String.format("Using %d as N", n);

    CityReport report = new CityReport("The top N populated cities in the world where N is provided by the user",
        outputStr, comment, outputCity);
    return report;
  }

  // /*
  // * "The top N populated cities in a continent where N is provided by the user"
  // */
  // public static CityReport topNPopulatedCitiesInNContinent(int n) {
  // String selectedContinent = "Asia";

  // ArrayList<Country> countriesInContinent =
  // Country.getCountriesByContinent(selectedContinent);
  // ArrayList<City> allCitiesInSelectedContinent = new ArrayList<City>();

  // ArrayList<String> output = new ArrayList<String>();

  // // loops over every country in selectedContinent to aggregate
  // // allCitiesInSelectedContinent
  // for (int i = 0; i < countriesInContinent.size(); i++) {
  // // current iteration
  // Country currentCountry = countriesInContinent.get(i);

  // ArrayList<City> currentCities =
  // City.getCitiesByCountryCode(currentCountry.Code);
  // allCitiesInSelectedContinent.addAll(currentCities);
  // }

  // // sort
  // Collections.sort(allCitiesInSelectedContinent, new Comparator<City>() {
  // public int compare(City c1, City c2) {
  // return c2.Population - c1.Population;
  // }
  // });

  // // print first n cities
  // for (int i = 0; i < n; i++) {
  // output.add(allCitiesInSelectedContinent.get(i).toString());
  // output
  // }

  // Report report = new Report("The top N populated cities in a continent where N
  // is provided by the user", output);
  // return report;
  // }

  // /*
  // * The top N populated cities in a region where N is provided by the user.
  // */
  // public static Report topNPopulatedCitiesInNRegion(int n) {
  // String selectedRegion = "Eastern Europe";

  // ArrayList<Country> countriesInRegion =
  // Country.getCountriesByRegion(selectedRegion);
  // ArrayList<City> allCitiesInSelectedRegion = new ArrayList<City>();
  // ArrayList<String> output = new ArrayList<String>();

  // // loops over every country in selectedRegion, to aggregate
  // // allCitiesInSelectedRegion

  // for (int i = 0; i < countriesInRegion.size(); i++) {
  // Country currentCountry = countriesInRegion.get(i);

  // ArrayList<City> currentCities =
  // City.getCitiesByCountryCode(currentCountry.Code);
  // allCitiesInSelectedRegion.addAll(currentCities);
  // }

  // // sort
  // Collections.sort(allCitiesInSelectedRegion, new Comparator<City>() {
  // public int compare(City c1, City c2) {
  // return c2.Population - c1.Population;
  // }
  // });

  // // print first n cities
  // for (int i = 0; i < n; i++) {
  // output.add(allCitiesInSelectedRegion.get(i).toString());
  // }

  // Report report = new Report("topNPopulatedCitiesInNRegion", output);
  // return report;
  // }

  // /*
  // * "The top N populated cities in a country where N is provided by the user."
  // */
  // public static Report topNPopulatedCitiesInNCountry(int n) {
  // String selectedCountryCode = "AUT";

  // ArrayList<City> citiesInCode =
  // City.getCitiesByCountryCode(selectedCountryCode);
  // ArrayList<String> output = new ArrayList<String>();

  // // sort
  // Collections.sort(citiesInCode, new Comparator<City>() {
  // public int compare(City c1, City c2) {
  // return c2.Population - c1.Population;
  // }
  // });

  // for (int i = 0; i < n; i++) {
  // City currentCity = citiesInCode.get(i);
  // output.add(currentCity.toString());
  // }

  // Report report = new Report("topNPopulatedCitiesInNCountry", output);
  // return report;
  // }

  // /*
  // * "The top N populated cities in a district where N is provided by the user."
  // */
  // public static Report topNPopulatedCitiesInNDistrict(int n) {
  // String selectedDistrict = "Zhejiang";

  // ArrayList<City> citiesInDistrict =
  // City.getCitiesInDistrict(selectedDistrict);
  // ArrayList<String> output = new ArrayList<String>();

  // // sort
  // Collections.sort(citiesInDistrict, new Comparator<City>() {
  // public int compare(City c1, City c2) {
  // return c2.Population - c1.Population;
  // }
  // });

  // for (int i = 0; i < n; i++) {
  // City currentCity = citiesInDistrict.get(i);
  // output.add(currentCity.toString());
  // }
  // Report report = new Report("topNPopulatedCitiesInNDistrict", output);
  // return report;
  // }
}
