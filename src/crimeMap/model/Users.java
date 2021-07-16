package crimeMap.model;

public class Users extends Persons{
  protected double credibilityRating;

  public Users(String userName, String password, String firstName, String lastName, double credibilityRating) {
    super(userName, password, firstName, lastName);
    this.credibilityRating = credibilityRating;
  }

  public Users(String userName, double credibilityRating) {
    super(userName);
    this.credibilityRating = credibilityRating;
  }
  
  public Users(String userName) {
    super(userName);
  }

  public double getCredibilityRating() {
    return credibilityRating;
  }

  public void setCredibilityRating(double credibilityRating) {
    this.credibilityRating = credibilityRating;
  }
}
