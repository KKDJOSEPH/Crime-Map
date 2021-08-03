package crimeMap.model;

import java.util.Date;

public class Reports {
  protected int reportId;
  protected double latitude;
  protected double longitude;
  protected Date reportTime;
  protected boolean publishedAsReport;
  protected CrimeTips crimeTip;

  
  public Reports(int reportId, double latitude, double longitude, Date reportTime, 
      boolean publishedAsReport, CrimeTips crimeTip) {
    this.reportId = reportId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.reportTime = reportTime;
    this.publishedAsReport = publishedAsReport;
    this.crimeTip = crimeTip;
  }

  public int getReportId() {
    return reportId;
  }


  public void setReportId(int reportId) {
    this.reportId = reportId;
  }


  public double getLatitude() {
    return latitude;
  }


  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }


  public double getLongitude() {
    return longitude;
  }


  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }


  public Date getReportTime() {
    return reportTime;
  }


  public void setReportTime(Date reportTime) {
    this.reportTime = reportTime;
  }


  public boolean isPublishedAsReport() {
    return publishedAsReport;
  }


  public void setPublishedAsReport(boolean publishedAsReport) {
    this.publishedAsReport = publishedAsReport;
  }


  public CrimeTips getCrimeTip() {
    return crimeTip;
  }


  public void setCrimeTip(CrimeTips crimeTip) {
    this.crimeTip = crimeTip;
  }
}
