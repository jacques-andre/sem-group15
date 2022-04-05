package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Represents a Country
 * Data is fetched from country table in DB
 */
public class Country {
  public String Code; // Primary key from DB ex: ABW
  public String Name; // Country name
  public String Continent;
  public int Population;
  public String Region;
  public City Capital;

  /**
   * Creates a new Country with the given params.
   * 
   * @param _code       Primary Key
   * @param _name       Country name
   * @param _continent  Continent
   * @param _population Population
   * @param _region     Region
   * @param _capital    Capital city
   * @return New Country with params
   */
  public Country(String _code, String _name, String _continent, int _population, String _region, City _capital) {
    this.Code = _code;
    this.Name = _name;
    this.Continent = _continent;
    this.Population = _population;
    this.Region = _region;
    this.Capital = _capital;
  }

  @Override
  public String toString() {
    String out = String.format("Code:%s,Name:%s,Continent:%s,Population:%d,Region:%s,Captial:%s", Code, Name, Continent,
        Population, Region, Capital.Name);
    return out;
  }

  /**
   * Goes through every row in country DB table,
   * creates a new Country class with row contents,
   * append to a ArrayList to return.
   * 
   * @return ArrayList of all countries in DB table.
   */
  public static ArrayList<Country> getAllCountries() {
    // create an intial connection to db
    Connection con = Database.dbConnect();

    // this will hold all countries
    ArrayList<Country> countries = new ArrayList<Country>();

    try {
      Statement sqlStatement = con.createStatement();
      ResultSet sqlReturn;

      // run sql on db
      sqlReturn = sqlStatement.executeQuery(
          "SELECT country.Name as 'countryName'," +
              "country.Code as 'countryCode'," +
              "country.Continent as 'countryContinent'," +
              "country.Region as 'countryRegion'," +
              "country.Population as 'countryPopulation'," +
              "city.ID as 'cityID'," +
              "city.Name as 'cityName'," +
              "city.CountryCode as 'cityCountryCode'," +
              "city.District as 'cityDistrict'," +
              "city.Population as 'cityPopulation'" +
              "FROM country " +
              "INNER JOIN city ON city.ID = country.Capital;");

      // itterate through all rows
      while (sqlReturn.next()) {
        String countryName = sqlReturn.getString("countryName");
        String countryCode = sqlReturn.getString("countryCode");
        String countryContinentName = sqlReturn.getString("countryContinent");
        String countryRegionName = sqlReturn.getString("countryRegion");
        int countryPopulation = sqlReturn.getInt("countryPopulation");

        String cityName = sqlReturn.getString("cityName");
        String cityDistrict = sqlReturn.getString("cityDistrict");
        int cityPopulation = sqlReturn.getInt("cityPopulation");

        City capitalCity = new City(cityName, countryName, cityDistrict, cityPopulation);

        // create new country with these vars
        Country toAdd = new Country(countryCode, countryName, countryContinentName, countryPopulation,
            countryRegionName, capitalCity);
        // append to output list
        countries.add(toAdd);
      }
      // finished, close connection
      con.close();
    } catch (Exception e) {
      // unable to connect to db
      System.out.println(e.getMessage());
    }

    // return list
    return countries;
  }

  /**
   * Returns all countries in param continent
   * 
   * @param continent String value of continent ex: "Asia"
   * @return ArrayList of all countries in continent
   */
  public static ArrayList<Country> getCountriesByContinent(String continent) {
    // get all countries
    ArrayList<Country> allCountries = getAllCountries();
    // holds output
    ArrayList<Country> countriesInContinent = new ArrayList<Country>();

    // go through all countries, check if current Continent matches param
    for (Country currentCountry : allCountries) {
      if (currentCountry.Continent.equalsIgnoreCase(continent)) {
        countriesInContinent.add(currentCountry);
      }
    }

    return countriesInContinent;
  }

  /**
   * Returns all countries in param region
   * 
   * @param region String value of region ex: "Caribbean"
   * @return ArrayList of all countries in region
   */
  public static ArrayList<Country> getCountriesByRegion(String region) {
    // get all countries
    ArrayList<Country> allCountries = getAllCountries();
    // holds output
    ArrayList<Country> countriesInRegion = new ArrayList<Country>();

    // go through all countries, check if current Region matches param
    for (Country currentCountry : allCountries) {
      if (currentCountry.Region.equalsIgnoreCase(region)) {
        countriesInRegion.add(currentCountry);
      }
    }

    return countriesInRegion;
  }
}
