import java.util.Date;

public class Transaction {

        //the amount of this transaction
    private double amount;

        //the time and date of the transaction
    private Date timestamp;

        //a memo for this transaction
    private String memo;

        //the account in which the transaction was performed
    private Account inAccount;

    //one with the memo, and one without. This is overloading.
    public Transaction( double amount, Account inAccount){

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo="";

    }

    public Transaction( double amount,String memo, Account inAccount){
        //call the 2 argument constructor first, i.e. the one above
        this(amount,inAccount);


        //then set the memo
        this.memo = memo;
    }


}
