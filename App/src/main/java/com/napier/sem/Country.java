package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Country {
    public String Code;
    public String Name;
    public Continent Continent; 
    public int Population;

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

    public Country(String _code, String _name, Continent _continent, int _population) {
        this.Code = _code;
        this.Name = _name;
        this.Continent = _continent;
        this.Population = _population;
    }

    @Override
    public String toString() {
        String out = String.format("Name:%s,Code:%s,Continent:%s,Population:%s", Name, Code, Continent, Population);
        return out;
    }

    public int getPopulation(){
      return this.Population;
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
                    "SELECT Code,Name,Continent,Population FROM country ORDER BY Population DESC;");

            // itterate through all rows
            while (rs.next()) {
                String countryName = rs.getString("Name");
                String code = rs.getString("Code");
                String continentName = rs.getString("Continent");
                int population = rs.getInt("Population");

                Continent continentToAdd = new Continent(continentName);

                Country toAdd = new Country(code, countryName, continentToAdd, population);
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
    public static List<Country> getCountriesByContinent(Continent continent){
        // easier to read in docker
        System.out.println("Called methood: getCountriesByContinent");

        // create an intial connection to db
        Connection con = Database.dbConnect();

        // this will hold all countries in continent
        ArrayList<Country> countriesInContinent = new ArrayList<Country>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs;

            // run sql on db
            rs = stmt.executeQuery(
                "SELECT Name,Continent,Code,Population FROM country WHERE Continent='" + continent.Name + "'");

            // itterate through all rows
            while (rs.next()) {
                String countryName = rs.getString("Name");
                String continentName = rs.getString("Continent");
                String code = rs.getString("Code");
                int population = rs.getInt("Population");

                Continent newContinent = new Continent(continentName);

                Country toAdd = new Country(code, countryName, newContinent, population);
                countriesInContinent.add(toAdd);
            }
            // finished, close connection
            con.close();
        } catch (Exception e) {
            // unable to connect to db
            System.out.println(e.getMessage());
        }

        // return list
        return countriesInContinent;
    }
}
