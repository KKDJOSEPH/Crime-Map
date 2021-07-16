package crimeMap.model;

import java.util.Date;

public class CrimeTips {
  protected int crimeTipId;
  protected Date created;
  protected Date occurredTime;
  protected String address;
  protected String city;
  protected String state;
  protected String zipcode;
  protected String content;
  protected Users userName;
  protected boolean publishedAsReport;
  
  public CrimeTips(int crimeTipId, Date created, Date occurredTime, String address,String city, String state,
  String zipcode,String content, Users userName, boolean publishedAsReport) {
    this.crimeTipId = crimeTipId;
    this.created = created;
    this.occurredTime = occurredTime;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.content = content;
    this.userName = userName;
    this.publishedAsReport = publishedAsReport;
  }

  public CrimeTips(int crimeTipId, Date created, Date occurredTime, String address,
      String city, String state, String zipcode, String content) {
    this.crimeTipId = crimeTipId;
    this.created = created;
    this.occurredTime = occurredTime;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.content = content;
  }
  
  public CrimeTips(Date created, Date occurredTime, String address,
      String city, String state, String zipcode, String content) {
    this.created = created;
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
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
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

  public Users getUserName() {
    return userName;
  }

  public void setUserName(Users userName) {
    this.userName = userName;
  }

  public boolean isPublishedAsReport() {
    return publishedAsReport;
  }

  public void setPublishedAsReport(boolean publishedAsReport) {
    this.publishedAsReport = publishedAsReport;
  }
}
