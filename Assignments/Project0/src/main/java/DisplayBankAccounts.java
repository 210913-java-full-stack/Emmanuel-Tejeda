import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Scanner;

public class DisplayBankAccounts
{

    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";


    //Displays all the user bank accounts using the username and password they provided to log in
    public void DisplayAccounts(String customerUserName, String customerPassword)
    {

        UserLogIn newLogIn = new UserLogIn();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        try {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);


            //creating a sequel statement with a condition
            PreparedStatement pstmt = conn.prepareStatement("SELECT first_name, last_name, balance, cba.bank_id, account_number " +
                    "FROM customer_personal_information AS cpi " +
                    "INNER JOIN customer_bank_info AS cbi " +
                    "ON cpi.customer_id = cbi.customer_id " +
                    "INNER JOIN customer_bank_accounts AS cba " +
                    "ON cba.bank_id = cbi.bank_id " +
                    "WHERE account_username = ?" +
                    "AND account_password = ?; ");


            pstmt.setString (1, customerUserName);
            pstmt.setString (2, customerPassword);


            pstmt.executeUpdate();

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String customersBankId = rs.getString("bank_id");
                float customerBalance = rs.getFloat("balance");
                int customerAccountNumbers = rs.getInt("account_number");
                String customerFirstName = rs.getString("first_name");
                String customerLastName = rs.getString("last_name");


                System.out.println(customerFirstName + " " +
                        customerLastName + " " +
                        "Your Bank ID = " + customersBankId + " " +
                        "Your Balance is = $" + decimalFormat.format(customerBalance) + " " +
                        "The account number is = " + customerAccountNumbers + " ");
            }

            System.out.println("\n");


        } catch (Exception e)
        {

            System.err.println("Got an exception in DisplayAccounts! \n Sorry :(");
            System.err.println(e.getMessage());
        }

    }

}
