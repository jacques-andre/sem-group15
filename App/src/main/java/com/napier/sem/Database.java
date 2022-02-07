package com.napier.sem;

import java.util.*;
// sql imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database{
  private static String dbUrl = "jdbc:mysql://db:3306/world"; // using docker contianer name as host
  private static String dbUserName = "root";
  private static String dbPassword = "password";

  public static Connection dbConnect(){
    Connection con = null;
    int tries = 0;

    // Takes some time for db to be created with inital tables
    // keep trying to get a connection to db
    while(con == null || tries < 10){
      try{
        con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        tries++;
      }
      catch (Exception e){
        con = null;
        try{
          Thread.sleep(5000);
        } catch (Exception f){
          System.out.println(f.getMessage());
        }
      }
    }
    return con;
  }
  public static List<String> getCityNames(){
    Connection con = dbConnect();
    List<String> cityNames = new ArrayList<String>();
    try {
      Statement stmt = con.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery("SELECT Name FROM city");
      while ( rs.next() ) {
        String cityName = rs.getString("Name");
        cityNames.add(cityName);
      }
      con.close();
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
    return cityNames;
  }
}
