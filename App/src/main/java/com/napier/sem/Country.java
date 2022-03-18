package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Country {
    public String Code;
    public String Name;
    public String Continent; 
    public int Population;
    public String Region;


    // public String Code2;
    // public String Region;
    // public float SurfaceArea;
    // public int IndepYear;
    // public float LifeExpectancy;
    // public float GNP;
    // public float GNPOld;
    // public String LocalName;
    // public String GovermentForm;
    // public String HeadOfState;
    // public String Capital; //link to city class

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

    public static List<Country> getCountries() {
        // easier to read in docker
        System.out.println("Called methood: getCountries");

        // create an intial connection to db
        Connection con = Database.dbConnect();

        // this will hold all countries
        ArrayList<Country> countries = new ArrayList<Country>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs;

            // run sql on db
            rs = stmt.executeQuery(
                    "SELECT Code,Name,Continent,Population,Region FROM country ORDER BY Population DESC;");

            // itterate through all rows
            while (rs.next()) {
                String countryName = rs.getString("Name");
                String code = rs.getString("Code");
                String continentName = rs.getString("Continent");
                String regionName = rs.getString("Region");
                int population = rs.getInt("Population");

                Country toAdd = new Country(code, countryName, continentName, population, regionName);
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
    public static List<Country> getCountriesByContinent(String continent){
        // easier to read in docker
        System.out.println("Called methood: getCountriesByContinent");

        // holds output 
        ArrayList<Country> countriesInContinent = new ArrayList<Country>();
        List<Country> allCountries = getCountries();

        for (int i = 0; i < allCountries.size(); i++) {
          if (allCountries.get(i).Continent.equalsIgnoreCase(continent)){
            countriesInContinent.add(allCountries.get(i));
          }
        }

        return countriesInContinent;
    }
    public static List<Country> getCountriesByRegion(String region){
        // easier to read in docker
        System.out.println("Called methood: getCountriesByRegion");

        // holds output 
        ArrayList<Country> countriesInRegion = new ArrayList<Country>();
        List<Country> allCountries = getCountries();

        for (int i = 0; i < allCountries.size(); i++) {
          if (allCountries.get(i).Region.equalsIgnoreCase(region)){
            countriesInRegion.add(allCountries.get(i));
          }
        }

        return countriesInRegion;
    }
}
