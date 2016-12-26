package com.quatela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

	static String ubuntu = "/home/rquatela/Documents/props.xml";
	static String windows = "E:/Windows/Users/rquatela/Documents/Java Spot/training/props.xml";
	
	public static void main(String[] args) {
/*		if(args.length == 0) {
			showUsage();
			return;
		}
		
		String fileName = args[0];
		if(!Files.exists(Paths.get(fileName))) {
			System.out.println("\nFile not found: " + fileName);
			return;
		}
		
		showFileLines(fileName);*/
/*		Properties props = new Properties();
		props.setProperty("displayName", "Rob Quatela");
		props.setProperty("accountNumber", "12344566");
		
		loadFromXML(props);
		
		String[] values = {
				props.getProperty("displayName"),
				props.getProperty("accountNumber")
		};
		
		for(String value : values)
			System.out.println(value);*/
		
/*		Properties defaults = new Properties();
		defaults.setProperty("position", "1");
		Properties props = new Properties(defaults);
		String nextPos = props.getProperty("position");
		
		int iPos = Integer.parseInt(nextPos);
		props.setProperty("position", Integer.toString(++iPos));
		System.out.println(props.getProperty("position"));*/
		
		try {
			Properties defaultProps = new Properties();
			try(InputStream input = Main.class.getResourceAsStream("MyDefaultValues.xml")) {
				defaultProps.loadFromXML(input);
			}
			Properties userProps = new Properties(defaultProps);
			loadUserProps(userProps);
			
			String welcomeMessage = userProps.getProperty("welcomeMessage");
			String farewellMessage = userProps.getProperty("farewellMessage");
			
			System.out.println(welcomeMessage);
			System.out.println(farewellMessage);
			
			if(userProps.getProperty("isFirstRun").equalsIgnoreCase("Y")) {
				userProps.setProperty("welcomeMessage", "Welcome back");
				userProps.setProperty("farewellMessage", "Things will be familiar now");
				userProps.setProperty("isFirstRun", "N");
				saveUserProps(userProps);
			}
		}
		catch(IOException e) {
			System.out.println("Exception <" + e.getClass().getSimpleName() + "> " + e.getMessage());
		}


	}
	
	private static Properties loadUserProps(Properties userProps) throws IOException {
		Path userFile = Paths.get("E:/Windows/Users/rquatela/Documents/Java Spot/training/userValues.xml");
		if(Files.exists(userFile)) {
			try(InputStream input = Files.newInputStream(userFile)) {
				userProps.loadFromXML(input);
			}
		}
		return userProps;
	}
	
	private static void saveUserProps(Properties userProps) throws IOException {
		try(OutputStream output = Files.newOutputStream(Paths.get("E:/Windows/Users/rquatela/Documents/Java Spot/training/userValues.xml"))) {
			userProps.storeToXML(output, null);
		}
	}
	
	private static void loadFromXML(Properties props) {
		try(InputStream in = Files.newInputStream(Paths.get(windows))) {
			props.loadFromXML(in);
		}
		catch(Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
	
	private static void storeAsXML(Properties props) {
		try(OutputStream out = Files.newOutputStream(Paths.get(windows))) {
			props.storeToXML(out, "My Comment");
		}
		catch(Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
	
	private static void writeProperty(Properties props) {

		try(Writer writer = Files.newBufferedWriter(Paths.get(windows))) {
			props.store(writer, "My comment");
		}
		catch(IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
	
	private static void readProperty(Properties props) {
		try(Reader reader = Files.newBufferedReader(Paths.get(windows))) {
			props.load(reader);
		}
		catch(IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}

	}
	
	private static void showFileLines(String filename) {
		System.out.println();
		
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
			String line = null;
			while((line = reader.readLine()) != null)
				System.out.println(line);
		}
		catch(Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
	
	private static void showUsage() {
		System.out.println();
		System.out.println("Please provide the file name to process on the terminal");
	}
}
