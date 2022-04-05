package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Represents a City
 * Data is fetched from city table in DB
 */
public class City {
  public String Name;
  public String CountryName;
  public String District;
  public int Population;

  /**
   * Creates a new City with the given params.
   * 
   * @param _id          Primary Key
   * @param _name        City name
   * @param _countryCode Country Code
   * @param _district    District of City
   * @param _population  Population
   * @return New City with params
   */
  public City(String _name, String _countryName, String _district, int _population) {
    this.Name = _name;
    this.CountryName = _countryName;
    this.District = _district;
    this.Population = _population;
  }

  @Override
  public String toString() {
    String out = String.format("CityName:%s,CountryName:%s,District:%s,Population:%d", Name, CountryName, District,
        Population);
    return out;
  }

  /**
   * Goes through every row in city DB table,
   * creates a new City class with row contents,
   * append to a ArrayList to return.
   * 
   * @return ArrayList of all cities in DB table.
   */
  public static ArrayList<City> getCities() {
    // create an initial connection to db
    Connection con = Database.dbConnect();

    // append all new cities to this list,
    // return this
    ArrayList<City> allCities = new ArrayList<City>();

    try {
      Statement sqlStatement = con.createStatement();
      ResultSet sqlReturn;

      // Run this query on DB, return values will be held in sqlReturn
      sqlReturn = sqlStatement.executeQuery(
          "SELECT city.Name as 'cityName'," +
              "country.Name as 'countryName'," +
              "city.District as 'cityDistrict'," +
              "city.Population as 'cityPopulation'" +
              " FROM city" +
              " LEFT JOIN country ON city.CountryCode = country.Code;");

      // iterate through all returned rows
      while (sqlReturn.next()) {
        // Get all the vars from the current iteration
        String cityName = sqlReturn.getString("cityName");
        String countryName = sqlReturn.getString("countryName");
        String district = sqlReturn.getString("cityDistrict");
        int population = sqlReturn.getInt("cityPopulation");

        // create a new City class with these vars
        City toAdd = new City(cityName, countryName, district, population);
        // append new City into output list
        allCities.add(toAdd);
      }
      // finished, close connection
      con.close();
    } catch (Exception e) {
      // unable to connect to db
      System.out.println(e.getMessage());
    }
    return allCities;
  }

  /**
   * Returns all cities with countryName
   * 
   * @param countryName Found in DB, ex: "China"
   * @return ArrayList with cities only in countryName
   */
  public static ArrayList<City> getCitiesByCountryName(String countryName) {
    // Create ArrayList to hold output
    ArrayList<City> citiesInCode = new ArrayList<City>();

    // Get every city using getCities()
    ArrayList<City> allCities = getCities();

    // loop over every city
    for (int i = 0; i < allCities.size(); i++) {
      // current iteration
      City currentCity = allCities.get(i);

      // check if the parameter countryCode matches current iteration
      if (currentCity.CountryName.equalsIgnoreCase(countryName)) {
        // matches! Add to output ArrayList
        citiesInCode.add(currentCity);
      }
    }
    return citiesInCode;
  }

  /**
   * Returns all cities with district
   * 
   * @param district Found in DB, ex: "Victoria"
   * @return ArrayList with cities only in district
   */
  public static ArrayList<City> getCitiesByDistrict(String district) {
    // Create ArrayList to hold output
    ArrayList<City> citiesInDistrict = new ArrayList<City>();

    // Get every city using getCities()
    ArrayList<City> allCities = getCities();

    // loop over every city
    for (int i = 0; i < allCities.size(); i++) {
      // current iteration
      City currentCity = allCities.get(i);

      // check if the parameter district matches current iteration
      if (currentCity.District.equalsIgnoreCase(district)) {
        // matches! Add to output ArrayList
        citiesInDistrict.add(currentCity);
      }
    }
    return citiesInDistrict;
  }
}
