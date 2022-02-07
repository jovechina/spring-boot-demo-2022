package com.jove.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class EstimationShortList implements Serializable {

	private static final long serialVersionUID = -7584057686632348825L;
	@Id
	private int estimationId;
	private String userName;
	private String roomType;
	private String interiorDecoration;
	@OneToOne
	@JoinColumn(name = "wires", referencedColumnName = "productid")	
	private RoomProduct wires;
	
	@OneToOne
	@JoinColumn(name = "cable", referencedColumnName = "productid")
	private RoomProduct cable;
	private Date createDate;
	private Date updateDate;
	public int getEstimationId() {
		return estimationId;
	}
	public void setEstimationId(int estimationId) {
		this.estimationId = estimationId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getInteriorDecoration() {
		return interiorDecoration;
	}
	public void setInteriorDecoration(String interiorDecoration) {
		this.interiorDecoration = interiorDecoration;
	}
	public RoomProduct getWires() {
		return wires;
	}
	public void setWires(RoomProduct wires) {
		this.wires = wires;
	}
	public RoomProduct getCable() {
		return cable;
	}
	public void setCable(RoomProduct cable) {
		this.cable = cable;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
