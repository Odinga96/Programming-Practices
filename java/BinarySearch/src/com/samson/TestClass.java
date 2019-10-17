package com.samson;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create an object of the binary search class
		BinarySearch search = new BinarySearch();
		//an array 
		int[] nums = {2, 3, 7, 7, 7, 7, 9, 10};
		
		System.out.println(8/2);
		
		//the element to be searched
		int x = 7;
		int result = search.rightmostSearch(nums, x);
		
		//check if the element is not found else return the index of the element
		if(result == -1){
			System.out.println("The element was not found!!");
		} else {
			//return the index of the element
			System.out.println("The left most element was found at index " + result);
		}

	}

}
