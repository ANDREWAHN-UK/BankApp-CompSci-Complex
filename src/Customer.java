import java.util.ArrayList;

public class Customer {

    private String firstName;

    private String lastName;

    private String id;

    private ArrayList<Account> accounts;

    public Customer(String firstName, String lastName, Bank theBank) {

        //set users name
        this.firstName = firstName;
        this.lastName = lastName;

        //get a new id for the user
        this.id = theBank.getNewUserID();

        //create an empty list of accounts
        this.accounts=new ArrayList<Account>();

        //Print log message
        System.out.printf("New User %s, %s with ID %s created.", lastName, firstName,this.id);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getID() {

        return this.id;
    }
}
