package com.samson;

public class BinarySearch {
	
	//using recursion
	public boolean search(int[] arr, int searchKey, int first, int last){
		
		//checking if the index of first element is not greater than the index of the last element
		if(first > last){
			return false;
		}
		
		//get the middle index
		int m = (first + last) / 2;
		//check if the element lies in the middle index
		if(arr[m] == searchKey){
			return true;
		}
		else if(arr[m] < searchKey){
			return search(arr, searchKey, m+1, last);
		} else {
			return search(arr, searchKey, first, m-1);
		}
		
	}

}
