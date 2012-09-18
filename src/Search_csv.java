import java.io.IOException;


public class Search_csv {

	public static void main(String[] args) {
		try {
			Arg arg = new Arg(args);
			Csv csv = new Csv();
			
			csv.open( arg.getProperty("in") );
			csv.get();
			csv.close();
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}

}
