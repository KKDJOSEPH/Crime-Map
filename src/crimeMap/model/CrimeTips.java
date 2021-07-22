package crimeMap.model;

import java.util.Date;

public class CrimeTips {
  protected int crimeTipId;
  protected Date createdTime;
  protected Date occurredTime;
  protected String address;
  protected String city;
  protected String state;
  protected String zipcode;
  protected String content;
  protected Persons userName;
  protected boolean publishedAsReport;
  
  public CrimeTips(int crimeTipId, Date createdTime, Date occurredTime, String address,String city, String state,
  String zipcode,String content, Persons userName, boolean publishedAsReport) {
    this.crimeTipId = crimeTipId;
    this.createdTime = createdTime;
    this.occurredTime = occurredTime;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.content = content;
    this.userName = userName;
    this.publishedAsReport = publishedAsReport;
  }

  public CrimeTips(int crimeTipId, Date createdTime, Date occurredTime, String address,
      String city, String state, String zipcode, String content, Persons userName) {
    this.crimeTipId = crimeTipId;
    this.createdTime = createdTime;
    this.occurredTime = occurredTime;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.content = content;
    this.userName = userName;
  }
  
  public CrimeTips(Date createdTime, Date occurredTime, String address,
      String city, String state, String zipcode, String content) {
    this.createdTime = createdTime;
    this.occurredTime = occurredTime;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.content = content;
  }

  public int getCrimeTipId() {
    return crimeTipId;
  }

  public void setCrimeTipId(int crimeTipId) {
    this.crimeTipId = crimeTipId;
  }

  public Date getCreated() {
    return createdTime;
  }

  public void setCreated(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getOccurredTime() {
    return occurredTime;
  }

  public void setOccurredTime(Date occurredTime) {
    this.occurredTime = occurredTime;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Persons getUserName() {
    return userName;
  }

  public void setUserName(Persons userName) {
    this.userName = userName;
  }

  public boolean isPublishedAsReport() {
    return publishedAsReport;
  }

  public void setPublishedAsReport(boolean publishedAsReport) {
    this.publishedAsReport = publishedAsReport;
  }
}
