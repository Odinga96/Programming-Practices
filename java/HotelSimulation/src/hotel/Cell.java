package hotel;

public class Cell {
    private int roomNumber;
    private int occupancy;
    private boolean cleanedStatus;
    private int capacity;
    private double accountBalance;

    Cell(int roomNumber, int occupancy, boolean cleanedStatus, int capacity, double accountBalance) {
        this.roomNumber = roomNumber;
        this.occupancy = occupancy;
        this.cleanedStatus = cleanedStatus;
        this.capacity = capacity;
        this.accountBalance = accountBalance;
    }

    int getRoomNumber() {
        return roomNumber;
    }

    int isOccupancy() {
        return occupancy;
    }

    void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    boolean isCleanedStatus() {
        return cleanedStatus;
    }

    void setCleanedStatus(boolean cleanedStatus) {
        this.cleanedStatus = cleanedStatus;
    }

    int getCapacity() {
        return capacity;
    }

    double getAccountBalance() {
        return accountBalance;
    }

    void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        String clean=(cleanedStatus)?"clean":" not clean";
        return "" +
                "  roomNumber: " + roomNumber +
                ", occupancy: " + occupancy +"/"+capacity+
                ", cleanedStatus: " + clean +
                ", capacity: " + capacity +
                ", accountBalance: " + accountBalance;
    }
}
