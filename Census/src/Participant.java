import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Participant {
	
	static HashMap<Integer,PrintWriter> pWriters = new HashMap<Integer,PrintWriter>();
	//static HashMap<Integer,String> voteMajor = new HashMap<Integer,String>();
	
	
	static ArrayList<String> options = new ArrayList<String>();
	static HashMap<Integer,String> pVotes = new HashMap<Integer,String>();
	static int voteCount = 0;
	
	static int participantNum = 0;
	
	static int pport;
	static int timeout;
	static int[] otherPPorts;
	
	static void log(Object msg) {
		System.out.println("Participant " + pport + ": " + msg);
	}

	
	static void logError(Exception err) {
		System.out.print("Participant " + pport + ": ");
		err.printStackTrace();
	}
	
	public static Scanner readCommand(BufferedReader in, String expectedCommand) throws IOException {
		Scanner scan;
		
		String line = in.readLine();
		if (line == null) {
			// TODO: fix this
			return null;
		}
		log("received: " + line);
		scan = new Scanner(line);
		String command = scan.next();
		if (!command.equals(expectedCommand)) {
			// should always be the expected command
			return null;
		}
		return scan;
	}
	
	public static void writeCommand(PrintWriter writer, String a) {
		log(a);
		writer.println(a);
		writer.flush();
	}
	
//	public static String voteCounter(int i, String vote) {
//		return ("VOTE COUNT " + vote + i);
//	}
	
	static void terminateAfterTimeout() {
		new Thread(new Runnable() {
			public void run(){
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logError(e);
				}
				System.out.println("Participant " + pport + " stopped after timeout");
				System.exit(-1);
			}
		}).start();
	}
	
	static void listenForOtherParticipants() throws IOException {
		
	}
	
	public static void connectOtherPPorts() throws IOException {
		
	}
		
	public static void main(String [] args){

		try{
	
		int cport = Integer.parseInt(args[0]);
		pport = Integer.parseInt(args[1]);
		timeout = Integer.parseInt(args[2]);
		int failure = Integer.parseInt(args[3]);
		
		terminateAfterTimeout();
			
			log("connecting to coordinator on port " + cport);
			Socket socket = new Socket("localhost", cport);
			log("SUCCESS! connected to coordinator on port " + cport);
			
			PrintWriter cOut = new PrintWriter(socket.getOutputStream());
			BufferedReader cIn = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			log("Joining coordinator");

			writeCommand(cOut, "JOIN " + pport);

			Scanner scan = readCommand (cIn,"DETAILS");

            String portsString="";
			while (scan.hasNext())
			    portsString += scan.next()+",";


			otherPPorts = Stream.of(portsString.split(","))
					.map(s -> Integer.parseInt(s))
					.mapToInt(Integer::intValue).toArray();


            otherPPorts=new int[portsString.split(",").length];

            //Store the ports of other clients
            int index=0;
            for (String ports:portsString.split(",")) {
                otherPPorts[index++]=Integer.parseInt(ports);
            }


            scan = readCommand (cIn, "VOTE_OPTIONS");
			while (scan.hasNext()) {
				String option = scan.next();
				options.add(option);
			}


			//Handle voiting
			
			log("listening on port " + pport);
			ServerSocket ss = new ServerSocket(pport);
			
			// listen for other participants
			new Thread(new Runnable(){
				public void run(){
					for (int i = 0; i<otherPPorts.length; i++) {
						log("waiting for client #" + (++participantNum));
						try{
												
							final Socket client = ss.accept();

                            System.out.println(client.getPort());
							// TODO: start reading/writing
							int otherPPort = client.getPort();

							PrintWriter pOut = new PrintWriter(client.getOutputStream(), true);
							pWriters.put(otherPPort, pOut);

						}catch (Exception e) {logError(e);}
					}
				}	
			}).start();
			
			for (int i = 0; i<otherPPorts.length; i++){

				final int otherPPort = otherPPorts[i];
				if (otherPPort == pport) {
					continue;
				}


				Socket pSocket = new Socket("localhost",otherPPort);
				
				new Thread(new Runnable(){
					public void run(){

						BufferedReader pIn;
						try {
							pIn = new BufferedReader(
							new InputStreamReader(pSocket.getInputStream()));

							Scanner scanVote = readCommand (pIn,"VOTE");

                            System.out.println(scanVote.next());
							String otherPVote = scanVote.next();

                            System.out.println(otherPPort +"  votes "+otherPVote);

							pVotes.put(otherPPort,otherPVote);
							log(pVotes);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							logError(e);
						}
								
						
					}
				}).start();
			}
			
			
			// random voting
			Random rand = new Random();

			int myVote = rand.nextInt(options.size());

			pVotes.put(pport, options.get(myVote));

			for (PrintWriter pOut : pWriters.values()) {
				writeCommand(pOut,"VOTE " + pport + " " + options.get(myVote));
			}


			log("waiting for other participants");
            System.out.println(pVotes);
			while (pVotes.size()<=pWriters.size()) {

			Thread.sleep(1);
			}


			log("all participants finished voting");
			
			Map<String, Long> voteCounts0 = pVotes.entrySet()
				.stream()
				.collect(Collectors.groupingBy(entry -> entry.getValue(),
								Collectors.counting())
				);
			
			List<Entry<String, Long>> voteCounts = voteCounts0.entrySet()
					.stream()
					.sorted(Collections.reverseOrder(Entry.comparingByValue()))
					.collect(Collectors.toList());
			
			//voteCounts.sort(Map.Entry.comparingByValue());
			
			
//			 Map.Entry<String, Long> majorVote = voteCounts
//				.entrySet()
//				.stream()
//				.max(Map.Entry.comparingByValue())
//				.get();
				
			
//			Map<Long, String> orderedVotes = new TreeMap<Long, String>(new Comparator<Integer>() {
//				public int compare(Integer o1, Integer o2) {
//					return o2.compareTo(o1);
//				}
//			});
//					
//			orderedVotes.putAll(voteCounts);
			
			String outcome = voteCounts.get(0).getKey();
			String participants = String.join(" ", 
					pVotes.keySet()
					.stream()
					.map(x -> x.toString())
					.toArray(String[]::new)
			);
			
//			log("OUTCOME " + outcome + " " + participants);
//			cOut.println("OUTCOME " + outcome + " " + participants);
//			cOut.flush();
			
			writeCommand(cOut,"OUTCOME " + outcome + " " + participants);
	
			
			
				//for(int i=0;i<10;i++){
//					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//					String input = reader.readLine();
//					out.println(input); out.flush();
//					System.out.println(input +" sent");
//					out.println("TCP message "+i); out.flush();
//					System.out.println("TCP message "+i+" sent");
//					Thread.sleep(1000);
				//}
		}catch(Exception e){logError(e);}
	}
	
}
