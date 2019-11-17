package RentalPropertyManagementSystem.Client.Container;

import RentalPropertyManagementSystem.Users.Landlord;

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
    private String address;
    private CityQuadrants cityQuadrant;
    private int ID;


    public Property(int idnum, Landlord l, String addr, int bedroom, int bathroom, boolean furnished, Fee rentfee)
    {
        ID = idnum;
        myLandlord = l;
        address = addr;
        numberOfBedrooms = bedroom;
        numberOfBathrooms = bathroom;
        this.furnished = furnished;
        rent = rentfee;

    }

    public String toString()
    {
        return address + "\t Num of bedrooms and bathrooms : " +
                numberOfBedrooms + " ,\t " + numberOfBathrooms +
                "\tLandlord info: " + myLandlord.getName().toString();
    }

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

    public String getAddress()
    {
        return address;
    }

    public CityQuadrants getCityQuadrant()
    {
        return cityQuadrant;
    }

    public void setMyLandlord(Landlord myLandlord)
    {
        this.myLandlord = myLandlord;
    }

    public void setRent(Fee rent)
    {
        this.rent = rent;
    }

    public void setRegistrationFee(Fee registrationFee)
    {
        this.registrationFee = registrationFee;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setState(STATE state)
    {
        this.state = state;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms)
    {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms)
    {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public void setFurnished(boolean furnished)
    {
        this.furnished = furnished;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCityQuadrant(CityQuadrants cityQuadrant)
    {
        this.cityQuadrant = cityQuadrant;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }
}
