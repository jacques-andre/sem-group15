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
  public int Id; // Primary Key from DB ex: 1
  public String Name;
  public String CountryCode;
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
  public City(int _id, String _name, String _countryCode, String _district, int _population) {
    this.Id = _id;
    this.Name = _name;
    this.CountryCode = _countryCode;
    this.District = _district;
    this.Population = _population;
  }

  @Override
  public String toString() {
    String out = String.format("ID:%d,Name:%s,CountryCode:%s,District:%s,Population:%d", Id, Name, CountryCode,
        District, Population);
    return out;
  }

  /**
   * Goes through every row in city DB table,
   * creates a new City class with row contents,
   * append to a list to return.
   * 
   * @return A list of all cities in DB table.
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
          "SELECT ID,Name,CountryCode,District,Population FROM city");

      // iterate through all returned rows
      while (sqlReturn.next()) {
        // Get all the vars from the current iteration
        int id = sqlReturn.getInt("ID");
        String cityName = sqlReturn.getString("Name");
        String countryCode = sqlReturn.getString("countryCode");
        String district = sqlReturn.getString("District");
        int population = sqlReturn.getInt("Population");

        // create a new City class with these vars
        City toAdd = new City(id, cityName, countryCode, district, population);
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

  public static List<City> getCitiesByCountryCode(String countryCode) {
    // easier to read in docker
    System.out.println("Called methood: getCitiesByCountryCode");

    // holds output
    List<City> citiesInCode = new ArrayList<City>();
    List<City> allCities = getCities();

    for (int i = 0; i < allCities.size(); i++) {
      // current iteration
      City currentCity = allCities.get(i);
      if (currentCity.CountryCode.equalsIgnoreCase(countryCode)) {
        citiesInCode.add(currentCity);
      }
    }

    return citiesInCode;
  }
}
