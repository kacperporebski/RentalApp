package RentalPropertyManagementSystem.Client.Container;

import java.util.ArrayList;

public class SearchCriteria
{
    private ArrayList<PropertyType> propertyType;
    private ArrayList<Integer> rangeOfBedrooms;
    private ArrayList<Integer> rangeOfBathrooms;
    private boolean furnished;
    private ArrayList<CityQuadrants> cityQuadrants;

    public SearchCriteria(ArrayList<PropertyType> type, ArrayList<Integer> bedrooms, ArrayList<Integer> bathrooms, boolean furnished, ArrayList<CityQuadrants> cityQuadrants)
    {
        if(rangeOfBathrooms.get(0) > rangeOfBathrooms.get(1))
        {
            System.out.println("Invalid Input");
            return;
        }
        if(rangeOfBedrooms.get(0) > rangeOfBedrooms.get(1))
        {
            System.out.println("Invalid Input");
            return;
        }
        this.propertyType = type;
        rangeOfBedrooms = bedrooms;
        rangeOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.cityQuadrants = cityQuadrants;

    }

    public boolean matchingProperty(Property p)
    {
        return (matchingPropertyType(p) && matchingBedroomRange(p) && matchingBathroomRange(p) && matchingFurnish(p)&& matchingCityQuadrant(p) );
    }

    boolean matchingPropertyType(Property p)
    {
        boolean matching = true;

        for(PropertyType type : propertyType)
        {
            if(type.equals(p.getType()) == false)
                matching = false;
            else
            {
                matching = true;
                break;
            }
        }

        return matching;
    }

    boolean matchingBedroomRange(Property p)
    {
        if(p.getNumberOfBedrooms() >= rangeOfBedrooms.get(0) && p.getNumberOfBedrooms() <= rangeOfBedrooms.get(1))
            return true;
        return false;
    }

    boolean matchingBathroomRange(Property p)
    {
        if(p.getNumberOfBedrooms() >= rangeOfBathrooms.get(0) && p.getNumberOfBedrooms() <= rangeOfBathrooms.get(1))
            return true;
        return false;
    }

    boolean matchingFurnish(Property p)
    {
        return p.isFurnished() == furnished;
    }

    boolean matchingCityQuadrant(Property p)
    {
        boolean matching = true;
        for(CityQuadrants quadrant : cityQuadrants)
        {
            if(quadrant.equals(p.getCityQuadrant()) == false)
                matching = false;
            else
            {
                matching = true;
                break;
            }
        }
        return matching;
    }
}