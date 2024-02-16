package Gov_connect;

import java.util.*;

public class Login{
    Services services = new Services();

    private String user_Name;
    private String password; 
    Login(){
        user_Name = "null";
        password = "null";
    } 

    public void set_Name(String name){
        this.user_Name = name;
    }
    public void set_Password(String password){
        this.password = password;
    }

    public String get_Name(){
        return user_Name;
    }
    public String get_Password(){
        return password;
    }

    public void list(Scanner sc,Login login){
        System.out.println("(1) To Raise an Issue\n" +
                   "(2) To Track Issue Service\n" +
                   "(3) Job Vacancy\n" +
                   "(4) Apply for Government Job\n" +
                   "(5) Aadhaar card Update\n" +
                   "(6) Pan card Update\n" +
                   "(7) Exit\n");

        int choice = sc.nextInt();
        Register register = new Register();

        switch (choice) {
            case 1:
                services.raise_Issue(login);
                break;
            case 2:
                services.track_Issue(login);
                break;
            case 3:
                services.Job_vaccancy(register);
                break;
            case 4:
                services.Job_vaccancy(register);
                services.apply_Job(login);
                break;
            case 5:
                System.out.println("Enter the Aadhaar number that you want to Update");
                String aadhar_no = sc.next();
                services.aadhaar_Update(login,aadhar_no);
                break;
            case 6:
                System.out.println("Enter the Pan number that you want to Update");
                String pan = sc.next();
                services.pan_Update(login,pan);
                break;

            case 7:
            break;
        
            default:
            System.out.println("Enter valid a choice");
            choice = sc.nextInt();
            break;
        }
    }
}