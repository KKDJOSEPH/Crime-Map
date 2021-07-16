package crimeMap.model;


public class Persons {
  protected String userName;
  protected String password;
  protected String firstName;
  protected String lastName;
  
  public Persons(String userName, String password, String firstName, String lastName) {
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public Persons(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
}
