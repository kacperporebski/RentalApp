package Database;

import RentalPropertyManagementSystem.Client.Container.CityQuadrants;
import RentalPropertyManagementSystem.Client.Container.PropertyType;
import RentalPropertyManagementSystem.Client.Container.SearchCriteria;
import RentalPropertyManagementSystem.Repositories.UserRepository;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.RegisteredRenter;

import java.sql.*;
import java.util.ArrayList;

public class SearchSQL extends MySQL {

    public void createSearchCriteriaTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "SearchCriteria", null );
            if(rs.next()==false) {
                String sql = "CREATE TABLE SearchCriteria" + "(username VARCHAR(255), " + "Apartment INTEGER not NULL," +
                        "AttachedHouse INTEGER not NULL," + "DetachedHouse INTEGER not NULL," + "TownHouse INTEGER not NULL,"+
                        "Condo INTEGER not NULL," + "Duplex INTEGER not NULL,"+
                        "bedroomsLower INTEGER not NULL, " + "bedroomsUpper INTEGER not NULL," +
                        "bathroomsLower INTEGER not NULL, " + "bathroomsUpper INTEGER not NULL," +
                        "furnished INTEGER not NULL, " + "se INTEGER not NULL, " + "ne INTEGER not NULL, " +
                        "sw INTEGER not NULL, " + "nw INTEGER not NULL, " +
                        "PRIMARY KEY (username))";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("Search Criteria table created");
            }
            else
                System.out.println("Search Criteria table already exists");
        }catch (SQLException e){
            System.out.println("Cant create Search Criteria table");
            e.printStackTrace();
        }
    }

      /*
       username 1
        Apartment, 2
    AttachedHouse, 3
    DetachedHouse, 4
    TownHouse, 5
    Condo,6
    Duplex,7
    bedroom upper  8
    lower limit 9
    Bathrooms upper 10
    lower   11
    furnished;        12
    se 14
    ne 15
    sw 16
    nw 17
     */

    public void addSearchCriteria(RegisteredRenter u){
        try{
            String query  = "INSERT INTO searchcriteria (username, apartment, attachedhouse, detachedhouse, " +
                    "townhouse, condo, duplex, bedroomslower, bedroomsupper, bathroomslower, bathroomsupper, furnished" +
                    ",se,ne,sw,nw) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setString(1, u.getUsername());
            pState.setInt(2, u.getRenter().getSearchCriteria().isApartment());
            pState.setInt(3, u.getRenter().getSearchCriteria().isAttachedHouse());
            pState.setInt(4, u.getRenter().getSearchCriteria().isDetachedHouse());
            pState.setInt(5, u.getRenter().getSearchCriteria().isTownHouse());
            pState.setInt(6, u.getRenter().getSearchCriteria().isCondo());
            pState.setInt(7, u.getRenter().getSearchCriteria().isDuplex());
            pState.setInt(8, u.getRenter().getSearchCriteria().getRangeOfBedrooms().get(1));
            pState.setInt(9, u.getRenter().getSearchCriteria().getRangeOfBedrooms().get(0));
            pState.setInt(10, u.getRenter().getSearchCriteria().getRangeOfBathrooms().get(1)); //TODO DOUBLE CHECK INDEX FOR UPPER N LOWER BOUNDS
            pState.setInt(11, u.getRenter().getSearchCriteria().getRangeOfBathrooms().get(0));
            pState.setInt(12, u.getRenter().getSearchCriteria().furnished());
            pState.setInt(13, u.getRenter().getSearchCriteria().isSE());
            pState.setInt(14, u.getRenter().getSearchCriteria().isNE());
            pState.setInt(15, u.getRenter().getSearchCriteria().isSW());
            pState.setInt(16, u.getRenter().getSearchCriteria().isNW());

            int rowCount = pState.executeUpdate();
            System.out.println("Search criteria added");
            pState.close();

        }catch (SQLException e){
            System.out.println("Failed to search criteria");
            e.printStackTrace();
        }

    }


    public void readIntoRepo(UserRepository uRepo){
        //String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM searchcriteria";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                RegisteredRenter temp = uRepo.findRegRenterUsername(rs.getString(1));
                ArrayList<PropertyType> type = new ArrayList<>();
                ArrayList<CityQuadrants> quads = new ArrayList<>();
                ArrayList<Integer> beds = new ArrayList<>();
                ArrayList<Integer> bath = new ArrayList<>();
                if(rs.getInt(2) == 1)
                    type.add(PropertyType.valueOf("Apartment"));
                if(rs.getInt(3) == 1)
                    type.add(PropertyType.valueOf("AttachedHouse"));
                if(rs.getInt(4) == 1)
                    type.add(PropertyType.valueOf("DetachedHouse"));
                if(rs.getInt(5) == 1)
                    type.add(PropertyType.valueOf("TownHouse"));
                if(rs.getInt(6) == 1)
                    type.add(PropertyType.valueOf("Condo"));
                if(rs.getInt(7) == 1)
                    type.add(PropertyType.valueOf("Duplex"));
                beds.add(rs.getInt(9));
                beds.add(rs.getInt(8));
                bath.add(rs.getInt(11));
                bath.add(rs.getInt(10));

                boolean furnished = false;
                if(rs.getInt(12)==1)
                    furnished=true;

                if(rs.getInt(13) == 1)
                    quads.add(CityQuadrants.valueOf("SE"));
                if(rs.getInt(14) == 1)
                    quads.add(CityQuadrants.valueOf("NE"));
                if(rs.getInt(15) == 1)
                    quads.add(CityQuadrants.valueOf("NW"));
                if(rs.getInt(16) == 1)
                    quads.add(CityQuadrants.valueOf("SW"));


               // public SearchCriteria(ArrayList<PropertyType> type, ArrayList<Integer> bedrooms, ArrayList<Integer> bathrooms,
               // boolean furnished, boolean unfurnished, ArrayList<CityQuadrants> cityQuadrants)
                SearchCriteria tempS = new SearchCriteria(type, beds, bath, furnished, !furnished, quads);
                temp.setSearchCriteria(tempS);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public boolean tableCreated() throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "searchcriteria", null );
        if(rs.next()==false){
            return false;
        }
        else
            return true;
    }



}
