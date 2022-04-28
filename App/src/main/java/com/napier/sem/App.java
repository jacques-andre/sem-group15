package com.napier.sem;

import java.util.*;

import com.napier.sem.Reports.CityReport;
import com.napier.sem.Reports.CountryReport;
import com.napier.sem.Reports.PopulationReport;
import com.napier.sem.Reports.Report;

public class App {
  public static void main(String[] args) {
    System.out.println(
        String.format("DB_HOST:%s", System.getenv("DB_HOST")));

    ArrayList<Report> allReports = new ArrayList<Report>();
    ArrayList<CountryReport> countryReports = new ArrayList<CountryReport>();
    ArrayList<CityReport> cityReports = new ArrayList<CityReport>();
    ArrayList<PopulationReport> populationReports = new ArrayList<PopulationReport>();

    countryReports.add(countryLargestPopulationToSmallest());
    countryReports.add(countryContinentOrgnaisedLargestSmallest("Africa"));
    countryReports.add(countryRegionOrgnaisedLargestSmallest("Caribbean"));
    countryReports.add(topNPopulatedCountriesInWorld(5));
    countryReports.add(topNPopulatedContinentCountries(5, "Africa"));
    countryReports.add(topNPopulatedRegionCountries(5, "Eastern Europe"));

    cityReports.add(citiesLargestPopulationToSmallest());
    cityReports.add(citiesInContinentLargestPopulationToSmallest("Africa"));
    cityReports.add(citiesInRegionLargestPopulationToSmallest("Caribbean"));
    cityReports.add(citiesInCountryLargestPopulationToSmallest("Australia"));
    cityReports.add(citiesInDistrictLargestPopulationToSmallest("Shandong"));

    cityReports.add(topNPopulatedCitiesInWorld(5));
    cityReports.add(topNPopulatedCitiesInNContinent(5, "Asia"));
    cityReports.add(topNPopulatedCitiesInNRegion(5, "Caribbean"));
    cityReports.add(topNPopulatedCitiesInNCountry(5, "France"));
    cityReports.add(topNPopulatedCitiesInNDistrict(5, "Xinxiang"));

    cityReports.add(capitalCitiesLargestPopulationToSmallest());
    cityReports.add(capitalCitiesInContinentLargestPopulationToSmallest("Africa"));
    cityReports.add(capitalCitiesInRegionLargestPopulationToSmallest("Caribbean"));

    cityReports.add(topNPopulatedCapitalCities(5));
    cityReports.add(topNPopulatedCapitalCitiesInContinent(5, "Africa"));
    cityReports.add(topNPopulatedCitiesInNRegion(5, "Caribbean"));
    
    populationReports.add(populationOfWorld());
    populationReports.add(populationOfContinent("Africa"));
    populationReports.add(populationOfRegion("Western Europe"));
    populationReports.add(populationOfCountry("Egypt"));
    populationReports.add(populatonOfDistrict("Buenos Aires"));
    populationReports.add(populationOfCity("Oxford"));

    allReports.addAll(countryReports);
    allReports.addAll(cityReports);
    allReports.addAll(populationReports);

    for (Report r : allReports) {
      r.outputReport();
      System.out.println("----");
    }

    Report.toHMTL(allReports, countryReports, cityReports, populationReports);
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

    // Go through all countries (sorted) append to output
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

    // Go through all countries (sorted) append to output
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

    // Go through all countries (sorted) append to output
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

    // Go through all countries (sorted) append to output
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

    // Go through all countries (sorted) append to output
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

    // Go through all countries (sorted) append to output
    for (City c : allCities) {
      outputStr.add(c.toString());
      outputCity.add(c);
    }

    CityReport report = new CityReport("All the cities in the world organised by largest population to smallest.",
        outputStr, "", outputCity);
    return report;
  }

  /*
   * "All the cities in a continent organised by largest population to smallest."
   */
  public static CityReport citiesInContinentLargestPopulationToSmallest(String continent) {
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through every country in continent, append cities of that country
    for (Country continentCountry : countriesInContinent) {
      outputCities.addAll(City.getCitiesByCountryName(continentCountry.Name));
    }
    // Go through every city in continent, append str to outputStr
    for (City continentCity : outputCities) {
      outputStr.add(continentCity.toString());
    }

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    String comment = String.format("Using %s as continent", continent);

    CityReport report = new CityReport("All the cities in a continent organised by largest population to smallest.",
        outputStr, comment, outputCities);
    return report;
  }

  /*
   * "All the cities in a region organised by largest population to smallest."
   */
  public static CityReport citiesInRegionLargestPopulationToSmallest(String region) {
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through every country in region, append cities of that country
    for (Country regionCountry : countriesInRegion) {
      outputCities.addAll(City.getCitiesByCountryName(regionCountry.Name));
    }
    // Go through every city in region, append str to outputStr
    for (City regionCity : outputCities) {
      outputStr.add(regionCity.toString());
    }

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    String comment = String.format("Using %s as region", region);

    CityReport report = new CityReport("All the cities in a region organised by largest population to smallest.",
        outputStr, comment, outputCities);
    return report;
  }

  /*
   * "All the cities in a country organised by largest population to smallest."
   */
  public static CityReport citiesInCountryLargestPopulationToSmallest(String country) {
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = City.getCitiesByCountryName(country);

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });
    // Go through every city in country, append str to outputStr
    for (City countryCity : outputCities) {
      outputStr.add(countryCity.toString());
    }

    String comment = String.format("Using %s as country", country);

    CityReport report = new CityReport("All the cities in a country organised by largest population to smallest.",
        outputStr, comment, outputCities);
    return report;
  }

  /*
   * "All the cities in a district organised by largest population to smallest."
   */
  public static CityReport citiesInDistrictLargestPopulationToSmallest(String district) {
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = City.getCitiesByDistrict(district);

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });
    // Go through every city in district, append str to outputStr
    for (City districtCity : outputCities) {
      outputStr.add(districtCity.toString());
    }

    String comment = String.format("Using %s as district", district);

    CityReport report = new CityReport("All the cities in a district organised by largest population to smallest.",
        outputStr, comment, outputCities);
    return report;
  }

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

    // Go through all cities (sorted) append to output
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

  /*
   * "The top N populated cities in a continent where N is provided by the user"
   */
  public static CityReport topNPopulatedCitiesInNContinent(int n, String continent) {
    // Get all countries in continent
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);

    // Get all cities from those countries
    ArrayList<City> citiesInContinent = new ArrayList<City>();

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through every country in continent, append cities of that country
    for (Country continentCountry : countriesInContinent) {
      citiesInContinent.addAll(City.getCitiesByCountryName(continentCountry.Name));
    }

    // sort citiesInContinent by population
    Collections.sort(citiesInContinent, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through citiesInContinent (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = citiesInContinent.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }

    String comment = String.format("Using %d as N, Using %s as continent", n, continent);

    CityReport report = new CityReport("The top N populated cities in a continent where N is provided by the user",
        outputStr, comment, outputCities);
    return report;
  }

  /*
   * "The top N populated cities in a region where N is provided by the user."
   */
  public static CityReport topNPopulatedCitiesInNRegion(int n, String region) {
    // Get countries in region
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    ArrayList<City> citiesInRegion = new ArrayList<City>();

    ArrayList<City> outputCities = new ArrayList<City>();
    ArrayList<String> outputStr = new ArrayList<String>();

    // Go through every country in region, append cities of that country
    for (Country regionCountry : countriesInRegion) {
      citiesInRegion.addAll(City.getCitiesByCountryName(regionCountry.Name));
    }

    // sort citiesInRegion by population
    Collections.sort(citiesInRegion, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through citiesInRegion (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = citiesInRegion.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }

    String comment = String.format("Using %d as N, Using %s as region", n, region);

    CityReport report = new CityReport("The top N populated cities in a region where N is provided by the user.",
        outputStr, comment, outputCities);
    return report;
  }

  /*
   * "The top N populated cities in a country where N is provided by the user."
   */
  public static CityReport topNPopulatedCitiesInNCountry(int n, String country) {
    // Get cities in country
    ArrayList<City> citiesInCountry = City.getCitiesByCountryName(country);

    ArrayList<City> outputCities = new ArrayList<City>();
    ArrayList<String> outputStr = new ArrayList<String>();

    // sort citiesInRegion by population
    Collections.sort(citiesInCountry, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through citiesInCountry (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = citiesInCountry.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }

    String comment = String.format("Using %d as N, Using %s as country", n, country);

    CityReport report = new CityReport("The top N populated cities in a country where N is provided by the user.",
        outputStr, comment, outputCities);
    return report;
  }

  /*
   * "The top N populated cities in a district where N is provided by the user."
   */
  public static CityReport topNPopulatedCitiesInNDistrict(int n, String district) {
    ArrayList<City> citiesInDistrict = City.getCitiesByDistrict(district);
    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // sort citiesInDistrict by population
    Collections.sort(citiesInDistrict, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through citiesInDistrict (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = citiesInDistrict.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }
    String comment = String.format("Using %d as N, Using %s as district", n, district);

    CityReport report = new CityReport("The top N populated cities in a district where N is provided by the user.",
        outputStr, comment, outputCities);

    return report;
  }

  /*
   * "All the capital cities in the world organised by largest population to smallest."
   */
  public static CityReport capitalCitiesLargestPopulationToSmallest() {
    ArrayList<Country> allCountries = Country.getAllCountries();

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through all countries, append capitals to outputCities
    for (Country c : allCountries) {
      outputCities.add(c.Capital);
    }

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through every capital city in outputCities, append to outputStr
    for (City capitalCity : outputCities) {
      outputStr.add(capitalCity.toString());
    }

    CityReport report = new CityReport(
        "All the capital cities in the world organised by largest population to smalles.",
        outputStr, "", outputCities);

    return report;
  }

  /*
   * "All the capital cities in a continent organised by largest population to smallest."
   */
  public static CityReport capitalCitiesInContinentLargestPopulationToSmallest(String continent) {
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through all countries in continent, append capitals to outputCities
    for (Country c : countriesInContinent) {
      outputCities.add(c.Capital);
    }

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });
    // Go through every capital city in outputCities, append to outputStr
    for (City capitalCity : outputCities) {
      outputStr.add(capitalCity.toString());
    }

    String comment = String.format("Using %s as continent", continent);

    CityReport report = new CityReport(
        "All the capital cities in a continent organised by largest population to smallest.",
        outputStr, comment, outputCities);

    return report;
  }

  /*
   * "All the capital cities in a region organised by largest to smallest."
   */
  public static CityReport capitalCitiesInRegionLargestPopulationToSmallest(String region) {
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through all countries in region, append capitals to outputCities
    for (Country c : countriesInRegion) {
      outputCities.add(c.Capital);
    }

    // sort outputCities by population
    Collections.sort(outputCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through every capital city in outputCities, append to outputStr
    for (City capitalCity : outputCities) {
      outputStr.add(capitalCity.toString());
    }

    String commnet = String.format("Using %s as region", region);

    CityReport report = new CityReport(
        "All the capital cities in a region organised by largest to smallest.",
        outputStr, commnet, outputCities);

    return report;
  }

  /*
   * "The top N populated capital cities in the world where N is provided by the user."
   */
  public static CityReport topNPopulatedCapitalCities(int n) {
    ArrayList<Country> allCountries = Country.getAllCountries();
    ArrayList<City> capitalCities = new ArrayList<City>();

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through all countries, append capitals to capitalCities
    for (Country c : allCountries) {
      capitalCities.add(c.Capital);
    }

    // sort capitalCities by population
    Collections.sort(capitalCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through capitalCities (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = capitalCities.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }

    String comment = String.format("Using %d as N", n);

    CityReport report = new CityReport(
        "The top N populated capital cities in the world where N is provided by the user.",
        outputStr, comment, outputCities);

    return report;
  }
  /*
   * "The top N populated capital cities in a continent where N is provided by the user."
   */
  public static CityReport topNPopulatedCapitalCitiesInContinent(int n, String continent) {
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);
    ArrayList<City> capitalCities = new ArrayList<City>();

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through all countries in continent, append capitals to capitalCities
    for (Country c : countriesInContinent) {
      capitalCities.add(c.Capital);
    }

    // sort capitalCities by population
    Collections.sort(capitalCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through capitalCities (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = capitalCities.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }

    String comment = String.format("Using %d as N, Using %s as continent", n, continent);

    CityReport report = new CityReport(
        "The top N populated capital cities in a continent where N is provided by the user.",
        outputStr, comment, outputCities);

    return report;
  }
  /*
   * "The top N populated capital cities in a region where N is provided by the user."
   */
  public static CityReport topNPopulatedCapitalCitiesInRegion(int n, String region) {
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    ArrayList<City> capitalCities = new ArrayList<City>();

    ArrayList<String> outputStr = new ArrayList<String>();
    ArrayList<City> outputCities = new ArrayList<City>();

    // Go through all countries in region, append capitals to capitalCities
    for (Country c : countriesInRegion) {
      capitalCities.add(c.Capital);
    }

    // sort capitalCities by population
    Collections.sort(capitalCities, new Comparator<City>() {
      public int compare(City c1, City c2) {
        return c2.Population - c1.Population;
      }
    });

    // Go through capitalCities (sorted) append to output
    for (int i = 0; i < n; i++) {
      City currentCity = capitalCities.get(i);
      outputStr.add(currentCity.toString());
      outputCities.add(currentCity);
    }

    String comment = String.format("Using %d as N, Using %s as region", n, region);

    CityReport report = new CityReport(
        "The top N populated capital cities in a region where N is provided by the user.",
        outputStr, comment, outputCities);

    return report;
  }
  /*
   * "The population of the world."
   */
  public static PopulationReport populationOfWorld(){
    ArrayList<Country> allCountries = Country.getAllCountries();
    int worldPopulation = 0;

    for(Country c : allCountries){
      worldPopulation += c.Population;
    }

    PopulationReport report = new PopulationReport("The population of the world", new ArrayList<String>(), "", worldPopulation);

    return report;
  }
  /*
   * "The population of a continent."
   */
  public static PopulationReport populationOfContinent(String continent){
    ArrayList<Country> countriesInContinent = Country.getCountriesByContinent(continent);
    int continentPopulation = 0;

    for(Country c : countriesInContinent){
      continentPopulation += c.Population;
    }

    String comment = String.format("Using %s as continent", continent);

    PopulationReport report = new PopulationReport("The population of a continent", new ArrayList<String>(), comment, continentPopulation);

    return report;
  }
  /*
   * "The population of a region."
   */
  public static PopulationReport populationOfRegion(String region){
    ArrayList<Country> countriesInRegion = Country.getCountriesByRegion(region);
    int regionPopulation = 0;

    for(Country c : countriesInRegion){
      regionPopulation += c.Population;
    }

    String comment = String.format("Using %s as region", region);

    PopulationReport report = new PopulationReport("The population of a region", new ArrayList<String>(), comment, regionPopulation);

    return report;
  }
  /*
   * "The population of a country"
   */
  public static PopulationReport populationOfCountry(String country){
    ArrayList<Country> allCountries = Country.getAllCountries();
    int countryPopulation = 0;

    for(Country c : allCountries){
      if(c.Name.equals(country)){
        countryPopulation += c.Population;
      }
    }

    String comment = String.format("Using %s as country", country);

    PopulationReport report = new PopulationReport("The population of a country", new ArrayList<String>(), comment, countryPopulation);

    return report;
  }
  /*
   * "The population of a district"
   */
  public static PopulationReport populatonOfDistrict(String district){
    ArrayList<City> citiesInDistrict = City.getCitiesByDistrict(district);
    int districtPopulation = 0;

    for(City c : citiesInDistrict){
        districtPopulation += c.Population;
    }

    String comment = String.format("Using %s as district", district);

    PopulationReport report = new PopulationReport("The population of a district", new ArrayList<String>(), comment, districtPopulation);

    return report;
  }
  /*
   * "The population of a city"
   */
  public static PopulationReport populationOfCity(String city){
    ArrayList<City> allCities = City.getCities();
    int cityPopulation = 0;

    for(City c: allCities){
      if(c.Name.equals(city)){
        cityPopulation += c.Population;
      }
    }

    String comment = String.format("Using %s as city", city);

    PopulationReport report = new PopulationReport("The population of a city", new ArrayList<String>(), comment, cityPopulation);

    return report;
  }
}
