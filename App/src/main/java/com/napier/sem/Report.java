package com.napier.sem;

import java.util.*;

public class Report {
  public String ReportTitle;
  public ArrayList<String> QuestionOutput;

  public Report(String _title, ArrayList<String> _questionOutput) {
    this.ReportTitle = _title;
    this.QuestionOutput = _questionOutput;
  }

  public void outputReport() {
    System.out.println(String.format("ReportTitle:%s", ReportTitle));
    for (String s : QuestionOutput) {
      System.out.println(s);
    }
  }
}
