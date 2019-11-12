package Database;

import Users.AccountHolder;

import java.sql.*;
import java.util.Calendar;

/**
 * Makes database for order
 */
public class UsersSQL extends MySQL {

    /**
     * Adds user
     * @param  u Users data
     */
    public void addUser(AccountHolder u){
        try{
            String query  = "INSERT INTO users (date, orderid, toolname, quantity) values (?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setString(1, Calendar.getInstance().getTime().toString() );
            pState.setInt(2,  (int)(Math.random() * (99999) + 1));
            pState.setString(3,u.getUsername());
            pState.setInt(4,40);
            int rowCount = pState.executeUpdate();
            System.out.println("Order made");
            pState.close();

        }catch (SQLException e){

        }

    }

    /**
     * Creates order table
     */
    public void createUserTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "toolorder", null );
            if(rs.next()==false) {
                String sql = "CREATE TABLE users " + "(date VARCHAR(255), " + "accountid INTEGER not NULL, " +
                        "username VARCHAR(255), " + "password VARCHAR(255), " + "PRIMARY KEY (accountid))";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("User table created");
            }
            else
                System.out.println("User tool already exists");
        }catch (SQLException e){
            System.out.println("Cant create user table");
            e.printStackTrace();
        }
    }

    /**
     * Prints order
     * @return order
     */
    public String printOrder(){
        String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM toolorder";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                s+= "Date: " + rs.getString(1) + "\n\n" + rs.getString(2) + ":  " + rs.getString(3)
                        + " " + rs.getString(4) + "\n ----------------------------------- \n";
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(s.equals("")){
            return "There are currently no orderlines made";
        }
        return s;
    }

    /**
     * Increase stock if below 20
     * @param name tool name
     * @param amount tool amount increases
     * @param toolSQL tool database
     */
    private void increaseItem(String name, int amount, ToolSQL toolSQL){
        try {
            String query2 = "UPDATE tool SET quantity = ? WHERE name= ?";
            PreparedStatement pStmt2 = conn.prepareStatement(query2);
            pStmt2.setInt(1, toolSQL.getQuantityInt(name) + amount);
            pStmt2.setString(2,name);
            pStmt2.executeUpdate();
            pStmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

