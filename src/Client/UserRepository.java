package Client;

import Client.Container.Account;
import Client.Container.Observer;
import Client.Container.UserType;
import Users.AccountHolder;
import Users.User;

import java.util.ArrayList;

public class UserRepository implements Subject
{
    private ArrayList<User> users;


    AccountHolder login(String username, String password)
    {
       return LoginService.getInstance().validate(username, password);
    }

    @Override
    public void register(Observer obs)
    {

    }

    @Override
    public void remove(Observer obs)
    {

    }

    @Override
    public void notifyObserver()
    {

    }
}
