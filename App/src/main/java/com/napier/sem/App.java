package com.napier.sem;

import java.util.*;

public class App {
  public static void main( String[] args ){
    List<String> countryNames = Database.largestPopulationToSmallest();

    for(String country : countryNames){
      System.out.println(String.format("Country:%s", country));
    }
    System.out.println(String.format("Total: %s", countryNames.size()));

    List<String> continentNames = Database.continentDescending();

    for(String continent : continentNames){
      System.out.println(String.format("Continent:%s", continent));
    }
    System.out.println(String.format("Total: %s", continentNames.size()));
  }
}

