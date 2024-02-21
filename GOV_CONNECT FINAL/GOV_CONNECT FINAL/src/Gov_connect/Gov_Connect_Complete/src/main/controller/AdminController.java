package main.controller;

import main.model.Issue;
import main.service.AdminServices;
import java.util.*;


public class AdminController {
    public static boolean loginAdmin(String emailId, String password) {
        return AdminServices.CheckUserCredentials(emailId, password);
    }
    public static int getAdminId(String adminEmail,String adminPassword){
        return AdminServices.getIdByAdminEmail( adminEmail, adminPassword);
    }
    public static List<Issue> viewIssueTable(int adminId){
        return  AdminServices.viewIssuesTableOfAdmin(adminId);
    }
    public static boolean changeStatustoAssigned(int adminId){
        return  AdminServices.changeStatusOfIssue(adminId);
    }
}
