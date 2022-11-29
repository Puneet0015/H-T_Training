package com.tweet.testCase.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.tweet.app.InitClass;  
  
public class JunitTestCase {
	private InitClass cl= new InitClass();
			
    @Test  
    public void testCube(){  
        System.out.println("test case cube");  
        assertEquals(27,InitClass.MenuData());  
    }  
    @Test  
    public void testReverseWord(){  
        System.out.println("test case reverse word");  
        assertEquals(6,InitClass.MenuData());  
    }  
    @After  
    public void tearDown() throws Exception {  
        System.out.println("tearDown");  
    }  
  
    @AfterClass  
    public static void tearDownAfterClass() throws Exception {  
        System.out.println("tea rDown after class");  
    }  
  
} 