package com.hcl.utilityFactory.model;

public class ProductsDetails {
	private String productName;
	private String selectedProducts;
	private ISServiceDetails serviceDetails;
	private String sourceVersion;
	private String targetVersion;

	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(String selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public ISServiceDetails getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(ISServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
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

	

}
