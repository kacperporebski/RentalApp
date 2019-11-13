package RentalPropertyManagementSystem.Database;

import java.sql.*;

/**
 * Tool database
 */
public class ToolSQL extends MySQL {

    /**
     * Creates table of tools
     */
    public void createToolTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "tool", null );
            if(rs.next()==false) {

                String sql = "CREATE TABLE tool " + "(id INTEGER not NULL, " + "name VARCHAR(255), " +
                        "quantity INTEGER not NULL, " + "price DOUBLE, " + "supplierid INTEGER not NULL, " + "PRIMARY KEY (id))";

                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("Tool table created");
            }
            else
                System.out.println("Table tool already exists");
        }catch (SQLException e){
            System.out.println("Cant create tool table");
        }
    }

    /**
     * Inserts a tool
     * @param id tool ID
     * @param name tool name
     * @param quant tool quantity
     * @param price tool price
     * @param supplierID tool supplier ID
     */
    public void insertToolPrepared(int id, String name, int quant , double price, int supplierID){
        try{
            String query  = "INSERT INTO TOOL (ID, name, quantity, price, supplierid) values (?,?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setInt(1,id);
            pState.setString(2,name);
            pState.setInt(3,quant);
            pState.setDouble(4,price);
            pState.setInt(5,supplierID);
            int rowCount = pState.executeUpdate();
            pState.close();
        }catch (SQLException e){

        }
    }

    /**
     * Prints tools
     * @return tool list
     */
    public String printAllTools(){
        String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM tool";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                s+=String.format("%-10s %-20s %-10s $%-10s %-10s\n", rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                // s+= rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                //  + " " + rs.getString(4) + " " + rs.getString(5) + "\n";
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Gets tool
     * @param name tool name
     * @return tool info
     */
    public String getTool(String name){
        String s = "";
        try{
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
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

    /**
     * Gets tool
     * @param id tool ID
     * @return tool info
     */
    public String getTool(int id){
        String s = "";
        try{
            String query = "SELECT * FROM tool where id= ?";
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

    /**
     * Gets tool quantity
     * @param name tool name
     * @return tool quantity
     */
    public String getQuantity(String name) {
        String s = "";
        try {
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                s += "The quantity for "+ rs.getString(2) +" is: " + rs.getString(3) + "\n";
            }
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Gets tool quantity
     * @param name tool name
     * @return quantity
     */
    public int getQuantityInt(String name){
        try {
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                return Integer.parseInt(rs.getString(3));
            }
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Decrease item
     * @param name tool name
     * @param orderSQL order database
     * @return message returned
     */
    public String decreaseItem(String name, UsersSQL orderSQL){
        int currentQuan = getQuantityInt(name);
        try {
            String query2 = "UPDATE tool SET quantity= ? WHERE name= ?";
            PreparedStatement pStmt2 = conn.prepareStatement(query2);
            pStmt2.setInt(1, getQuantityInt(name)-1);
            pStmt2.setString(2,name);
            pStmt2.executeUpdate();
            pStmt2.close();
            if(currentQuan < 21 ){
                System.out.println("Making order");
                //orderSQL.makeOrder(name, this);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Quantity failed to be decreased.";
        }
        return "Quantity successfully decreased.";
    }

    /**
     * Buys item
     * @param name tool name
     * @param amount amount being bought
     * @param orderSQL order database
     * @return message
     */
    public String buyItem(String name, int amount, UsersSQL orderSQL){
        int currentStock = getQuantityInt(name);
        if( currentStock>=amount){
            try {
                String query2 = "UPDATE tool SET quantity= ? WHERE name= ?";
                PreparedStatement pStmt2 = conn.prepareStatement(query2);
                pStmt2.setInt(1, Integer.parseInt(rs.getString(3))-amount);
                pStmt2.setString(2,name);
                pStmt2.executeUpdate();
                pStmt2.close();
                if(getQuantityInt(name)-amount < 21 ){
                   // orderSQL.makeOrder(name, this);
                }
                return "Successfully purchased " + amount + " of " + name;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            return "Not enough in stock to purchase " + amount;
        return "";
    }

}
