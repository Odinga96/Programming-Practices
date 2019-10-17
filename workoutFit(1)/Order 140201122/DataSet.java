import java.io.*;
import java.util.ArrayList;

/**
 * A dataset is a collection of statistics
 */
public class DataSet {
	
	/** the collection of statistics **/
	private ArrayList<StateStatistic> stats = new ArrayList<StateStatistic>(20);

	/**
	 * the full-argument constructor
	 * @param stats the ArrayList of Statistics
	 */
	public DataSet(ArrayList<StateStatistic> stats) {
		this.stats = stats;
	}
		
	/**
	 * the no-argument constructor which initializes the stats collection
	 */
	public DataSet() {
		this.stats = new ArrayList<StateStatistic>();
	}

	/**
	 * addStatistic will add a stat to the stats collection
	 * @param stat the statistic to add
	 */
	public void addStatistic(StateStatistic stat) {
		this.stats.add(stat);
	}

	/**
	 * standard accessor method
	 * @return the stats
	 */
	public ArrayList<StateStatistic> getStats() {
		return stats;
	}

	/**
	 * standard mutator method
	 * @param stats the stats to set
	 */
	public void setStats(ArrayList<StateStatistic> stats) {
		this.stats = stats;
	}

	public void loadStatistics(DataSet set, String filename, int header_rows) throws StaticDataNotFoundException {

		File file=new File(filename);

		if (!file.exists())
			throw new StaticDataNotFoundException(filename,"file not found");


		FileReader fileReader= null;
		try {

			fileReader = new FileReader(file);


		BufferedReader reader=new BufferedReader(fileReader);

		String readline;

		int count=1;
		while((readline=reader.readLine()) !=null){

			if (count >header_rows) {


				String[] data=readline.split(",");

				State state=State.valueOf(data[0]);
				Long  HerdAreaAcresBLM=Long.parseLong(data[1]);
				Long  HerdAreaAcresOther=Long.parseLong(data[2]);
				Long  HerdManagementAreaAcresBLM=Long.parseLong(data[3]);
				Long  HerdManagementAreaAcresOther=Long.parseLong(data[4]);
				Long  NumHorses=Long.parseLong(data[5]);
				Long  NumBurros=Long.parseLong(data[6]);



				StateStatistic stateStatistic=new StateStatistic(state,HerdAreaAcresBLM,HerdAreaAcresOther,
						                         HerdManagementAreaAcresBLM,HerdManagementAreaAcresOther,NumHorses,NumBurros);



				set.addStatistic(stateStatistic);
			}
			count++;
		}


		fileReader.close();
		reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

		public void displayStatistics(){
		for (StateStatistic  statistic:getStats()) {
			System.out.println(statistic.getState()+"\t\t\t"+statistic.getHerdAreaAcresBLM()+"\t\t\t"+
					statistic.getHerdAreaAcresOther()+"\t\t\t"+statistic.getHerdManagementAreaAcresBLM()+"\t\t\t"+
					statistic.getHerdManagementAreaAcresOther()+"\t\t\t"+statistic.getNumHorses()+"\t\t\t"+statistic.getNumBurros());

		}
	}
}
