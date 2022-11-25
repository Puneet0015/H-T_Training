package com.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerRetreval {
	
    static final String DB_URL="jdbc:mysql://localhost:3306/assignment_cogn";
    static final String user="root";
    static final String pass="pass@word1";
    static final String Query="select * from customers";

    public static void main(String args[]){
    	
    try{
        Connection conn = DriverManager.getConnection(DB_URL,user,pass);

    Statement st=conn.createStatement();
    ResultSet rs =st.executeQuery(Query);
        {
            while(rs.next()) {

                System.out.println("cust id :"+ rs.getInt("cust_id"));
                System.out.println("cust name :"+ rs.getString("cust_name"));
                System.out.println("cust age :"+ rs.getString("cust_age"));
                System.out.println("cust address :"+ rs.getString("cust_address"));
                System.out.println("cust income :"+ rs.getString("cust_income"));

    }
    }}catch(SQLException e){

    }
}
}
