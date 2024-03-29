package RentalPropertyManagementSystem.Client.Container;

import RentalPropertyManagementSystem.Users.Landlord;

public class Property
{

    private Landlord myLandlord;
    private Fee rent;
    private Fee registrationFee;
    private STATE state;
    private Date dateRegistered;
    private Date dateRented;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean furnished;
    private Address address;
  //  private String address;
  //private CityQuadrants cityQuadrant;
    private PropertyType propertyType;
    private static int ID = 0;


    public Property(Landlord l, Address addr, int bedroom, int bathroom, boolean furnished, Fee rentfee, PropertyType type)
    {
        ID ++;
        myLandlord = l;
        address = addr;
        numberOfBedrooms = bedroom;
        numberOfBathrooms = bathroom;
        this.furnished = furnished;
        rent = rentfee;
        registrationFee = new Fee ( 60);
        propertyType = type;
        state = STATE.ACTIVE;
        dateRegistered = new Date();
        dateRented = null;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public String toString()
    {
        return state + "\t Monthly Fee: " + rent + "\t Type: " + propertyType + "\t Address: " + address + "\t Bedrooms: " +
                numberOfBedrooms + "\t Bathrooms: " + numberOfBathrooms;
    }

    public String toStringSummary() {
        return "Landlord: " + myLandlord.getName() + "\t House ID: " + getID() + "\t Address: " + address;
    }

    public boolean furnished(){
        return furnished;
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

    public Address getAddress()
    {
        return address;
    }

    public CityQuadrants getCityQuadrant()
    {
        return address.getCityQuadrant();
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

    public void setState(STATE state)
    {
        this.state = state;

        if(state == STATE.RENTED)
            this.setDateRented(new Date());

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

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public PropertyType getPropertyType()
    {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType)
    {
        this.propertyType = propertyType;
    }

    public int getID()
    {
        return ID;
    }


}
