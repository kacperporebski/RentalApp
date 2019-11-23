package Database;


import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Client.Container.Date;
import RentalPropertyManagementSystem.Repositories.PropertyRepository;
import RentalPropertyManagementSystem.Repositories.UserRepository;
import RentalPropertyManagementSystem.Users.Landlord;

import java.sql.*;

/**
 * Tool database
 */
public class PropertySQL extends MySQL {

    //TODO FIX MY COPY PASTE

    /**
     * Creates table of property
     */

    public void createPropertyTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "property", null );
            if(rs.next()==false) {

                String sql = "CREATE TABLE property " + "(id INTEGER not NULL, " + "housenum INTEGER not NULL," +
                        "street VARCHAR(255), " + "city VARCHAR(255)," +
                        "cityQuadrant VARCHAR(255), " +"state VARCHAR(255), " + "rent_fee DOUBLE, " +
                        "reg_fee DOUBLE, " + "bedrooms INTEGER not NULL, " + "bathrooms INTEGER not NULL," +
                        "furnished INTEGER not NULL," + "Type VARCHAR(255)," + "landLordUsername VARCHAR(255),"
                        + "day INTEGER not NULL," + "month INTEGER not NULL," + "year INTEGER not NULL," + "PRIMARY KEY (id))";

                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("Property table created");
            }
            else
                System.out.println("Property table already exists");
        }catch (SQLException e){
            System.out.println("Cant create property table");
        }
    }


    public void addProperty(Property addThisProperty){
        try{
            String query  = "INSERT INTO property (ID, houseNum, street, city, cityQuadrant, state, " +
                    "rent_fee, reg_fee, bedrooms, bathrooms, furnished , type," +
                    " landLordUsername, day, month, year) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pState = conn.prepareStatement(query);
            pState.setInt(1, addThisProperty.getID());
            pState.setInt(2,addThisProperty.getAddress().getHouseNum());
            pState.setString(3,addThisProperty.getAddress().getStreet());
            pState.setString(4,addThisProperty.getAddress().getCity());
            pState.setString(5, addThisProperty.getAddress().getCityQuadrant().toString());
            pState.setString(6,addThisProperty.getState().toString());
            pState.setDouble(7, addThisProperty.getRent().getPaymentAmount());
            pState.setDouble(8, addThisProperty.getRegistrationFee().getPaymentAmount());
            pState.setInt(9, addThisProperty.getNumberOfBedrooms());
            pState.setInt(10,addThisProperty.getNumberOfBathrooms());
            int val = (addThisProperty.furnished()) ? 1 : 0;
            pState.setInt(11,val);
            pState.setString(12,addThisProperty.getPropertyType().toString());
            pState.setString(13, addThisProperty.getMyLandlord().getUsername());
            pState.setInt(14, addThisProperty.getDateRegistered().getDay());
            pState.setInt(15, addThisProperty.getDateRegistered().getMonth());
            pState.setInt(16,addThisProperty.getDateRegistered().getYear());
            int rowCount = pState.executeUpdate();
            pState.close();
        }catch (SQLException e){
                e.printStackTrace();
        }
    }


    public String printAllProperties(){
        String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM property";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                s+=String.format("%-10s %-20s %-10s $%-10s %-10s\n", rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public String getProperty(int id){
        String s = "";
        try{
            String query = "SELECT * FROM property where id= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, id);
            rs= pStmt.executeQuery();
            while (rs.next()){
                s+= "ID: " + rs.getString(1) + "   Name: " + rs.getString(2) + "   Quantity: " + rs.getString(3)
                        + "\nPrice: $" + rs.getString(4) + "   Supplier ID: " + rs.getString(5);
            }
            pStmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public boolean tableCreated() throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "property", null );
        if(rs.next()==false){
            return false;
        }
        else
            return true;
    }


    public void readIntoRepo(PropertyRepository pRepo, UserRepository uRepo){
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM property";
            rs=stmt.executeQuery(query);
            while(rs.next()){

              //  public Property(Landlord l, Address addr, int bedroom, int bathroom, boolean furnished, Fee rentfee, PropertyType
              //   type)
                //public Landlord(String fname, String lname, String mail, Account account)
                boolean furnished = (rs.getInt(11) == 0) ? true : false;
                Address address = new Address(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) );
                Property temp = new Property(uRepo.findLandlordUsername(rs.getString(13)),  address, rs.getInt(9),
                rs.getInt(10), furnished , new Fee(rs.getDouble(7)), PropertyType.valueOf(rs.getString(12)));
                temp.getRegistrationFee().setPaid(true);
                temp.setDateRegistered(new Date(rs.getInt(14), rs.getInt(15), rs.getInt(16)));
               // Property temp = new Property();
                pRepo.addProperty(temp);

            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
