package crimeMap.model;

public class Administrators extends Persons{
  public Administrators(String userName) {
    super(userName);
  }
  
  public Administrators(String userName, String password, String firstName, String lastName) {
    super(userName, password, firstName, lastName);
  }
}
