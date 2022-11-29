package com.tweet.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import static java.lang.System.exit;

public class InitClass {
	////////////Tweet App///////////By Puneet///////////
	static  String emailGloble=null;
	static  String passwordGloble=null;
	static final String DB_URL="jdbc:mysql://localhost:3306/tweet_db";
    static final String user="root";
    static final String pass="pass@word1";
    static Scanner sc = new Scanner(System.in);
    
    public static void getAllUsers(){
    	System.out.println("             @@  All User's Detail  @@               ");
     	System.out.println("************************************************** ");
        String Query="select * from user";
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
        Statement st=conn.createStatement();
        ResultSet rs =st.executeQuery(Query);
            {
                while(rs.next()) {
                    System.out.println("User Name :"+ rs.getString("first_name"));
                    System.out.println("User Email :"+ rs.getString("email"));
                    if(rs.getBoolean("activeUser")==true) {
                    System.out.println("Currently Login : Yes");
                    }else {
                    	 System.out.println("Currently Login : No");
                    }
                    System.out.println("---------------------------------------------");
        	     	System.out.println(" ");
        }
        }}catch(SQLException e){
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////
    public static void getAllTweet(){
    	System.out.println("              @@ All User's Tweets @@                ");
     	System.out.println("************************************************** ");
        String Query="select * from tweet";
        try{

            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

        Statement st=conn.createStatement();
        ResultSet rs =st.executeQuery(Query);
            {
                while(rs.next()) {

                	 System.out.println("Tweet : "+ rs.getString("tweet_info"));
                     System.out.println("Created On :" +rs.getTimestamp("created_on") +" ,Created By :"+  rs.getString("email"));
                     System.out.println("...................................................");
         	     	System.out.println(" ");
        }
        }}catch(SQLException e){
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////
	@SuppressWarnings("deprecation")
	public static void getActiveUserTweet(){
    	System.out.println("             @@  Active User's Tweets  @@               ");
     	System.out.println("************************************************** ");
    	String Query="select * from tweet where email IN(select email from user where activeUser=?)";
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            PreparedStatement ps=conn.prepareStatement(Query);
            {
                 ps.setBoolean(1, true);
                 ResultSet rs=ps.executeQuery();
                 
                while(rs.next()) {
                	  System.out.println("Tweet : "+ rs.getString("tweet_info"));
                	  System.out.println("Created On :" +rs.getTimestamp("created_on") +" ,Created By :"+  rs.getString("email"));
                      System.out.println("...................................................");
          	     	System.out.println(" ");
        }
        }}catch(SQLException e){
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////
    @SuppressWarnings("deprecation")
    public static void getMyTweet(){
    	System.out.println("               @@  My Tweets  @@                     ");
     	System.out.println("************************************************** ");
    	 String Query="select * from tweet where email=?";
    try{
        Connection conn = DriverManager.getConnection(DB_URL,user,pass);
        PreparedStatement ps=conn.prepareStatement(Query);
        {
                        ps.setString(1, emailGloble);
                        ResultSet rs=ps.executeQuery();
                       
            while( rs.next()) {
                System.out.println("Tweet : "+ rs.getString("tweet_info"));
                System.out.println("Created On :" +rs.getTimestamp("created_on"));
                System.out.println("..................................................");
    	     	System.out.println(" ");
            } }
}catch(SQLException e){
    e.printStackTrace();
}
}
 ///////////////////////////////////////////////////////
    
    public static void createTweet(){
     	System.out.println("         @@   Create Your Tweet  @@                    ");
    	System.out.println("**************************************************");
        String Query="insert into tweet(tweet_info,email,created_on) values(?,?,?)";
       
        	try{
                Connection conn = DriverManager.getConnection(DB_URL,user,pass);
                PreparedStatement ps=conn.prepareStatement(Query);{
                Scanner sc=new Scanner(System.in);
                    System.out.println("Please Write Your Tweet :");
                    String tweet=sc.nextLine();
                    java.sql.Timestamp date =new java.sql.Timestamp(new java.util.Date().getTime());
                    ps.setString(1, tweet);
                    ps.setString(2, emailGloble);
                    ps.setTimestamp(3, date);
                    int rowsDeleted = ps.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("A Tweet is Created successfully!");
                        System.out.println("**************************************************");
            	     	System.out.println(" ");
                    }
                    
                }} catch(SQLException e){
    e.printStackTrace();
}       	
    }      
    ///////////////////////////////////////////////////////
   
    public static void signIn(){
    	   String Query="select * from user where email=? AND password=?";
 	    String Query1="UPDATE user SET activeUser=? where email=? AND password=?";
    	 try{
	            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
	            PreparedStatement ps=conn.prepareStatement(Query);
	            PreparedStatement ps1=conn.prepareStatement(Query1);{
	               Scanner sc=new Scanner(System.in);
	               System.out.println("           @@  Welcome To User Login  @@              ");
	               System.out.println("**************************************************");
	                System.out.println("Enter User Email  : ");
	                String cemail=sc.nextLine();
	                if( cemail.isEmpty()){
	                	System.out.println("Email can not be blank,Please try again ");
	                	System.out.println("**************************************************");
	        	     	System.out.println(" ");
	                	signIn();
	                }
	                System.out.println("Enter User Password  :");
	                String cpswd=sc.nextLine();
	              
	                if( cpswd.isEmpty()){
	                	System.out.println("Password can not be blank,Please try again ");
	                	System.out.println("**************************************************");
	        	     	System.out.println(" ");
	                	signIn();
	                }
	                ps.setString(1, cemail);
	                ps.setString(2, cpswd);
	                ps1.setBoolean(1, true);
	                ps1.setString(2, cemail);
	                ps1.setString(3, cpswd);
///////////////emailGlobleSet for use at diffrent queries///////////
	                emailGloble=cemail;
	                passwordGloble=cpswd;
	                ResultSet rs=ps.executeQuery();
	                ps1.executeUpdate();
	                if(rs.next()) {
	                	System.out.println("User Login Successfull");
	                	System.out.println("**************************************************");
	        	     	System.out.println(" ");
	                	MenuDataLogin();
	                }
	                else {
	                	System.out.println("Login Failed, Entered Details are Incorrect,Try Again");
	                	System.out.println("**************************************************");
	        	     	System.out.println(" ");
	                	  int userSelected;
	                	  selectionLogin();	
	                }
	            }
	            
    	 } catch(SQLException e){
    e.printStackTrace();
}       	
    }  
    
    ///////////////////////////////
	 
	 public static void MenuDataLogin()
	{
	int selection;
	Scanner sc=new Scanner(System.in);
	while (true) {
	System.out.println("        @@   Please Choose Your Option   @@           ");
	System.out.println("**************************************************");
	System.out.println("1 - Post Tweet: ");
	System.out.println("2 - View My Tweet: ");
	System.out.println("3 - View All Tweets: ");
	System.out.println("4 - View All Users:");
	System.out.println("5 - View All Active Users Tweets: ");
	System.out.println("6 - Reset Password:");
	System.out.println("7 - Logout");
	System.out.println("Enter Your Choice : ");
	
    selection=sc.nextInt();
    System.out.println("**************************************************");
 	System.out.println(" ");
		switch(selection) {
		case 1:
			createTweet();
			break;
		case 2:
			getMyTweet();
			break;
		case 3:
			getAllTweet();
			break;
		case 4:
			getAllUsers();
			break;
		case 5:
			getActiveUserTweet();
			break;
		case 6:
			resetPassword();
			break;
		case 7:
			 logout();
            exit(0);
           
		default:
			System.out.println("Sorry,Enter correct option");
			System.out.println("**************************************************");
	     	System.out.println(" ");
				break;
		}}
		
	}	 
    //////////////////////////////////////////////////
	 public static void signUp(){
   	 String Query="insert into user(first_name,email,password,activeUser) values(?,?,?,?)";
   	String Query1="select email from user where email=?";
      
   	try{
        Connection conn = DriverManager.getConnection(DB_URL,user,pass);
        PreparedStatement ps1=conn.prepareStatement(Query1);
        PreparedStatement ps=conn.prepareStatement(Query);{
           Scanner sc=new Scanner(System.in);
           System.out.println("             @@   Welcome To Sign Up  @@        ");
           System.out.println("**************************************************");
            System.out.println("Enter User Name  : ");
            String cname=sc.nextLine();
            if( cname.isEmpty()){
            	System.out.println("User Name can not be blank,Please try again ");
            	System.out.println("**************************************************");
             	System.out.println(" ");
            	signUp();
            }
            
            System.out.println("Enter User Email  : ");
            String cemail=sc.nextLine();
            ps1.setString(1, cemail);
            if( cemail.isEmpty()){
            	System.out.println("Email can not be blank,Please try again ");
            	System.out.println("**************************************************");
             	System.out.println(" ");
            	signUp();
            }
            ResultSet rs=ps1.executeQuery();
            if(rs.next()) {
               System.out.println("User with Entered Email Id is already Registered,Please Try with Another Email Id!");
               System.out.println("**************************************************");
            	System.out.println(" ");
               signUp();
           }
            
            System.out.println("Enter User Password  : ");
            String cpswd=sc.nextLine();
            if( cpswd.isEmpty()){
            	System.out.println("Password can not be blank,Please try again ");
            	System.out.println("**************************************************");
             	System.out.println(" ");
            	signUp();
            }

            ps.setString(1, cname);
            ps.setString(2, cemail);
            ps.setString(3, cpswd);
            ps.setBoolean(4, false);
           
            int Registered = ps.executeUpdate();
            if (Registered > 0) {
                System.out.println("Congrats, New User is Registered successfully!");
                System.out.println("**************************************************");
             	System.out.println(" ");
            }
        }
        selectionLogin();	   
   	} catch(SQLException e){
   e.printStackTrace();
}       	
   }  
///////////////////////////////////////////////////////
    
public static void logout(){
String Query1="UPDATE user SET activeUser=? where email=? AND password=?";
try{
Connection conn = DriverManager.getConnection(DB_URL,user,pass);
PreparedStatement ps1=conn.prepareStatement(Query1);{
ps1.setBoolean(1, false);
ps1.setString(2, emailGloble);
ps1.setString(3, passwordGloble);
ps1.executeUpdate();
System.out.println("Logout Successfull");
System.out.println("**************************************************");
	System.out.println(" ");
}
} catch(SQLException e){
e.printStackTrace();
}       	
}  

///////////////////////////////
public static void forgotPassword(){

  String Query="select * from user where email=?";
  String Query1="UPDATE user SET password=? where email=? ";
	 try{
         Connection conn = DriverManager.getConnection(DB_URL,user,pass);
         PreparedStatement ps=conn.prepareStatement(Query);
         PreparedStatement ps1=conn.prepareStatement(Query1);{
            Scanner sc=new Scanner(System.in);
            System.out.println("  @@  Forgot Password? Please create New Password  @@ ");
            System.out.println("**************************************************");
             System.out.println("Enter User Email  : ");
             String cemail=sc.nextLine();
             ps.setString(1, cemail);
             if( cemail.isEmpty()){
             	System.out.println("Email can not be blank,Please try again ");
             	System.out.println("**************************************************");
             	System.out.println(" ");
             	forgotPassword();
             }
             ResultSet rs=ps.executeQuery();
             if(rs.next()) {
             	System.out.println("Enter New Password  :: ");
                String pass=sc.nextLine();
                if( pass.isEmpty()){
                 	System.out.println("Password can not be blank,Please try again ");
                 	System.out.println("**************************************************");
                 	System.out.println(" ");
                 	forgotPassword();
                 }
                ps1.setString(2, cemail);
                ps1.setString(1, pass);
                ps1.executeUpdate();
             	System.out.println("Hi,New Password Created Successfully");
             	
             	System.out.println("**************************************************");
             	System.out.println(" ");
             }
             else
             {
              	System.out.println("You Enterd Wrong Email Id ,Please Try Again "); 
              	System.out.println("**************************************************");
             	System.out.println(" ");
             }
             selectionLogin();	
         }} catch(SQLException e){
e.printStackTrace();
}  	
}
////////////////////
public static void resetPassword()
{
	  String Query1="UPDATE user SET password=? where email=? ";
		 try{
	         Connection conn = DriverManager.getConnection(DB_URL,user,pass);
	         PreparedStatement ps1=conn.prepareStatement(Query1);{
	            Scanner sc=new Scanner(System.in);
	            System.out.println("      @@    Welcome,Reset Login Password    @@            ");
	            System.out.println("**************************************************");
	             System.out.println("Enter Old Password  : ");
	             String oldPassword=sc.nextLine();
	             if( oldPassword.isEmpty()){
	              	System.out.println("Old Password can not be blank,Please try again ");
	              	System.out.println("**************************************************");
	             	System.out.println(" ");
	              	resetPassword();
	              }
	             if(passwordGloble.equals(oldPassword)) {
	            
	             	System.out.println("Authorized User,Please Enter New Password  : ");
	                String pass=sc.nextLine();
	                if( pass.isEmpty()){
		              	System.out.println("New Password can not be blank,Please try again ");
		              	System.out.println("**************************************************");
		             	System.out.println(" ");
		              	resetPassword();
		              }
	                
	                ps1.setString(1, pass);
	                ps1.setString(2, emailGloble);
	                ps1.executeUpdate();
	             	System.out.println("Hi,Password Changed Successfully");
	             	System.out.println("**************************************************");
	             	System.out.println(" ");
	             }
	             else
	             {
	              	System.out.println("You Enterd Wrong Old Password ,Please Try Again ");
	              	System.out.println("**************************************************");
	             	System.out.println(" ");
	             }
	             MenuDataLogin();	       
	             
	         }} catch(SQLException e){
	e.printStackTrace();
	}  	
	}

public static void main(String[] args){
	selectionLogin();	 
	}
//////////////////////
public static void selectionLogin()
{
	int userSelected;

do {
	userSelected=MenuData();
	switch(userSelected) {
	case 1:
		signUp();
		break;
	case 2:
		signIn();
		break;
	case 3:
		forgotPassword();
		break;
	case 4:
		System.out.println("Exit Successfull");
     	System.out.println("**************************************************");
        exit(0);
        break;
    default:
        System.out.println("Sorry,Enter correct option");
     	System.out.println("**************************************************");
     	System.out.println(" ");
			break;
	}
	
}
while(userSelected>4);

}
	public static int MenuData()
	{
	int selection;
	Scanner sc=new Scanner(System.in);
	
	System.out.println("@@ Welcome To Tweet App, Please Choose Your Option @@");
	System.out.println("**************************************************");
	System.out.println("1 - Sign Up");
	System.out.println("2 - Login ");
	System.out.println("3 - Forgot Password");
	System.out.println("4 - Exit");
	System.out.println(" ");
	System.out.println("Enter Your Choice : ");
    selection=sc.nextInt();
    System.out.println("**************************************************");
 	System.out.println(" ");
    return selection;
	}
}
