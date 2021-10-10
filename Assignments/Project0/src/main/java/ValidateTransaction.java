import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ValidateTransaction
{
    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";


    /*
    This is a class that was not implemented. It would have checked to make sure that the transactions made by
    a customer could not access a different customers bank account and withdraw or deposit money in those inappropriate
    accounts
     */
    public int Validate()
    {

        MyLinkedList sList = new MyLinkedList();

        try
        {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);


            //creating a sequel statement with a condition
            PreparedStatement pstmt = conn.prepareStatement("SELECT account_number " +
                    "FROM customer_personal_information AS cpi " +
                    "INNER JOIN customer_bank_info AS cbi " +
                    "ON cpi.customer_id = cbi.customer_id " +
                    "INNER JOIN customer_bank_accounts AS cba " +
                    "ON cba.bank_id = cbi.bank_id " +
                    "WHERE account_username = ?" +
                    "AND account_password = ?; ");


            pstmt.setString (1, "manny123");
            pstmt.setString (2, "password");


            pstmt.executeUpdate();

            ResultSet rs = pstmt.executeQuery();
            int customerAccountNumbers;

            if(sList.head == null)
            {
                while (rs.next())
                {
                    customerAccountNumbers = rs.getInt("account_number");
                    sList.addNode(customerAccountNumbers);

                    System.out.println(customerAccountNumbers);
                }
            }

            System.out.println("\n");


        } catch (Exception e)
        {

            System.err.println("Got an exception in DisplayAccounts! \n Sorry :(");
            System.err.println(e.getMessage());
        }

        Scanner dd = new Scanner(System.in);

        System.out.println("These are your account numbers. " +
                "Select where you want to make your transaction");

        int userChoice = dd.nextInt();


        return userChoice;
    }
}
