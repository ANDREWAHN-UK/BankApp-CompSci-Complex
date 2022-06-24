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


    public double getAmount() {
        return this.amount;
    }
//see printAccountSummary in Customer.java for explanation
    public String getSummaryLine() {

        //the if relates to the deposits and the else to withdrawals, hence the () in the formatting.

        //£ does not properly display here
        if(this.amount >= 0){
            return String.format("Date and time :%s\n Amount : %.02f\n Memo: %s\n",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("Date and time :%s\n Amount : (%.02f)\n Memo: %s\n",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        }
    }
}
