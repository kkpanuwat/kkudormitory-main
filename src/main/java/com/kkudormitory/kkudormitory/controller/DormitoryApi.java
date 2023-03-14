package com.kkudormitory.kkudormitory.controller;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kkudormitory.kkudormitory.model.bean.Dormitory;
import com.kkudormitory.kkudormitory.model.repository.DormRepo;

@CrossOrigin
@RestController
@RequestMapping("api/dorm")
public class DormitoryApi {

	@Autowired
	private DormRepo repo;


	@GetMapping("/main")
	public   ArrayList getDrom() throws ClassNotFoundException, SQLException {
	    ArrayList resultList = new ArrayList<>();
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/kkudormitory?characterEncoding=utf-8&useSSL=true", "root", "");
	    PreparedStatement near = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.detail, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID WHERE d.zoneid=1 GROUP BY d.dormID LIMIT 4");
	    ResultSet resultSetNear = near.executeQuery();


	    Map<String, Object> dormitories = new HashMap<>();
		// Map<String, Object> items = new HashMap<>();
		ArrayList<Object> x = new ArrayList();
		// items.put("items", items)

		dormitories.put("name", "LangMo");
		// dormitories.add(dormitories);
	    while (resultSetNear.next()) {
	        Map<String, Object> dormData = new HashMap<>();
	        dormData.put("dormID", resultSetNear.getObject("dormID"));
	        dormData.put("dorm_name", resultSetNear.getObject("dorm_name"));
	        dormData.put("detail", resultSetNear.getObject("detail"));
	        dormData.put("month_price", resultSetNear.getObject("month_price"));
	        String imageUrlsStr = resultSetNear.getString("image_urls");
	        if (imageUrlsStr != null) {
	            String[] imageUrls = imageUrlsStr.split(",");
	            dormData.put("image_urls", Arrays.asList(imageUrls)); 
	        }
	        x.add(dormData);
	    }
	    
		dormitories.put("items", x);
      //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	    PreparedStatement Kangsadan = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.detail, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID WHERE d.zoneid=2 GROUP BY d.dormID LIMIT 4");
	    ResultSet resultSetKangsadan = Kangsadan.executeQuery();
	    Map<String, Object> dormitoriesKangsadan = new HashMap<>();
		ArrayList<Object> y = new ArrayList();
		dormitoriesKangsadan.put("name", "Kangsadan");
	    while (resultSetKangsadan.next()) {
	        Map<String, Object> dormData = new HashMap<>();
	        dormData.put("dormID", resultSetKangsadan.getObject("dormID"));
	        dormData.put("dorm_name", resultSetKangsadan.getObject("dorm_name"));
	        dormData.put("detail", resultSetKangsadan.getObject("detail"));
	        dormData.put("month_price", resultSetKangsadan.getObject("month_price"));
	        String imageUrlsStr = resultSetKangsadan.getString("image_urls");
	        if (imageUrlsStr != null) {
	            String[] imageUrls = imageUrlsStr.split(",");
	            dormData.put("image_urls", Arrays.asList(imageUrls)); 
	        }
	        y.add(dormData);
	    }
		dormitoriesKangsadan.put("items", y);
	      //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		    PreparedStatement Mueang = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.detail, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID WHERE d.zoneid=5 GROUP BY d.dormID LIMIT 4");
		    ResultSet resultSetMueang = Mueang.executeQuery();
		    List<Map<String, Object>> dormitoriesMueang= new ArrayList<>();
		    while (resultSetMueang.next()) {
		        Map<String, Object> dormData = new HashMap<>();
		        dormData.put("dormID", resultSetMueang.getObject("dormID"));
		        dormData.put("dorm_name", resultSetMueang.getObject("dorm_name"));
		        dormData.put("detail", resultSetMueang.getObject("detail"));
		        dormData.put("month_price", resultSetMueang.getObject("month_price"));
		        String imageUrlsStr = resultSetMueang.getString("image_urls");
		        if (imageUrlsStr != null) {
		            String[] imageUrls = imageUrlsStr.split(",");
		            dormData.put("image_urls", Arrays.asList(imageUrls)); 
		        }
		        dormitoriesMueang.add(dormData);
		    }
		      //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		    PreparedStatement Kholambo = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.detail, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID WHERE d.zoneid=4 GROUP BY d.dormID LIMIT 4");
		    ResultSet resultSetKholambo = Kholambo.executeQuery();
		    List<Map<String, Object>> dormitoriesKholambo= new ArrayList<>();
		    while (resultSetKholambo.next()) {
		        Map<String, Object> dormData = new HashMap<>();
		        dormData.put("dormID", resultSetKholambo.getObject("dormID"));
		        dormData.put("dorm_name", resultSetKholambo.getObject("dorm_name"));
		        dormData.put("detail", resultSetKholambo.getObject("detail"));
		        dormData.put("month_price", resultSetKholambo.getObject("month_price"));
		        String imageUrlsStr = resultSetKholambo.getString("image_urls");
		        if (imageUrlsStr != null) {
		            String[] imageUrls = imageUrlsStr.split(",");
		            dormData.put("image_urls", Arrays.asList(imageUrls)); 
		        }
		        dormitoriesKholambo.add(dormData);
		    }
		      //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			    PreparedStatement NonMuang = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.detail, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID WHERE d.zoneid=3 GROUP BY d.dormID LIMIT 4");
			    ResultSet resultSetNonMuang = NonMuang.executeQuery();
			    List<Map<String, Object>> dormitoriesNonMuang= new ArrayList<>();
			    while (resultSetNonMuang.next()) {
			        Map<String, Object> dormData = new HashMap<>();
			        dormData.put("dormID", resultSetNonMuang.getObject("dormID"));
			        dormData.put("dorm_name", resultSetNonMuang.getObject("dorm_name"));
			        dormData.put("detail", resultSetNonMuang.getObject("detail"));
			        dormData.put("month_price", resultSetNonMuang.getObject("month_price"));
			        String imageUrlsStr = resultSetNonMuang.getString("image_urls");
			        if (imageUrlsStr != null) {
			            String[] imageUrls = imageUrlsStr.split(",");
			            dormData.put("image_urls", Arrays.asList(imageUrls)); 
			        }
			        dormitoriesNonMuang.add(dormData);
			    }

				
	    resultList.add(dormitories);
	    resultList.add(dormitoriesKangsadan);
		// resultList.add(Map.of("Mueang",dormitoriesMueang));
		// resultList.add(Map.of("Kholambo",dormitoriesKholambo));
		// resultList.add(Map.of("NonMuang",dormitoriesNonMuang));



	    // resultList.add(Map.of("LangMo", dormitories,"Kangsadan",dormitoriesKangsadan,"Mueang",dormitoriesMueang,"Kholambo",dormitoriesKholambo,"NonMuang",dormitoriesNonMuang));

		con.close();
	    return resultList;
	}
	//	หอที่แนะนำทางขวาหน้าจอที่มาใหม่สุด
	@GetMapping("/new")
	public  List<Map<String, Object>>   getDrome(Model model) throws ClassNotFoundException, SQLException {
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://dbjavaweb.mysql.database.azure.com:3306/kkudormitory?characterEncoding=utf-8&useSSL=true", "supphitan", "0648801344@O");
	    PreparedStatement dorm = con.prepareStatement("SELECT d.dormID, d.dorm_name, d.detail, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID "
	    		+"GROUP BY d.dormID "
	    		+ "ORDER BY dormid DESC "
	    		+ "LIMIT 4");
	    ResultSet resultSet = dorm.executeQuery();
	    List<Map<String, Object>> dormitories = new ArrayList<>();
	    while (resultSet.next()) {
	        Map<String, Object> dormData = new HashMap<>();
	        dormData.put("dormID", resultSet.getObject("dormID"));
	        dormData.put("dorm_name", resultSet.getObject("dorm_name"));
	        dormData.put("detail", resultSet.getObject("detail"));
	        dormData.put("month_price", resultSet.getObject("month_price"));
	        String imageUrlsStr = resultSet.getString("image_urls");
	        if (imageUrlsStr != null) {
	            String[] imageUrls = imageUrlsStr.split(",");
	            dormData.put("image_urls", Arrays.asList(imageUrls)); 
	        }
	        dormitories.add(dormData);
	    }
	    resultList.add(Map.of("data", dormitories));

		return  resultList;
	}
	
	@PostMapping("/updated/{id}")
	public Dormitory updatedDorm(@PathVariable("id") Integer id) {
		Dormitory f = repo.findById(id).get();
		return repo.save(f);
	}
	
	@DeleteMapping("/del/{id}")
	public String delDorm(@PathVariable("id") Integer id) {
		 repo.deleteById(id);
		return "SUCCESS";
	}
}
