package Gov_connect;

import java.util.Scanner;

public class Register {
    private int user_id;
    private String user_Name;
    private String user_email;
    private String user_password;
    private String user_Address;
    private String user_mobile_no;
    private String user_aadhar_no;

    Register() {
        user_id = 0;
        user_Name = "";
        user_email = "";
        user_password = "";
        user_Address = "";
        user_mobile_no = "";
        user_aadhar_no = "";
    }

    public void setName(String name) {
        this.user_Name = name;
    }

    public void setEmail(String email) {
        this.user_email = email;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }

    public void setAddress(String address) {
        
        this.user_Address = address;
    }

    public void setMobile_no(String mobile_no) {
        this.user_mobile_no = mobile_no;
    }

    public void setAadhaar(String aadhaar) {
        this.user_aadhar_no = aadhaar;
    }

    public int getId() {
        return user_id + 1;
    }

    public String getName() {
        return user_Name;
    }

    public String getEmail() {
        return user_email;
    }

    public String getPassword() {
        return user_password;
    }

    public String getAddress() {
        return user_Address;
    }

    public String get_mobile_no() {
        return user_mobile_no;
    }

    public String get_aadhar_no() {
        return user_aadhar_no;
    }

    Scanner sc = new Scanner(System.in);

    public void list(Register register, Scanner sc) {
        System.out.println("(1) To Raise an Issue\n" +
        "(2) To Track Issue Service\n" +
        "(3) Job Vacancy\n" +
        "(4) Apply for Government Job\n" +
        "(5) Aadhaar card Update\n" +
        "(6) Pan card Update\n" +
        "(7) Exit\n");
        int choice = sc.nextInt();
        Services services = new Services();

        switch (choice) {
            case 1:
                services.raise_Issue(register);
                break;
            case 2:
                services.track_Issue(register);
                break;
            case 3:
                services.Job_vaccancy(register);
                break;
            case 4:
                services.Job_vaccancy(register);
                services.apply_Job(register);
                break;
            case 5:
                System.out.println("Enter the Aadhaar number that you want to Update");
                String aadhar_no = sc.next();
                services.aadhaar_Update(register,aadhar_no);
                break;
            case 6:
                System.out.println("Enter the Pan number that you want to Update");
                String pan = sc.next();
                services.pan_Update(register,pan);
                break;

            case 7:
                System.out.println("Thank You");
                break;

            default:
                System.out.println("Enter valid a choice");
                choice = sc.nextInt();
                break;
        }
    }
}