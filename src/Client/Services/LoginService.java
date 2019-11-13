package Client.Services;

import Users.AccountHolder;

import java.util.ArrayList;

public class LoginService
{
    //TODO SINGLETON

    private ArrayList<AccountHolder> users;
    static private LoginService instance;


    public static LoginService getInstance()
    {
        return instance;
    }

    public void add(String username, String password)
    {

        //TODO users.add(new AccountHolder??? WHAT TYPE THOUGH AAAAA U WOULD HAVE TO PASS THE ACCOUNT IN HERE NOT CREATE IT HERE)

    }

    public AccountHolder validate(String username, String password)
    {
        //TODO validate
        for (AccountHolder a : users)
        {
            //  if()

        }

    }


}
