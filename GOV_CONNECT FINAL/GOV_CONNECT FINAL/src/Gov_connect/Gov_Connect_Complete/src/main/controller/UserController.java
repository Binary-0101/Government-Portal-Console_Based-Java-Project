package main.controller;
import main.model.Issue;
import main.service.UserService;


public class UserController {
    public static boolean loginUser(String emailId, String password) {
        return UserService.CheckAdminCredentials(emailId, password);
    }

    public static boolean addUser(String uName, String emailId, String password,String gender, String dob, String fatherName, String address, int pincode, String mobile_no,String aadhar_No) {
        if (!UserService.checkUserAlreadyInDb(emailId)) {
            return UserService.addUserToDb(uName, emailId, password,gender, dob, fatherName, address,pincode, mobile_no, aadhar_No);
        }
        return false;
    }
    public static void viewAlltheJob() {
        UserService.getAlltheJob();
    }
    public static boolean applyForJob(int user_id,int job_id,String qualification){
        if(UserService.addUserToApplyForJob(user_id,job_id,qualification)){
            return true;
        }
        return false;
    }
    public static int getUserIdByEmail(String user_email,String user_password) {
        int userId = UserService.getUserEmail(user_email,user_password);
        if(userId > 0){
            return userId;
        }
        else{
            return -1;
        }
    }
    public static boolean raiseIssue(int user_id,Issue issue){
        if(UserService.insertIntoIssueTable(user_id,issue)){
            return true;
        }
        return false;
    }
    public static void trackIssue(String user_email,String user_password){
        int user_id = getUserIdByEmail(user_email,user_password);
        UserService.trackOfIssue(user_id);
    }
    public static boolean updateUserName(int uId,String name){
        if(UserService.updateNameOfUser(uId,name))
            return true;

            return false;
    }
    public static boolean updateFatherName(int uId,String fatherName){
        if(UserService.updateFatherNameOfUser(uId,fatherName))
            return true;

            return false;
    }
    public static boolean updateDob(int uId,String dob){
        if(UserService.updateDOBNameOfUser(uId,dob))
            return true;

            return false;
    }
    public static boolean updateAddress(int uId,String address){
        if(UserService.updateAddressNameOfUser(uId,address))
            return true;

            return false;
    }
}