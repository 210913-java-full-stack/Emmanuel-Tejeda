import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateBankAccount
{
    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";


    /*
    adds a new bank account to the table depending on who is logged in at the moment.
     */
    public void createAccount(int customerBankId)
    {
        UserLogIn newLogIn = new UserLogIn();


        try
        {

            Connection conn = DriverManager.getConnection(hostUrl);

            //Check to see if the connection is alive
            boolean isValid = conn.isValid(0);

            System.out.println("Creating Bank Account");

            //Inserts information into the customer_bank_accounts
            String cbaQuery = ("INSERT INTO customer_bank_accounts(bank_id, balance) VALUES (?, ?);");


            PreparedStatement cbaStmt = conn.prepareStatement(cbaQuery);
            cbaStmt.setInt(1,customerBankId);
            cbaStmt.setFloat(2, 0);

            cbaStmt.executeUpdate();

            System.out.println("Bank Account Created..");

        }catch (Exception e)
        {

            System.err.println("Got an exception in CreateBankAccount! \n Sorry :(");
            System.err.println(e.getMessage());
        }
    }


}
