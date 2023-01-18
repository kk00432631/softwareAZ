package com.hcl.utilityFactory.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class GenerateDatFile {

	public static void main(String arg[]) throws IOException {
		/*
		 * String sourceFilePath=
		 * "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome1.dat";
		 * String targetFilePath=
		 * "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\temp.dat"; File
		 * sourceFile = new File(sourceFilePath); File targetFile = new
		 * File(targetFilePath); if(targetFile.exists()) { targetFile.delete(); }
		 * copyFile(sourceFile, targetFile); modifyFile(targetFilePath,"",targetFile);
		 */
		//generateLogFile();
	}
	
 public static String generateLogFile(File file) throws Exception {
     int lines = 100;
     int readLines = 0;
     StringBuilder builder = new StringBuilder();
     try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
         long fileLength = file.length()-1;
         // Set the pointer at the last of the file
         randomAccessFile.seek(fileLength);

         for (long pointer = fileLength; pointer >= 0; pointer--) {
             randomAccessFile.seek(pointer);
             char c;
             // read from the last, one char at the time
             c = (char) randomAccessFile.read();
             // break when end of the line
             if (c == '\n') {
                 readLines++;
                 if (readLines == lines)
                     break;
             }
             builder.append(c);
             fileLength = fileLength - pointer;
         }
         // Since line is read from the last so it is in reverse order. Use reverse
         // method to make it correct order
         builder.reverse();
       //  System.out.println(builder.toString());
         return builder.toString();
     }
 }

	public static String generateFile(String sourceFilePath, String targetFilePath, String targetVersion) throws IOException {
		File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetFilePath);
		if(targetFile.exists()) {
			targetFile.delete();
		}
		copyFile(sourceFile, targetFile);
		return modifyFile(targetFilePath,targetVersion,targetFile);
	}
	
	private static void copyFile(File sourceFile, File targetFile) throws IOException {
		Files.copy(sourceFile.toPath(), targetFile.toPath());
	}
	
	static String modifyFile(String filePath, String targetVersion,  File targetFile)
    {
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(targetFile));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
            	 if(line.contains("srcVersion")) {
            		 line = "<value name=\"srcVersion\">"+targetVersion+"</value>" ;
                 }
            	 oldContent = oldContent + line + System.lineSeparator();
                 
               
                line = reader.readLine();
            }
             
            writer = new FileWriter(targetFile);
             
            writer.write(oldContent);
            return oldContent;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        return oldContent;
    }
}
