package com.jove.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class RoomService {
	@Id
	@Column(name="serviceid")
	private int serviceId;
	
	@Column(name="categoryid")
	private int categoryId;
	
	@Column(name="servicename")
	private String serviceName;
	
	@Column(name="serviceshortname")
	private String serviceShortName;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "serviceId")
	private List<RoomProduct> roomProducts;
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceShortName() {
		return serviceShortName;
	}
	public void setServiceShortName(String serviceShortName) {
		this.serviceShortName = serviceShortName;
	}
	public List<RoomProduct> getRoomProducts() {
		return roomProducts;
	}
	public void setRoomProducts(List<RoomProduct> roomProducts) {
		this.roomProducts = roomProducts;
	}
}
