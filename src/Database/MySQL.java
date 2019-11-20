package Database;

import java.sql.*;

/**
 *
 */
public class MySQL implements DatabaseCreds {
    /**
     * Connection for MySQL
     */
    protected Connection conn;
    /**
     * Results from mySQL
     */
    protected ResultSet rs;

    /**
     * Connects to database
     */
    public void connect(){

        try{
           Driver driver = new com.mysql.cj.jdbc.Driver();
           DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(DB_URL, USERNAME,PASSWORD);
        }catch(SQLException e){
            System.out.println("Connection failed");
        }
    }

    /**
     * Closes connection
     */
    public void closeConnection(){
        try{
            rs.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


