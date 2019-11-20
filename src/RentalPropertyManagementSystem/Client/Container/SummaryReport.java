package RentalPropertyManagementSystem.Client.Container;

import java.util.ArrayList;

public class SummaryReport {

    private int numOfListed;
    private int numOfRented;
    private int numOfActive;
    private ArrayList<Property> listedHouses;
    private Period periodOfTime;

    public SummaryReport(int numL, int numR, int numA, ArrayList<Property> listedH, Period p){
        numOfListed = numL;
        numOfRented = numR;
        numOfActive = numA;
        listedHouses = listedH;
        periodOfTime = p;
    }

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

    public String numbersToString() {
        return "For Period: " + periodOfTime.toString() + "\n\nTotal number of houses listed in period: " + numOfListed + "\nTotal number of houses rented in the period: "
                + numOfRented + "\nTotal number of active listings: " + numOfActive;
    }
}
