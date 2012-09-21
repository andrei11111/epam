import java.io.IOException;


public class Search_csv {

	public static void main(String[] args) {
		try {
			Arg arg = new Arg(args);
			Csv csv_r = new Csv();
			Csv csv_w = new Csv();
			csv_w.open( arg.getProperty("out"), arg.getProperty("enc"), 'w');
			csv_r.open( arg.getProperty("in"), arg.getProperty("enc"), 'r' );
			
			String[] content = new String[ csv_r.getLenghtColumns() ];
			String buf = null;

		
			//while ( (buf = csv_r.getLine()[0]) != null) 
			
			csv_r.close();
	
			csv_w.open( arg.getProperty("out"), arg.getProperty("enc"), 'w');
			String[] column = { "a", "b", "c"};
			csv_w.write(column);
			csv_w.close();
			
		} catch (Exception e) {
			System.out.println( "Exception msg: " + e.getLocalizedMessage() );
		}
	}

}
