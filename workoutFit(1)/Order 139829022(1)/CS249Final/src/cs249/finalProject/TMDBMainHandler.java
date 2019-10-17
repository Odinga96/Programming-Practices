package cs249.finalProject;

public class TMDBMainHandler {
	TMDBConfiguration tmdb;

	public TMDBMainHandler() {
		tmdb = new TMDBConfiguration();
		System.out.println(tmdb.getBase_URL());
	
		// TODO Auto-generated constructor stub
	}
	public String returnImageUrl(String image) {
		return tmdb.getImage_Base_URL()+image;
	}
	
	
	
	
	//Movie methods
	
	public String searchMovies(String title, int year,String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchMovies(String title, String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchMovies(String title, int year) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchMovies(String title ) {
		StringBuilder Result = new StringBuilder();
		
		
		
		return Result.toString();
		
	}
	public String getMovie(int id) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	
	//TVMethods
	
	public String searchTVShows(String title, int year,String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchTVShows(String title, String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchTVShows(String title, int year) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchTVShows(String title ) {
		StringBuilder Result = new StringBuilder();
		
		
		
		return Result.toString();
		
	}
	public String getTV(int id) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	

	
	
	

}
