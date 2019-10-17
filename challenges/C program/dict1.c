 #include<stdio.h>
 #include<stdlib.h>
 
 struct dict1{
 	struct dict* left, *right;
 	int VendorID, passenger_count, payment_type, store_and_fwd_flag, PULocationID, DOLocationID, trip_duration;
 	double trip_distance, fare_amount, extra, mta_tax, RatecodeID, tip_amount, tolls_amount, improvement_surcharge, total_amount;
 	
 	char PUdatetime[20], DOdatetime[20];
 	
 }*Root=NULL;
 
 typedef struct dict1 dictionary;
 
 /*Define functions*/
 void insert(/*dictionary d*/);
 void search(/*char str[]*/);
 void view();
 
 /* Inmplement insert */
 
void insert(/*dictionary d*/){
	dictionary *newdict;
	newdict = (dictionary *) malloc(sizeof(dictionary));
	newdict->left = NULL;
	newdict->right = NULL;
	//Read the csv file and set all the variables
	printf("\nReady to insert\n");
	
	dictionary *parent, *pointer;
	int flag = 0;
	pointer = Root;
	
	if(pointer == NULL) {
		printf("\nThe first insertion\n");
		
	} else {
		while(pointer != NULL  && flag == 0) {
			if(strcmp(newdict->PUdatetime, pointer->PUdatetime) == 1) {
				parent = pointer;
				pointer = pointer->right;
			} else if(strcmp(newdict->PUdatetime, pointer->PUdatetime) == -1) {
				parent = pointer;
				pointer = pointer->left;
			} else {
				flag  = 1;
				printf("\nKey --> %S exists\n", newdict->PUdatetime);
				
				//break;
			}
		}
	}
	
}


/*Implement search */
void search(/*char key[]*/) {
	char key[20];
	dictionary *pointer;
	int flag = 0;
	pointer = Root;
	printf("\nEnter key to search :");
	scanf("%s", key);
	while (pointer != NULL &&  flag == 0 ) {
		if(strcmp(key, pointer->PUdatetime) == 1) {
			pointer = pointer->right;
		}else if(strcmp(key, pointer->PUdatetime) == -1) {
			pointer = pointer->left;
		}else {
			printf("\nFound!\n");
			flag = 1;
			//break;	
		}
	}
	if (pointer == NULL &&  flag == 0) {
		printf("\n%s --> NOTFOUND\n", key);
	}
}
/*implement view*/
void view () {
	printf("\nReady to view\n");
}
 
 
 
 int main(int argc, char const *argv[]) {
 	int choice;
 	while(choice != 4) {
 		printf("\n1. Search\n2. Insert\n3. View\n4. Quit\nEnter your choice :");
 		scanf("%d",&choice);
 		switch(choice) {
 			case 1:
 				search();
 				break;
 			case 2:
 				insert();
 				break;
 			case 3:
 				view();
 				break;
 			case 4:
 				exit(0);
 			default:
 				printf("\nInvalid choice.\n");
 				break;
		 }
	 }
	 return 0;
 }
 
