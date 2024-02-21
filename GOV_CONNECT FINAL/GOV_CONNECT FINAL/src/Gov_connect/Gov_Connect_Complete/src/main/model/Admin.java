package main.model;

public class Admin {
    private int admin_Id;
    private String admin_Name;
    private String admin_Password;
    private String admin_dept;

    
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
}
