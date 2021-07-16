package crimeMap.model;

public class OffenseType {
  protected int reportId;
  protected Type offenseType;
  
  public enum Type {
    AGGRAVATED_ASSAULT,
    ALL_OTHER_LARCENY,
    ANIMAL_CRUELTY, ARSON,
    ASSISTING_OR_PROMOTING_PROSTITUDE,
    BAD_CHECKS, BETTING_WAGERING,
    BRIBERY, BURGLARY_BREAKING_ENTERING,
    COUNTERFEITING_FORGERY,
    CREDIT_CARD_AUTOMATED_TELLER_MACHINE_FRAUD,
    CURFEW_LOITERING_VAGRANCY_VIOLATIONS,
    DESTRUCTION_DAMAGE_VANDALISM_OF_PROPERTY,
    DRIVING_UNDER_THE_INFLUENCE,
    DRUG_EQUIPMENT_VIOLATIONS,
    DRUG_NARCOTIC_VOILATIONS,
    DRUNKENNESS, EMBEZZLEMENT
  }

  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }

  public Type getOffenseType() {
    return offenseType;
  }

  public void setOffenseType(Type offenseType) {
    this.offenseType = offenseType;
  }
}
