package com.hcl.utilityFactory.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hcl.utilityFactory.model.DatFileFilter;
import com.hcl.utilityFactory.model.DeprecatedServices;
import com.hcl.utilityFactory.model.ISServiceDetails;
import com.hcl.utilityFactory.model.MigrationService;
import com.hcl.utilityFactory.model.ProductsDetails;
import com.hcl.utilityFactory.model.ServicePojo;
import com.hcl.utilityFactory.model.UserDetail;
import com.hcl.utilityFactory.utility.CSVReader;
import com.hcl.utilityFactory.utility.GeneralUtility;
import com.hcl.utilityFactory.utility.GenerateDatFile;

@Controller
public class SAGUtilityController {
	private ISServiceDetails serviceDetails;
	private ServicePojo servicePojo;
	private DeprecatedServices services;
	private ProductsDetails productsDetails;
	private UserDetail userDetail;
	private DatFileFilter datFileFilter;
	
	
	@GetMapping("/")
	public String login(@ModelAttribute ("userDetail") UserDetail userDetail, Model model, HttpSession session) {
		if(model != null)
			model.addAttribute("error", null);
		
		if(loginValidation(userDetail)) {
			return "redirect:/home";
		}
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout( Model model, HttpServletRequest request) {
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("password");
		return "/";
	}
	
	@PostMapping("/homePageIS")
	public String homePageIS(@ModelAttribute ("productsDetails") ProductsDetails productsDetails, Model model) {
		System.out.println("Inside deprecated......");
		System.out.println("productsDetails-->"+productsDetails.getProductName());
		System.out.println("All selected products-->"+productsDetails.getSelectedProducts());
		serviceDetails = new ISServiceDetails();
		servicePojo = new ServicePojo();
		List<String> sourceVersion = new ArrayList<>();
		List<String> targetVersion = new ArrayList<>();
	
		servicePojo.setSourceVersion(sourceVersion);
		servicePojo.setTargetVersion(targetVersion);

		serviceDetails.setServicePojo(servicePojo);
		serviceDetails.setMigrationFile("C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome.dat");
		serviceDetails.setSourceInstance("default");
		serviceDetails.setTargetInstance("kkTest");
		serviceDetails.setSourceISpath("C:\\SoftwareAG_10.5\\IntegrationServer");
		serviceDetails.setTargetISpath("C:\\SoftwareAG\\IntegrationServer\\bin\\migrate");
		serviceDetails.setName("Kkaushal");
		model.addAttribute("serviceDetails", serviceDetails);
		return "homeIS";
	}
    @PostMapping("/migrationIS")
	public String migrationIS(@ModelAttribute ("serviceDetails") ISServiceDetails serviceDetails, Model model) throws Exception {
    	String logs = "";
    	String logPath = serviceDetails.getTargetISpath().substring(0, serviceDetails.getTargetISpath().indexOf("\\bin"));
		logPath = logPath+ "\\instances\\logs\\"+serviceDetails.getTargetInstance()+".log";
		if (new File(logPath).exists()) {
			logs = GenerateDatFile.generateLogFile(new File(logPath));
		}
		model.addAttribute("logPath", logPath);
    	model.addAttribute("migrationLog", logs);
		model.addAttribute("serviceDetails", serviceDetails);
        MigrationService migration = new MigrationService();
        migration.migratecmd(serviceDetails);
		return "successIS";
	}
    @PostMapping("/logGenerater")
    public String logGenerator(@ModelAttribute ("serviceDetails") ISServiceDetails serviceDetails, Model model) throws Exception {
    	System.out.println("logPaht-->"+serviceDetails.getTargetInstance());
    	System.out.println("logPaht11-->"+serviceDetails.getTargetISpath());
    	String logs = "";
    	String logPath = serviceDetails.getTargetISpath().substring(0, serviceDetails.getTargetISpath().indexOf("\\bin"));
		logPath = logPath+ "\\instances\\logs\\"+serviceDetails.getTargetInstance()+".log";
		if (new File(logPath).exists()) {
			logs = GenerateDatFile.generateLogFile(new File(logPath));
		}
		model.addAttribute("logPath", logPath);
    	model.addAttribute("migrationLog", logs);
    	return "successIS";
    }
	
	@Value("${welcome.message:test}")
	private String message = "Integration Server Migration";

	@GetMapping("/deprecated/{targetVersion}")
	public String deprecatedServices(@PathVariable String targetVersion,  Model model) {
		System.out.println("targetVersion---------->"+targetVersion);
		List<DeprecatedServices> serviceList = CSVReader.readFile() ;
		model.addAttribute("serviceList", serviceList);
		//model.addAttribute("targetVersion", "10.11");
		return "deprecatedServices";
	}
	
	@GetMapping("/feasibility/{targetVersion}")
	public String feasibility(@PathVariable String targetVersion,  Model model) {
		System.out.println("targetVersion---------->"+targetVersion);
		
		model.addAttribute("targetVersion", targetVersion);
		return "feasibility";
	}

	@PostMapping("/generateFile")
	public String generateFile(@ModelAttribute ("datFileFilter") DatFileFilter datFileFilter, Model model) throws IOException {
		System.out.println("sourcePath:--"+datFileFilter.isMigrateMasterPassword());
		String sourceFilePath = getServiceDetails().getMigrationFile();
		String targetFilePath = getServiceDetails().getTargetISpath()+"\\temp.dat";
		String content = GenerateDatFile.generateFile(sourceFilePath, targetFilePath, getServiceDetails().getTargetVersion());
		model.addAttribute("targetFilePath", targetFilePath);
		model.addAttribute("FileContent", content);
		return "fileGenerated";
	}
	@GetMapping("/navigateFileFilter")
	public String navigateFileFilter(@ModelAttribute ("datFileFilter") DatFileFilter datFileFilter, Model model) {
		System.out.println("22-->"+datFileFilter.getTargetVersion());
		return "fileFilterSelection";
	}
	
	public boolean loginValidation(UserDetail userDetail) {
		if(!GeneralUtility.stringNotEmpty(userDetail.getUsername()) || !GeneralUtility.stringNotEmpty(userDetail.getPassword())) {
			return false;
		}
		if(!userDetail.getUsername().equalsIgnoreCase("admin") || !userDetail.getPassword().equalsIgnoreCase("admin")) {
			return false;
		}
		
		return true;
	}
	
	@GetMapping("/loginController")
	public String loginController(@ModelAttribute ("userDetail") UserDetail userDetail, @ModelAttribute ("serviceDetails") ISServiceDetails serviceDetails,@ModelAttribute ("productsDetails") ProductsDetails productsDetails, Model model, HttpServletRequest request) {
		System.out.println("username==="+userDetail.getUsername());
		/*
		 * if(userDetail != null || userDetail.getUsername() !=null ||
		 * userDetail.getPassword() != null) {
		 * request.getSession().setAttribute("userName", userDetail.getUsername());
		 * request.getSession().setAttribute("password", userDetail.getPassword()); }
		 * System.out.println("From session username==="+request.getSession().
		 * getAttribute("userName"));
		 * userDetail.setPassword(request.getSession().getAttribute("userName").toString
		 * ());
		 * userDetail.setUsername(request.getSession().getAttribute("password").toString
		 * ()); if(!loginValidation(userDetail)) { model.addAttribute("error",
		 * "loginError"); return "/login"; }
		 */
		List<String> products = new ArrayList<String>();
		products.add("Integration Server");
		products.add("My WebMethod Server");
		products.add("Universal Messaging");
		model.addAttribute("productsDetails", productsDetails);
		model.addAttribute("products", products);
		model.addAttribute("error", null);
		serviceDetails = new ISServiceDetails();
		servicePojo = new ServicePojo();
		List<String> sourceVersion = new ArrayList<>();
		List<String> targetVersion = new ArrayList<>();
	
		servicePojo.setSourceVersion(sourceVersion);
		servicePojo.setTargetVersion(targetVersion);

		serviceDetails.setServicePojo(servicePojo);
		serviceDetails.setSourceVersion(productsDetails.getSourceVersion());
		serviceDetails.setTargetVersion(productsDetails.getTargetVersion());
		serviceDetails.setServicePojo(servicePojo);
		serviceDetails.setMigrationFile("C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome.dat");
		serviceDetails.setSourceInstance("default");
		serviceDetails.setTargetInstance("kkTest");
		serviceDetails.setSourceISpath("C:\\SoftwareAG_10.5\\IntegrationServer");
		serviceDetails.setTargetISpath("C:\\SoftwareAG\\IntegrationServer\\bin\\migrate");
		model.addAttribute("serviceDetails", serviceDetails);
		setServiceDetails(serviceDetails);

		return "home";
	}

	@PostMapping("/integrationServerController")
	public String integrationServerController(@ModelAttribute ("serviceDetails") ISServiceDetails serviceDetails,@ModelAttribute ("productsDetails") ProductsDetails productsDetails, Model model) {
		List<String> products = new ArrayList<String>();
		products.add("Integration Server");
		products.add("My WebMethod Server");
		products.add("Universal Messaging");
		model.addAttribute("productsDetails", productsDetails);
		model.addAttribute("products", products);
		serviceDetails.setSourceVersion(productsDetails.getSourceVersion());
		serviceDetails.setTargetVersion(productsDetails.getTargetVersion());
		serviceDetails.setServicePojo(servicePojo);
		serviceDetails.setMigrationFile("C:\\SoftwareAG_10.5\\IntegrationServer\\bin\\migrate\\migrateCustome.dat");
		serviceDetails.setSourceInstance("default");
		serviceDetails.setTargetInstance("kkTest");
		serviceDetails.setSourceISpath("C:\\SoftwareAG_10.5\\IntegrationServer");
		serviceDetails.setTargetISpath("C:\\SoftwareAG\\IntegrationServer\\bin\\migrate");
		model.addAttribute("serviceDetails", serviceDetails);
		setServiceDetails(serviceDetails);

		return "homeIS";
	}

	public ISServiceDetails getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(ISServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public ServicePojo getServicePojo() {
		return servicePojo;
	}

	public void setServicePojo(ServicePojo servicePojo) {
		this.servicePojo = servicePojo;
	}
	public DeprecatedServices getServices() {
		return services;
	}
	public void setServices(DeprecatedServices services) {
		this.services = services;
	}
	public ProductsDetails getProductsDetails() {
		return productsDetails;
	}
	public void setProductsDetails(ProductsDetails productsDetails) {
		this.productsDetails = productsDetails;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public DatFileFilter getDatFileFilter() {
		return datFileFilter;
	}

	public void setDatFileFilter(DatFileFilter datFileFilter) {
		this.datFileFilter = datFileFilter;
	}
	

}