package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class FacilitiesType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer FacTypeID;
	private String FacTypeName;
    public Integer getFacTypeID() {
        return FacTypeID;
    }
    public void setFacTypeID(Integer facTypeID) {
        FacTypeID = facTypeID;
    }
    public String getFacTypeName() {
        return FacTypeName;
    }
    public void setFacTypeName(String facTypeName) {
        FacTypeName = facTypeName;
    }
}
