package com.hcl.utilityFactory.model;

import java.util.List;

public class ServicePojo {

	private List<String> sourceVersion;
	private List<String> targetVersion;

	public List<String> getSourceVersion() {
		return sourceVersion;
	}

	public void setSourceVersion(List<String> sourceVersion) {
		sourceVersion.add("10.3");
		sourceVersion.add("10.5");
		sourceVersion.add("10.7");
		this.sourceVersion = sourceVersion;
	}

	public List<String> getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(List<String> targetVersion) {
		targetVersion.add("10.5");
		targetVersion.add("10.7");
		targetVersion.add("10.11");
		this.targetVersion = targetVersion;
	}

}
