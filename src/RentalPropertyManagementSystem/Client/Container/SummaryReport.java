package RentalPropertyManagementSystem.Client.Container;

import java.util.ArrayList;

public class SummaryReport {

    private int numOfListed;
    private int numOfRented;
    private int numOfActive;
    private ArrayList<Property> listedHouses;
    private Period periodOfTime;

    //Getters
    public ArrayList<Property> getListedHouses() {
        return listedHouses;
    }
    public int getNumOfActive() {
        return numOfActive;
    }
    public int getNumOfListed() {
        return numOfListed;
    }
    public int getNumOfRented() {
        return numOfRented;
    }
    public Period getPeriodOfTime() {
        return periodOfTime;
    }


}
