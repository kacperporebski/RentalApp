package Database;


import RentalPropertyManagementSystem.Client.Container.*;
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

                String sql = "CREATE TABLE property " + "(id INTEGER not NULL, " + "address VARCHAR(255), " +
                        "cityQuadrant VARCHAR(255), " +"state VARCHAR(255), " + "rent_fee DOUBLE, " +
                        "reg_fee DOUBLE, " + "bedrooms INTEGER not NULL, " + "bathrooms INTEGER not NULL," +
                        "furnished INTEGER not NULL," + "Type VARCHAR(255)," +  "cityQuad VARCHAR(255)," + "landLordUsername VARCHAR(255)," + "PRIMARY KEY (id))";

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
            String query  = "INSERT INTO property (ID, address, cityQuadrant, state, " +
                    "rent_fee, reg_fee, bedrooms, bathrooms, furnished , type, cityQuad, landLordUsername) values (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pState = conn.prepareStatement(query);
            pState.setInt(1, addThisProperty.getID());
            pState.setString(2,addThisProperty.getAddress());
            //temp fix for the below
            pState.setString(3, "SE");
           // TODO FIX pState.setString(3,addThisProperty.getCityQuadrant().toString());
            pState.setString(4,addThisProperty.getState().toString());
            pState.setDouble(5, addThisProperty.getRent().getPaymentAmount());
            pState.setDouble(6, addThisProperty.getRegistrationFee().getPaymentAmount());
            pState.setInt(7, addThisProperty.getNumberOfBedrooms());
            pState.setInt(8,addThisProperty.getNumberOfBathrooms());
            int val = (addThisProperty.furnished()) ? 1 : 0;
            pState.setInt(9,val);
            pState.setString(10,addThisProperty.getPropertyType().toString());
            pState.setString(12, addThisProperty.getMyLandlord().getUsername());
            pState.setString(11, addThisProperty.getCityQuadrant().toString());
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

              //  public Property(Landlord l, String addr, int bedroom, int bathroom, boolean furnished, Fee rentfee, PropertyType
              //   type)
                //public Landlord(String fname, String lname, String mail, Account account)
                boolean furnished = (rs.getInt(9) == 0) ? true : false;
                Property temp = new Property(uRepo.findLandlordUsername(rs.getString(12)), rs.getString(2), rs.getInt(7),
                rs.getInt(8), furnished , new Fee(rs.getDouble(5)), PropertyType.valueOf(rs.getString(10)),
                        CityQuadrants.valueOf(rs.getString(11)));
                uRepo.findLandlordUsername(rs.getString(11));

               // Property temp = new Property();
                pRepo.addProperty(temp);

            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }




}
