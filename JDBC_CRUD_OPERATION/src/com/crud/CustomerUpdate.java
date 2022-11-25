package com.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerUpdate {
	 
    static final String DB_URL="jdbc:mysql://localhost:3306/assignment_cogn";
    static final String user="root";
    static final String pass="pass@word1";
    static final String Query="UPDATE customers SET cust_name=? WHERE cust_id=?";

    public static void main(String[] args){
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            PreparedStatement ps=conn.prepareStatement(Query);
            {
            Scanner sc=new Scanner(System.in);
                System.out.println("enter cust id");
                int cid=sc.nextInt();
                System.out.println("enter cust name");
                String cname=sc.next();
                ps.setInt(2, cid);
                ps.setString(1, cname);
              //  ps.executeUpdate();
                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("A user was Updated successfully!");
                }
            }
            }catch(SQLException e){

        }
    }
}

