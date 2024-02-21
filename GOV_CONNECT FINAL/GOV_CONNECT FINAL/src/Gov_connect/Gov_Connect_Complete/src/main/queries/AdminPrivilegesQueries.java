package main.queries;

public class AdminPrivilegesQueries {
    public static String getIdOfAdminByEmail(){
        return "SELECT adminId FROM admin WHERE adminEmail = ? AND adminPassword = ?";
    }
    public static String viewIssueTable(){
        return "SELECT * FROM issue_table WHERE Minister_Id = ?";
    }
    public static String changeStatusQuery(){
        return "UPDATE issue_table SET Status = 'Work Assigned' WHERE Minister_Id = ?";
    }
}
