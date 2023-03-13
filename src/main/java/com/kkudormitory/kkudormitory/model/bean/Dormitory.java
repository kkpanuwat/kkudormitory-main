package com.kkudormitory.kkudormitory.model.bean;

import jakarta.persistence.*;

@Entity
public class Dormitory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dormID ;
	private String dormName;
	private String address;
	private Integer monthPrice;
	private Integer daliyPrice;
	private Integer waterFeePrice;
	private Integer eletricFeePrice;
	private Integer centralFeePrice;
	private Integer insurancePrice;
	private String detail;
	private String map;
	private Integer zoneID;
	
	private Integer adminID=1;

	public Integer getDormID() {
		return dormID;
	}

	public void setDormID(Integer dormID) {
		this.dormID = dormID;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMonthPrice() {
		return monthPrice;
	}

	public void setMonthPrice(Integer monthPrice) {
		this.monthPrice = monthPrice;
	}

	public Integer getDaliyPrice() {
		return daliyPrice;
	}

	public void setDaliyPrice(Integer daliyPrice) {
		this.daliyPrice = daliyPrice;
	}

	public Integer getWaterFeePrice() {
		return waterFeePrice;
	}

	public void setWaterFeePrice(Integer waterFeePrice) {
		this.waterFeePrice = waterFeePrice;
	}

	public Integer getEletricFeePrice() {
		return eletricFeePrice;
	}

	public void setEletricFeePrice(Integer eletricFeePrice) {
		this.eletricFeePrice = eletricFeePrice;
	}

	public Integer getCentralFeePrice() {
		return centralFeePrice;
	}

	public void setCentralFeePrice(Integer centralFeePrice) {
		this.centralFeePrice = centralFeePrice;
	}

	public Integer getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(Integer insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public Integer getZoneID() {
		return zoneID;
	}

	public void setZoneID(Integer zoneID) {
		this.zoneID = zoneID;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
}
