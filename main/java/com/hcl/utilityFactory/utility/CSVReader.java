package com.hcl.utilityFactory.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hcl.utilityFactory.model.DeprecatedServices;

public class CSVReader {
	public static void main(String arg[]) {

		String line = "";
		String splitBy = ",";
		try {
			List<DeprecatedServices> serviceList = new ArrayList<DeprecatedServices>();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(
					"C:\\STSWorkspace\\SAG_UpgradeFactory\\src\\main\\resources\\DeprecatedServices.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				DeprecatedServices service = new DeprecatedServices();
				String[] cvsServices = line.split(splitBy); // use comma as separator
				service.setServiceName(cvsServices[0]);
				service.setServiceReplacement(cvsServices[1]);
				service.setWorkAround(cvsServices[2]);
				serviceList.add(service);
			}
			
			for(DeprecatedServices s :serviceList) {
				System.out.println(s.getServiceName()+" "+s.getServiceReplacement()+" "+s.getWorkAround());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<DeprecatedServices> readFile() {
		String line = "";
		String splitBy = ",";
		List<DeprecatedServices> serviceList = new ArrayList<DeprecatedServices>();
		try {
			
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(
					"C:\\STSWorkspace\\SAG_UpgradeFactory\\src\\main\\resources\\DeprecatedServices.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				DeprecatedServices service = new DeprecatedServices();
				String[] cvsServices = line.split(splitBy); // use comma as separator
				service.setServiceName(cvsServices[0]);
				service.setServiceReplacement(cvsServices[1]);
				service.setWorkAround(cvsServices[2]);
				serviceList.add(service);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serviceList;

	}
}
