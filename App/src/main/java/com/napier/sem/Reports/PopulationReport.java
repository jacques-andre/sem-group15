package com.napier.sem.Reports;

import java.util.ArrayList;

public class PopulationReport extends Report {
  public int Population;

  public PopulationReport(String _title, ArrayList<String> _stringOutput, String _comments, int _population) {
    super(_title, _stringOutput, _comments);
    this.Population = _population;
  }

}
