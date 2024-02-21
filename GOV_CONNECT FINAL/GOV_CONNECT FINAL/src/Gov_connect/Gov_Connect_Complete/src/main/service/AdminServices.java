package main.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.model.DBConnection;
import main.model.Issue;
import main.queries.AdminPrivilegesQueries;
import main.queries.AuthQueries;

public class AdminServices {
    public static boolean CheckUserCredentials(String emailId, String password) {
        String query = AuthQueries.adminSingIn();
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

    public static int getIdByAdminEmail(String AdminEmail, String AdminPassword) {
        String query = AdminPrivilegesQueries.getIdOfAdminByEmail();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setString(1, AdminEmail);
                preparedStatement.setString(2, AdminPassword);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int adminId = rs.getInt(1);
                    return adminId;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return 0;
    }

    public static List<Issue> viewIssuesTableOfAdmin(int adminId) {
        List<Issue> issuesList = new ArrayList<>();
        String query = AdminPrivilegesQueries.viewIssueTable();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setInt(1, adminId);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String issue = rs.getString("Issue");
                    int minister_id = rs.getInt("Minister_Id");
                    String status = rs.getString("status");
                    issuesList.add(new Issue(userId, issue, minister_id, status));
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return issuesList;
    }

    public static boolean changeStatusOfIssue(int adminId) {
        String query = AdminPrivilegesQueries.changeStatusQuery();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if (getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                preparedStatement.setInt(1, adminId);
                int rowsUpdated = preparedStatement.executeUpdate();
                return rowsUpdated > 0; 
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }
}