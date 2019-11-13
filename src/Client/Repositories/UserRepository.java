package Client.Repositories;

import Client.Container.Account;
import Client.Container.Observer;
import Client.Services.LoginService;
import Client.Container.Subject;
import Users.AccountHolder;
import Users.User;

import java.util.ArrayList;

public class UserRepository
{
    private ArrayList<AccountHolder> users;

    AccountHolder login(String username, String password)
    {
       return LoginService.getInstance().validate(username, password);
    }

    boolean addUser(Account account)
    {
        //Todo link users arraylist to that of LoginService or maybe change this to Registration
        LoginService.getInstance().add(account);
        return true;
    }

}
