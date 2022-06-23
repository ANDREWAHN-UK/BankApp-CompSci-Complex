import java.util.ArrayList;

public class Account {

    private String name;

    private double balance;

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
}
