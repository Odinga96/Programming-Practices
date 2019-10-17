import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Coordinator {

	static void log(Object msg) {
		System.out.println("Coordinator " + ": " + msg);
	}

	
	static void logError(Exception err) {
		System.out.print("Coordinator " + ": ");
		err.printStackTrace();
	}

	public static Scanner readCommand(BufferedReader in, String expectedCommand) throws IOException {
		Scanner scan;
		
		String line = in.readLine();
		if (line == null) {
			// TODO: fix this
			return null;
		}
		System.out.println(line+" received");
		scan = new Scanner(line);
		String command = scan.next();
		if (!command.equals(expectedCommand)) {
			// should always be the expected command
			return null;
		}
		return scan;
	}
	
	static void terminateAfterTimeout() {

		int timeout = 500000;
		new Thread(new Runnable() {
			public void run(){
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Coordinator stopped after timeout");
				System.exit(-1);
			}
		}).start();
	}
		
	public static void main(String [] args){
		terminateAfterTimeout();
		
		int port = Integer.parseInt(args[0]);
		int parts = Integer.parseInt(args[1]);

		ArrayList<String> options = new ArrayList<String>();
		
		// store vote outcome by pport (id)
		HashMap<Integer, String> votes = new HashMap<Integer,String>();
		
		for (int i = 2; i<args.length; i++) {
			options.add(args[i]);
		}
		
		try{
			log("starting to listen on port: " + port);

			ServerSocket ss = new ServerSocket(port);

			for(int i = 0; i < (parts); i++){
				log("waiting for client #" + (i+1) + "/" + parts);

				try{


					final Socket client = ss.accept();
					final PrintWriter out = new PrintWriter(client.getOutputStream());

					final BufferedReader in = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
					
					new Thread(new Runnable(){
						public void run(){
							try{
								// receive JOIN
								Scanner scan = readCommand (in,"JOIN");
								

								int pport = scan.nextInt();// handle join here
								votes.put(pport,null);

								log("new participant: " + pport + " has joined");
								// wait for defined number of participants to join
								while (votes.size() < parts) {
									Thread.sleep(5);
								}
								
								// we are now sure that all participants have joined
								// send DETAILS to everyone
								out.print("DETAILS");

								//for (int i=0; i<votes.keySet().size(); i++) {
								for (int port : votes.keySet()) {
									out.print(" " + port);
									out.flush();
								}
								out.println();
								out.flush();
								
								// send out options
								out.print("VOTE_OPTIONS");
								for (String option : options) {
									out.print(" " + option);
								}
								out.println();
								out.flush();
								
								// wait for outcome and print it out


                                //Take the vote of a port and forward to other Participants


								
								client.close(); 
							}
							catch(Exception e){
								logError(e);
							}
						}
					}).start();
				}catch(Exception e){logError(e);}
			}
		}catch(Exception e){logError(e);}
	}
}
