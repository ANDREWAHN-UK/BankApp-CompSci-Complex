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
        System.out.printf("New User: %s %s with ID %s created.", firstName, lastName, this.id);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getID() {

        return this.id;
    }

    public Object getFirstName() {
        return this.firstName;
    }


    //this prints a summary of the accounts of this user
    public void printAccountsSummary() {

        System.out.printf("\n\n%s's Accounts Summary", this.firstName);

        // sets our counter at 0,
        // as long as the counter is less than the size of the accounts array
        // adds one to the counter
        for (int a = 0; a <this.accounts.size(); a++){

            //%d is the format for an integer
            //%s is the summary line - each account instance will generate one of these
            //getSummaryLine() is defined in Accounts.java
            System.out.printf("%d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    public int numAccounts() {

        return this.accounts.size();
    }

    public void printAccountTransactionHistory(int accountIndex) {
        this.accounts.get(accountIndex).printTransHistory();
    }
}
