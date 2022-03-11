package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Continent {
    public String Name;

    public Continent(String _name) {
        this.Name = _name;
    }

    @Override
    public String toString() {
        String out = String.format("Name:%s", Name);
        return out;
    }

    public static List<Continent> getContinents() {
        // easier to read in docker
        System.out.println("Called methood: getContinents");

        // create an intial connection to db
        Connection con = Database.dbConnect();

        // this will hold all countries
        ArrayList<Continent> continents = new ArrayList<Continent>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs;

            // run sql on db
            rs = stmt.executeQuery(
                "SELECT DISTINCT Continent FROM country;");

            // itterate through all rows
            while (rs.next()) {
                // extract string, append to list
                String name = rs.getString("Continent");

                Continent toAdd = new Continent(name);
                continents.add(toAdd);
            }
            // finished, close connection
            con.close();
        } catch (Exception e) {
            // unable to connect to db
            System.out.println(e.getMessage());
        }

        // return list
        return continents;
    }
}
