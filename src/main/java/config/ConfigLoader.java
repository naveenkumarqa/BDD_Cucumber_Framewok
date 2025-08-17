package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	private static Properties prop = new Properties();

	static {
		try(FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties file", e);
		}
	}

	private ConfigLoader() {
		
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public static Properties getProperties() {
		return prop;
	}
}