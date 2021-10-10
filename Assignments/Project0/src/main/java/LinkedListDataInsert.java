import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LinkedListDataInsert
{
    private final String hostUrl = "jdbc:mariadb://training-db.cbnfod65220d." +
            "us-east-2.rds.amazonaws.com:3306/Project2?user=admin&password=Compusa10baez";

    MyLinkedList sList = new MyLinkedList();


    public MyLinkedList showAllTables()
    {


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

            while (rs.next()) {

                int customer_id = rs.getInt("customer_id");
                sList.addNode(customer_id);

                String firstName = rs.getString("first_name");
                sList.addNode(firstName);

                String lastName = rs.getString("last_name");
                sList.addNode(lastName);

                String customerEmail = rs.getString("email");
                sList.addNode(customerEmail);

                String userName = rs.getString("account_username");
                sList.addNode(userName);

                String userPassword = rs.getString("account_password");
                sList.addNode(userPassword);

                String customerBankId = rs.getString("bank_id");
                sList.addNode(customerBankId);

                float customerBalance = rs.getFloat("balance");
                sList.addNode(customerBalance);
            }

        } catch (Exception e) {

            System.err.println("Got an exception in showAllTables! \n Sorry :(");
            System.err.println(e.getMessage());
        }

        return sList;
    }

}
