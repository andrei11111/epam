import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Csv {
	private BufferedReader br;
	public boolean eof = true;
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

	public void open(String file, String charset) throws IOException, Exception {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		
		fillHeader();
	}
	
	public void close() throws IOException {
		br.close();
	}
	
	public int getLenghtColumns() {
		return hm.size();
	}
	
	public String[] getLine() throws IOException, Exception {
		
		String[] content = br.readLine().split(";");
		
		if (content.length != hm.size())
			throw new Exception("Content is not equals headers");
		
		return content;
	}

}
