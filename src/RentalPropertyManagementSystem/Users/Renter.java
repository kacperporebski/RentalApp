package RentalPropertyManagementSystem.Users;

import RentalPropertyManagementSystem.Client.Container.CityQuadrants;
import RentalPropertyManagementSystem.Client.Container.PropertyType;
import RentalPropertyManagementSystem.Client.Container.SearchCriteria;

import java.util.ArrayList;

public class Renter
{
    SearchCriteria searchCriteria;

    public void Renter()
    {

    }

    public void Renter(ArrayList<PropertyType> type, ArrayList<Integer> bedrooms, ArrayList<Integer> bathrooms, boolean furnished, boolean unfurnished, ArrayList<CityQuadrants> cityQuadrants)
    {
        searchCriteria = new SearchCriteria(type, bedrooms, bathrooms, furnished, unfurnished, cityQuadrants);
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }
}
