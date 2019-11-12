package Database;

/**
 * MySQL values
 */
public interface DatabaseCreds {
    /**
     * driver for mySQL
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * the URL of mySQL
     */
    static final String DB_URL = "jdbc:mysql://localhost/mydb";
    /**
     * username for mySQL
     */
    static final String USERNAME = "root";
    /**
     * password for mySQL
     */
    static final String PASSWORD = "whitened";
}
