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

    //note the below is something the *customer* does, hence being defined here
    //this prints a summary of the accounts of this user
    public void printAccountsSummary() {

        System.out.printf("\n\n%s's Accounts Summary \n", this.firstName);

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

    //note the below is something the *customer* does, hence being defined here
    //Account Index is the index of the account to use, which exists in the Accounts class
    public void printAccountTransactionHistory(int accountIndex) {
        this.accounts.get(accountIndex).printTransHistory();
    }
    //note the below is something the *customer* does, hence being defined here
    //this gets the balance of a particular account
    public double getAccountBalance(int accountIndex){
        return this.accounts.get(accountIndex).getBalance();
    }

    //note the below is something the *customer* does, hence being defined here
    //this gets the Acct ID of a particular account - in group project this is the account number
    public String getAcctID(int accountIndex) {
        return this.accounts.get(accountIndex).getID();
    }

    //note the below is something the *customer* does, hence being defined here
    //this adds to the Transaction class
    //note that addTransaction() is defined in Accounts
    public void addAccountTransaction(int accountIndex, double amount, String memo) {
        this.accounts.get(accountIndex).addTransaction(amount, memo);
    }
}
