package Client.Container;

import Users.AccountHolder;

public class Email {
    private AccountHolder sender;
    private AccountHolder receiver;
    private String message;

    public String getMessage() {
        return message;
    }
}
