package com.kkudormitory.kkudormitory.model.bean;

import jakarta.persistence.*;

@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer zoneID;
    private String zonenamethai;
    private String zonenameeng;
    public Integer getZoneID() {
        return zoneID;
    }
    public void setZoneID(Integer zoneID) {
        this.zoneID = zoneID;
    }
    public String getZonenamethai() {
        return zonenamethai;
    }
    public void setZonenamethai(String zonenamethai) {
        this.zonenamethai = zonenamethai;
    }
    public String getZonenameeng() {
        return zonenameeng;
    }
    public void setZonenameeng(String zonenameeng) {
        this.zonenameeng = zonenameeng;
    }

    
}
