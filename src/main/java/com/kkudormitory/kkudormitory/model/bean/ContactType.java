package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class ContactType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	contactType;
	private String name;
    public Integer getContactType() {
        return contactType;
    }
    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
