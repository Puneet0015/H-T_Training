package com.lamda.stream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
interface City{
	
	void house();
}

public class StraemDemo{
	
	public static void main(String[] args) {
		//Stream Demo Implementation
	List<Integer> numbers=Arrays.asList(2,4,3,2,5,67,1,0,9,2,31);
	System.out.println("====================(Stream Demo Implementation)=============================");
	System.out.println("Modified List after applying Stream functions :");
	//System.out.println("=================================================");
	numbers.stream()
	.filter(b->(b>5))
	.map(c->c+5).sorted()
	.forEach(n->System.out.println(n));
	
	//Lamda Demo Implementation
	System.out.println("==================== (Lamda Demo Implementation)=============================");
	City c=()-> System.out.println("House In the City is Beautiful.");
	c.house();
	}
	
}
	
	
