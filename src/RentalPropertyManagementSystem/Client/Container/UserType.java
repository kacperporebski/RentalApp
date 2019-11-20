package RentalPropertyManagementSystem.Client.Container;

import RentalPropertyManagementSystem.Users.Manager;

public enum UserType
{
    Manager("Manager"),
    Landlord("Landlord"),
    RegRenter("Registered Renter"),
    MANAGER("Manager"),
    LANDLORD("Landlord"),
    REGRENTER("Registered Renter");

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
