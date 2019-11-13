package Client.Repositories;

import Client.Container.Account;
import Client.Container.Observer;
import Client.Services.LoginService;
import Client.Container.Subject;
import Users.AccountHolder;
import Users.User;

import java.util.ArrayList;

public class UserRepository implements Subject
{
    private ArrayList<AccountHolder> users;
    private ArrayList<Observer> observerList;



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

    @Override
    public void registerObserver(Observer obs)
    {

    }

    @Override
    public void removeObserver(Observer obs)
    {

    }

    @Override
    public void notifyObserver()
    {

    }
}
