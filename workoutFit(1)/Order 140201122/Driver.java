import java.util.Random;

public class Driver {

    public static void main(String[] args) throws StaticDataNotFoundException {
        DataSet dataSet=new DataSet();

        dataSet.loadStatistics(dataSet,"herdManagement.csv",3);

        System.out.println("State\tHerdAreaAcresBLM\tHerdAreaAcresOther\tHerdManagementAreaABLM\tHerdManagementAreaOther\tNumHorses\tNumBurros\n");
        dataSet.displayStatistics();

        dataSet.getStats().get(new Random().nextInt(10)).serialize();

        StateStatistic stateStatistic=StateStatistic.desirialize();

        System.out.println();
        System.out.println();
        System.out.println("There are "+stateStatistic.getNumBurros()+" burros and "+stateStatistic.getNumHorses()+
                " horses  in "+stateStatistic.getState());
    }
}
