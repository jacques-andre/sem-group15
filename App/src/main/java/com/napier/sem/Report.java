package com.napier.sem;

import java.util.*;

/**
 * Represents a Report,
 * All questions are generated into reports with titles,etc
 */
public class Report {
  public String ReportTitle;
  public ArrayList<String> QuestionOutput;

  public Report(String _title, ArrayList<String> _questionOutput) {
    this.ReportTitle = _title;
    this.QuestionOutput = _questionOutput;
  }

  /**
   * Print output of a report
   */
  public void outputReport() {
    System.out.println(String.format("ReportTitle:%s", ReportTitle));
    for (String s : QuestionOutput) {
      System.out.println(s);
    }
  }
}
