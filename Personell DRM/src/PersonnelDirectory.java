import java.util.Scanner;
public class PersonnelDirectory
{



   public static void main(String[] args)
   {          PersonelFactory personelFactory=new PersonelFactory();
//              Personnel per = new Personnel();
			  totalObjects total = new totalObjects();

			  Scanner scan = new Scanner(System.in);
			  String firstN, lastN, middleN;

			  int empID;
			  double salary;
              int choice = -1;


      do{


          System.out.println("Welcome to the Personnel Directory Management System");
          System.out.println("====================================================");

          System.out.println("\n\n\t 1. Add Personel");
          System.out.println("\n\t 2. Find Personel");
          System.out.println("\n\t 3. Print Names");
          System.out.println("\n\t 4. Number of Entries in the Directory");

          System.out.println("\n\t Select one of the options above (1, 2, 3, 4)");
          choice = scan.nextInt();
          scan.nextLine();

          switch(choice)
          {
			  case 1:
			  System.out.println("Enter first name: ");
			  firstN = scan.nextLine();

			  System.out.println("Enter last name: ");
			  lastN = scan.nextLine();

			  System.out.println("Enter middle name: ");
			  middleN = scan.nextLine();

			  System.out.println("Enter empploy id : ");
			  empID = scan.nextInt();

			  System.out.println("Enter base salaey" );
			  salary = scan.nextDouble();

			  System.out.println("Enter category ");
			  String empT = scan.nextLine();

			  scan.nextLine();



			  personelFactory.createPersonell(empT,lastN,  firstN,  middleN,  empID,  salary);
			  total.objectAdded();

			  break;

			  case 2:

			  System.out.println("Enter fisrt name : ");
			  firstN = scan.nextLine();

			  System.out.println("Enter last name : ");
			  lastN = scan.nextLine();


                                  boolean found = false;
			  		 			   int loc =-1;
			  		 			  for(int i =0; i <personelFactory.getPersonnel().getPersonList().size(); i++)
			  		 			  {
			  		 				if( personelFactory.getPersonnel().getPersonList().get(i).getFirst().equals(firstN) &&
											personelFactory.getPersonnel().getPersonList().get(i).getLast().equals(lastN))
			  		 				{
			  		 				found = true;
			  		 			    loc = i;
			  		 			    }
			  		 			  }

			  		 			  if(found)
			  		 			  {
			  		 				  System.out.println("Found");
									  personelFactory.getPersonnel().getPersonList().get(loc).printNameinFirstOrder();

			  		 			  }else
			  		 			  {
			  		 				  System.out.println("not found");
			  		 				  Person p1  = new Person(lastN, firstN, " ");
//			  		 				  per.personList.add(p1);
									  personelFactory.getPersonnel().addPersonnel(p1);
			  		 				  total.objectAdded();
			                      }

              break;

			  case 3:

			  System.out.println("Enter the order 0: first, middle,  last, 1: first, last, middle, 2: last, first , middle ");
			  int order = scan.nextInt();
			  for(int i=0; i<personelFactory.getPersonnel().getPersonList().size(); i++)
			  {

//				  per.getPersonList().get(i).printName(order);

				  if (order == 0)
					  personelFactory.getPersonnel().getPersonList().get(i).printNameinFirstOrder();
				  else if (order == 1)
					  personelFactory.getPersonnel().getPersonList().get(i).printNameinSecondOrder();
				  else if (order == 2)
					  personelFactory.getPersonnel().getPersonList().get(i).printNameinThirdOrder();
			  }

               break;

			  case 4:
			  System.out.println("Total Entries : " + total.getTotalObjects());

               break;

		  }

		 } while(true);


  }

}