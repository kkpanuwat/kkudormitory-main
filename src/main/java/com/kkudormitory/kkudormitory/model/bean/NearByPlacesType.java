package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class NearByPlacesType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer NearByPlacesTypeID;
	private String name;
    public Integer getNearByPlacesTypeID() {
        return NearByPlacesTypeID;
    }
    public void setNearByPlacesTypeID(Integer nearByPlacesTypeID) {
        NearByPlacesTypeID = nearByPlacesTypeID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
