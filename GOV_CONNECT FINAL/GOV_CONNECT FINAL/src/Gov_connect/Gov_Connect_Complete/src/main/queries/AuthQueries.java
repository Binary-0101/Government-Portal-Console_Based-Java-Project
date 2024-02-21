package main.queries;

public class AuthQueries{
    public static String userSignIn(){
        return "SELECT password FROM users WHERE emailId = ?";
    }
    public static String userInDb(){
        return "SELECT password FROM users WHERE emailId = ?";
    }
    public static String userSignUp(){
        return "INSERT INTO users(userName , emailId , password, gender, dob, fatherName, address, pincode, mobile_No, aadhaar_no) VALUES (? , ?, ? , ?, ?, ?, ?, ?, ?, ?)";
    }
    public static String adminSingIn(){
        return "SELECT adminPassword FROM admin WHERE adminEmail = ?";
    } 
}
