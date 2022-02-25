package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class App {
  /**
   * Returns a string list of countries ordered largest to smallest
   * @return list<String> country names
   */
  public static List<String> largestPopulationToSmallest() {
    // easier to read in docker
    System.out.println("Called methood: largestPopulationToSmallest");

    // create an intial connection to db
    Connection con = Database.dbConnect();

    // this will hold all names
    ArrayList<String> countriesLargestToSmallest = new ArrayList<String>();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs;

      // run sql on db
      rs = stmt.executeQuery(
          "SELECT Name FROM country ORDER BY Population DESC;");

      // itterate through all rows
      while (rs.next()) {
        // extract string, append to list
        String countryName = rs.getString("Name");
        countriesLargestToSmallest.add(countryName);
      }
      // finished, close connection
      con.close();
    } catch (Exception e) {
      // unable to connect to db
      System.out.println(e.getMessage());
    }

    // return list
    return countriesLargestToSmallest;
  }

  public static void main(String[] args) {
    List<String> countryNames = largestPopulationToSmallest();

    for (int i = 0; i < countryNames.size(); i++){
      System.out.println(String.format("pos:%d,Country:%s", i, countryNames.get(i)));
    }
    System.out.println(String.format("Total: %s", countryNames.size()));
  }
}
