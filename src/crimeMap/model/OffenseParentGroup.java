package crimeMap.model;

public class OffenseParentGroup {
  protected int reportId;
  protected ParentGroup offenseParentGroup;
  
  public enum ParentGroup {
	  DRUG_NARCOTIC_OFFENSES,
	  LARCENY_THEFT,
	  ROBBERY,
	  DESTRUCTION_DAMAGE_VANDALISM_OF_PROPERTY,
	  DRIVING_UNDER_THE_INFLUENCE,
	  FRAUD_OFFENSES,
	  COUNTERFEITING_FORGERY,
	  PORNOGRAPHY_OBSCENE_MATERIAL,
	  EXTORTION_BLACKMAIL,
	  MOTOR_VEHICLE_THEFT,
	  STOLEN_PROPERTY_OFFENSES,
	  EMBEZZLEMENT,
	  WEAPON_LAW_VIOLATIONS,
	  ARSON,
	  LIQUOR_LAW_VIOLATIONS,
	  BAD_CHECKS,
	  PROSTITUTION_OFFENSES,
	  CURFEW_LOITERING_VAGRANCY_VIOLATIONS,
	  ANIMAL_CRUELTY,
	  DRUNKENNESS,
	  BRIBERY,
	  GAMBLING_OFFENSES,
	  TRESPASS_OF_REAL_PROPERTY,
	  ASSAULT_OFFENSES,
	  BURGLARY_BREAKING_ENTERING
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
