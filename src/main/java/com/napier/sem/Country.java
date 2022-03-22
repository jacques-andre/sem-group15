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

  /**
   * Creates a new Country with the given params.
   * 
   * @param _code       Primary Key
   * @param _name       Country name
   * @param _continent  Continent
   * @param _population Population
   * @param _region     Region
   * @return New Country with params
   */
  public Country(String _code, String _name, String _continent, int _population, String _region) {
    this.Code = _code;
    this.Name = _name;
    this.Continent = _continent;
    this.Population = _population;
    this.Region = _region;
  }

  @Override
  public String toString() {
    String out = String.format("Name:%s,Code:%s,Continent:%s,Population:%s", Name, Code, Continent, Population);
    return out;
  }

  /**
   * Goes through every row in country DB table,
   * creates a new Country class with row contents,
   * append to a ArrayList to return.
   * 
   * @return ArrayList of all countries in DB table.
   */
  public static ArrayList<Country> getCountries() {
    // create an intial connection to db
    Connection con = Database.dbConnect();

    // this will hold all countries
    ArrayList<Country> countries = new ArrayList<Country>();

    try {
      Statement sqlStatement = con.createStatement();
      ResultSet sqlReturn;

      // run sql on db
      sqlReturn = sqlStatement.executeQuery(
          "SELECT Code,Name,Continent,Population,Region FROM country;");

      // itterate through all rows
      while (sqlReturn.next()) {
        String countryName = sqlReturn.getString("Name");
        String code = sqlReturn.getString("Code");
        String continentName = sqlReturn.getString("Continent");
        String regionName = sqlReturn.getString("Region");
        int population = sqlReturn.getInt("Population");

        // create new country with these vars
        Country toAdd = new Country(code, countryName, continentName, population, regionName);
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
    ArrayList<Country> allCountries = getCountries();
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
    ArrayList<Country> allCountries = getCountries();
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
