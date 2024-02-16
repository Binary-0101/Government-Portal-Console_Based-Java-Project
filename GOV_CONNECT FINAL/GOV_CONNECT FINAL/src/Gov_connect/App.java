package Gov_connect;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner (System.in)) {
            User user = new User();
            Admin admin = new Admin();
            Register register = new Register();
            Login login = new Login();
            
            System.out.println("WELCOME TO GOV_CONNECT");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Admin Login");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the UserName:");
                    String login_Name = sc.next();
                    System.out.println("Enter the Password");
                    String login_Password = sc.next();

                    login.set_Name(login_Name);
                    login.set_Password(login_Password);

                    if(!user.authenticateUser(login))
                    return;
                    
                    login.list(sc, login);
                    break;

                case 2:
                System.out.println("Enter your Name");
                String register_Name = sc.next();
                System.out.println("Enter your Email");
                String register_email = sc.next();
                System.out.println("Enter your Password");
                String register_password = sc.next();
                System.out.println("Enter your Address");
                String register_Address = sc.next();
                System.out.println("Enter your Mobile_no");
                String register_mobile_no = sc.next();
                System.out.println("Enter your Aadhaar Number");
                String register_Aadhaar = sc.next();

                register.setName(register_Name);
                register.setEmail(register_email);
                register.setPassword(register_password);
                register.setAddress(register_Address);
                register.setMobile_no(register_mobile_no);
                register.setAadhaar(register_Aadhaar);
                user.create_User(register);

                register.list(register,sc);
                break;

                case 3:
                System.out.println("Enter the Minister Id:");
                int admin_Id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the Minister Name:");
                String admin_Name = sc.nextLine();
                System.out.println("Enter the Password");
                String admin_Password = sc.nextLine();
                System.out.println("Enter the Department");
                String admin_dept = sc.nextLine();


                admin.set_Id(admin_Id);
                admin.set_Name(admin_Name);
                admin.set_Password(admin_Password);
                admin.set_Dept(admin_dept);

                admin.authenticateAdmin(admin);
                admin.list(sc,admin);
                break;

                default:
                    break;
            }
        }
    }
}
