package cs249.finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnectionHandler {

	public HttpConnectionHandler() {
		// TODO Auto-generated constructor stub
	}

	public String getInformation(String URL) throws IOException {
	
        URL RealURL = new URL(URL);
        URLConnection conn = RealURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder response = new StringBuilder(); 
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
          response.append("\r");
        }
        in.close();
        
        return response.toString();
	}


}
