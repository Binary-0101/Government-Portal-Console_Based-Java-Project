package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DBConnection {
    static Connection connection;
    private DBConnection() throws SQLException{
        try {
            //load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //get Connection
            String url = "jdbc:mysql://localhost:3306/gov_connect_final";
            String username = "root";
            String password="Sanjay@_17";
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void connect(){
        // Created a singleton Class
        try {
            DBConnection db = new DBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Optional<PreparedStatement> getPreparedStatement(String query){
        try {
            return Optional.of(connection.prepareStatement(query));
        }catch (SQLException sqlException){
            sqlException.fillInStackTrace();
        }
        return Optional.empty();
    }

}