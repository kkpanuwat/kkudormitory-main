package com.kkudormitory.kkudormitory.controller;
import java.sql.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kkudormitory.kkudormitory.model.bean.*;
import com.kkudormitory.kkudormitory.model.repository.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("api/crud")
public class CrudDormAPI {
	@Autowired
	private DormRepo repo;

	@Autowired
	private ImageRepo Imgrepo;
	@Autowired
	private RulesRepo rulerepo;
	@Autowired
	private FacilityRepo facrepo;
	@Autowired
	private ContactRepo contactrepo;
	@Autowired
	private ContractRepo contractrepo;
	@Autowired
	private NearByRepo nearbyrepo;
	@PersistenceContext
	private EntityManager entityManager;
	
	
	//	http://localhost:8080/crud/main 
	// ข้อมูลที่มาจะมีชื่อหอ ที่อยู่ ละโซน ทำ pagination ไม่ได้
	//จะมาทุกหอเรียงใหม่สุดไปเก่า
	   @GetMapping("/main")
	public List<Object> showMain (){
		return repo.adminMain();
	}
	
	
//	   หอพักทำ pagination 
	//http://localhost:8080/crud/page?sortBy=dormName&page=0
	@GetMapping("/page")
	public Page<Dormitory> test(
			@RequestParam  Optional<Integer> page,
			@RequestParam Optional<String> sortBy
	) {
	    Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("dormID"));
	    return repo.findAll(pageable);
	}
	

//เวลาคลิกเข้าไปมันจะดึงรายละเอียดหอพักมาให้โดยใช้ id ที่ส่งเข้ามา
// เช่น  http://localhost:8080/crud/detail/1 ต้องลองรันแล้วเอาไปใส่ postman
	@GetMapping("/detail/{id}")
	public List<Map<String, Object>> getDetail(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://dbjavaweb.mysql.database.azure.com:3306/kkudormitory?characterEncoding=utf-8&useSSL=true", "supphitan", "0648801344@O");
	    PreparedStatement pStatement = con.prepareStatement("SELECT facilites.facid,facilites.fac_typeid,facilites.fac_name FROM dormitory\n"
	            + "JOIN facilites ON (facilites.dormid = dormitory.dormid)\n"
	            + "WHERE dormitory.dormid=?");
	    pStatement.setInt(1, id);
	    ResultSet resultSetFac = pStatement.executeQuery();
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    List<Object> aList = new ArrayList<>();
	    while (resultSetFac.next()) {
	        Map<String, Object> a = new HashMap<>();
	        a.put("fac_typeid", resultSetFac.getObject("fac_typeid"));
	        a.put("fac_name", resultSetFac.getObject("fac_name"));
	        a.put("facid", resultSetFac.getObject("facid"));
	        aList.add(a);
	    }
//	    ------------------------------------------------------------------------------------------------
	    Map<String, Object> b = new HashMap<>();
	    PreparedStatement dromdata = con.prepareStatement("SELECT * FROM dormitory\n"
	            + "WHERE dormitory.dormid=?");
	    dromdata.setInt(1, id);
	    ResultSet resultSetdorm = dromdata.executeQuery();

	    if (resultSetdorm.next()) {
	        ResultSetMetaData dormMetaData = resultSetdorm.getMetaData();
	        int dormColumnCount = dormMetaData.getColumnCount();
	        for (int i = 1; i <= dormColumnCount; i++) {
	            b.put(dormMetaData.getColumnName(i), resultSetdorm.getObject(i));
	        }
	    }
	    
//	    ------------------------------------------------------------------------------------------------
	    PreparedStatement rule = con.prepareStatement("SELECT ruleid,rule_name FROM dormitory\n"
	            + "JOIN rules ON (dormitory.dormid = rules.dormid)\n"
	            + "WHERE dormitory.dormid = ?");
	    rule.setInt(1, id);
	    ResultSet resultSetRule = rule.executeQuery();
	    List<Map<String, Object>> ruleList = new ArrayList<>();
	    while (resultSetRule.next()) {
	        Map<String, Object> ruleData = new HashMap<>();
	        ruleData.put("ruleid", resultSetRule.getObject("ruleid"));
	        ruleData.put("rule_name", resultSetRule.getObject("rule_name"));
	        ruleList.add(ruleData);
	    }
//	    ------------------------------------------------------------------------------------------------
	    PreparedStatement img = con.prepareStatement("SELECT imageid,image_name FROM dormitory\n"
	            + "JOIN images ON (dormitory.dormid = images.dormid)\n"
	            + "WHERE dormitory.dormid = ?");
	    img.setInt(1, id);
	    ResultSet resultSetImg = img.executeQuery();
	    List<Map<String, Object>> imgList = new ArrayList<>();
	    while (resultSetImg.next()) {
	        Map<String, Object> ruleData = new HashMap<>();
	        ruleData.put("imageid", resultSetImg.getObject("imageid"));
	        ruleData.put("image_name", resultSetImg.getObject("image_name"));
	        imgList.add(ruleData);
	    }
//	    ------------------------------------------------------------------------------------------------
	    PreparedStatement contract = con.prepareStatement("SELECT name FROM dormitory\n"
	            + "JOIN contract_type ON (dormitory.dormid = contract_type.dormid)\n"
	            + "WHERE dormitory.dormid = ?");
	    contract.setInt(1, id);
	    ResultSet resultSetcontract = contract.executeQuery();
	    List<Map<String, Object>> contractList = new ArrayList<>();
	    while (resultSetcontract.next()) {
	        Map<String, Object> ruleData = new HashMap<>();
	        ruleData.put("name", resultSetcontract.getObject("name"));
	        contractList.add(ruleData);
	    }
//	    ------------------------------------------------------------------------------------------------
	    PreparedStatement contact = con.prepareStatement("SELECT contacts.contactid,contact_type.contact_type ,contact_type.name, contacts.contact_value FROM `dormitory` \n"
	    		+ "JOIN contacts ON (dormitory.dormid = contacts.dormid)\n"
	    		+ "JOIN contact_type ON (contact_type.contact_type = contacts.contact_type)\n"
	    		+ "WHERE dormitory.dormid=?");
	    contact.setInt(1, id);
	    ResultSet resultSetcontact = contact.executeQuery();
	    List<Map<String, Object>> contactList = new ArrayList<>();
	    while (resultSetcontact.next()) {
	        Map<String, Object> ruleData = new HashMap<>();
	        ruleData.put("contactid", resultSetcontact.getObject("contactid"));
	        ruleData.put("contact_type", resultSetcontact.getObject("name"));
	        ruleData.put("value", resultSetcontact.getObject("contact_value"));
	        contactList.add(ruleData);
	    }
//	    ------------------------------------------------------------------------------------------------
	    PreparedStatement near = con.prepareStatement("SELECT near_by_places.near_by_places, near_by_places.name,near_by_places.distance,near_by_places_type.near_by_places_typeid,near_by_places_type.name as \"typeName\"  FROM `dormitory` \n"
	    		+ "JOIN near_by_places ON (dormitory.dormid= near_by_places.dormid)\n"
	    		+ "JOIN near_by_places_type ON (near_by_places.near_by_places_type = near_by_places_type.near_by_places_typeid)\n"
	    		+ "WHERE dormitory.dormid=?");
	    near.setInt(1, id);
	    ResultSet resultSetNear = near.executeQuery();
	    List<Map<String, Object>> NearList = new ArrayList<>();
	    while (resultSetNear.next()) {
	        Map<String, Object> ruleData = new HashMap<>();
	        ruleData.put("id", resultSetNear.getObject("near_by_places"));
	        ruleData.put("name", resultSetNear.getObject("name"));
	        ruleData.put("distance", resultSetNear.getObject("distance"));
	        ruleData.put("typeName", resultSetNear.getObject("typeName"));
	        ruleData.put("near_by_places_typeid", resultSetNear.getObject("near_by_places_typeid"));
	        NearList.add(ruleData);
	    }
	    resultList.add(Map.of("fac", aList, "dorm", b,"rule",ruleList,"img",imgList,"contract",contractList,"contact",contactList,"near_by_places_type",NearList));
	    
	    con.close();
	    return resultList;
	}
//	 หน้าบ้านต้องส่ง json มาแบบนี้
//	{
//        "address":"test",
//        "adminID":1,
//        "centralFeePrice"::เป็น int หรือไม่ต้องส่งมาก็ได,
//        "daliyPrice"::เป็น int หรือไม่ต้องส่งมาก็ได,
//        "detail":"TEST",
//        "dormName":"TEST",
//        "eletricFeePrice"::เป็น int หรือไม่ต้องส่งมาก็ได,
//        "insurancePrice"::เป็น int หรือไม่ต้องส่งมาก็ได,
//        "map":"16.48289732677371,102.81734489945974",
//        "monthPrice":เป็น int หรือไม่ต้องส่งมาก็ได้ ,
//        "waterFeePrice"::เป็น int หรือไม่ต้องส่งมาก็ได,
//        "zoneID":1 
//}
	@PostMapping("/create")
	public String createDorm(@RequestBody Object dorm, Model model) {
		  Map<String, Object> objectMap = (Map<String, Object>) dorm;
		  String dormname = (String) objectMap.get("dormname");
		  String address = (String) objectMap.get("address");
		 List< String> contactsTel =( List<String>) objectMap.get("contactsTel");
		 List< String> contactsEmail =( List<String>) objectMap.get("contactsEmail");
		 List< String> contactsFacebook =( List<String>) objectMap.get("contactsFacebook");
		 List< String> contactsLine=( List<String>) objectMap.get("contactsLine");
		 List< String> facprivate=( List<String>) objectMap.get("facprivate");
		 List< String> facpublic=( List<String>) objectMap.get("facpublic");
		 List< String> rule=( List<String>) objectMap.get("rule");
		  String zone = (String) objectMap.get("zone");
		  String dorm_detail = (String) objectMap.get("dorm_detail");
		  String googlemap = (String) objectMap.get("googlemap");
		  Integer monthly = (Integer) objectMap.get("monthly");
		  Integer daily = (Integer) objectMap.get("daily");
		  Integer waterFee = (Integer) objectMap.get("waterFee");
		  Integer electricFee = (Integer) objectMap.get("electricFee");
		  Integer centralFee = (Integer) objectMap.get("centralFee");
		  Integer insurance = (Integer) objectMap.get("insurance");
			 List< String> contract=( List<String>) objectMap.get("contract");
			 List< String> nearbyedu=( List<String>) objectMap.get("nearbyedu");
			 List< String> nearbydistanceredu=( List<String>) objectMap.get("nearbydistanceedu");
			 List< String> nearbyroad=( List<String>) objectMap.get("nearbyroad");
			 List< String> nearbydistanceroad=( List<String>) objectMap.get("nearbydistanceroad");
			 List< String> nearbystore=( List<String>) objectMap.get("nearbystore");
			 List< String> nearbydistancestore=( List<String>) objectMap.get("nearbydistancestore");
			 List< String> images=( List<String>) objectMap.get("images");

//
	Dormitory d = new Dormitory();
		d.setAddress(address);
		d.setDormName(dormname);
		d.setCentralFeePrice(centralFee);
		d.setDaliyPrice(daily);
		d.setDetail(dorm_detail);
		d.setEletricFeePrice(electricFee);
		d.setInsurancePrice(insurance);
		d.setMap(googlemap);
		d.setMonthPrice(monthly);
		d.setWaterFeePrice(waterFee);
		d.setZoneID(Integer.parseInt(zone));
		Integer id = repo.save(d).getDormID();
		System.out.println(images);
		for (String imageName : images) {
			if(!imageName.isBlank()) {
			    Images image = new Images();
			    image.setDormID(id);
			    image.setImageName(imageName);
			    Imgrepo.save(image);
			}

		}
		for (String name : rule) {
			if(!name.isBlank()) {
			    Rules r = new Rules();
			    r.setDormID(id);
			    r.setRuleName(name);
			    rulerepo.save(r);
			}

		}
		for (String name : contactsTel) {
			if(!name.isBlank()) {

			    Contacts r = new Contacts();
			    r.setDormID(id);
			    r.setContactType(4);
			    r.setContactValue(name);
			    contactrepo.save(r);
			}

		}
		for (String name : contactsEmail) {
			if(!name.isBlank()) {
				 Contacts r = new Contacts();
				    r.setDormID(id);
				    r.setContactType(1);
				    r.setContactValue(name);
				    contactrepo.save(r);
			}
		}
		for (String name : contactsFacebook) {
			if(!name.isBlank()) {
			    Contacts r = new Contacts();
			    r.setDormID(id);
			    r.setContactType(2);
			    r.setContactValue(name);
			    contactrepo.save(r);
			}

		}
		for (String name : contactsLine) {
			if(!name.isBlank()) {
			    Contacts r = new Contacts();
			    r.setDormID(id);
			    r.setContactType(2);
			    r.setContactValue(name);
			    contactrepo.save(r);
			}

		}
		for (String name : facpublic) {
			if(!name.isBlank()) {
			    Facilites r = new Facilites();
			    r.setDormID(id);
			    r.setFacTypeID(2);
			    r.setFacName(name);
			    facrepo.save(r);
			}

		}
		for (String name : facprivate) {
			if(!name.isBlank()) {
			    Facilites r = new Facilites();
			    r.setDormID(id);
			    r.setFacTypeID(1);
			    r.setFacName(name);
			    facrepo.save(r);
			}

		}
		for (String name : contract) {
			if(!name.isBlank()) {
			    ContractType r = new ContractType();
			    r.setDormID(id);
			    r.setName(name);
			    contractrepo.save(r);
			}

		}
			 List<List<Object>> pairedList = new ArrayList<List<Object>>();
			 for (int i = 0; i < nearbyedu.size() && i < nearbydistanceredu.size(); i++) {
				 if(!nearbyedu.get(i).isEmpty() && !nearbydistanceredu.get(i).isEmpty()) {
				     List<Object> pair = new ArrayList<Object>();
				     pair.add(nearbyedu.get(i));
				     pair.add(nearbydistanceredu.get(i));
				     pairedList.add(pair);
				 }
			 }
			 for (List<Object> pair : pairedList) {
				 NearByPlaces r = new NearByPlaces();
	
				 Integer count = 0;
				 for (Object element : pair) {
				        if(count==0) {
							   r.setName((String) element);
				        }else {
							   r.setDistance(Integer.parseInt((String) element));
				        }
				        count++;
				    }

				   r.setDormID(id);
				   r.setNearByPlacesType(3);
				   nearbyrepo.save(r);
				   
				}
			 List<List<Object>> pairedstore = new ArrayList<List<Object>>();
			 for (int i = 0; i < nearbystore.size() && i < nearbydistancestore.size(); i++) {
				 if(!nearbystore.get(i).isEmpty()  && !nearbydistancestore.get(i).isEmpty()) {
				     List<Object> pair = new ArrayList<Object>();
				     pair.add(nearbystore.get(i));
				     pair.add(nearbydistancestore.get(i));
				     pairedstore.add(pair);
				 }
			 }
			 for (List<Object> pair : pairedstore) {
				 NearByPlaces r = new NearByPlaces();
	
				 Integer count = 0;
				 for (Object element : pair) {
				        if(count==0) {
							   r.setName((String) element);
				        }else {
							   r.setDistance(Integer.parseInt((String) element));
				        }
				        count++;
				    }

				   r.setDormID(id);
				   r.setNearByPlacesType(1);
				   nearbyrepo.save(r);
				   
				}
			 
			 List<List<Object>> pairedroad = new ArrayList<List<Object>>();
			 for (int i = 0; i < nearbyroad.size() && i < nearbydistanceroad.size(); i++) {
				 if(!nearbyroad.get(i).isEmpty()) {
				     List<Object> pair = new ArrayList<Object>();
				     pair.add(nearbyroad.get(i));
				     pair.add(nearbydistanceroad.get(i));
				     pairedroad.add(pair);
				 }
			 }
			 for (List<Object> pair : pairedroad) {
				 NearByPlaces r = new NearByPlaces();
	
				 Integer count = 0;
				 for (Object element : pair) {
				        if(count==0) {
							   r.setName((String) element);
				        }else {
							   r.setDistance(Integer.parseInt((String) element));
				        }
				        count++;
				    }
				   r.setDormID(id);
				   r.setNearByPlacesType(2);
				   nearbyrepo.save(r);
				   
				}
			 
		return "TEST";
	}

	@DeleteMapping("/del/{id}")
	public void deleteDorm(@PathVariable("id") Integer id) {
		repo.deleteById(id);
		Imgrepo.deleteImages(id);
		contactrepo.deleteIContact(id);
		contractrepo.deleteContract(id);
		facrepo.deleteFac(id);
		nearbyrepo.deleteNear(id);
		rulerepo.deleteRule(id);
		System.out.println("Delete Success id " + id);
	}
	@PutMapping("/updated/{id}")
	public String updateDorm(@PathVariable("id")  Integer id, @RequestBody Object dorm, Model model) {
		  Map<String, Object> objectMap = (Map<String, Object>) dorm;
		  String dormname = (String) objectMap.get("dormname");
		  String address = (String) objectMap.get("address");
		 List< String> contactsTel =( List<String>) objectMap.get("contactsTel");
		 List< String> contactsEmail =( List<String>) objectMap.get("contactsEmail");
		 List< String> contactsFacebook =( List<String>) objectMap.get("contactsFacebook");
		 List< String> contactsLine=( List<String>) objectMap.get("contactsLine");
		 List< String> facprivate=( List<String>) objectMap.get("facprivate");
		 List< String> facpublic=( List<String>) objectMap.get("facpublic");
		 List< String> rule=( List<String>) objectMap.get("rule");
		  String zone = (String) objectMap.get("zone");
		  String dorm_detail = (String) objectMap.get("dorm_detail");
		  String googlemap = (String) objectMap.get("googlemap");
		  Integer monthly = (Integer) objectMap.get("monthly");
		  Integer daily = (Integer) objectMap.get("daily");
		  Integer waterFee = (Integer) objectMap.get("waterFee");
		  Integer electricFee = (Integer) objectMap.get("electricFee");
		  Integer centralFee = (Integer) objectMap.get("centralFee");
		  Integer insurance = (Integer) objectMap.get("insurance");
			 List< String> contract=( List<String>) objectMap.get("contract");
			 List< String> nearbyedu=( List<String>) objectMap.get("nearbyedu");
			 List< String> nearbydistanceredu=( List<String>) objectMap.get("nearbydistanceedu");
			 List< String> nearbyroad=( List<String>) objectMap.get("nearbyroad");
			 List< String> nearbydistanceroad=( List<String>) objectMap.get("nearbydistanceroad");
			 List< String> nearbystore=( List<String>) objectMap.get("nearbystore");
			 List< String> nearbydistancestore=( List<String>) objectMap.get("nearbydistancestore");
			 List< String> images=( List<String>) objectMap.get("images");
			
				Dormitory updateDorm = repo.findById(id).get();
				updateDorm.setAddress(address);
				updateDorm.setDormName(dormname);
				updateDorm.setCentralFeePrice(centralFee);
				updateDorm.setDaliyPrice(daily);
				updateDorm.setDetail(dorm_detail);
				updateDorm.setEletricFeePrice(electricFee);
				updateDorm.setInsurancePrice(insurance);
				updateDorm.setMap(googlemap);
				updateDorm.setMonthPrice(monthly);
				updateDorm.setWaterFeePrice(waterFee);
				updateDorm.setZoneID(Integer.parseInt(zone));
				repo.save(updateDorm);
				
				Imgrepo.deleteImages(id);
				contactrepo.deleteIContact(id);
				contractrepo.deleteContract(id);
				facrepo.deleteFac(id);
				nearbyrepo.deleteNear(id);
				rulerepo.deleteRule(id);
				for (String imageName : images) {
					if(!imageName.isBlank()) {
					    Images image = new Images();
					    image.setDormID(id);
					    image.setImageName(imageName);
					    Imgrepo.save(image);
					}

				}
				for (String name : rule) {
					if(!name.isBlank()) {
					    Rules r = new Rules();
					    r.setDormID(id);
					    r.setRuleName(name);
					    rulerepo.save(r);
					}

				}
				for (String name : contactsTel) {
					if(!name.isBlank()) {

					    Contacts r = new Contacts();
					    r.setDormID(id);
					    r.setContactType(4);
					    r.setContactValue(name);
					    contactrepo.save(r);
					}

				}
				for (String name : contactsEmail) {
					if(!name.isBlank()) {
						 Contacts r = new Contacts();
						    r.setDormID(id);
						    r.setContactType(1);
						    r.setContactValue(name);
						    contactrepo.save(r);
					}
				}
				for (String name : contactsFacebook) {
					if(!name.isBlank()) {
					    Contacts r = new Contacts();
					    r.setDormID(id);
					    r.setContactType(2);
					    r.setContactValue(name);
					    contactrepo.save(r);
					}

				}
				for (String name : contactsLine) {
					if(!name.isBlank()) {
					    Contacts r = new Contacts();
					    r.setDormID(id);
					    r.setContactType(2);
					    r.setContactValue(name);
					    contactrepo.save(r);
					}

				}
				for (String name : facpublic) {
					if(!name.isBlank()) {
					    Facilites r = new Facilites();
					    r.setDormID(id);
					    r.setFacTypeID(2);
					    r.setFacName(name);
					    facrepo.save(r);
					}

				}
				for (String name : facprivate) {
					if(!name.isBlank()) {
					    Facilites r = new Facilites();
					    r.setDormID(id);
					    r.setFacTypeID(1);
					    r.setFacName(name);
					    facrepo.save(r);
					}

				}
				for (String name : contract) {
					if(!name.isBlank()) {
					    ContractType r = new ContractType();
					    r.setDormID(id);
					    r.setName(name);
					    contractrepo.save(r);
					}

				}
					 List<List<Object>> pairedList = new ArrayList<List<Object>>();
					 for (int i = 0; i < nearbyedu.size() && i < nearbydistanceredu.size(); i++) {
						 if(!nearbyedu.get(i).isEmpty() && !nearbydistanceredu.get(i).isEmpty()) {
						     List<Object> pair = new ArrayList<Object>();
						     pair.add(nearbyedu.get(i));
						     pair.add(nearbydistanceredu.get(i));
						     pairedList.add(pair);
						 }
					 }
					 for (List<Object> pair : pairedList) {
						 NearByPlaces r = new NearByPlaces();
			
						 Integer count = 0;
						 for (Object element : pair) {
						        if(count==0) {
									   r.setName((String) element);
						        }else {
									   r.setDistance(Integer.parseInt((String) element));
						        }
						        count++;
						    }

						   r.setDormID(id);
						   r.setNearByPlacesType(3);
						   nearbyrepo.save(r);
						   
						}
					 List<List<Object>> pairedstore = new ArrayList<List<Object>>();
					 for (int i = 0; i < nearbystore.size() && i < nearbydistancestore.size(); i++) {
						 if(!nearbystore.get(i).isEmpty()  && !nearbydistancestore.get(i).isEmpty()) {
						     List<Object> pair = new ArrayList<Object>();
						     pair.add(nearbystore.get(i));
						     pair.add(nearbydistancestore.get(i));
						     pairedstore.add(pair);
						 }
					 }
					 for (List<Object> pair : pairedstore) {
						 NearByPlaces r = new NearByPlaces();
			
						 Integer count = 0;
						 for (Object element : pair) {
						        if(count==0) {
									   r.setName((String) element);
						        }else {
									   r.setDistance(Integer.parseInt((String) element));
						        }
						        count++;
						    }

						   r.setDormID(id);
						   r.setNearByPlacesType(1);
						   nearbyrepo.save(r);
						   
						}
					 
					 List<List<Object>> pairedroad = new ArrayList<List<Object>>();
					 for (int i = 0; i < nearbyroad.size() && i < nearbydistanceroad.size(); i++) {
						 if(!nearbyroad.get(i).isEmpty()) {
						     List<Object> pair = new ArrayList<Object>();
						     pair.add(nearbyroad.get(i));
						     pair.add(nearbydistanceroad.get(i));
						     pairedroad.add(pair);
						 }
					 }
					 for (List<Object> pair : pairedroad) {
						 NearByPlaces r = new NearByPlaces();
			
						 Integer count = 0;
						 for (Object element : pair) {
						        if(count==0) {
									   r.setName((String) element);
						        }else {
									   r.setDistance(Integer.parseInt((String) element));
						        }
						        count++;
						    }
						   r.setDormID(id);
						   r.setNearByPlacesType(2);
						   nearbyrepo.save(r);
						   
						}
					 
			 
			 
		return "T_T";
	}

}
