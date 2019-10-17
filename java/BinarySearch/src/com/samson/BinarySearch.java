package com.samson;

public class BinarySearch {
	
	//implement a method that takes an array and the element to serach
	
	public int Search(int[] arr, int x){
		
		/*//if the array is not sorted, we first sort it
		for (int i=0; i<arr.length; i++){
			for(int j=i+1; j < arr.length; j++){
				if(arr[j] < arr[i]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		*/
		
		//the start index
		int s = 0;
		//get the last index by getting the array size then subtracting one
		int l = arr.length - 1;
		
		
		
		//iterate until an element is found otherwise return -1
		while(s <= l){
			//get the index of the medium element in the array
			int m = (s  + l) /2;
			
			//check if the medium element matches the element to be searched
			if(arr[m] == x){
				//return the index
				return m;
			}
			//check if the element is greater than the one in the middle
			if(arr[m] < x){
				//make the start index to be the one after the index on the middle element
				s = m + 1;
			}
			else{
				//the element is smaller hence search in the lower half by making the last 
				//index to be the one before the index on the element in the middle
				l = m -1;
			}
		}
		
		//return -1 if the element is not found
		return -1;
	}
	
	public int leftmostSearch(int[] arr, int searchKey){
		int start = 0;
		int last =  arr.length - 1;
		
		//check whether the start index is not equal to last index
		while(start < last){
			//get the middle index
			int middle = (start + last)/2;
			if(arr[middle] < searchKey){
				start = middle + 1;
			}else{
				last = middle;
			}
			
		}
		
		return start;
	}
	
	public int rightmostSearch(int[] arr, int searchKey){
		
		int start = 0;
		int last = arr.length - 1;
		
		//check whether the start index is not equal to last index
		while(start < last){
			//get the middle index
			int middle = (start + last)/2;
			
			if(arr[middle] <= searchKey){
				start = middle + 1;
			} else {
				last = middle;
			}
		}
		
		return start - 1;
	}
	
	

}
