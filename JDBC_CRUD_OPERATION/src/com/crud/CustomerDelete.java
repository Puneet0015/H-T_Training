package com.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDelete {
	 static final String DB_URL="jdbc:mysql://localhost:3306/assignment_cogn";
	    static final String user="root";
	    static final String pass="pass@word1";
	    static final String Query="select * from customers";
    static final String sql="Delete from customers where cust_id=?";


    public static void main(String[] args){
        try{
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            PreparedStatement st=conn.prepareStatement(sql);
            ResultSet rs =st.executeQuery(Query);
            {

                while(rs.next()) {
                	System.out.println("cust id :"+ rs.getInt("cust_id"));
                    System.out.println("cust name :"+ rs.getString("cust_name"));
                    System.out.println("cust age :"+ rs.getString("cust_age"));
                    System.out.println("cust address :"+ rs.getString("cust_address"));
                    System.out.println("cust income :"+ rs.getString("cust_income"));

                }
                System.out.println("Enter cust id");
                Scanner sc=new Scanner(System.in);
                int custid=sc.nextInt();
                st.setInt(1, custid);
                //st.executeUpdate();
                int rowsDeleted = st.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("A user was Deleted successfully!");
                }
            }}catch(SQLException e){

        }
    }
}

