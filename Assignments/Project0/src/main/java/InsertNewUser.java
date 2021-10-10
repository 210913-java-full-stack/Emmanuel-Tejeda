import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

public class InsertNewUser {

    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";
    int customerId = 1;
    int bankId = 1;

    // -----------------------------------------------------------------------------------------------------------------

    /*
    When the program terminates, this method assures the bankID  and the customerID for the next user that is created
    will be accurate
     */
    public void setValues()
    {
        try
        {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);

            String query = "SELECT * FROM customer_personal_information ORDER BY customer_id DESC LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.customerId = rs.getInt("customer_id") + 1;
            this.bankId = rs.getInt("customer_id") + 1;


        } catch (Exception e)
        {
            System.err.println("Got an exception in setValues! \n Sorry :(");
            System.err.println(e.getMessage());
        }
    }


    //Checks to see if string contains only letters
    public boolean validateString(String str)
    {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z'))
            {
                System.out.println("Invalid first or last name. Can only contain letters in the alphabet!");
                return false;
            }
        }
        return true;
    }

    //checks to see if the email entered by the user is accurate
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }



    /*
  This method allows the user to register for an account. For the programmer, this asks the user the needed info,
  inserts that information into the table and creates a linked list object with that users information
   */
    public void insertNewUser()
    {

        setValues();

        Scanner dd = new Scanner(System.in);

        System.out.println("Enter your first name");
        String usersFirstName = dd.nextLine();

        System.out.println("Enter your last name.");
        String userLastName = dd.nextLine();
        System.out.println("Enter your email address.");
        String userEmail = dd.nextLine();
        System.out.println("Enter your username.");
        String userName = dd.nextLine();
        System.out.println("Enter your password.");
        String userPassword = dd.nextLine();

        if(isValidEmail(userEmail) == false)
        {
            System.out.println("Invalid Email format");
            System.exit(0);
        }


        boolean x = validateString(usersFirstName);
        boolean y = validateString(userLastName);

        if(x == false || y == false)
        {
            return;
        }



        try
        {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);

            System.out.println("Creating New Account...");

            //Inserts information into the customer_personal_information table
            String cpiQuery = ( "INSERT INTO customer_personal_information(first_name, last_name, email) " +
                    "VALUES (?, ?, ? );");


            PreparedStatement cpiStmt = conn.prepareStatement(cpiQuery);
            cpiStmt.setString (1, usersFirstName);
            cpiStmt.setString (2, userLastName);
            cpiStmt.setString (3, userEmail);

            cpiStmt.executeUpdate();


            //Inserts information into the customer_bank_info table
            String cbiQuery = ("INSERT INTO customer_bank_info" +
                    "(customer_id, account_username, account_password) VALUES (?, ?, ?);");

            PreparedStatement cbiStmt = conn.prepareStatement(cbiQuery);
            cbiStmt.setInt(1, this.customerId);
            cbiStmt.setString (2, userName);
            cbiStmt.setString (3, userPassword);

            cbiStmt.executeUpdate();


            //Inserts information into the customer_bank_accounts
            String cbaQuery = ("INSERT INTO customer_bank_accounts(bank_id, balance) VALUES (?, ?);");


            PreparedStatement cbaStmt = conn.prepareStatement(cbaQuery);
            cbaStmt.setInt(1, this.bankId);
            cbaStmt.setFloat(2, 0);

            cbaStmt.executeUpdate();

            System.out.println("Account Created!");



        } catch (Exception e) {

            System.err.println("Got an exception in insertNewUser! \n Sorry :(");
            System.err.println(e.getMessage());
        }


    }

    // -----------------------------------------------------------------------------------------------------------------
    /*
    Developer Tool. Will become inaccessible to the user once the program is done
     */
    public void deleteDatabase()
    {
        this.bankId = 0;
        this.customerId = 0;

        try
        {
            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);



            Statement stmt = conn.createStatement();
            String cbaQuery = "DELETE FROM customer_bank_accounts;";
            stmt.executeUpdate(cbaQuery);

            String cbiQuery = "DELETE FROM customer_bank_info;";
            stmt.executeUpdate(cbiQuery);

            String cpiQuery = "DELETE FROM customer_personal_information;";
            stmt.executeUpdate(cpiQuery);

            //Renew the Auto increment to 1 when deleting all the tables
            String cbaQueryAI = "ALTER TABLE customer_bank_accounts AUTO_INCREMENT = " + 1;
            String cbiQueryAI = "ALTER TABLE customer_bank_info AUTO_INCREMENT = " + 1;
            String cpiQueryAI = "ALTER TABLE customer_personal_information AUTO_INCREMENT = " + 1;


            stmt.executeUpdate(cbaQueryAI);
            stmt.executeUpdate(cbiQueryAI);
            stmt.executeUpdate(cpiQueryAI);


            System.out.println("Successfully truncated all tables");

        } catch (SQLException e) {

            System.out.println("Could not truncate test_table " + e.getMessage());
        }
    }
}
