import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        //initialise the scanner
        Scanner sc = new Scanner(System.in);

        //initialise the bank
        Bank theBank = new Bank("Novus Banking Services");

        //add a customer to the bank, which also creates a savings account - for Group Project this is handled separately
        //this is hard coded here for testing purposes
        //for Group Project we need something to interact with, i.e. menus, to create customers and accounts

        Customer aCustomer = theBank.addCustomer("Mister","Test");

        //create an ISA for testing purposes
        Account anAccount = new Account("ISA",aCustomer,theBank);
        aCustomer.addAccount(anAccount);
        theBank.addAccount(anAccount);

        Customer currentCustomer;
        while(true){

            //'sc' below refers to the scanner, which gets passed to the methods
            //remove or redo for group project
            //stay in the login prompt until successful login
            currentCustomer = ATM.mainMenuPrompt(theBank, sc);

            //stay in main menu until user quits
            ATM.printUserMenu(currentCustomer, sc);

        }

    }

    private static void printUserMenu(Customer currentCustomer, Scanner sc) {

        //print a summary of user accounts
        //defined in Customer.java
        currentCustomer.printAccountsSummary();

        //initialise
        int userChoice;

        //user menu
        do
        {
            System.out.printf("Welcome %s, what would you like to do?: \n", currentCustomer.getFirstName());
            System.out.println(" 1. Show Account Transaction History");
            System.out.println(" 2. Make a withdrawal");
            System.out.println(" 3. Make a deposit");
            System.out.println(" 4. Make a Transfer");
            System.out.println(" 5. Exit");
            System.out.println();
            System.out.println(" Enter your choice");
            userChoice = sc.nextInt();
            //for the group project we should also have a "go back to menu option
            if (userChoice <1 || userChoice > 5){
                System.out.println("Invalid choice, please choose 1 - 5.");
            }
        } while( userChoice <1 || userChoice > 5);

        //process the userChoice

        switch (userChoice) {
            case 1 -> ATM.showTransactionHistory(currentCustomer, sc);
            case 2 -> ATM.withdrawFunds(currentCustomer, sc);
            case 3 -> ATM.depositFunds(currentCustomer, sc);
            case 4 -> ATM.transferFunds(currentCustomer, sc);


            //in the video there is no default case, but I think we need one

        }

        //redisplay the menu unless the user wants to quit

        //the below is recursion, calling a method within a method. Time to watch Inception again.
            if(userChoice !=5){
                ATM.printUserMenu(currentCustomer, sc);
        }

    }

    //the below methods match the menu options above.
    //as the menu expands, define the new methods below
    public static void showTransactionHistory(Customer currentCustomer, Scanner sc) {

        int theAccount;
        //get the account for which we wish to show the history

        do{
            //account is chosen from the arraylist. Number refers to the account in the array list, not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "you wish to see the history of:",  currentCustomer.numAccounts());

            theAccount = sc.nextInt()-1;
            if(theAccount < 0  || theAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while(theAccount < 0  || theAccount >= currentCustomer.numAccounts());

        //print transactions history
        currentCustomer.printAccountTransactionHistory(theAccount);
    }

    public static void transferFunds(Customer currentCustomer, Scanner sc){
        //initialise
        //we need a source account, destination account and amount to transfer
        //some logic to ensure amount sent accords with balance

        int fromAccount;
        int toAccount;
        double transferAmount;
        double accountBalance;

        //get the source account - same logic, i.e. accessing an array of accounts, as showTransactionHistory();
        do{
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "to transfer from:", currentCustomer.numAccounts());
            fromAccount=sc.nextInt()-1;
            if(fromAccount < 0 || fromAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while(fromAccount < 0 || fromAccount >= currentCustomer.numAccounts());

        //now we need the account balance, from the account just chosen

        accountBalance = currentCustomer.getAccountBalance(fromAccount);

        //get the destination account
        do{
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "to transfer to:",  currentCustomer.numAccounts());
            toAccount=sc.nextInt()-1;
            if(toAccount < 0 || toAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while(toAccount < 0 || toAccount >= currentCustomer.numAccounts());

        //get the amount to transfer
        do{
            System.out.printf("Enter the amount to transfer( max %.02f): ", accountBalance);
            transferAmount= sc.nextDouble();

            //logic below stops transferring negative funds, or exceeding account balance.
            //remember to factor in ISA minimum account balance for group project
            if(transferAmount <0){
                System.out.println("Amount must be greater than zero!");
            } else if (transferAmount > accountBalance){
                System.out.println("You cannot transfer more than your balance\nof %.02f.\n");
            }
        } while(transferAmount < 0 || transferAmount > accountBalance);

        //now transfer can occur
        //note addAccountTransaction + getAcctId are defined in Customer
        currentCustomer.addAccountTransaction(fromAccount, -1*transferAmount, String.format("Origin account %s",
                currentCustomer.getAcctID(toAccount)));
        currentCustomer.addAccountTransaction(toAccount, transferAmount, String.format("Destination account %s",
                currentCustomer.getAcctID(fromAccount)));

    }

    private static void withdrawFunds(Customer currentCustomer, Scanner sc) {

        //the logic here is similar to transferFunds();
        int fromAccount;
        double withdrawAmount;
        double accountBalance;
        String memo;

        //get the account to withdraw from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to withdraw from: ", currentCustomer.numAccounts());
            fromAccount = sc.nextInt()-1;
            if(fromAccount < 0 || fromAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while (fromAccount < 0 || fromAccount >= currentCustomer.numAccounts());

        //now we need the account balance, from the account just chosen
        accountBalance= currentCustomer.getAccountBalance(fromAccount);

        //get the amount to transfer
        do{
            System.out.printf("Enter the amount to withdraw( max %.02f): ", accountBalance);
            withdrawAmount= sc.nextDouble();

            //logic below stops withdrawing negative funds, as that would be basically a deposit
            // or exceeding account balance.
            //remember to factor in ISA minimum account balance for group project
            if(withdrawAmount <0){
                System.out.println("Amount must be greater than zero!");
            } else if (withdrawAmount > accountBalance){
                System.out.printf("You cannot withdraw more than your balance\nof %.02f.\n", accountBalance);
                ATM.printUserMenu(currentCustomer,sc);
            }
        } while(withdrawAmount < 0 || withdrawAmount > accountBalance);



        sc.nextLine();

        //now the memo - this is useful for the customer/admin to add a note about the withdrawal.
        // It can safely be dispensed with, ergo consider removing for group project,
        //that would mean redefining addAccountTransaction();
        System.out.println("Enter a memo");
        memo= sc.nextLine();

        //finally allow the withdrawal
        currentCustomer.addAccountTransaction(fromAccount,-1*withdrawAmount, memo);
    }

    private static void depositFunds(Customer currentCustomer, Scanner sc) {

        //the logic here is basically an inversion of withdrawFunds();
        int toAccount;
        double depositAmount;
        String memo;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to deposit to: ", currentCustomer.numAccounts());
            toAccount = sc.nextInt()-1;
            if(toAccount < 0 || toAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while (toAccount < 0 || toAccount >= currentCustomer.numAccounts());

        //get the amount to deposit
        do{
            System.out.println("Enter the amount to deposit");
            depositAmount= sc.nextDouble();

            //logic below stops users depositing a negative amount, as that would effectively be a withdrawal.
            //remember to factor in ISA minimum account balance for group project
            if(depositAmount <0){
                System.out.println("You cannot deposit less than zero");
            }

        } while(depositAmount < 0);

        sc.nextLine();

        //now the memo - this is useful for the customer/admin to add a note about the deposit.
        // It can safely be dispensed with, ergo consider removing for group project,
        //that would mean redefining addAccountTransaction();
        System.out.println("Enter a memo");
        memo= sc.nextLine();

        //finally allow the deposit
        currentCustomer.addAccountTransaction(toAccount,depositAmount, memo);
        ATM.printUserMenu(currentCustomer,sc);
    }

    private static Customer mainMenuPrompt(Bank theBank, Scanner sc) {

        //this will need to be redone, or removed, for the group project
        //initialise
        String CustomerID;
        Customer AuthCustomer;

        do{
            //prompt user for log in details until correct input is achieved

            System.out.printf("Welcome to %s\n\n", theBank.getName());
            System.out.print("Enter Customer ID:");
            CustomerID = sc.nextLine();

            //ignoring PIN stuff from the video

            //try to get the Customer object that corresponds to the ID
            AuthCustomer = theBank.customerLogin(CustomerID);
            if (AuthCustomer == null){
                System.out.println("Incorrect User. \n Try again.");
            }
        } while( AuthCustomer == null);//continues looping until successful login            

        return AuthCustomer;
    }

}
