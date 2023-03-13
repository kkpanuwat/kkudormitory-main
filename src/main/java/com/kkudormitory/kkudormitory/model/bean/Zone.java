package com.kkudormitory.kkudormitory.model.bean;

import jakarta.persistence.*;

@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer zoneID;
    private String zonename;
    public Integer getZoneID() {
        return zoneID;
    }
    public void setZoneID(Integer zoneID) {
        this.zoneID = zoneID;
    }
    public String getZonename() {
        return zonename;
    }
    public void setZonename(String zonename) {
        this.zonename = zonename;
    }
    
}
