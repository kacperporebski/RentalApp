package Database;

import RentalPropertyManagementSystem.Database.MySQL;

import java.sql.*;

/**
 * Tool database
 */
public class PropertySQL extends MySQL {

    //TODO FIX MY COPY PASTE

    /**
     * Creates table of tools
     */
    public void createPropertyTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "tool", null );
            if(rs.next()==false) {

                String sql = "CREATE TABLE tool " + "(id INTEGER not NULL, " + "name VARCHAR(255), " +
                        "location VARCHAR(255), " + "price DOUBLE, " + "supplierid INTEGER not NULL, " + "PRIMARY KEY (id))";

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


    public void insertPropertyPrepared(int id, String name, String location , double price, int supplierID){
        try{
            String query  = "INSERT INTO PROPERTY (ID, name, location, price, propertyid) values (?,?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setInt(1,id);
            pState.setString(2,name);
            pState.setString(3,location);
            pState.setDouble(4,price);
            pState.setInt(5,supplierID);
            int rowCount = pState.executeUpdate();
            pState.close();
        }catch (SQLException e){

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




}
