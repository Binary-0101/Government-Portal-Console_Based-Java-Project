package Gov_connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class User implements User_Interface{
    Connection con;
    private int id = 0;
    private String login_Name;

    @Override
    public void create_User(Register register) {
        try{
            con = DBConnection.createDBConnection();
            String query = "insert into user_table (user_name,user_email,user_password,user_Address,user_mobile_No,user_Aadhar_no) values(?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1,register.getName());
            pstmt.setString(2,register.getEmail());
            pstmt.setString(3,register.getPassword());
            pstmt.setString(4,register.getAddress());
            pstmt.setString(5,register.get_mobile_no());
            pstmt.setString(6,register.get_aadhar_no());

            int cnt = pstmt.executeUpdate();

            if(cnt != 0){
                System.out.println("Profile Created");
            }
            pstmt.close();;
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Somthing went wrong. Try again ):");
            return;
        }
    }
    
    @Override
    public boolean authenticateUser(Login login) {
        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT * FROM user_table WHERE user_name = ?";
    
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, login.get_Name());
    
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                // User with the given username exists, check the password
                String storedPassword = rs.getString("user_password");
    
                if (login.get_Password().equals(storedPassword)) {
                    System.out.println("Authentication successful");
                    return true;
                } else {
                    System.out.println("Incorrect password");
                    return false;
                }
            } else {
                System.out.println("User not found");
            }
    
            // Close the ResultSet, PreparedStatement, and Connection
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    
    
}
