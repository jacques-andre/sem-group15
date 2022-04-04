package com.napier.sem.Reports;

import java.util.*;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.Writer;
import java.text.DateFormat;
import java.io.FileWriter;

import java.text.SimpleDateFormat;

/**
 * Represents a Report,
 * All questions are generated into reports with titles,etc
 */
public class Report {
  public String ReportTitle;
  public ArrayList<String> StringOutput;
  public String Comments;

  public Report(String _title, ArrayList<String> _stringOutput, String _comments) {
    this.ReportTitle = _title;
    this.StringOutput = _stringOutput;
    this.Comments = _comments;
  }

  /**
   * Print string output of a report
   */
  public void outputReport() {
    System.out.println(String.format("ReportTitle:%s", ReportTitle));
    for (String s : StringOutput) {
      System.out.println(s);
    }
  }

  /**
   * Genereate output.html file based off resources/template.html using thymeleaf,
   * output.html produces a html report based off the param allReports.
   * 
   * @param allReports ArrayList of type Report, will be used in output report.
   */
  public static void toHMTL(ArrayList<Report> allReports, ArrayList<CountryReport> countryReports) {
    TemplateEngine templateEngine = new TemplateEngine();
    ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
    Context ct = new Context();

    resolver.setCharacterEncoding("UTF-8");
    resolver.setSuffix(".html");
    resolver.setTemplateMode(TemplateMode.HTML);
    templateEngine.setTemplateResolver(resolver);

    // Get build time
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String dateStr = dateFormat.format(date);

    // inject into html
    ct.setVariable("reports", allReports);
    ct.setVariable("countryReports", countryReports);
    ct.setVariable("time", dateStr);

    try {
      Writer writer = new FileWriter("index.html");
      writer.write(templateEngine.process("template.html", ct));
      writer.close();
    } catch (Exception e) {
      System.out.println("Error");
    }
  }

  // private static ArrayList<Report> tidyReportsForHTML(ArrayList<Report>
  // allReports) {
  // // hold the Reports we will return
  // ArrayList<Report> output = new ArrayList<Report>();

  // // go through param allReports
  // for (int i = 0; i < allReports.size(); i++) {
  // Report currentReport = allReports.get(i);

  // // if the currentReport output size is too long,
  // // we want to shorten it
  // if (currentReport.QuestionOutput.size() > 35) {
  // // create a new shorter output with only 25 elements
  // ArrayList<String> shortenedOutput = new
  // ArrayList<String>(currentReport.QuestionOutput.subList(0, 25));

  // // show that the output was shortened
  // shortenedOutput.add("....");

  // // Create a new report with this smaller output,
  // // keep the same name as the current iteration
  // Report truncatedReport = new Report(currentReport.ReportTitle,
  // shortenedOutput);
  // // add new report to output list
  // output.add(truncatedReport);
  // } else {
  // // no need to shorten, add orginal
  // output.add(currentReport);
  // }
  // }
  // return output;
  // }
}
