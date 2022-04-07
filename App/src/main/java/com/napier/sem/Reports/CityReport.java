package com.napier.sem.Reports;

import java.util.ArrayList;

import com.napier.sem.City;

/**
 * Represents a Report,
 * All questions are generated into reports with titles,etc
 */
public class CityReport extends Report {
  public ArrayList<City> cityOutput = new ArrayList<City>();

  public CityReport(String _title, ArrayList<String> _stringOutput, String _comments,
      ArrayList<City> _cityOutput) {
    super(_title, _stringOutput, _comments);
    this.cityOutput = _cityOutput;
  }

}
