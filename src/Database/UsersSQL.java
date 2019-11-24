package Database;

import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Client.Container.UserType;
import RentalPropertyManagementSystem.Repositories.UserRepository;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.Landlord;
import RentalPropertyManagementSystem.Users.Manager;
import RentalPropertyManagementSystem.Users.RegisteredRenter;

import java.sql.*;
import java.util.Calendar;

/**
 * Makes database for order
 */
public class UsersSQL extends MySQL {

    //TODO FIX MY COPY PASTE
    /**
     * Adds user
     * @param  u RentalPropertyManagementSystem.Users data
     */
    public void addUser(AccountHolder u){
        try{
            // String sql = "CREATE TABLE RentalUsers" + "(first VARCHAR(255), " + "last VARCHAR(255), " + "email VARCHAR(255), " + "accountType VARCHAR(255), " +
            //                        "username VARCHAR(255), " + "password VARCHAR(255), " + "PRIMARY KEY (username))";
            String query  = "INSERT INTO RentalUsers (first, last, email, accountType, username, password) values (?,?,?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setString(1, u.getName().getFname() );
            pState.setString(2,  u.getName().getLname());
            pState.setString(3,u.getEmail());
            pState.setString(4, u.getAccountType().toString());
            pState.setString(5,u.getUsername());
            pState.setString(6,u.getPassword());
            int rowCount = pState.executeUpdate();
            System.out.println("User added");
            pState.close();

        }catch (SQLException e){
            System.out.println("Failed to add user");
            e.printStackTrace();
        }

    }

    /**
     * Creates user table
     */
    public void createUserTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "RentalUsers", null );
            if(rs.next()==false) {
                String sql = "CREATE TABLE RentalUsers" + "(first VARCHAR(255), " + "last VARCHAR(255), " + "email VARCHAR(255), " + "accountType VARCHAR(255), " +
                        "username VARCHAR(255), " + "password VARCHAR(255), " + "PRIMARY KEY (username))";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("User table created");
            }
            else
                System.out.println("User table already exists");
        }catch (SQLException e){
            System.out.println("Cant create user table");
            e.printStackTrace();
        }
    }

    /**
     * Prints order
     * @return order
     */
    public void readIntoRepo(UserRepository uRepo){
        //String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM RentalUsers";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                int switchVal=1;
                if(rs.getString(4).compareTo("RegRenter")==0)
                    switchVal=2;
                else if(rs.getString(4).compareTo("Manager")==0)
                switchVal=3;

                switch (switchVal){
                    case 1:
                        Landlord temp = new Landlord(rs.getString(1), rs.getString(2) , rs.getString(3),
                                new Account(rs.getString(5), rs.getString(6), UserType.valueOf(rs.getString(4))));
                        uRepo.addUser( temp );
                        break;
                    case 2:
                        RegisteredRenter temp1 = new RegisteredRenter(rs.getString(1), rs.getString(2) , rs.getString(3),
                                new Account(rs.getString(5), rs.getString(6), UserType.valueOf(rs.getString(4))));
                        uRepo.addUser( temp1 );
                        break;
                    case 3:
                        Manager temp2 = new Manager(rs.getString(1), rs.getString(2) , rs.getString(3),
                                new Account(rs.getString(5), rs.getString(6), UserType.valueOf(rs.getString(4))));
                        uRepo.addUser( temp2 );
                        break;


                }
                //public Landlord(String fname, String lname, String mail, Account account)
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public boolean tableCreated() throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "RentalUsers", null );
        if(rs.next()==false){
            return false;
        }
        else
            return true;
    }


}

