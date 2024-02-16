
package Gov_connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    private int admin_Id;
    private String admin_Name;
    private String admin_Password;
    private String admin_dept;

    private Connection con;

    public void set_Id(int admin_Id){
        this.admin_Id = admin_Id;
    }

    public void set_Name(String name){
        this.admin_Name = name; 
    }

    public void set_Password(String password){
        this.admin_Password = password;
    }

    public void set_Dept(String dept){
        this.admin_dept = dept;
    }

    public int get_Id(){
        return admin_Id;
    }

    public String get_Name(){
        return admin_Name;
    }

    public String get_Password(){
        return admin_Password;
    }

    public String get_dept(){
        return admin_dept;
    }
    
    public boolean authenticateAdmin(Admin admin) {
        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT * FROM admin_table WHERE admin_name = ?";
    
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, admin.get_Name());
    
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                // Admin with the given username exists, check the password
                String storedPassword = rs.getString("admin_password");
    
                if (admin.get_Password().equals(storedPassword)) {
                    System.out.println("Authentication successful");
                    return true;
                } else {
                    System.out.println("Incorrect password");
                }
            } else {
                System.out.println("Admin not found");
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
    public void list(Scanner sc,Admin admin){
        System.out.println("(1) View the Raise Issue Table \n (2) Exit");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                admin.view_raise_issue(admin);
                break;
            case 2:

                break;
        
            default:
                System.out.println("Enter a valid choice");
                break;
        }
    }
    public void view_raise_issue(Admin admin){
        try {
            con = DBConnection.createDBConnection();
            int id = admin.get_Id();
            String query = "";
            if(id != 1)
            query = "SELECT * FROM raise_issue WHERE admin_id = ?";

            else
            query = "SELECT * FROM raise_issue";

            PreparedStatement stmt = con.prepareStatement(query);

            if(id != 1)
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();
            System.out.println("Issue Table:");
            System.out.println("admin_id | user_Name | user_Address | user_Mobile | dept | issue_type | description | status");
            System.out.println("-----------------------------------------------------------------");

            while(rs.next()) {
                int adminId = rs.getInt("admin_id");
                String user_name = rs.getString("user_name");
                String user_email = rs.getString("user_email");
                String user_address = rs.getString("user_address");
                String dept = rs.getString("department");
                String issueType = rs.getString("issue_type");
                String description = rs.getString("description");
                String status = rs.getString("status");
                System.out.printf("%-9d|%-10s|%-6s|%-11s|%-12s|%-6s%n", adminId, user_name, user_email, user_address, dept, issueType, description, status);                
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}