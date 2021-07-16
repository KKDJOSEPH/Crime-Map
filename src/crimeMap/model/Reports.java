package crimeMap.model;

import java.util.Date;

public class Reports {
  protected int reportId;
  protected double latitude;
  protected double longitude;
  protected Date reportTime;
  protected boolean publishedAsReport;
  protected CrimeTips crimeTipId;

  
  public Reports(int reportId, double latitude, double longitude, Date reportTime, 
      boolean publishedAsReport, CrimeTips crimeTipId) {
    this.reportId = reportId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.reportTime = reportTime;
    this.publishedAsReport = publishedAsReport;
    this.crimeTipId = crimeTipId;
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


  public CrimeTips getCrimeTipId() {
    return crimeTipId;
  }


  public void setCrimeTipId(CrimeTips crimeTipId) {
    this.crimeTipId = crimeTipId;
  }
}
