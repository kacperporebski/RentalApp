package RentalPropertyManagementSystem.Client.Container;

import java.util.ArrayList;

public class SearchCriteria
{
    private ArrayList<PropertyType> propertyType;
    private ArrayList<Integer> rangeOfBedrooms;
    private ArrayList<Integer> rangeOfBathrooms;
    private boolean furnished;
    private boolean unfurnished;
    private ArrayList<CityQuadrants> cityQuadrants;

    public SearchCriteria(ArrayList<PropertyType> type, ArrayList<Integer> bedrooms, ArrayList<Integer> bathrooms, boolean furnished, boolean unfurnished, ArrayList<CityQuadrants> cityQuadrants)
    {
        if(bedrooms.get(0) > bedrooms.get(1))
        {
            System.out.println("Invalid Input");
            return;
        }
        if(bathrooms.get(0) > bathrooms.get(1))
        {
            System.out.println("Invalid Input");
            return;
        }
        this.propertyType = type;
        rangeOfBedrooms = bedrooms;
        rangeOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.unfurnished = unfurnished;
        this.cityQuadrants = cityQuadrants;

    }

    public boolean matchingProperty(Property p)
    {
        System.out.println(p.toString());

        boolean returnValue = (matchingPropertyType(p) && matchingBedroomRange(p) && matchingBathroomRange(p) && matchingFurnish(p) && matchingCityQuadrant(p) );

        System.out.println(" type: " + matchingPropertyType(p) + " bed: " + matchingBedroomRange(p) + " bath: " + matchingBathroomRange(p) + " f: " + matchingFurnish(p)
                + " cq: " + matchingCityQuadrant(p) + " value: " + returnValue);
        return returnValue;
    }

    boolean matchingPropertyType(Property p)
    {
        boolean matching = true;

        for(PropertyType type : propertyType)
        {
            if(type.equals(p.getPropertyType()) == false)
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
        boolean returnValue = false;
        if(((p.isFurnished()) && furnished) || ((!p.isFurnished()) && unfurnished))
            returnValue = true;
        return returnValue;
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