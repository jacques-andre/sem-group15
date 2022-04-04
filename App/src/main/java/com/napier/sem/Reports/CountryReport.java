package com.napier.sem.Reports;

import java.util.ArrayList;

import com.napier.sem.Country;

/**
 * Represents a Report,
 * All questions are generated into reports with titles,etc
 */
public class CountryReport extends Report {
  public ArrayList<Country> countryOutput = new ArrayList<Country>();

  public CountryReport(String _title, ArrayList<String> _stringOutput, String _comments,
      ArrayList<Country> _countryOutput) {
    super(_title, _stringOutput, _comments);
    this.countryOutput = _countryOutput;
  }

}
