package crimeMap.model;

public class CrimeType {
  protected int reportId;
  protected Type crimeType;
  
  public enum Type {
    SOCIETY,
    PROPERTY,
    PERSON
  }

  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }

  public Type getCrimeType() {
    return crimeType;
  }

  public void setCrimeType(Type crimeType) {
    this.crimeType = crimeType;
  }
}
