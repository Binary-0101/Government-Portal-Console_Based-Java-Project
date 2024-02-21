package main.views;

import java.util.List;
import java.util.Scanner;

import main.controller.AdminController;
import main.model.Issue;



public class AdminView {
    private static final Scanner sc = Main.sc;
    public static void adminView(int adminId) {
        System.out.println("===========================================");
        System.out.println("|    Choose an Option:                    |");
        System.out.println("|-----------------------------------------|");
        System.out.println("| (1) View the Raise Issue Table          |");
        System.out.println("| (2) Exit                                |");
        System.out.println("===========================================");

        int choice  = sc.nextInt();

        switch (choice) {
            case 1:
               List<Issue> list =  AdminController.viewIssueTable(adminId);
               if(list.size() == 0){
                  System.out.println("No issue raised to this department");
               }
               list.stream().forEach(System.out::println);
              
               System.out.println(  "┌───────────────────┐\r\n" + //
                                    "│ (1) To Assign Work│\r\n" + //
                                    "└───────────────────┘\r\n" + //
                                      "");
               System.out.println(  "┌───────────────────────────────┐\r\n" + //
                                    "│ (2) If the Issue Still Needs to Be in Waiting List     │\r\n" + //
                                    "└───────────────────────────────┘");
               choice = sc.nextInt();
               if(choice == 1){
               System.out.println("To whom do you want to assign the task:");
               System.out.println("(1) Deputy Minister of Infrastructure - Sarah Johnson");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
               System.out.println("(2) Director of Public Works - Alex Thompson");
               System.out.println("(3) Local Government Official - Michael Smith");
               
               AdminController.changeStatustoAssigned(adminId);
               System.out.println("Status Updated to Work Assigned");
               }
               else
               System.out.println( "┌───────────────┐\r\n" + //
                                   "│   Thank You   │\r\n" + //
                                   "└───────────────┘");
               break;
        
            default:
                break;
        }
    }

}
