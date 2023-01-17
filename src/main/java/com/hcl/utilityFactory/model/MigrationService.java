package com.hcl.utilityFactory.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hcl.utilityFactory.controller.MigrationController;

public class MigrationService {
	public static void main(String arg[]) {
		String sourcePath = "C:\\SoftwareAG_10.5\\IntegrationServer";
		String targetISpath = "C:\\SoftwareAG\\IntegrationServer\\bin\\migrate";
		String sourceIntance = "default";
		String targetInstance = "kkInstancejava";
		String migrationFile = "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome.dat";
		MigrationController mc = new MigrationController();
		//String filePath = "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrate.bat -srcDir "+sourcePath+" -importFile "+migrationFile+" -instanceName "+sourceIntance+" -newInstanceName "+targetInstance;
		String filePath = "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrate.bat";
				// " -srcDir "+sourcePath;
				//+" -importFile "+migrationFile+" -instanceName "+sourceIntance+" -newInstanceName "+targetInstance;
		//mc.test(filePath);
		//mc.migrate();
		mc.migratecmd();
		
		

	}
	
	public void migratecmd() {
		String sourcePath = "C:\\SoftwareAG_10.5\\IntegrationServer";
		String targetISpath = "C:\\SoftwareAG\\IntegrationServer\\bin\\migrate";
		String sourceIntance = "default";
		String targetInstance = "kkInstancejava";
		String migrationFile = "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome.dat";
		Process p = null;
		try {
			p = Runtime.getRuntime()
					.exec("cmd /c start "+targetISpath+"\\migrate.bat -srcDir "
							+ sourcePath + " -importFile+ " + migrationFile + " -instanceName " + sourceIntance
							+ " -newInstanceName " + targetInstance+" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void migratecmd(ISServiceDetails serviceDetails) {
		
		Process p = null;
		try {
			p = Runtime.getRuntime()
					.exec("cmd /c start C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrate.bat -srcDir "
							+ serviceDetails.getSourceISpath() + " -importFile+ " + serviceDetails.getMigrationFile()+ " -instanceName " + serviceDetails.getSourceInstance()
							+ " -newInstanceName " + serviceDetails.getTargetInstance()+" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void migrate() {
		String sourcePath = "C:\\SoftwareAG_10.5\\IntegrationServer";
		String targetISpath = "C:\\SoftwareAG\\IntegrationServer\\bin\\migrate";
		String sourceIntance = "default";
		String targetInstance = "kkInstancejava1";
		String migrationFile = "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome.dat";

		Process process = null;
		try {
			String  path = "C:\\Software\\MigrationUtility\\Test.bat";
	   process = Runtime.getRuntime().exec(new String[] {"cmd.exe","/c" +targetISpath+"\\migrate.bat -srcDir "+sourcePath+" -importFile+ "+migrationFile+" -instanceName "+sourceIntance+" -newInstanceName "+targetInstance });
		//	process = Runtime.getRuntime().exec(new String[] {"cmd.exe","/c",path});
		
			StringBuilder output = new StringBuilder();

			
			  BufferedReader reader = new BufferedReader(new
			 InputStreamReader(process.getInputStream()));
			//  InputStreamReader(process.getErrorStream()));
		
			  String line; while ((line = reader.readLine()) != null) { output.append(line
			  + "\n"); }
			 

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println(output);
				System.exit(0);
			} else {
				// abnormal...
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		
		
		
	}
	
	public void test(String path) {
         path = "C:\\Software\\MigrationUtility\\Test.bat";
		/*
		 * ProcessBuilder processBuilder = new ProcessBuilder(
		 * "C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\\\migrate\\migrate.bat");
		 */
		 ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "Test.bat");
		File dir = new File("C:/Software/MigrationUtility");
		try {
			processBuilder.directory(dir);
			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println(output);
				System.exit(0);
			} else {
				// abnormal...
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

