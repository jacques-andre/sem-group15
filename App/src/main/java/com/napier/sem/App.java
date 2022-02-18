package com.napier.sem;

import java.util.*;

public class App {
  public static void main( String[] args ){
    List<String> countryNames = Database.largestPopulationToSmallest();

    for(String country : countryNames){
      System.out.println(String.format("Country:%s", country));
    }
    System.out.println(String.format("Total: %s", countryNames.size()));
  }
}

