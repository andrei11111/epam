import java.io.IOException;


public class Search_csv {

	public static void main(String[] args) {
		try {
			Arg arg = new Arg(args);
			Csv csv_r = new Csv();
			Csv csv_w = new Csv();
			
			csv_r.open( arg.getProperty("in"), arg.getProperty("enc"), 'r' );
			csv_w.open( arg.getProperty("out"), arg.getProperty("enc"), 'w');
			
			String[] content = new String[ csv_r.getLenghtColumns() ];
			int indexSearchColumn = csv_r.getIndexColumn( arg.getProperty("col") );
			String searchExp = arg.getProperty("exp");
			
			while ((content = csv_r.getLine()) != null) {
				if (content[indexSearchColumn].equals(searchExp))
					csv_w.write(content);
			}
			
			csv_r.close();
			csv_w.close();
			
		} catch (Exception e) {
			System.out.println( "Exception msg: " + e.getLocalizedMessage() );
		}
	}

}
