package main.views;

import java.util.Scanner;

import main.controller.AdminController;
import main.controller.UserController;
import main.model.Admin;
import main.model.User;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void landingPage() {
        User user = new User();

        while (true) {
            System.out.println("===========================================");
            System.out.println("|        WELCOME TO GOV_CONNECT           |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 1. Login                                |");
            System.out.println("| 2. Register                             |");
            System.out.println("| 3. Admin Login                          |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| Select one of the options above:        |");
            System.out.println("===========================================");

            int choice = sc.nextInt();

            if (choice == 1) {
                sc.nextLine();
                System.out.println("===========================================");
                System.out.println("|                User Login                |");
                System.out.println("|-----------------------------------------|");
                System.out.print("| Enter the user emailId:                | ");
                String emailId = sc.nextLine();
                System.out.print("| Enter the user password:               | ");
                String password = sc.nextLine();
                System.out.println("===========================================");

                user.setEmail(emailId);
                user.setPassword(password);

                boolean status = UserController.loginUser(emailId, password);
                if (status) {
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("|             User Logged in successfully                                     |");
                    System.out.println("----------------------------------------------------------------------------");
                    UserView.userView(user);
                } else {
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("|             Invalid credentials                                              |");
                    System.out.println("----------------------------------------------------------------------------");
                    return;
                }

            }
            if (choice == 2) {
                sc.nextLine();
                System.out.println("===========================================");
                System.out.println("|            User Registration            |");
                System.out.println("|-----------------------------------------|");
                System.out.print("| Enter your Name:                       | ");
                String register_Name = sc.nextLine();
                System.out.print("| Enter your Email:                      | ");
                String register_email = sc.nextLine();
                System.out.print("| Enter your Password:                   | ");
                String register_password = sc.nextLine();
                System.out.print("| Enter your Gender:                     | ");
                String gender = sc.nextLine();
                System.out.print("| Enter your Date Of Birth:                     | ");
                String dob = sc.nextLine();
                System.out.print("| Enter your Mobile Number:              | ");
                String register_mobile_no = sc.nextLine();
                System.out.print("| Enter your Aadhaar Number:             | ");
                String register_Aadhaar = sc.nextLine();
                System.out.print("| Enter your Father's Name:              | ");
                String register_fatherName = sc.nextLine();
                System.out.print("| Enter your Address:                    | ");
                String register_Address = sc.nextLine();
                System.out.print("| Enter your Pincode:                    | ");
                int pincode = sc.nextInt();
                System.out.println("===========================================");

                user.setName(register_Name);
                user.setEmail(register_email);
                user.setPassword(register_password);
                user.setMobile_no(register_mobile_no);
                user.setAddress(register_Address);
                user.setAadhaar_no(register_Aadhaar);
                user.setPincode(pincode);
                user.setFatherName(register_fatherName);
                user.setGender(gender);
                user.setDOB(dob);
                
                boolean status = UserController.addUser(register_Name, register_email, register_password, gender, dob, register_fatherName,
                        register_Address,pincode, register_mobile_no, register_Aadhaar);
                if (status) {
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("|                             User signed up successfully                    |");
                    System.out.println("----------------------------------------------------------------------------");
                    UserView.userView(user);
                } else {
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("|                              User Already exists                           |");
                    System.out.println("----------------------------------------------------------------------------");
                }

            }
            if (choice == 3) {
                sc.nextLine();
                System.out.println("===========================================");
                System.out.println("|            Admin Login               |");
                System.out.println("|-----------------------------------------|");
                System.out.print("| Enter your Email:                      | ");
                String admin_email = sc.next();
                System.out.print("| Enter your Password:                   | ");
                String admin_password = sc.next();
                System.out.println("===========================================");

                boolean status = AdminController.loginAdmin(admin_email, admin_password);
                int adminId = AdminController.getAdminId(admin_email,admin_password);
                if (status) {
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("|             Admin Logged in successfully                                     |");
                    System.out.println("----------------------------------------------------------------------------");
                    AdminView.adminView(adminId);
                } else {
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("|             Invalid credentials                                              |");
                    System.out.println("----------------------------------------------------------------------------");
                    return;
                }
            }

        }
    }
}
