package com.kkudormitory.kkudormitory.controller;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kkudormitory.kkudormitory.model.bean.*;
import com.kkudormitory.kkudormitory.model.repository.*;

@CrossOrigin
@RestController
@RequestMapping("api/dorm")
public class DormitoryApi {

	@Autowired
	private DormRepo repo;
	@Autowired
	private ZoneRepo repo2;

	// mapping
	private Map<String, Object> mappingDormitory(List<Object> resultList) {
		Map<String, Object> dormitories = new HashMap<>();
		ArrayList<Object> items = new ArrayList<>();
		for (Object result : resultList) {
			Map<String, Object> dormData = new HashMap<>();
			dormData.put("dormID", ((Object[]) result)[0]);
			dormData.put("dorm_name", ((Object[]) result)[1]);
			dormData.put("detail", ((Object[]) result)[2]);
			dormData.put("month_price", ((Object[]) result)[3]);
			String imageUrlsStr = (String) ((Object[]) result)[4];
			if (imageUrlsStr != null) {
				String[] imageUrls = imageUrlsStr.split(",");
				dormData.put("image_urls", Arrays.asList(imageUrls)); 
			}
			items.add(dormData);
		}
		String namezone = (String)((Object[]) resultList.get(0))[5];
		dormitories.put("amount", resultList.size());
		dormitories.put("name", namezone);
		dormitories.put("items", items);
		return dormitories;
	}

	@GetMapping("/main")
	public ArrayList getTest(){
		ArrayList resultList = new ArrayList<>();
		for(int i = 1 ; i < (repo2.getCount()+1);i++){
			List<Object> result = repo.getMain(i);
			resultList.add(mappingDormitory(result));
		}
    	
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
