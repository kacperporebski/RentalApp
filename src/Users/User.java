package Users;

import Client.Container.Name;

public abstract class User
{
    Name name;
    String email;

    public User(String fname, String lname, String mail)
    {
        name = new Name(fname, lname);
        email = mail;
    }





}
