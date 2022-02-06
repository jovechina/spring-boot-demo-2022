package com.jove.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class RoomProduct {
	@Id
	@Column(name="productid")
	private int productId;
	@Column(name="serviceid")
	private int serviceId;
	@Column(name="sortid")
	private int sortId;
	@Column(name="productname")
	private String productName;
	@Column(name="productprice")
	private int productPrice;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	
}
