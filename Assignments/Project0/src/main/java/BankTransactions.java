import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BankTransactions
{
    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";


    /*
      Handles bank transactions
    */
    public void bankTransactions()
    {

        float userBalanceChange = 0;

        Scanner dd = new Scanner(System.in);

        System.out.println("What is the account number");
        int userBankId = dd.nextInt();
        dd.nextLine();

        System.out.println("Enter 1 to add money to your account || Enter 2 to withdraw money from your account");
        String userChoice = dd.next();
        dd.nextLine();

        /*
        makes the amount the user entered negative or leaves it positive based on the user's choice to deposit or
        withdraw money
         */
        if(userChoice.equalsIgnoreCase("1"))
        {
            try {
                System.out.println("How much money would you like to add. Enter the amount in " +
                                "the form of 1000.00 with no dollar sign ");

                userBalanceChange = Float.parseFloat(dd.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Error! Invalid Float. Try again.");
                System.out.println("Transaction Unsuccessful\n ");
                return;
            }

        }

        if(userChoice.equalsIgnoreCase("2"))
        {
            System.out.println("How much money would you like to add /n Enter the amount in the form of 1000.00 with no" +
                    "dollar sign ");
            userBalanceChange = dd.nextFloat() * -1;

        }

        System.out.println("Requested amount = " + userBalanceChange);

        //executes the change in the table
        try {

            System.out.println("Processing Transaction...");
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);

            //Has to update the users account balance

            String cbaQuery = "UPDATE customer_bank_accounts "
                    + "SET balance = GREATEST (balance + ?, 0)"
                    + "WHERE account_number = ?";

            PreparedStatement cbaStmt = conn.prepareStatement(cbaQuery);
            cbaStmt.setFloat (1,  userBalanceChange);
            cbaStmt.setInt (2, userBankId);

            cbaStmt.executeUpdate();

            System.out.println("Transaction Successful");

        }catch (Exception e) {

            System.err.println("Got an exception! \n Sorry :(");
            System.err.println(e.getMessage());
        }
    }

    //Developer tool, updates all balances in all tables to zero for testing purposes
    public void eraseBalances()
    {
        try {

            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);

            //Has to update the users account balance

            String cbaQuery = "UPDATE customer_bank_accounts "
                    + "SET balance = ?; ";

            PreparedStatement cbaStmt = conn.prepareStatement(cbaQuery);
            cbaStmt.setFloat (1,  0);

            cbaStmt.executeUpdate();

            System.out.println("All Balances changed to zero");

        }catch (Exception e) {

            System.err.println("Got an exception! \n Sorry :(");
            System.err.println(e.getMessage());
        }

    }
}
