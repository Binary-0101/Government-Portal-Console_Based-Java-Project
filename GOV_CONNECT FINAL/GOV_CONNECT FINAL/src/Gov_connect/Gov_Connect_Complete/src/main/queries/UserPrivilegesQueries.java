package main.queries;

public class UserPrivilegesQueries {
    public static String getListOfJob(){
        return "SELECT * FROM job_vaccancy";
    }
    public static String jobApplication() {
        return "INSERT INTO jobapplication (userId, jobId, user_qualification) " +
                "VALUES (?, ?, ?)";
    }
    public static String getUserIdByEmail() {
        return "SELECT user_id FROM users WHERE emailId = ? AND password = ?";
    }
    public static String issueQuery() {
        return "INSERT INTO issue_table (user_id, Issue, Minister_Id, Minister_dept, status) " +
        "VALUES (?, ?, ?, ?, ?)";
    }
    public static String trackIssue() {
        return "SELECT * FROM issue_table WHERE user_id = ?";
    }
    public static String nameUpdateQuery() {
        return "UPDATE users SET user_name = ? WHERE user_id = ?";
    }
    public static String fathernameUpdateQuery() {
        return "UPDATE users SET fatherName = ? WHERE user_id = ?";
    }
    public static String dobUpdateQuery() {
        return "UPDATE users SET dob = ? WHERE user_id = ?";
    }
    public static String addressUpdateQuery() {
        return "UPDATE users SET  = ? WHERE user_id = ?";
    }
}
