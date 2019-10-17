import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

    @Test
    void providedSampleTest() {
        // a street 100 units long with George's car parked at the leftmost 10 units
        StreetParking street = new GeorgeLinkedStreet(100, 10);

        int camper = street.push(30); //park a camper van
        int motorbike = street.push(5);// park a motorbike
        boolean itsTrue = street.block(10);//a local driver blocks the end of the street

        String st = street.toString(); //outputs: `G5 G30 G10 E45 B10`

        assertEquals("G5 G30 G10 E45 B10", st);

        int[] stArr = {5, 30 ,10, 45, 10};
        int i = 0;
        Iterator it = street.iterator();
        while(it.hasNext()) {
            assertEquals(stArr[i], it.next()); //outputs: `5 30 10 45 10`
            i++;
        }
    }
}
