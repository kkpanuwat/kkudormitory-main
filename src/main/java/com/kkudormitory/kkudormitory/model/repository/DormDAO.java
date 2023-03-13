package com.kkudormitory.kkudormitory.model.repository;

import java.sql.*;
import java.util.*;

import org.springframework.web.bind.annotation.PathVariable;


public class DormDAO {
    private Connection con;
    public DormDAO() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://dbjavaweb.mysql.database.azure.com:3306/kkudormitory?characterEncoding=utf-8&useSSL=true", "supphitan", "0648801344@O");
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // แสดงแต่ละโซน
	public List<Map<String, Object>> getDormitory(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        PreparedStatement near = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.address, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID WHERE d.zoneid=? GROUP BY d.dormID");
	    near.setInt(1, id);
        ResultSet resultSetNear = near.executeQuery();
	    List<Map<String, Object>> dormitories = new ArrayList<>();
	    while (resultSetNear.next()) {
	        Map<String, Object> dormData = new HashMap<>();
	        dormData.put("dormID", resultSetNear.getObject("dormID"));
	        dormData.put("dorm_name", resultSetNear.getObject("dorm_name"));
	        dormData.put("month_price", resultSetNear.getObject("month_price"));
            dormData.put("dorm_address", resultSetNear.getObject("address"));
	        String imageUrlsStr = resultSetNear.getString("image_urls");
	        if (imageUrlsStr != null) {
	            String[] imageUrls = imageUrlsStr.split(",");
	            dormData.put("image_urls", Arrays.asList(imageUrls)); 
	        }
            PreparedStatement pStatement = con.prepareStatement("SELECT facilites.facid,facilites.fac_typeid,facilites.fac_name FROM dormitory JOIN facilites ON (facilites.dormid = dormitory.dormid) WHERE dormitory.dormid=? GROUP BY facilites.facid");
            pStatement.setInt(1, Integer.parseInt(resultSetNear.getString("dormID")));
            ResultSet resultSetFac = pStatement.executeQuery();
            List<Object> aList = new ArrayList<>();
            while (resultSetFac.next()) {
                Map<String, Object> a = new HashMap<>();
                a.put("fac_typeid", resultSetFac.getObject("fac_typeid"));
                a.put("fac_name", resultSetFac.getObject("fac_name"));
                a.put("facid", resultSetFac.getObject("facid"));
                aList.add(a);
            }
            dormData.put("dorm_fac",aList);

	        dormitories.add(dormData);
	    }
        con.close();

        return dormitories;
    }

    // แสดงทั้งหมด
    public List<Map<String, Object>> allDormitory() throws SQLException, ClassNotFoundException {
        PreparedStatement near = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.address, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID GROUP BY d.dormID");
        ResultSet resultSetNear = near.executeQuery();
	    List<Map<String, Object>> dormitories = new ArrayList<>();
	    while (resultSetNear.next()) {
	        Map<String, Object> dormData = new HashMap<>();
	        dormData.put("dormID", resultSetNear.getObject("dormID"));
	        dormData.put("dorm_name", resultSetNear.getObject("dorm_name"));
	        dormData.put("month_price", resultSetNear.getObject("month_price"));
            dormData.put("dorm_address", resultSetNear.getObject("address"));
	        String imageUrlsStr = resultSetNear.getString("image_urls");
	        if (imageUrlsStr != null) {
	            String[] imageUrls = imageUrlsStr.split(",");
	            dormData.put("image_urls", Arrays.asList(imageUrls)); 
	        }
            PreparedStatement pStatement = con.prepareStatement("SELECT facilites.facid,facilites.fac_typeid,facilites.fac_name FROM dormitory JOIN facilites ON (facilites.dormid = dormitory.dormid) WHERE dormitory.dormid=? GROUP BY facilites.facid");
            pStatement.setInt(1, Integer.parseInt(resultSetNear.getString("dormID")));
            ResultSet resultSetFac = pStatement.executeQuery();
            List<Object> aList = new ArrayList<>();
            while (resultSetFac.next()) {
                Map<String, Object> a = new HashMap<>();
                a.put("fac_typeid", resultSetFac.getObject("fac_typeid"));
                a.put("fac_name", resultSetFac.getObject("fac_name"));
                a.put("facid", resultSetFac.getObject("facid"));
                aList.add(a);
            }
            dormData.put("dorm_fac",aList);

	        dormitories.add(dormData);
	    }
        con.close();

        return dormitories;
    }

    // ค้าหาหอพัก



}
