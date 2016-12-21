package com.quatela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

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
		Properties props = new Properties();
		props.setProperty("displayName", "Rob Quatela");
		props.setProperty("accountNumber", "12344566");
		
		loadFromXML(props);
		
		String[] values = {
				props.getProperty("displayName"),
				props.getProperty("accountNumber")
		};
		
		for(String value : values)
			System.out.println(value);


	}
	
	private static void loadFromXML(Properties props) {
		try(InputStream in = Files.newInputStream(Paths.get("/home/rqutela/Documents/props.xml"))) {
			props.loadFromXML(in);
		}
		catch(Exception e) {
			
		}
	}
	
	private static void storeAsXML(Properties props) {
		try(OutputStream out = Files.newOutputStream(Paths.get("/home/rquatela/Documents/props.xml"))) {
			props.storeToXML(out, "My Comment");
		}
		catch(Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
	
	private static void writeProperty(Properties props) {

		try(Writer writer = Files.newBufferedWriter(Paths.get("/home/rquatela/Documents/xyz.properties"))) {
			props.store(writer, "My comment");
		}
		catch(IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
	
	private static void readProperty(Properties props) {
		try(Reader reader = Files.newBufferedReader(Paths.get("/home/rquatela/Documents/xyz.properties"))) {
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
