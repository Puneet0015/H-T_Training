package com.food.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import static java.lang.System.exit;

public class InitClass {
	
	static  String emailGloble=null;
	
	static final String DB_URL="jdbc:mysql://localhost:3306/food_db";
    static final String user="root";
    static final String pass="pass@word1";
    static Scanner sc = new Scanner(System.in);
    
    static void getAllOrder(){
        String Query="select * from food";
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

        Statement st=conn.createStatement();
        ResultSet rs =st.executeQuery(Query);
            {
                while(rs.next()) {

                    System.out.println("foodName :"+ rs.getString("foodName"));
                    System.out.println("quantity :"+ rs.getString("quantity"));
                    System.out.println("email :"+ rs.getString("email"));

        }
        }}catch(SQLException e){
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////
    static void getOrderById(){
    	 String Query="select * from food where email=?";
    try{
        Connection conn = DriverManager.getConnection(DB_URL,user,pass);
        PreparedStatement ps=conn.prepareStatement(Query);
        {
                        ps.setString(1, emailGloble);
                        ResultSet rs=ps.executeQuery();
                       
            while( rs.next()) {

            	 System.out.println("foodName :"+ rs.getString("foodName"));
                 System.out.println("quantity :"+ rs.getString("quantity"));
                 System.out.println("email :"+ rs.getString("email"));
    
            } }
}catch(SQLException e){
    e.printStackTrace();
}
}
 ///////////////////////////////////////////////////////
    static void deleteOrderById(){
    	String Query="select * from food";
        String sql="Delete from food where email=?";
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            PreparedStatement st=conn.prepareStatement(sql);
            ResultSet rs =st.executeQuery(Query);
            {

                while(rs.next()) {
                	 System.out.println("foodName :"+ rs.getString("foodName"));
                     System.out.println("quantity :"+ rs.getString("quantity"));
                     System.out.println("email :"+ rs.getString("email"));

                }
                st.setString(1, emailGloble);
                int rowsDeleted = st.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("order was Deleted successfully!");
                }
            }}catch(SQLException e){
    e.printStackTrace();
}
}
    static void updateOrder(){
        String Query="UPDATE food SET quantity=? WHERE email=?";
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            PreparedStatement ps=conn.prepareStatement(Query);
            {
            Scanner sc=new Scanner(System.in);
                System.out.println("enter quantity");
                String quantity=sc.next();
                ps.setString(2, emailGloble);
                ps.setString(1, quantity);
                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Order was Updated successfully!");
                }
            }
            }catch(SQLException e){
    e.printStackTrace();
}
}
    
    static void createOrder(){
        String Query="insert into food(foodName,quantity,email) values(?,?,?)";
       
        	try{
                Connection conn = DriverManager.getConnection(DB_URL,user,pass);

                PreparedStatement ps=conn.prepareStatement(Query);{
        Scanner sc=new Scanner(System.in);
                    
                    System.out.println("enter foodName");
                    String foodName=sc.next();
                    System.out.println("enter quantity");
                    String quantity=sc.next();
                   
                    ps.setString(1, foodName);
                    ps.setString(2, quantity);
                    ps.setString(3, emailGloble);

                    int rowsDeleted = ps.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("A order was Created successfully!");
                    }
                    }
        	} catch(SQLException e){
    e.printStackTrace();
}       	
    }      
   
    static void signIn(){
    	 String Query="select * from user where email=? AND password=?";
       
    	 try{
	            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

	            PreparedStatement ps=conn.prepareStatement(Query);{
	               Scanner sc=new Scanner(System.in);
	               System.out.println("Welcome To User Login");
	               System.out.println("=======================================");
	                System.out.println("Enter User Email  :: ");
	                String cemail=sc.nextLine();
	                System.out.println("Enter User Password  ::");
	                String cpswd=sc.nextLine();
	                
	                emailGloble=cemail;
	                ps.setString(1, cemail);
	                ps.setString(2, cpswd);
	                
	                ResultSet rs=ps.executeQuery();
	               
	                if(rs.next()) {
	                	 System.out.println("=======================================");
	   	              
	                	System.out.println("Login Successfull");
	                	MenuDataLogin();
	                }
	                else {
	                	 System.out.println("=======================================");
	   	              
	                	System.out.println("Login Failed, Entered Details are Incorrect");
	                	MenuDatas();
	                }
	            }} catch(SQLException e){
    e.printStackTrace();
}       	
    }  
    
	 public static void MenuDataLogin()
	{
	int selection;
	Scanner sc=new Scanner(System.in);
	while (true) {
	System.out.println("Select your option:");
	System.out.println("..........................");
	System.out.println("1 - Create Order:");
	System.out.println("2 - Update Order:");
	System.out.println("3 - Fetch Order:");
	System.out.println("4 - Delete Order:");
	System.out.println("5 - Exit");
	System.out.println("Your Selection Option Is :");
	
    selection=sc.nextInt();
		switch(selection) {
		case 1:
			createOrder();
			break;
		case 2:
			updateOrder();
			break;
		case 3:
			getOrderById();
			break;
		case 4:
			deleteOrderById();
			break;
		case 5:
			System.out.println("Exit Successfull");
            exit(0);
		default:
			System.out.println("Choose again");
				break;
			
		}}
		
	}////////////////////
	 
	 public void loginOption(){
			
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
				default:
					System.out.println("Choose again");
						break;
					
				}
				
			}
			while(userSelected>2);
			
		}
    static void signUp(){
   	 String Query="insert into user(first_name,email,password) values(?,?,?)";
      
   	try{
        Connection conn = DriverManager.getConnection(DB_URL,user,pass);

        PreparedStatement ps=conn.prepareStatement(Query);{
           Scanner sc=new Scanner(System.in);
           System.out.println("Welcome To User Registration");
           System.out.println("=======================================");
           
            System.out.println("Enter User Name  :: ");
            String cname=sc.nextLine();
            System.out.println("Enter User Email  :: ");
            String cemail=sc.nextLine();
            System.out.println("Enter User Password  ::");
            String cpswd=sc.nextLine();

            ps.setString(1, cname);
            ps.setString(2, cemail);
            ps.setString(3, cpswd);
           
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
            	 System.out.println("=======================================");
                System.out.println("Congrats, New User is Registered successfully!");
                MenuDatas();
            }
        }} catch(SQLException e){
   e.printStackTrace();
}       	
   }  
public static void main(String[] args){
		
	MenuDatas();
		
	}
public static void MenuDatas()
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
			System.out.println("Exit successfull");
            exit(0);

        default:
            System.out.println("Enter correct option");
		
				break;
			
		}
		
	}
	while(userSelected>2);
}
	public static int MenuData()
	{
	int selection;
	Scanner sc=new Scanner(System.in);
	System.out.println("Select your option:");
	System.out.println("..........................");
	System.out.println("1 - Register Here:");
	System.out.println("2 - Sign In Here:");
	System.out.println("3 - Exit");
	
	System.out.println("Your Selection Option Is :");
	
    selection=sc.nextInt();
    return selection;
	}
}
