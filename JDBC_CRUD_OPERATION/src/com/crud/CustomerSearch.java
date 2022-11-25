package com.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerSearch {
	
    static final String DB_URL="jdbc:mysql://localhost:3306/assignment_cogn";
    static final String user="root";
    static final String pass="pass@word1";
    static final String Query="select * from customers where cust_id=?";

    public static void main(String args[]){
    	
    try{
        Connection conn = DriverManager.getConnection(DB_URL,user,pass);
        PreparedStatement ps=conn.prepareStatement(Query);
        {
            Scanner sc=new Scanner(System.in);
                        System.out.println("Enter customer id to Search");
                        int cid=sc.nextInt();
                        ps.setInt(1, cid);                        
                        ResultSet rs=ps.executeQuery();
                       
            while( rs.next()) {

                System.out.println("cust id :"+ rs.getInt("cust_id"));
                System.out.println("cust name :"+ rs.getString("cust_name"));
                System.out.println("cust age :"+ rs.getString("cust_age"));
                System.out.println("cust address :"+ rs.getString("cust_address"));
                System.out.println("cust income :"+ rs.getString("cust_income"));
    
            } }}catch(SQLException e){

    }
}
}