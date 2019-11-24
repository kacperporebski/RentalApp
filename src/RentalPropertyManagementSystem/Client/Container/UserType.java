package RentalPropertyManagementSystem.Client.Container;

import RentalPropertyManagementSystem.Users.Manager;

public enum UserType
{
    Manager("Manager"),
    Landlord("Landlord"),
    RegRenter("RegRenter"),
    MANAGER("Manager"),
    LANDLORD("Landlord"),
    REGRENTER("RegRenter");

    String type;

    UserType(String string)
    {
        type = string;
    }

    public String toString()
    {
        return type;
    }
}
