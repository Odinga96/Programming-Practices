package hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Hotel {
    private Cell[][]  rooms;
    private static final String fileName="src/rooms.txt";

    public Hotel() {
        rooms=new Cell[3][3];
        loadRooms();
    }

    private void loadRooms(){
        try {
            File file=new File(fileName);
            Scanner scanner=new Scanner(file);

            int row=0;
            int col=0;
            while (scanner.hasNext()){
                String[] room=scanner.nextLine().split(",");

                int roomNumber           =Integer.parseInt(room[0]);
                int occupancy_status     =Integer.parseInt(room[1]);
                boolean cleanedStatus    =Boolean.parseBoolean(room[2]);
                int capacity             =Integer.parseInt(room[3]);
                double accountBalance    =Double.parseDouble(room[4]);

                Cell cell=new Cell(roomNumber,occupancy_status,cleanedStatus,capacity,accountBalance);

                rooms[row][col++]=cell;

                if (col==3){
                    col=0;
                    row++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void saveData(){

        File file=new File(fileName);
        try {
            PrintWriter writer=new PrintWriter(file);

            for (Cell[] cells : rooms) {
                for (int j = 0; j < rooms[0].length; j++) {

                    Cell room = cells[j];

                    String roomData = room.getRoomNumber() + "," +
                            room.isOccupancy() + "," +
                            room.isCleanedStatus() + "," +
                            room.getCapacity() + "," +
                            room.getAccountBalance();
                    writer.println(roomData);

                }

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Cell[][] getRooms() {
        return rooms;
    }

    private void printFloorData(int floor){

        System.out.println("\n--------------------**************Rooms data for floor "+floor+"**************----------------------------\n");

        for (int i = 0; i < getRooms()[0].length; i++) {
            Cell room=getRooms()[floor-1][i];
            System.out.println(room.toString());
        }
        System.out.println("\n----------------------------------***************************-------------------------------------\n");

    }

    private void printRoomData(int floor, int roomNumber){
        System.out.println("\n----------------------------------***************************-------------------------------------");
        System.out.println( getRooms()[floor-1][roomNumber-1].toString());
        System.out.println("\n----------------------------------***************************-------------------------------------");
    }

    private void editRoomData(){
        System.out.println("\n----------------------------------***************************-------------------------------------");
        Scanner scanner=new Scanner(System.in);
        int floor=getFloor(scanner);

        int room=getRoom(scanner);

        Cell cell=getRooms()[floor-1][room-1];

        int userChoice;

        while ((userChoice=menu(scanner, 1)) >0 && userChoice <8){
            switch (userChoice){
                case 1:
                    cell.setCleanedStatus(true);
                    break;
                case 2:
                    if (cell.isOccupancy() < cell.getCapacity()) {

                        System.out.println("Enter number of guests to add");
                        int numberOfGuests = scanner.nextInt();

                        while (numberOfGuests > (cell.getCapacity()-cell.isOccupancy())) {
                            System.out.println("Number of guests can only be less than or equal to " + (cell.getCapacity()-cell.isOccupancy()));
                            System.out.println("Enter number of guests to add");
                            numberOfGuests = scanner.nextInt();
                        }

                        cell.setOccupancy(cell.isOccupancy()+numberOfGuests);

                    }else {
                        System.out.println("This room is fully occupied you have to check out guests first");
                    }
                    break;
                case 3:
                    if (cell.isOccupancy() >0 ) {

                        System.out.println("Enter number of guests to delete");
                        int numberOfGuests = scanner.nextInt();

                        while (numberOfGuests > cell.isOccupancy() || numberOfGuests<0) {
                            System.out.println("Number of guests check out can only be less than or equal to " + cell.isOccupancy());
                            System.out.println("Enter number of guests to check out");
                            numberOfGuests = scanner.nextInt();
                        }

                        cell.setOccupancy(cell.isOccupancy()-numberOfGuests);
                        cell.setCleanedStatus(false);
                    }
                    else
                        System.out.println("This room is not occupied by any guest");

                    break;
                case 4:
                    if (cell.isOccupancy() !=0) {
                        System.out.print("Enter the amount:\t");
                        cell.setAccountBalance(cell.getAccountBalance() + scanner.nextDouble());
                    }else {
                        System.out.println("You cannot add tab to a room not occupied");
                    }
                    break;
                case 5:
                    cell.setAccountBalance(0.00);
                    break;
                case 6:
                    System.out.println("\n----------------------------------***************************-------------------------------------");
                    return;
                case 7:
                    saveData();
                    System.out.println("\n----------------------------------***************************-------------------------------------");
                    System.exit(0);
                    break;


            }
        }
    }


    private int menu(Scanner scanner, int menuChoice){

        if (menuChoice == 1)
        System.out.print("\nEnter the action to perform\n" +
                "\t1:  Clean room\n" +
                "\t2:  Add new guest to room\n" +
                "\t3:  Check out guest\n" +
                "\t4:  Add tab\n" +
                "\t5:  Payout the tab\n" +
                "\t6:  Return to main menu:\n" +
                "\t7:  Exit\n" +
                "" +
                "Enter your choice:  ");
        else
            System.out.print("\n\nChoose an action to perform\n" +
                    "\t1: View rooms by floor\n" +
                    "\t2: View room data\n" +
                    "\t3: Edit room data\n" +
                    "\t4: Exit\n" +
                    "Enter your choice:  ");

        return scanner.nextInt();

    }


    public void mainMenu(){
        Scanner scanner= new Scanner(System.in);
        int menuChoice;
        while ((menuChoice=menu(scanner,2))>0 && menuChoice<5){
            switch (menuChoice){
                case 1:
                    this.printFloorData(getFloor(scanner));
                    break;
                case 2:
                    int floor=getFloor(scanner);
                    int room=getRoom(scanner);
                    this.printRoomData(floor,room);
                    break;
                case 3:
                    this.editRoomData();
                    break;
                case 4:
                    saveData();
                    System.exit(0);
                    break;
            }
        }
    }


    private int getFloor(Scanner scanner){
        System.out.print("Enter floor number (1,2,3):\t");
        int floor=scanner.nextInt();
        while (floor<0 || floor>4){
            System.out.println("The floor number can only be 1 or 2 or 3");
            System.out.print("Enter floor number (1,2,3):\t");
            floor=scanner.nextInt();
        }

        return floor;
    }

    private int getRoom(Scanner scanner){
        System.out.print("Enter room number (1,2,3):\t");
        int room=scanner.nextInt();
        while (room<0 || room>4){
            System.out.println("The room number can only be 1 or 2 or 3");
            System.out.println("Enter room number (1,2,3):\t");
            room=scanner.nextInt();
        }

        return room;
    }
}
