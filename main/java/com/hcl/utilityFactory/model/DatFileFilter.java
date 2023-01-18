package com.hcl.utilityFactory.model;

public class DatFileFilter {

	private boolean migrateMasterPassword;
	private boolean migrateJDBCFunctions;
	private boolean migrateJDBCDrivers;
	private boolean migrateJDBCPools;
	private boolean migrateEmbeddedDb;
	private boolean migratePassman;
	private String targetVersion;

	public boolean isMigrateMasterPassword() {
		return migrateMasterPassword;
	}

	public void setMigrateMasterPassword(boolean migrateMasterPassword) {
		this.migrateMasterPassword = migrateMasterPassword;
	}

	public boolean isMigrateJDBCFunctions() {
		return migrateJDBCFunctions;
	}

	public void setMigrateJDBCFunctions(boolean migrateJDBCFunctions) {
		this.migrateJDBCFunctions = migrateJDBCFunctions;
	}

	public boolean isMigrateJDBCDrivers() {
		return migrateJDBCDrivers;
	}

	public void setMigrateJDBCDrivers(boolean migrateJDBCDrivers) {
		this.migrateJDBCDrivers = migrateJDBCDrivers;
	}

	public boolean isMigrateJDBCPools() {
		return migrateJDBCPools;
	}

	public void setMigrateJDBCPools(boolean migrateJDBCPools) {
		this.migrateJDBCPools = migrateJDBCPools;
	}

	public boolean isMigrateEmbeddedDb() {
		return migrateEmbeddedDb;
	}

	public void setMigrateEmbeddedDb(boolean migrateEmbeddedDb) {
		this.migrateEmbeddedDb = migrateEmbeddedDb;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public boolean isMigratePassman() {
		return migratePassman;
	}

	public void setMigratePassman(boolean migratePassman) {
		this.migratePassman = migratePassman;
	}

}
