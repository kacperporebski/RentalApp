package Users;

import Client.Container.Account;

public abstract class AccountHolder extends User {

    private Account account;

    public String getUsername(){
        return account.getUsername();
    }

    //TODO DO I NEED ANYTHING EXTRA HERE?


}
