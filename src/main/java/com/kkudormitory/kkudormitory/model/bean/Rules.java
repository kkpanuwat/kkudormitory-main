package com.kkudormitory.kkudormitory.model.bean;
import jakarta.persistence.*;

@Entity
public class Rules {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ruleID;
	private String ruleName;
	private Integer dormID;
    public Integer getRuleID() {
        return ruleID;
    }
    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public Integer getDormID() {
        return dormID;
    }
    public void setDormID(Integer dormID) {
        this.dormID = dormID;
    }
    
}
