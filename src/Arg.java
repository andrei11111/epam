import java.util.Properties;

public class Arg {
	private Properties 	_prop = new Properties();
	
	private void addProperties(String key, String value) {
		_prop.put(key, value);
	}
	
	private void fill(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++)
			if (args[i].toCharArray()[0] == '-') {
				if (args[i + 1].isEmpty() | args[i + 1].toCharArray()[0] == '-')
					throw new Exception("Properties is Empty");
				
				addProperties(args[i].substring(1, args[i].length()), args[i + 1]);				
			}
	}
	
	public Arg(String[] args) throws Exception {
		if (args.length == 0)
			throw new Exception("args length == 0");
		
		fill(args);
	}
	
	public String getProperty(String key) {
		return _prop.getProperty(key);
	}
	
}
