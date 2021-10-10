import java.util.Scanner;

public class UI
{

    //Compares two strings. returns true if the match
    private boolean comparator(String compare, String userInput)
    {
        return compare.equalsIgnoreCase(userInput);
    }


    //Creates the UI
    public void startUI()
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        DataBaseCollection currentDisplay = new DataBaseCollection();

        InsertNewUser newInsert = new InsertNewUser();


        BankTransactions newTransaction = new BankTransactions();

        CreateBankAccount newBankAccount = new CreateBankAccount();

        DisplayBankAccounts displayAccounts = new DisplayBankAccounts();

        UserLogIn newLogIn = new UserLogIn();



        System.out.println("If you are a new user Enter 1 to register an account. Otherwise enter anything to Log in");

        String userChoice = myObj.nextLine();

        if (comparator(userChoice, "1")) {
            newInsert.insertNewUser();

        }

        if(!newLogIn.LogIn())
        {

            System.exit(0);
        }



        //User Interfaces
        while (true)
        {
            System.out.println("Enter 0 to exit");
            System.out.println("Enter 3 delete the database");
            System.out.println("Enter 4 to show all Tables");
            System.out.println("Enter 5 to make a transaction");
            System.out.println("Enter 7 to create another bank account");
            System.out.println("Enter 8 to display all your bank accounts");


            String userInput = myObj.nextLine();  // Read user input

            //exits the console
            if (comparator(userInput, "0")) {
                System.out.println("Goodbye!");
                return;
            }
            //deletes the database
            if (comparator(userInput, "3")) {
                newInsert.deleteDatabase();

            }
            //shows all the created tables
            if (comparator(userInput, "4")) {
                currentDisplay.showAllTables();
            }
            //allows the user to make a transaction
            if (comparator(userInput, "5")) {
                newTransaction.bankTransactions();
            }
            //erases all the balances
            if (comparator(userInput, "6")) {
                newTransaction.eraseBalances();
            }
            //creates a new bank account
            if (comparator(userInput, "7")) {
                newBankAccount.createAccount(newLogIn.getBankId());
            }
            //Display All bank accounts belonging to the logged-in user
            if (comparator(userInput, "8")) {
                displayAccounts.DisplayAccounts(newLogIn.getUsername(), newLogIn.getUserPassword());

            }


        }

    }
}



