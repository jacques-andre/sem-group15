package com.napier.sem;

// sql imports
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database setups the connection to the mysql DB
 */
public class Database {
  // constants
  private static String DB_HOST = System.getenv("DB_HOST"); // get env from docker
  private static String DB_URL = String.format("jdbc:mysql://%s:3306/world", DB_HOST);

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
