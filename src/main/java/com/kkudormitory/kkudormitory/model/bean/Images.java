package com.kkudormitory.kkudormitory.model.bean;

import jakarta.persistence.*;

@Entity
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageID;
	private Integer dormID;
	private String ImageName;
	public Integer getImageID() {
		return imageID;
	}
	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}
	public Integer getDormID() {
		return dormID;
	}
	public void setDormID(Integer dormID) {
		this.dormID = dormID;
	}
	public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}
	
}
