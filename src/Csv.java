import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;


public class Csv {
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	public boolean eof = true;
	HashMap<Integer, HashMap<String, String>> hm = new HashMap<Integer, HashMap<String, String>>();
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
			String[] column = headString.split(";")[i].trim().split(" ");
			
			if (!isExistType(column[1]))
				throw new Exception("type not found");
			
			HashMap<String, String> ct = new HashMap<String, String>();
			ct.put(column[0], column[1]);
			hm.put(i,  ct);

		}
	}

	public void open(String file, String charset, char mode) throws Exception {
		switch (mode) {
			case 'r':
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
				fillHeader();
				break;
			case 'w':
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
				break;
			default:
				throw new Exception("mode hz");
		}
	}
	
	public void close() throws IOException {
		if (br != null)
			br.close();
		if (bw != null) {
			bw.close();
		}
	}
	
	public int getLenghtColumns() {
		return hm.size();
	}
	
	public String[] getLine() throws IOException, Exception {
		String buf = br.readLine();
		if (buf == null)
			return null;
		
		String[] content = buf.split(";");
		
		if (content.length != hm.size())
			throw new Exception("Content is not equals headers");

	
		return content;
	}
	
	private String array2string(String[] arr, String delimiter) {
		String str = new String();
		
		for (int i = 0; i < arr.length; i++) {
			str = str.concat( arr[i].toString() );
			
			if (i < arr.length - 1)
				str = str.concat(delimiter);
				
		}
		
		return str;
	}
	
	public void write(String[] column) throws IOException {
		bw.write( array2string(column, ";") );
	}
	
	public int getIndexColumn(String column) throws Exception {
		for (int i = 0; i < hm.size(); i++) 
			if ( hm.get(i).containsKey(column) )
				return i;
		
		throw new Exception("Column undefined");
	}

}
