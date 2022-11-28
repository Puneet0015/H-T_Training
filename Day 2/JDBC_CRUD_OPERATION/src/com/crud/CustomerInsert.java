package com.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerInsert {
    static final String DB_URL="jdbc:mysql://localhost:3306/assignment_cogn";
    static final String user="root";
    static final String pass="pass@word1";
    static final String Query="insert into customers(cust_id,cust_name,cust_age,cust_address,cust_income) values(?,?,?,?,?)";

    public static void main(String[] args){
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            PreparedStatement ps=conn.prepareStatement(Query);{
    Scanner sc=new Scanner(System.in);
                System.out.println("enter cust id");
                int cid=sc.nextInt();
                System.out.println("enter cust name");
                String cname=sc.next();
                System.out.println("enter cust age");
                String cage=sc.next();
                System.out.println("enter cust address");
                String caddress=sc.next();
                System.out.println("enter cust income");
                int cincome=sc.nextInt();
                
                
                
                ps.setInt(1, cid);
                ps.setString(2, cname);
                ps.setString(3, cage);
                ps.setString(4, caddress);
                ps.setInt(5, cincome);
                //ps.executeUpdate();
                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("A user was Created successfully!");
                }
            }}catch(SQLException e){

        }
    }
}
