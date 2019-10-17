import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    private List<Symptom> symptoms;


    public Data(List<Symptom> playerSyptoms) {
        this.symptoms = playerSyptoms;
    }

    public Data() {
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
