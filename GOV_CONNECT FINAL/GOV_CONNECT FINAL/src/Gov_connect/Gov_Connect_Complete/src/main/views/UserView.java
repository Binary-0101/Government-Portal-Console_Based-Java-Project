package main.views;

import java.util.Scanner;

import main.controller.UserController;
import main.model.Issue;
import main.model.JobVaccancy;
import main.model.User;

public class UserView {
    private static final Scanner sc = Main.sc;
    static User user = new User();

    public static void userView(User user) {
        System.out.println("===============================================");
        System.out.println("|            Welcome to Gov_Connect           |");
        System.out.println("|---------------------------------------------|");
        System.out.println("|   (1) To Raise an Issue                     |");
        System.out.println("|   (2) To Track Issue Service                |");
        System.out.println("|   (3) Job Vacancy                           |");
        System.out.println("|   (4) Apply for Government Job              |");
        System.out.println("|   (5) Aadhaar card Update                   |");
        System.out.println("|   (6) Pan card Update                       |");
        System.out.println("|   (7) Exit                                  |");
        System.out.println("===============================================");

        int choice = sc.nextInt();
        if (choice == 1) {
            Issue issue = new Issue();

            System.out.println("===============================================");
            System.out.println("|         Government Complaint Portal         |");
            System.out.println("===============================================");
            System.out.println("Which department would you like to complain about?");
            System.out.print("Department: ");
            String dept = sc.next();

            System.out.println("Select the ID of the Minister Name:");
            System.out.println("Minister ID 1: Mathan");
            System.out.println("Minister ID 2: John");
            System.out.println("Minister ID 3: Alice");
            System.out.println("Minister ID 4: Bob");
            System.out.print("Minister ID: ");
            int minister_id = sc.nextInt();

            System.out.println("Enter the Issue type:");
            System.out.println("(1) Infrastructure Issue");
            System.out.println("(2) Utilities Issue");
            System.out.println("(3) Waste Management");
            System.out.println("(4) Parks and Public Spaces");
            System.out.print("Choice: ");

            String issue_Type = "";
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    issue_Type = "Infrastructure Issue";
                    break;
                case 2:
                    issue_Type = "Utilities Issue";
                    break;
                case 3:
                    issue_Type = "Waste Management";
                    break;
                case 4:
                    issue_Type = "Parks and Public Spaces";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

            System.out.println("Enter your Complaint:");
            sc.nextLine(); 
            String complaint = sc.nextLine();

            String status = "pending";
            System.out.println("===============================================");

            issue.setMinisterDept(dept);
            issue.setMinisterId(minister_id);
            issue.setIssueType(issue_Type);
            issue.setComplaint(complaint);
            issue.setStatus(status);

            int userId = UserController.getUserIdByEmail(user.getuserEmail(), user.getUserPassword());
            if (UserController.raiseIssue(userId, issue)) {
                System.out.println("Your complaint has been uploaded successfully.");
            } else {
                System.out.println("Something went wrong while uploading your complaint.");
            }

        } else if (choice == 2) {
            UserController.trackIssue(user.getuserEmail(), user.getUserPassword());
        } else if (choice == 3) {
              JobVaccancy.jobListView();
        } else if (choice == 4) {
            JobVaccancy.jobListView();
            System.out.println("=========================================");
            System.out.println("|           Job Application             |");
            System.out.println("|---------------------------------------|");
            System.out.println("| Enter the Job ID:                     | ");
            int job_id = sc.nextInt();
            sc.nextLine();
            System.out.print("| Enter your Qualification:               | ");
            String qualification = sc.next();
            System.out.println("=========================================");

            int userId = UserController.getUserIdByEmail(user.getuserEmail(), user.getUserPassword());

            if (UserController.applyForJob(userId, job_id, qualification)) {
                System.out.println("===============================================");
                System.out.println("|          Applied for Job Successfully        |");
                System.out.println("===============================================");
            } else {
                System.out.println("===============================================");
                System.out.println("|                Application Failed            |");
                System.out.println("===============================================");
            }

        } else if (choice == 5) {
            System.out.println("-------------------------------");
            System.out.println("|   Aadhaar Details Update   |");
            System.out.println("-------------------------------");
            System.out.println("Which details would you like to update?");
            System.out.println("(1) Update spelling of the name");
            System.out.println("(2) Update Father's Name");
            System.out.println("(3) Update date of birth");
            System.out.println("(4) Update address");

            choice = sc.nextInt();
            sc.nextLine();
            int userId;
            switch (choice) {
                case 1:
                    System.out.println("Enter your Name to Update");
                    String updateName = sc.nextLine();
                    userId = UserController.getUserIdByEmail(user.getuserEmail(), user.getUserPassword());
                    if (UserController.updateUserName(userId, updateName))
                        System.out.println("User Name Updated Successfully");
                    else
                        System.out.println("User Name Update failed");
                    break;

                case 2:
                    System.out.println("Enter your Father's Name to Update:");
                    String updateFatherName = sc.nextLine();
                    userId = UserController.getUserIdByEmail(user.getuserEmail(), user.getUserPassword());
                    if (UserController.updateFatherName(userId, updateFatherName))
                        System.out.println("Father's Name Updated Successfully.");
                    else
                        System.out.println("Father's Name Update Failed.");
                    break;
                case 3:
                    System.out.println("Enter your Date Of Birth in format DD-MM-YYYY:");
                    String updateDob = sc.nextLine();
                    userId = UserController.getUserIdByEmail(user.getuserEmail(), user.getUserPassword());
                    if (UserController.updateDob(userId, updateDob))
                        System.out.println("Date Of Birth Updated Successfully.");
                    else
                        System.out.println("Date Of Birth Update Failed.");
                    break;
                case 4:
                    System.out.println("Enter your new Address:");
                    String address = sc.nextLine();
                    userId = UserController.getUserIdByEmail(user.getuserEmail(), user.getUserPassword());
                    if (UserController.updateAddress(userId, address))
                        System.out.println("Address Updated Successfully.");
                    else
                        System.out.println("Address Update Failed.");
                    break;
                
                    default:
                    break;
            }
        } else if (choice == 6) {

        } else if (choice == 7) {

        } else {
            System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}
