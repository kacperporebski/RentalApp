package RentalPropertyManagementSystem.Users;

import RentalPropertyManagementSystem.Client.Container.CityQuadrants;
import RentalPropertyManagementSystem.Client.Container.SearchCriteria;

import java.util.ArrayList;

public class Renter
{
    SearchCriteria searchCriteria;

    public void enterCriteria(ArrayList<String> type, ArrayList<Integer> bedrooms, ArrayList<Integer> bathrooms, boolean furnished, ArrayList<CityQuadrants> cityQuadrants)
    {
        searchCriteria = new SearchCriteria(type, bedrooms, bathrooms, furnished, cityQuadrants);
    }

}
