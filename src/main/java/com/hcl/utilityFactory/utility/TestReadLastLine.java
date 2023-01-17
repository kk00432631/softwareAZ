package com.hcl.utilityFactory.utility;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class TestReadLastLine {
	 public static void main(String[] args) throws Exception {

	      //  List<String> lines = readLastLine(new File("C:\\SoftwareAG\\IntegrationServer\\instances\\logs\\kkTest.log"), 3);
	      //  lines.forEach(x -> System.out.println(x));
		 
		 
		 readFromLast(new File("C:\\SoftwareAG\\IntegrationServer\\instances\\logs\\kkTest.log"));
		// C:\SoftwareAG\IntegrationServer\instances\logs\kkInstancejava1.log
	    }

	 
	 public static void readFromLast(File file) throws Exception {
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
	            System.out.println(builder.toString());
	        }

	    }
	    public static List<String> readLastLine(File file, int numLastLineToRead) {

	        List<String> result = new ArrayList<>();

	        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, StandardCharsets.UTF_8)) {

	            String line = "";
	            while ((line = reader.readLine()) != null && result.size() < numLastLineToRead) {
	                result.add(line);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return result;

	    }

	}


