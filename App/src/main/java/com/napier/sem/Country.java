package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Country {
    public String Code;
    // public String Code2;
    public String Name;
    public String Continent; // eventually make this link to continent class;
    // public String Region;
    // public float SurfaceArea;
    // public int IndepYear;
    public int Population;
    // public float LifeExpectancy;
    // public float GNP;
    // public float GNPOld;
    // public String LocalName;
    // public String GovermentForm;
    // public String HeadOfState;
    // public String Capital; //link to city class

    public Country(String _code, String _name, String _continent, int _population) {
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
                // extract string, append to list
                String countryName = rs.getString("Name");
                String code = rs.getString("Code");
                String continent = rs.getString("Continent");
                int population = rs.getInt("Population");

                Country toAdd = new Country(code, countryName, continent, population);
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
}