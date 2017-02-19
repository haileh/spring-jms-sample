package com.github.haileh.sample.jms.model;

import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * Default UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String productId;
	private String productName;
	private String productDescription;
	
	public Product(String productId, String productName, String productDescription) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
