package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class ContractType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactType  ;
	private Integer dormID;
	private String name;
    public Integer getContactType() {
        return contactType;
    }
    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }
    public Integer getDormID() {
        return dormID;
    }
    public void setDormID(Integer dormID) {
        this.dormID = dormID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
