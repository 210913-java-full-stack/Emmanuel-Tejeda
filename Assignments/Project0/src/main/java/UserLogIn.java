import java.sql.*;
import java.util.Scanner;

public class UserLogIn
{
    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";

    private String userName;
    private String userPassword;
    private int bankId;

    /*
    Handles asking the user the proper information to log in. Then uses that information from the try block to create
     variables that can be accessed by getter methods. This information is used by other methods in order to get
     the right information for the user.
     */
    public boolean LogIn()
    {
        System.out.println("Welcome! Enter the following information to Log In to your account");
        Scanner dd = new Scanner(System.in);

        System.out.println("Enter your username");
        String userName = dd.nextLine();
        System.out.println("Enter your password");
        String userPassword = dd.nextLine();

        try {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);


            //creating a sequel statement with a condition
            PreparedStatement pstmt = conn.prepareStatement("SELECT * " +
                    "FROM customer_personal_information AS cpi " +
                    "INNER JOIN customer_bank_info AS cbi " +
                    "ON cpi.customer_id = cbi.customer_id " +
                    "INNER JOIN customer_bank_accounts AS cba " +
                    "WHERE account_username = ?" +
                    "AND account_password = ?; ");


            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);


            pstmt.executeUpdate();

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            String customerFirstName = rs.getString("first_name");
            String customerLastName = rs.getString("last_name");
            String customerUserName = rs.getString("account_username");
            String customerPassword = rs.getString("account_password");
            int customerBankID = rs.getInt("cbi.bank_id");


            this.userName = customerUserName;
            this.userPassword = customerPassword;
            this. bankId = customerBankID;

            System.out.println(customerFirstName + " " + customerLastName);



        } catch (Exception e) {

            System.err.println("Sorry, the username or password you entered does not match our records!");
            return false;
        }
        return true;
    }

    //Getter methods for other classes to get the correct information
    public String getUsername()
    {
        return userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public int getBankId()
    {
        return bankId;
    }

}