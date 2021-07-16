package crimeMap.model;

public class OffenseParentGroup {
  protected int reportId;
  protected ParentGroup offenseParentGroup;
  
  public enum ParentGroup {
    ANIMAL_CRUELTY, ARSON, ASSAULT_OFFENSES,
    BAD_CHECKS, BRIBERY, BURGLARY_COUNTERFEITING_FORGERY,
    CURFEW_LOITERING_VAGRANCY_VOILATIONS,
    DESTRUCTION_DAMAGE_VANDALISM_OF_PROPERTY,
    DRIVING_UNDER_THE_INFLUENCE,
    DRUG_NARCOTIC_OFFENSES,
    DRUNKENNESS, EMBEZZLEMENT
  }

  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }

  public ParentGroup getOffenseParentGroup() {
    return offenseParentGroup;
  }

  public void setOffenseParentGroup(ParentGroup offenseParentGroup) {
    this.offenseParentGroup = offenseParentGroup;
  }
}
