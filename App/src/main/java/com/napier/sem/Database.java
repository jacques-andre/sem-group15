package com.napier.sem;

// sql imports
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
  // constants
  private static String DB_URL = "jdbc:mysql://localhost:3306/world"; // using docker contianer name as host
  private static String DB_USERNAME = "root";
  private static String DB_PASSWORD = "password";

  /**
   * Attempt to connect to the docker mysql db
   * 
   * @return connection to interact with db
   */
  public static Connection dbConnect() {
    Connection con = null;
    int tries = 0;

    // Takes some time for db to be created with inital tables
    // keep trying to get a connection to db
    while (con == null || tries < 10) {
      try {
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        tries++;
      } catch (Exception e) {
        con = null;
        try {
          // get slower each try
          Thread.sleep(5000 * tries);
        } catch (Exception f) {
          System.out.println(f.getMessage());
        }
      }
    }
    return con;
  }
}
