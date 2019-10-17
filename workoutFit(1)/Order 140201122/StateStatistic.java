import java.io.*;

/**
 * This class will manage statistics for USA Herd Management data
 * 
 * Herd Areas (HAs) are those geographic areas where wild horses and/or burros
 * were found at the passage of the Wild Horse and Burros Act in 1971.�
 * 
 * Herd Management Areas (HMAs) are those areas within Herd Areas where 
 * the decision has been made, through Land Use Plans, to manage for 
 * populations of wild horses and/or burros.�
 * 
 * BLM stands for the Bureau of Land Management
 * 
 * @author myersjac
 *
 */
public class StateStatistic extends Statistic {
	/** the state that contains herds of horses and burros **/
	private State state;
	
	/** the number of acres that contain horses and burros overseen by the BLM **/
	private long herdAreaAcresBLM;
	
	/** the number of acres that contain horses and burros not overseen by the BLM **/
	private long herdAreaAcresOther;
	
	/** the subset of herdAreaAcres overseen by the BLM where population management is active **/
	private long herdManagementAreaAcresBLM;
	
	/** the subset of herdAreaAcres not overseen by the BLM where population management is active **/
	private long herdManagementAreaAcresOther;
	
	/** the number of wild horses in the state **/
	private long numHorses;
	
	/** the number of wild burros in the state **/
	private long numBurros;

	
	/**
	 * the full argument constructor
	 * @param state the state that contains herds of horses and burros
	 * @param herdAreaAcresBLM the number of acres that contain horses and burros overseen by the BLM
	 * @param herdAreaAcresOther the number of acres that contain horses and burros not overseen by the BLM
	 * @param herdManagementAreaAcresBLM the subset of herdAreaAcres overseen by the BLM where population management is active
	 * @param herdManagementAreaAcresOther the subset of herdAreaAcres not overseen by the BLM where population management is active
	 * @param numHorses the number of wild horses in the state
	 * @param numBurros the number of wild burros in the state
	 */
	public StateStatistic(State state, long herdAreaAcresBLM,
			long herdAreaAcresOther, long herdManagementAreaAcresBLM,
			long herdManagementAreaAcresOther, long numHorses, long numBurros) {

		super();
		this.state = state;
		this.herdAreaAcresBLM = herdAreaAcresBLM;
		this.herdAreaAcresOther = herdAreaAcresOther;
		this.herdManagementAreaAcresBLM = herdManagementAreaAcresBLM;
		this.herdManagementAreaAcresOther = herdManagementAreaAcresOther;
		this.numHorses = numHorses;
		this.numBurros = numBurros;
	}



	/**
	 * Standard accessor method
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Standard mutator method
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Standard accessor method
	 * @return the herdAreaAcresBLM
	 */
	public long getHerdAreaAcresBLM() {
		return herdAreaAcresBLM;
	}

	/**
	 * Standard mutator method
	 * @param herdAreaAcresBLM the herdAreaAcresBLM to set
	 */
	public void setHerdAreaAcresBLM(long herdAreaAcresBLM) {
		this.herdAreaAcresBLM = herdAreaAcresBLM;
	}

	/**
	 * Standard accessor method
	 * @return the herdAreaAcresOther
	 */
	public long getHerdAreaAcresOther() {
		return herdAreaAcresOther;
	}

	/**
	 * Standard mutator method
	 * @param herdAreaAcresOther the herdAreaAcresOther to set
	 */
	public void setHerdAreaAcresOther(long herdAreaAcresOther) {
		this.herdAreaAcresOther = herdAreaAcresOther;
	}

	/**
	 * Standard accessor method
	 * @return the herdManagementAreaAcresBLM
	 */
	public long getHerdManagementAreaAcresBLM() {
		return herdManagementAreaAcresBLM;
	}

	/**
	 * Standard mutator method
	 * @param herdManagementAreaAcresBLM the herdManagementAreaAcresBLM to set
	 */
	public void setHerdManagementAreaAcresBLM(long herdManagementAreaAcresBLM) {
		this.herdManagementAreaAcresBLM = herdManagementAreaAcresBLM;
	}

	/**
	 * Standard accessor method
	 * @return the herdManagementAreaAcresOther
	 */
	public long getHerdManagementAreaAcresOther() {
		return herdManagementAreaAcresOther;
	}

	/**
	 * Standard mutator method
	 * @param herdManagementAreaAcresOther the herdManagementAreaAcresOther to set
	 */
	public void setHerdManagementAreaAcresOther(long herdManagementAreaAcresOther) {
		this.herdManagementAreaAcresOther = herdManagementAreaAcresOther;
	}

	/**
	 * Standard accessor method
	 * @return the numHorses
	 */
	public long getNumHorses() {
		return numHorses;
	}

	/**
	 * Standard mutator method
	 * @param numHorses the numHorses to set
	 */
	public void setNumHorses(long numHorses) {
		this.numHorses = numHorses;
	}

	/**
	 * Standard accessor method
	 * @return the numBurros
	 */
	public long getNumBurros() {
		return numBurros;
	}

	/**
	 * Standard mutator method
	 * @param numBurros the numBurros to set
	 */
	public void setNumBurros(long numBurros) {
		this.numBurros = numBurros;
	}



	public void serialize(){

		try{
			File file=new File("serialized.txt");

			FileOutputStream file_out=new FileOutputStream(file);

			ObjectOutputStream out=new ObjectOutputStream(file_out);

			out.writeObject(this);

			file_out.close();
			out.close();

		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static StateStatistic desirialize(){

		StateStatistic stateStatistic=null;

		try {
			File file=new File("serialized.txt");

			FileInputStream file_in=new FileInputStream(file);

			ObjectInputStream input=new ObjectInputStream(file_in);

			stateStatistic= (StateStatistic)input.readObject();


			file_in.close();
			input.close();
		}catch (IOException | ClassNotFoundException e){
			 e.printStackTrace();
		}
          return stateStatistic;
	}
}
