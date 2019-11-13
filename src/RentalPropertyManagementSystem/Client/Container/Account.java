package RentalPropertyManagementSystem.Client.Container;

public class Account {

  private String username;
  private String password;
  private UserType accountType;

  public Account (String x, String y, UserType type)
  {
      username=x;
      password=y;
      accountType = type;

  }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public UserType getAccountType() {return accountType;}
    //TODO thats it ?

}
