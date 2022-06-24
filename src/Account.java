import java.util.ArrayList;

public class Account {

    //name here = account type in group project
    private String name;

    private double balance;

    //AcctId = Account Number in Group Project
    private String AcctID;

    private Customer holder;

    private ArrayList<Transaction> transactions;

    public Account(String name, Customer holder, Bank theBank) {

        //set the account name and holder
        this.name = name;
        this.holder = holder;

        // get id for the account
        this.AcctID=theBank.getNewAcctId();

        //initialise transactions
        this.transactions = new ArrayList<Transaction>();
    }

    public String getID() {
        return this.AcctID;
    }

    public String getSummaryLine() {
        //get the balance
        double balance = this.getBalance();

        //format the summary line depending on whether the balance is overdrawn - consider if needed for Group Projects

        if (balance >= 0){

            //this tells the string to format as a decimal, with 2 points
            //%s is the account id, % is the balance in brackets for negative values, then another %s for the account name
            return String.format("Account number: %s, Account Balance: %.02f , Account Type: %s", this.AcctID, balance, this.name);
        } else {
            return String.format("Account number: %s, Account Balance: (%.02f) , Account Type: %s", this.AcctID, balance, this.name);
        }

    }

    //get the balance of the account
    public double getBalance() {
        double balance = 0;
        //run a loop to go through the transactions array list - defined above - and return the balance
        for(Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    //print the transaction history of the account
    public void printTransHistory() {
        System.out.printf("\nTransaction History for account %s\n", this.AcctID);

        //this prints the transactions in reverse order, i.e. the most recent first
        for(int t = this.transactions.size()-1; t>=0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    //below called by Customer but defined here, because it creates a new Transaction object,
    // and adds it to the array list, which then allows it to be accessed
    public void addTransaction(double amount, String memo) {

        Transaction newTransaction = new Transaction(amount, memo, this);
        this.transactions.add(newTransaction);
    }
}
