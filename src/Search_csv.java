import java.io.IOException;


public class Search_csv {

	public static void main(String[] args) {
		try {
			Arg arg = new Arg(args);
			Csv csv = new Csv();
			
			csv.open( arg.getProperty("in"), arg.getProperty("enc") );
			
			String[] content = new String[ csv.getLenghtColumns() ];
			String buf = null;
		
			while ( (buf = csv.getLine()[0]) != null) 
				System.out.println(buf);
			
				
			csv.close();
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}

}
