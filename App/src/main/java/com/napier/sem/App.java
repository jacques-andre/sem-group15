package com.napier.sem;

import java.util.*;

public class App {
  public static void main( String[] args ){
    List<String> cityNames = Database.getCityNames();

    for(String city : cityNames){
      System.out.println(String.format("City: %s", city));
    }
    System.out.println(String.format("Total: %s", cityNames.size()));
  }
}
