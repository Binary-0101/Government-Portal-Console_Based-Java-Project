package main.service;

import main.model.DBConnection;
import main.model.Issue;
import main.queries.AuthQueries;
import main.queries.UserPrivilegesQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    public static boolean CheckAdminCredentials(String emailId, String password) {
        String query = AuthQueries.userSignIn();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setString(1, emailId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                String dbPassword = rs.getString(1);
                return dbPassword.equals(password);
            } catch (SQLException se) {
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static boolean checkUserAlreadyInDb(String emailId) {
        String query = AuthQueries.userInDb();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setString(1, emailId);
                ResultSet rs = preparedStatement.executeQuery();
                return rs.next();
            } catch (SQLException se) {
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static boolean addUserToDb(String uName, String emailId, String password, String gender, String dob, String fathername, String address, int pincode, String mobile_no,
            String aadhaar) {
        String query = AuthQueries.userSignUp();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setString(1, uName);
                preparedStatement.setString(2, emailId);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, dob);
                preparedStatement.setString(6, fathername);
                preparedStatement.setString(7, address);
                preparedStatement.setInt(8, pincode);
                preparedStatement.setString(9, mobile_no);
                preparedStatement.setString(10, aadhaar);
                preparedStatement.execute();
                return true;
            } catch (SQLException se) {
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static void getAlltheJob() {
        String query = UserPrivilegesQueries.getListOfJob();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("===============================================");
                System.out.println("|                 List of Jobs                |");
                System.out.println("===============================================");
                while (rs.next()) {
                    System.out.println("===================================================");
                    System.out.printf("| %-15s | %-30s |\n", "Job ID", rs.getInt("job_id"));
                    System.out.printf("| %-15s | %-30s |\n", "Job Title", rs.getString("job_Title"));
                    System.out.printf("| %-15s | %-30s |\n", "Job Vaccancy", rs.getInt("job_vaccancy"));
                    System.out.printf("| %-15s | %-30s |\n", "Job Description", rs.getString("job_description"));
                    System.out.printf("| %-15s | %-30s |\n", "Requirements", rs.getString("requirements"));
                    System.out.println("===================================================");
                }

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static int getUserEmail(String userEmail, String userPassword) {
        String query = UserPrivilegesQueries.getUserIdByEmail();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2, userPassword);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    return userId;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return -1;
    }
    
    public static boolean addUserToApplyForJob(int user_id,int jobId, String qualification) {
        String query = UserPrivilegesQueries.jobApplication();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setInt(1, user_id);
                preparedStatement.setInt(2, jobId);
                preparedStatement.setString(3, qualification);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insertIntoIssueTable(int user_id, Issue issue) {
        String query = UserPrivilegesQueries.issueQuery();
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = DBConnection.getPreparedStatement(query).orElse(null);
            
            if (preparedStatement != null) {
                preparedStatement.setInt(1, user_id);
                preparedStatement.setString(2, issue.getComplaint());
                preparedStatement.setInt(3, issue.getMinisterId());
                preparedStatement.setString(4, issue.getMinisterDept());
                preparedStatement.setString(5, issue.getStatus());
                
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public static void trackOfIssue(int user_id) {
        String query = UserPrivilegesQueries.trackIssue();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setInt(1, user_id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String issue = rs.getString("Issue");
                    int minister_id = rs.getInt("Minister_Id");
                    String minister_dept = rs.getString("Minister_dept");
                    String status = rs.getString("status");
                    
                    System.out.println("===================================================");
                    System.out.printf("| %-15s | %-30s |\n", "Issue", issue);
                    System.out.printf("| %-15s | %-30s |\n", "Minister ID", minister_id);
                    System.out.printf("| %-15s | %-30s |\n", "Minister Dept", minister_dept);
                    System.out.printf("| %-15s | %-30s |\n", "Status", status);
                    System.out.println("===================================================");
                }                
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public static boolean updateNameOfUser(int userId,String uName) {
        String query = UserPrivilegesQueries.nameUpdateQuery(); 
        Optional<PreparedStatement> optionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (optionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setString(1, uName);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }
    public static boolean updateFatherNameOfUser(int userId,String uFatherName) {
        String query = UserPrivilegesQueries.fathernameUpdateQuery(); 
        Optional<PreparedStatement> optionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (optionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setString(1, uFatherName);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }
    public static boolean updateDOBNameOfUser(int userId,String dob) {
        String query = UserPrivilegesQueries.dobUpdateQuery(); 
        Optional<PreparedStatement> optionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (optionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setString(1, dob);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }
    public static boolean updateAddressNameOfUser(int userId,String address) {
        String query = UserPrivilegesQueries.addressUpdateQuery(); 
        Optional<PreparedStatement> optionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (optionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setString(1, address);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }
}
