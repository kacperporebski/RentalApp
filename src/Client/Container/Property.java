package Client.Container;

import Users.Landlord;

public class Property
{

    private Landlord myLandlord;
    private Fee rent;
    private Fee registrationFee;
    private String type; //whats this?
    private STATE state;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean furnished;
    private String cityQuadrant;

    public Landlord getMyLandlord()
    {
        return myLandlord;
    }

    public Fee getRent()
    {
        return rent;
    }

    public Fee getRegistrationFee()
    {
        return registrationFee;
    }

    public String getType()
    {
        return type;
    }

    public STATE getState()
    {
        return state;
    }

    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    public int getNumberOfBathrooms()
    {
        return numberOfBathrooms;
    }

    public boolean isFurnished()
    {
        return furnished;
    }

    public String getCityQuadrant()
    {
        return cityQuadrant;
    }
}
