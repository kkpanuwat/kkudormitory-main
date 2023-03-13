package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class Facilites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer FacID;
	private String FacName;
	private Integer FacTypeID;
	private Integer dormID;
    public Integer getFacID() {
        return FacID;
    }
    public void setFacID(Integer facID) {
        FacID = facID;
    }
    public String getFacName() {
        return FacName;
    }
    public void setFacName(String facName) {
        FacName = facName;
    }
    public Integer getFacTypeID() {
        return FacTypeID;
    }
    public void setFacTypeID(Integer facTypeID) {
        FacTypeID = facTypeID;
    }
    public Integer getDormID() {
        return dormID;
    }
    public void setDormID(Integer dormID) {
        this.dormID = dormID;
    }
}
