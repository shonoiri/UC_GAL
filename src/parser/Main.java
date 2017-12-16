package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

/**
 * The main class of application.
 * 
 * @author Shonovi
 *
 */
public class Main {
	public static String inputFile;
	public static String outputFile;
	
	/**
	 * In this method XML parser is created.
	 * @param input input file 
	 */
	private static void load(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
		    InputStream    xmlInput  = new FileInputStream(inputFile);
		    SAXParser      saxParser = factory.newSAXParser();
		    DefaultHandler handler   = new SaxHandler(inputFile.split("\\.")[0]);
		    saxParser.parse(xmlInput, handler);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		} 		
		catch (Throwable err) {
		    err.printStackTrace ();
		}
	}

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Arguments expected : input output");
			return;
		}
			
		inputFile = args[0];
		outputFile = args[1];
		if(inputFile == null || outputFile == null) {
			System.out.println("Please enter valid input and output file names");
			return;
		}
		load();
	}
 }
