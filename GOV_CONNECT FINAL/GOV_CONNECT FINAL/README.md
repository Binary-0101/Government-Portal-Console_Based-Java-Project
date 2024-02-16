## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


//     @Override
//     public void raise_Issue(Register register) {
//         System.out.println("Which department would you like to complain about?");
//         String dept = sc.next();

//         System.out.println("Select id of the Minister Name");
//         System.out.println("Minister Id 1 : Mathan");
//         System.out.println("Minister Id 2: John");
//         System.out.println("Minister Id 3: Alice");
//         System.out.println("Minister Id 4: Bob");
//         int minister_id = sc.nextInt();

//         System.out.println("Enter the Issue type?");

//         System.out.println("(1) Infrastructure Issue");
//         System.out.println("(2) Utilities Issue");
//         System.out.println("(3) Waste Management");
//         System.out.println("(4) Parks and Public Spaces");

//         String issue_Type = "";

//         int choice = sc.nextInt();

//         switch (choice) {
//             case 1:
//                 issue_Type += "Infrastructure Issue";
//                 break;
//             case 2:
//                 issue_Type += "Utilities Issue";
//                 break;
//             case 3:
//                 issue_Type += "Waste Management";
//                 break;
//             case 4:
//                 issue_Type += "Parks and Public Spaces";
//                 break;

//             default:
//                 System.out.println("Enter valid choice");
//                 break;
//         }

//         System.out.println("Enter your Complaint");
//         sc.nextLine();
//         String description = sc.nextLine();

//         String status = "pending";

//         complaint_register(register, dept,minister_id, issue_Type, description, status);
//     }

//     public void raise_Issue(Login login) {
//         System.out.println("Which department would you like to complain about?");
//         String dept = sc.next();

//         System.out.println("Select id of the Minister Name");
//         System.out.println("Minister Id 1 : Mathan");
//         System.out.println("Minister Id 2: John");
//         System.out.println("Minister Id 3: Alice");
//         System.out.println("Minister Id 4: Bob");
//         int minister_id = sc.nextInt();

//         System.out.println("Enter the Issue type?");

//         System.out.println("(1) Infrastructure Issue");
//         System.out.println("(2) Utilities Issue");
//         System.out.println("(3) Waste Management");
//         System.out.println("(4) Parks and Public Spaces");

//         String issue_Type = "";

//         int choice = sc.nextInt();

//         switch (choice) {
//             case 1:
//                 issue_Type += "Infrastructure Issue";
//                 break;
//             case 2:
//                 issue_Type += "Utilities Issue";
//                 break;
//             case 3:
//                 issue_Type += "Waste Management";
//                 break;
//             case 4:
//                 issue_Type += "Parks and Public Spaces";
//                 break;

//             default:
//                 System.out.println("Enter valid choice");
//                 choice = sc.nextInt();
//                 break;
//         }
//         sc.nextLine();
//         System.out.println("Enter your query");
//         String description = sc.nextLine();

//         String status = "pending";

//         complaint_login(login, dept, minister_id,issue_Type, description, status);
//     }

//     public void complaint_login(Login login, String dept, int minister_id,String issue_Type, String description, String status) {
//         String user_Name = login.get_Name();
//         String password  = login.get_Password();
//         String user_email = "";
//         String user_address = "";
//         int user_mobile = 0;

//         try {
//             con = DBConnection.createDBConnection();
//             String query = "SELECT * FROM user_table WHERE user_name = ? AND user_password = ?";
//             PreparedStatement stmt = con.prepareStatement(query);
            
//             stmt = con.prepareStatement(query);
//             stmt.setString(1, user_Name);
//             stmt.setString(2, password);

//             ResultSet rs = stmt.executeQuery();

//             if (rs.next()) {
//                  user_email = rs.getString("user_email");
//                  user_address = rs.getString("user_Address");
//                  user_mobile = rs.getInt("user_mobile_No");
//             }
//             con = DBConnection.createDBConnection();
//             String query1 = "insert into raise_issue (admin_id,user_name,user_email,user_Address,user_mobile,department,issue_Type,description,status) values(?,?,?,?,?,?,?,?,?)";

//             PreparedStatement pstmt = con.prepareStatement(query1);

//             pstmt.setInt(1, minister_id);
//             pstmt.setString(2, user_Name);
//             pstmt.setString(3, user_email);
//             pstmt.setString(4, user_address);
//             pstmt.setInt(5, user_mobile);
//             pstmt.setString(6, dept);
//             pstmt.setString(7, issue_Type);
//             pstmt.setString(8, description);
//             pstmt.setString(9, status);

//             int cnt = pstmt.executeUpdate();

//             if (cnt != 0) {
//                 System.out.println("Your Query Updated");
//             }
//             pstmt.close();
//             ;
//             con.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public void complaint_register(Register register, String dept,int minister_id, String issue_Type, String description,
//             String status) {
//         try {
//             con = DBConnection.createDBConnection();
//             String query = "insert into raise_issue (admin_id,user_name,user_email,user_Address,user_mobile,department,issue_Type,description,status) values(?,?,?,?,?,?,?,?,?)";

//             PreparedStatement pstmt = con.prepareStatement(query);
//             pstmt.setInt(1, minister_id);
//             pstmt.setString(2, register.getName());

//             pstmt.setString(3, register.getEmail());
//             pstmt.setString(4, register.getAddress());
//             pstmt.setInt(5, register.get_mobile_no());
//             pstmt.setString(6, dept);
//             pstmt.setString(7, issue_Type);
//             pstmt.setString(8, description);
//             pstmt.setString(9, status);

//             int cnt = pstmt.executeUpdate();

//             if (cnt != 0) {
//                 System.out.println("Your Query Updated");
//             }
//             pstmt.close();
//             con.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
