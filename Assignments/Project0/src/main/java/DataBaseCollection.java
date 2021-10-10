import java.sql.*;
import java.util.Scanner;

public class DataBaseCollection extends MyLinkedList {

    private String tableName;
    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";


/*
This class was used in order to create the first methods and holds the show all tables. All of these functionalities
are already implemented in their own separate class.
 */
    // -----------------------------------------------------------------------------------------------------------------
    // Get users account info
    public void getAccountInfo()
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your account ID");
        int userInput = myObj.nextInt();  // Read user input


        try
        {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);


            //creating a sequel statement with a condition
            PreparedStatement ps = conn.prepareStatement( "SELECT * " +
                        "FROM customer_personal_information AS cpi " +
                        "INNER JOIN customer_bank_info AS cbi " +
                        "ON cpi.customer_id = cbi.customer_id " +
                        "INNER JOIN customer_bank_accounts AS cba " +
                        "ON cba.bank_id = cbi.bank_id " +
                        "WHERE cpi.customer_id = ?;");

            //fills in the condition
            ps.setObject(1, userInput);

            ResultSet rs = ps.executeQuery();

            System.out.println("Your Info");
            System.out.println("---------------------------------------");
            while (rs.next())
            {
                int customer_id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String customerEmail = rs.getString("email");
                String userName = rs.getString("account_username");
                String userPassword = rs.getString("account_password");
                String customerBankId = rs.getString("bank_id");
                float customerBalance = rs.getFloat("balance");

                System.out.println(customer_id + " " + customerBankId + " " + userName + " " + userPassword + " "
                        + first_name + " " + last_name + " " + customerEmail + " " + customerBalance);
            }


            rs.close();
            ps.close();

            conn.close();


        } catch (Exception e) {

            e.printStackTrace();
            //Handle exception better
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    /*
    Handles bank transactions
     */
    public void bankTransactions(int existingBankId)
    {
        Scanner dd = new Scanner(System.in);

        System.out.println("Press 1 to add money to your account || Press 2 to withdraw money from your account");
        String userChoice = dd.nextLine();

        float userBalanceChange = 0;

        if(userChoice.equalsIgnoreCase("1") == true)
        {
            System.out.println("How much money would you like to add /n Enter the amount in the form of 1000.00 with no" +
                    "dollar sign ");
            userBalanceChange = dd.nextFloat();
        }

        if(userChoice.equalsIgnoreCase("2") == true)
        {
            System.out.println("How much money would you like to add /n Enter the amount in the form of 1000.00 with no" +
                    "dollar sign ");
            userBalanceChange = dd.nextFloat() * -1;

        }


        try {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);

            //Has to update the users account balance

            String cbaQuery = "UPDATE customer_bank_accounts"
                    + "SET balance = balance + ?"
                    + "WHERE bank_id = ?";

            PreparedStatement cbaStmt = conn.prepareStatement(cbaQuery);
            cbaStmt.setFloat (1, userBalanceChange);
            cbaStmt.setInt (2, existingBankId);

        }catch (Exception e) {

            System.err.println("Got an exception! \n Sorry :(");
            System.err.println(e.getMessage());
        }
    }




    /*
    Developer tool to show everyone's information from the table
     */

    public void showAllTables() {

        try {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);


            //creating a sequel statement with a condition
            PreparedStatement ps = conn.prepareStatement("SELECT * " +
                    "FROM customer_personal_information AS cpi " +
                    "INNER JOIN customer_bank_info AS cbi " +
                    "ON cpi.customer_id = cbi.customer_id " +
                    "INNER JOIN customer_bank_accounts AS cba " +
                    "ON cba.bank_id = cbi.bank_id; ");


            ResultSet rs = ps.executeQuery();

            System.out.println("---------------------------------------");

            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String customerEmail = rs.getString("email");
                String userName = rs.getString("account_username");
                String userPassword = rs.getString("account_password");
                String customerBankId = rs.getString("bank_id");
                float customerBalance = rs.getFloat("balance");
                int customerAccountNumbers = rs.getInt("account_number");

                System.out.println(customer_id + " " + firstName + " " + lastName + " " + customerEmail + " " +
                        userName + " " + userPassword + " " + customerBankId + " " +customerBalance + " " + customerAccountNumbers);
            }


        } catch (Exception e) {

            System.err.println("Got an exception in showAllTables! \n Sorry :(");
            System.err.println(e.getMessage());
        }
    }

}
