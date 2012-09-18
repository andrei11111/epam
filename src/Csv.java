import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Csv {
	private BufferedReader br;
	HashMap<Integer, String> hm = new HashMap<Integer, String>();
	String[] headerTypes = {
			"String",
			"Date",
			"Integer",
			"Float"
	};
	
	private boolean isExistType(String type) {
		for (String _type : headerTypes)
			if (type.equals(_type))
				return true;
		
		return false;
	}
	
	private void fillHeader() throws Exception {
		String headString = br.readLine();
		
		for (int i = 0; i < headString.split(";").length; i++) {
			String[] collumn = headString.split(";")[i].trim().split(" ");
			
			if (!isExistType(collumn[1]))
				throw new Exception("type not found");
			
			hm.put(i, collumn[0]);

		}
	}

	public void open(String file) throws IOException, Exception {
		br = new BufferedReader(new FileReader(file));
		
		fillHeader();
	}
	
	public void close() throws IOException {
		br.close();
	}
	
	public void get() throws IOException {
		for (int i = 0; i < hm.size(); i++)
			System.out.printf("%s ", hm.get(i));
	}

}
