package main.model;

public class User {
    private int user_id;
    private String userName;
    private String emailId;
    private String password;
    private String address;
    private String mobile_no;
    private String aadhaar_no;
    private int pincode;
    private String gender;
    private String fatherName;
    private String dob;

    public User(){

    }
    public User(int user_id,String userName,String emailId,String password,String address,String mobile_no,String aadhaar_no,String gender,String fatherName,int pincode,String dob){
        this.user_id = user_id;
        this.emailId = emailId;
        this.password = password;
        this.address = address;
        this.mobile_no = mobile_no;
        this.aadhaar_no = aadhaar_no;
        this.gender = gender;
        this.fatherName = fatherName;
        this.pincode = pincode;
        this.dob = dob;
    }
    public void setName(String name){
        this.userName = name;
    }
    public void setDOB(String dob){
        this.dob = dob;
    }
    public void setEmail(String email){
        this.emailId = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setMobile_no(String mobile_no){
        this.mobile_no = mobile_no;
    }
    public void setFatherName(String fatherName){
        this.fatherName = fatherName;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setPincode(int pincode){
        this.pincode = pincode;
    }
    public void setAadhaar_no(String aadhar_no){
        this.aadhaar_no = aadhar_no;
    }
    public int getUserId(){
        return user_id;
    }
    public String getUserName(){
        return userName;
    }
    public String getDOB(){
        return dob;
    }
    public String getuserEmail(){
        return emailId;
    }
    public String getUserPassword(){
        return password;
    }
    public String getUserAddress(){
        return address;
    }
    public String getmobile_no(){
        return mobile_no;
    }
    public String getAadhaar_no(){
        return aadhaar_no;
    }
    public int getPincode(){
        return pincode;
    }
    public String getFatherName(){
        return fatherName;
    }
    public String getGender(){
        return gender;
    }
}
