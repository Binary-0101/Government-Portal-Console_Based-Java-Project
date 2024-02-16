package Gov_connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Services implements ServicesInterface {
    Scanner sc = new Scanner(System.in);
    Connection con;

    @Override
    public void raise_Issue(Register register) {
        System.out.println("Which department would you like to complain about?");
        String dept = sc.next();

        System.out.println("Select id of the Minister Name");
        System.out.println("Minister Id 1 : Mathan");
        System.out.println("Minister Id 2: John");
        System.out.println("Minister Id 3: Alice");
        System.out.println("Minister Id 4: Bob");
        int minister_id = sc.nextInt();

        System.out.println("Enter the Issue type?");

        System.out.println("(1) Infrastructure Issue");
        System.out.println("(2) Utilities Issue");
        System.out.println("(3) Waste Management");
        System.out.println("(4) Parks and Public Spaces");

        String issue_Type = "";

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                issue_Type += "Infrastructure Issue";
                break;
            case 2:
                issue_Type += "Utilities Issue";
                break;
            case 3:
                issue_Type += "Waste Management";
                break;
            case 4:
                issue_Type += "Parks and Public Spaces";
                break;

            default:
                System.out.println("Enter valid choice");
                break;
        }

        System.out.println("Enter your Complaint");
        sc.nextLine();
        String description = sc.nextLine();

        String status = "pending";

        complaint_register(register, dept, minister_id, issue_Type, description, status);
    }

    public void raise_Issue(Login login) {
        System.out.println("Which department would you like to complain about?");
        String dept = sc.next();

        System.out.println("Select id of the Minister Name");
        System.out.println("Minister Id 1 : Mathan");
        System.out.println("Minister Id 2: John");
        System.out.println("Minister Id 3: Alice");
        System.out.println("Minister Id 4: Bob");
        int minister_id = sc.nextInt();

        System.out.println("Enter the Issue type?");

        System.out.println("(1) Infrastructure Issue");
        System.out.println("(2) Utilities Issue");
        System.out.println("(3) Waste Management");
        System.out.println("(4) Parks and Public Spaces");

        String issue_Type = "";

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                issue_Type += "Infrastructure Issue";
                break;
            case 2:
                issue_Type += "Utilities Issue";
                break;
            case 3:
                issue_Type += "Waste Management";
                break;
            case 4:
                issue_Type += "Parks and Public Spaces";
                break;

            default:
                System.out.println("Enter valid choice");
                choice = sc.nextInt();
                break;
        }
        sc.nextLine();
        System.out.println("Enter your query");
        String description = sc.nextLine();

        String status = "pending";

        complaint_login(login, dept, minister_id, issue_Type, description, status);
    }

    public void complaint_login(Login login, String dept, int minister_id, String issue_Type, String description,
            String status) {
        String user_Name = login.get_Name();
        String password = login.get_Password();
        String user_email = "";
        String user_address = "";
        int user_mobile = 0;

        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT * FROM user_table WHERE user_name = ? AND user_password = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt = con.prepareStatement(query);
            stmt.setString(1, user_Name);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user_email = rs.getString("user_email");
                user_address = rs.getString("user_Address");
                user_mobile = rs.getInt("user_mobile_No");
            }
            con = DBConnection.createDBConnection();
            String query1 = "insert into raise_issue (admin_id,user_name,user_email,user_password,user_Address,user_mobile,department,issue_Type,description,status) values(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query1);

            pstmt.setInt(1, minister_id);
            pstmt.setString(2, user_Name);
            pstmt.setString(3, user_email);
            pstmt.setString(4, password);
            pstmt.setString(5, user_address);
            pstmt.setInt(6, user_mobile);
            pstmt.setString(7, dept);
            pstmt.setString(8, issue_Type);
            pstmt.setString(9, description);
            pstmt.setString(10, status);

            int cnt = pstmt.executeUpdate();

            if (cnt != 0) {
                System.out.println("Your Query Updated");
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void complaint_register(Register register, String dept, int minister_id, String issue_Type,
            String description,
            String status) {
        try {
            con = DBConnection.createDBConnection();
            String query = "insert into raise_issue (admin_id,user_name,user_email,user_password,user_Address,user_mobile,department,issue_Type,description,status) values(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, minister_id);
            pstmt.setString(2, register.getName());
            pstmt.setString(3, register.getEmail());
            pstmt.setString(4, register.getPassword());
            pstmt.setString(5, register.getAddress());
            pstmt.setString(6, register.get_mobile_no());
            pstmt.setString(7, dept);
            pstmt.setString(8, issue_Type);
            pstmt.setString(9, description);
            pstmt.setString(10, status);

            int cnt = pstmt.executeUpdate();

            if (cnt != 0) {
                System.out.println("Your Query Updated");
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void track_Issue(Register register) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String username = register.getName();
        String password = register.getPassword();
        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT description, status FROM raise_issue WHERE username = ? AND user_password = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String complaint = resultSet.getString("description");
                String status = resultSet.getString("status");

                System.out.println("Name: " + register.getName());
                System.out.println("Complaint: " + complaint);
                System.out.println("Status: " + status);
            } else {
                System.out.println("You haven't raised any issue " + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void track_Issue(Login login) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String username = login.get_Name();
        String password = login.get_Password();
        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT description, status FROM raise_issue WHERE user_name = ? AND user_password = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String complaint = resultSet.getString("description");
                String status = resultSet.getString("status");

                System.out.println("Name: " + login.get_Name());
                System.out.println("Complaint: " + complaint);
                System.out.println("Status: " + status);
            } else {
                System.out.println("You haven't raised any issue " + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aadhaar_Update(Register register, String aadhaar_no) {
        try {
            con = DBConnection.createDBConnection();
            String query = "select user_id from user_table where user_name = ? and user_password = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, register.getName());
            pst.setString(2, register.getPassword());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt("user_id");

                String update_aadhar = "update user_table SET user_Aadhar_no = ? where user_id = ?";
                PreparedStatement updatepst = con.prepareStatement(update_aadhar);
                updatepst.setString(1, aadhaar_no);
                updatepst.setInt(2, user_id);

                int rowsAffected = updatepst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Aadhaar number updated successfully.");
                } else {
                    System.out.println("Failed to update Aadhaar number.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aadhaar_Update(Login login, String aadhaar_no) {
        try {
            con = DBConnection.createDBConnection();
            String query = "select user_id from user_table where user_name = ? and user_password = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, login.get_Name());
            pst.setString(2, login.get_Password());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt("user_id");

                String update_aadhar = "update user_table SET user_Aadhar_no = ? where user_id = ?";
                PreparedStatement updatepst = con.prepareStatement(update_aadhar);
                updatepst.setString(1, aadhaar_no);
                updatepst.setInt(2, user_id);

                int rowsAffected = updatepst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Aadhaar number updated successfully.");
                } else {
                    System.out.println("Failed to update Aadhaar number.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pan_Update(Register register, String pan) {
        try {
            con = DBConnection.createDBConnection();
            String query = "select user_id from user_table where user_name = ? and user_password = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, register.getName());
            pst.setString(2, register.getPassword());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt("user_id");

                String update_pan = "update user_table SET user_pan_card = ? where user_id = ?";
                PreparedStatement updatepst = con.prepareStatement(update_pan);
                updatepst.setString(1, pan);
                updatepst.setInt(2, user_id);

                int rowsAffected = updatepst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Pan Card number updated successfully.");
                } else {
                    System.out.println("Failed to update Pan Card number.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pan_Update(Login login, String pan) {
        try {
            con = DBConnection.createDBConnection();
            String query = "select user_id from user_table where user_name = ? and user_password = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, login.get_Name());
            pst.setString(2, login.get_Password());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt("user_id");

                String update_pan = "update user_table SET user_pan_card = ? where user_id = ?";
                PreparedStatement updatepst = con.prepareStatement(update_pan);
                updatepst.setString(1, pan);
                updatepst.setInt(2, user_id);

                int rowsAffected = updatepst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Pan Card number updated successfully.");
                } else {
                    System.out.println("Failed to update Pan Card number.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Job_vaccancy(Register register) {
        try {
            con = DBConnection.createDBConnection();
            String query = "select * from job_vacancy";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            System.out.println("job_id | job_title | job_vaccancy | job_description | job_eligibility");
            System.out.println("-----------------------------------------------------------------");

            while (rs.next()) {
                int job_id = rs.getInt("job_id");
                String job_title = rs.getString("job_title");
                int job_vacancy = rs.getInt("job_vaccancy");
                String job_description = rs.getString("job_description");
                String job_eligibility = rs.getString("job_eligibility");

                // String formattedJob = String.format("%-5d | %-20s | %-10d | %-100s | %-200s",
                        // job_id, job_title, job_vacancy, job_description, job_eligibility);

                System.out.println(
                        "+-------+----------------------+------------+--------------------------------------------------+----------------------------------------------------------------------------------------------------------------+");
                System.out.println(
                        "| Job ID |      Job Title       | Vacancies  |                 Description                          |                                       Eligibility                                                          |");
                System.out.println(
                        "+-------+----------------------+------------+--------------------------------------------------+----------------------------------------------------------------------------------------------------------------+");
                System.out.println(String.format("| %-5d | %-20s | %-10d | %-100s | %-200s |", job_id, job_title,
                        job_vacancy, job_description, job_eligibility));
                System.out.println(
                        "+-------+----------------------+------------+--------------------------------------------------+----------------------------------------------------------------------------------------------------------------+");
            }

                        Services services = new Services();
                        System.out.println("Do you want to apply for the Job");
                        System.out.println("Yes(1) || No(2)");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                services.apply_Job(register);
                                break;
                            case 2:
                                System.out.println("Thank You");
                                break;
                            default:
                                System.out.println("Enter a valid choice");
                                choice = sc.nextInt();
                                break;
                        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Job_vaccancy(Login login) {
        try {
            Services services = new Services();
            con = DBConnection.createDBConnection();
            String query = "select * from job_vacancy";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            System.out.println("job_id | job_title | job_vaccancy | job_description | job_eligibility");
            System.out.println("-----------------------------------------------------------------");

            while (rs.next()) {
                int job_id = rs.getInt("job_id");
                String job_title = rs.getString("job_title");
                int job_vacancy = rs.getInt("job_vaccancy");
                String job_description = rs.getString("job_description");
                String job_eligibility = rs.getString("job_eligibility");

                // String formattedJob = String.format("%-5d | %-20s | %-10d | %-100s | %-200s",
                // job_id, job_title, job_vacancy, job_description, job_eligibility);

                System.out.println(
                        "+-------+----------------------+------------+--------------------------------------------------+----------------------------------------------------------------------------------------------------------------+");
                System.out.println(
                        "| Job ID |      Job Title       | Vacancies  |                 Description                          |                                       Eligibility                                                          |");
                System.out.println(
                        "+-------+----------------------+------------+--------------------------------------------------+----------------------------------------------------------------------------------------------------------------+");
                System.out.println(String.format("| %-5d | %-20s | %-10d | %-100s | %-200s |", job_id, job_title,
                        job_vacancy, job_description, job_eligibility));
                System.out.println(
                        "+-------+----------------------+------------+--------------------------------------------------+----------------------------------------------------------------------------------------------------------------+");

                System.out.println("Do you want to apply for the Job");
                System.out.println("Yes(1) || No(2)");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        services.apply_Job(login);
                        break;
                    case 2:

                        break;

                    default:
                        System.out.println("Enter a valid choice");
                        choice = sc.nextInt();
                        break;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apply_Job(Register register) {
        System.out.println("Select the job id");
        int job_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter you Qualification");
        String qualification = sc.nextLine();

        String user_name = register.getName();
        String user_password = register.getPassword();

        String user_email = "";
        String user_address = "";
        int user_mobile = 0;
        int user_id = 0;

        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT user_id,user_email,user_Address,user_mobile_No FROM user_table WHERE user_name = ? AND user_password = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt = con.prepareStatement(query);
            stmt.setString(1, user_name);
            stmt.setString(2, user_password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user_id = rs.getInt("user_id");
                user_email = rs.getString("user_email");
                user_address = rs.getString("user_Address");
                user_mobile = rs.getInt("user_mobile_No");
            }
            con = DBConnection.createDBConnection();
            String query1 = "insert into job_application (job_id,user_id,user_name,user_email,user_password,user_Address,user_mobile,user_qualification) values(?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query1);

            pstmt.setInt(1, job_id);
            pstmt.setInt(2, job_id);
            pstmt.setString(3, user_name);
            pstmt.setString(4, user_email);
            pstmt.setString(5, user_password);
            pstmt.setString(6, user_address);
            pstmt.setInt(7, user_mobile);
            pstmt.setString(8, qualification);

            int cnt = pstmt.executeUpdate();

            if (cnt != 0) {
                System.out.println("Job Application Successfully done");
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void apply_Job(Login login) {
        System.out.println("Select the job id");
        int job_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter you Qualification");
        String qualification = sc.nextLine();

        String user_name = login.get_Name();
        String user_password = login.get_Password();

        String user_email = "";
        String user_address = "";
        int user_mobile = 0;
        int user_id = 0;

        try {
            con = DBConnection.createDBConnection();
            String query = "SELECT user_id,user_email,user_Address,user_mobile_No FROM user_table WHERE user_name = ? AND user_password = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt = con.prepareStatement(query);
            stmt.setString(1, user_name);
            stmt.setString(2, user_password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user_id = rs.getInt("user_id");
                user_email = rs.getString("user_email");
                user_address = rs.getString("user_Address");
                user_mobile = rs.getInt("user_mobile_No");
            }
            // con = DBConnection.createDBConnection();
            String query1 = "insert into job_application (job_id,user_id,user_name,user_email,user_password,user_Address,user_mobile,user_qualification) values(?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query1);

            pstmt.setInt(1, job_id);
            pstmt.setInt(2, user_id);
            pstmt.setString(3, user_name);
            pstmt.setString(4, user_email);
            pstmt.setString(5, user_password);
            pstmt.setString(6, user_address);
            pstmt.setInt(7, user_mobile);
            pstmt.setString(8, qualification);

            int cnt = pstmt.executeUpdate();

            if (cnt != 0) {
                System.out.println("Job Application Successfully done");
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}