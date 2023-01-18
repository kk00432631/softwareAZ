package com.hcl.utilityFactory.model;

import java.util.List;

public class ISServiceDetails {

	private ServicePojo servicePojo;
	private String sourceVersion;
	private String targetVersion;
	private String sourceISpath;
	private String targetISpath;
	private String sourceInstance;
	private String targetInstance;
	private String migrationFile;
	private String name;
	

	
	public ServicePojo getServicePojo() {
		return servicePojo;
	}

	public void setServicePojo(ServicePojo servicePojo) {
		this.servicePojo = servicePojo;
	}


	public String getSourceISpath() {
		return sourceISpath;
	}

	public void setSourceISpath(String sourceISpath) {
		this.sourceISpath = sourceISpath;
	}

	public String getTargetISpath() {
		return targetISpath;
	}

	public void setTargetISpath(String targetISpath) {
		this.targetISpath = targetISpath;
	}

	public String getSourceVersion() {
		return sourceVersion;
	}

	public void setSourceVersion(String sourceVersion) {
		this.sourceVersion = sourceVersion;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public String getSourceInstance() {
		return sourceInstance;
	}

	public void setSourceInstance(String sourceInstance) {
		this.sourceInstance = sourceInstance;
	}

	public String getTargetInstance() {
		return targetInstance;
	}

	public void setTargetInstance(String targetInstance) {
		this.targetInstance = targetInstance;
	}

	public String getMigrationFile() {
		return migrationFile;
	}

	public void setMigrationFile(String migrationFile) {
		this.migrationFile = migrationFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		
	
}
