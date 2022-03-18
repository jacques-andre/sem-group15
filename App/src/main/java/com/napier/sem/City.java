package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class City {
    public int Id;
    public String Name;
    public String CountryCode; 
    public String District;
    public int Population;

    public City(int _id, String _name, String _countryCode, String _district, int _population) {
      this.Id = _id;
      this.Name = _name;
      this.CountryCode = _countryCode;
      this.District = _district;
      this.Population = _population; 
    }
    public static List<City> getCities() {
        // easier to read in docker
        System.out.println("Called methood: getCities");

        // create an intial connection to db
        Connection con = Database.dbConnect();

        // this will hold all countries
        ArrayList<City> allCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs;

            // run sql on db
            rs = stmt.executeQuery(
                    "SELECT ID,Name,CountryCode,District,Population FROM city ORDER BY Population DESC;");

            // itterate through all rows
            while (rs.next()) {
                int id = rs.getInt("ID");
                String cityName = rs.getString("Name");
                String countryCode = rs.getString("countryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");


                City toAdd = new City(id,cityName,countryCode,district,population);
                allCities.add(toAdd);
            }
            // finished, close connection
            con.close();
        } catch (Exception e) {
            // unable to connect to db
            System.out.println(e.getMessage());
        }

        // return list
        return allCities;
    }
    public static List<City> getCitiesByCountryCode(String countryCode){
        // easier to read in docker
        System.out.println("Called methood: getCitiesByCountryCode");

        // holds output 
        List<City> citiesInCode = new ArrayList<City>();
        List<City> allCities = getCities();

        for (int i = 0; i < allCities.size(); i++) {
          // current iteration
          City currentCity = allCities.get(i);
          if(currentCity.CountryCode.equalsIgnoreCase(countryCode)){
            citiesInCode.add(currentCity);
          }
        }

        return citiesInCode;
    }
}
