package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class Contacts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactID;
	private Integer dormID;
	private Integer	contactType;
	private String contactValue;
    public Integer getContactID() {
        return contactID;
    }
    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }
    public Integer getDormID() {
        return dormID;
    }
    public void setDormID(Integer dormID) {
        this.dormID = dormID;
    }
    public Integer getContactType() {
        return contactType;
    }
    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }
    public String getContactValue() {
        return contactValue;
    }
    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }  
}
