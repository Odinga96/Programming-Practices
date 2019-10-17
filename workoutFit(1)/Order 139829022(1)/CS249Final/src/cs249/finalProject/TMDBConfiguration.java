/**
 * 
 */
package cs249.finalProject;

import java.io.*;
import com.google.gson.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

/**
 * @author mtmay
 *
 */
public class TMDBConfiguration {
	private String base_URL;
	private String apiKey;
	private String image_Base_URL;
	private String image_Config_URL;
	private boolean adult; 
	private String language;
	

	/**
	 * configures all the information for connecting to The Movie DB API from a configuration file located in the src folder
	 */
	public TMDBConfiguration() {
		
		Properties prop = new Properties();
        try {
            // the configuration file name
            String fileName = "app.config";
            ClassLoader classLoader = TMDBConfiguration.class.getClassLoader();

            // Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                "Can't find configuration file app.config");

            InputStream is = new FileInputStream(res.getFile());
            

            // load the properties file
            prop.load(is);
            
           apiKey =  prop.getProperty("app.apiKey", "NULL");
           base_URL =  prop.getProperty("app.baseURL", "NULL");
           adult =  Boolean.parseBoolean(prop.getProperty("app.adult", "true"));
           language = prop.getProperty("app.language", "en-US");
           System.out.println(prop.getProperty("app.name", "NULL"));
           image_Config_URL = base_URL+"configuration?api_key="+apiKey;
           JsonParser jp = new JsonParser();
           
           HttpConnectionHandler handle = new HttpConnectionHandler();
           String response = handle.getInformation(image_Config_URL);
           JsonElement je = jp.parse(response);
           System.out.println(response);
           
           
           
           image_Base_URL = je.getAsJsonObject().getAsJsonObject("images").get("base_url").toString().replace("\"", "")+"original";           
           
           
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		// TODO Auto-generated constructor stub
	}
	
	//returns if you want to have adult genre included in result of search
	public boolean isAdult() {
		return adult;
	}
	
	//returns the language you want 
	public String getLanguage() {
		return language;
	}
	
	//retruns the base url for all methods dealing with the api
	public String getBase_URL() {
		return base_URL;
	}
	
	
	//Returns the API Key that you have to get from your account that you setup at https://www.themoviedb.org/
	public String getApiKey() {
		return apiKey;
	}
	
	//returns the base imageurl for getting movie/tv/actor image
	public String getImage_Base_URL() {
		return image_Base_URL;
	}
	
	//returns the url for all image configuration including sizes for the images that are supported
	public String getImage_Config_URL() {
		return image_Config_URL;
	}

}
