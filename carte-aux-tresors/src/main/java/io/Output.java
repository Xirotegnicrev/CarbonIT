package io;

import java.io.*;
import java.time.LocalDateTime;

/**
 * The Class Output.
 */
public class Output {
	
	/** 
	 * 
	 *   
	 *   
	 */
	private static PrintWriter outToReportFile;
	
	/**
	 * Creates the output file and link it to the output stream.
	 *
	 * @param path
	 * 		Folder where the output file will be created
	 * @throws IOException
	 * 		Signals that an I/O exception has occurred.
	 */
	public static void createOut(String path) throws IOException {
		
		//Name of the file : YYYY-MM-DD HH-MM-SS
		String name = LocalDateTime.now().toString();
		name = name.replaceAll(":", "-");
		name = name.replaceAll("T", " ");
		String directory = path + "\\" + name + ".txt";
		
		//Creation of the file
		FileWriter fw = new FileWriter(new File(directory));
    	
		//Linking of the output stream to the file
		outToReportFile = new PrintWriter(fw);
    	
    	//Throws an IOException if an error appeared during the process
    	if(outToReportFile.checkError()) throw new IOException("Error during the creation of the output stream");
	}
	
	/**
	 * Close the output stream.
	 */
	public static void closeOut() {
		outToReportFile.close();
	}
	
	/**
	 * Write a line at the end of the output file using the output stream.
	 *
	 * @param line
	 * 		The line to write
	 */
	public static void writeReport(String line) {
		
		if(line!="") outToReportFile.println(line);
		outToReportFile.flush();
	}
}