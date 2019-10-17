import java.util.Iterator;

/**
 * The interface that you must implement to handle the actions associated with StreetParking
 */
public interface StreetParking  {
    /**
    attempt to park a car with the given length to the street
    @param length the length of the car being added to the street
    @return -1 if the car cannot be parked on the street or a unique number
    that may be used to retrieve the car otherwise.
    */
    int push (int length);

    /**
    Remove the vehicle with the given ticket number.
    throws NoSuchElementException if no such car exists
    @param ticketNumber the ticketNumber of the car to remove
    */
    void remove(int ticketNumber);

    /**
    A local driver attempts to block the end of the street
    @param size the size of the vehicle trying to block the end of the street
    @return true if there is room at the end of the street to block it. False if there
    is already a vehicle blocking the end of the street or there is not enough room for the vehicle to
    fit on the street
    */
    boolean block(int size);

    /*
    return true if the street is currently blocked, false otherwise
    */
    boolean isBlocked();

    /*
    return the number of cars that George has parked on the street
    */
    int numberOfCars();

    /*
    return the amount of unoccupied space on the street
    */
    int freeSpace();

    /*
    Return the contents of the street in String form by referring to the lengths of the items on the street,
    whether they are cars George parked, empty spaces, or cars blocking the end of the street.
    Preface each type of item as follows:
    Cars that George has parked are prefaced with a G,
    Empty space is prefaced with an E, and
    any blocking cars are prefaced with a B.
    */
    String toString();

    /**
     * return an iterator to iterate over the integer spaces occupied (or unoccupied on the street
     * @return the iterator to the leftmost part of the street
     */
    Iterator<Integer> iterator();

}
