package Database;




public class Database {

    private PropertySQL propertyDatabase;
    private UsersSQL userDatabase;

    public Database(){
        propertyDatabase = new PropertySQL();
        userDatabase = new UsersSQL();
        propertyDatabase.connect();
        userDatabase.connect();
    }

    public PropertySQL getPropertyDatabase() {
        return propertyDatabase;
    }

    public UsersSQL getUserDatabase() {
        return userDatabase;
    }
}
