package RentalPropertyManagementSystem.Client.Container;

public class Account {

  private String username;
  private String password;
  private UserType accountType;
  static private int accountID = 0;

  public Account (String x, String y, UserType type)
  {
      accountID++;
      username=x;
      password=y;
      accountType = type;

  }

    public int getAccountID() {
        return accountID;
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
