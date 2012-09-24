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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.Regexp;


public class Csv {
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	public boolean eof = true;
	HashMap<Integer, String[]> hm = new HashMap<Integer, String[]>();
	String[] headerTypes = {
			"String",
			"Date",
			"Integer",
			"Float"
	};
	
	HashMap<String, String> regExp = new HashMap<String, String>();
	
	public Csv() {
		regExp.put("Float", "(\\d+)(\\.)(\\d*)");
		regExp.put("Integer", "(\\d+)");
		regExp.put("Date", "(\\d{2}).(\\d{2}).(\\d{4})");
		regExp.put("String", "(\")[a-zA-Z0-9\"\\;\\.\\/\\,\\?\\!]+(\")");
	}

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

			String[] ct = { column[0], column[1] };
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
	
	private boolean checkValue(String value, String type) {
		Pattern pattern = Pattern.compile( regExp.get(type) );
		Matcher matcher = pattern.matcher(value);
		
		
		return matcher.matches();
	}
	
	public String[] getLine() throws IOException, Exception {
		String buf = br.readLine();
		if (buf == null)
			return null;
		
		String[] content = buf.split(";");
		

		for (int i = 0; i < content.length; i++)
			if (!checkValue(content[i], hm.get(i)[1]))
				throw new Exception("!");
				
		
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
			if ( hm.get(i)[0].equals(column) )
				return i;
		
		throw new Exception("Column undefined");
	}

}
