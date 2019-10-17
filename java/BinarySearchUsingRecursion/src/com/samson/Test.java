package com.samson;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arrayTest = {1,4,7,9,10,66,99};
		
		BinarySearch test = new BinarySearch();
		int last = arrayTest.length;
		
		if(test.search(arrayTest, 11, 0, last)){
			System.out.println("The element was found!!");
		} else {
			System.out.println("The element was not found!");
		}
	}

}
