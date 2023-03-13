package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class NearByPlaces {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer NearByPlaces;
	private String name;
	private Integer distance;
	private Integer NearByPlacesType;
	private Integer dormID;
    public Integer getNearByPlaces() {
        return NearByPlaces;
    }
    public void setNearByPlaces(Integer nearByPlaces) {
        NearByPlaces = nearByPlaces;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDistance() {
        return distance;
    }
    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    public Integer getNearByPlacesType() {
        return NearByPlacesType;
    }
    public void setNearByPlacesType(Integer nearByPlacesType) {
        NearByPlacesType = nearByPlacesType;
    }
    public Integer getDormID() {
        return dormID;
    }
    public void setDormID(Integer dormID) {
        this.dormID = dormID;
    }
}
