package Database;




public class Database {

    private PropertySQL propertyDatabase;
    private UsersSQL userDatabase;
    private SearchSQL searchCriteriaDatabase;

    public Database(){
        propertyDatabase = new PropertySQL();
        userDatabase = new UsersSQL();
        searchCriteriaDatabase = new SearchSQL();
        propertyDatabase.connect();
        userDatabase.connect();
        searchCriteriaDatabase.connect();
    }

    public PropertySQL getPropertyDatabase() {
        return propertyDatabase;
    }

    public UsersSQL getUserDatabase() {
        return userDatabase;
    }

    public SearchSQL getSearchCriteriaDatabase() {
        return searchCriteriaDatabase;
    }
}
