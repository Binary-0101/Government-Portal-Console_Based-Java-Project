package Gov_connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection con;
    public static Connection createDBConnection() throws SQLException{
        try {
            //load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //get Connection
            String url = "jdbc:mysql://localhost:3306/gov_connect?useSSL=false";
            String username = "root";
            String password="Sanjay@_17";
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
}
