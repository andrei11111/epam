import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Csv {
	private BufferedReader br;

	public void open(String file) throws IOException {
		br = new BufferedReader(new FileReader(file));
	}
	
	public void close() throws IOException {
		br.close();
	}
	
	public void get() throw IOException {
		System.out.println( br.readLine().split(";")[4].trim().split(" ")[0] );
	}

}
