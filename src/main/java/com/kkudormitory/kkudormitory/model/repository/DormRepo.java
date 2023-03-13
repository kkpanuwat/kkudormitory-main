package com.kkudormitory.kkudormitory.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kkudormitory.kkudormitory.model.bean.Dormitory;

public interface DormRepo extends JpaRepository<Dormitory, Integer>  {
    //	@Query(
    //			  value = "SELECT dormitory.dormID, dormitory.dorm_name, dormitory.address, zone.zonename "
    //			  		+ "FROM dormitory "
    //			  		+ "JOIN zone ON zone.zoneid = dormitory.zoneid "
    //			  		+ "ORDER BY dormitory.dormID ASC", 
    //			  nativeQuery = true)
    //	       Page<AdminMainObject> adminMain(Pageable pageable);
    //	
    
    //	    @Query(value = "SELECT dormitory.dormID, dormitory.dorm_name, dormitory.address, zone.zonename "
    //	                 + "FROM dormitory JOIN zone ON (zone.zoneid = dormitory.zoneid) "
    //	                 + "ORDER BY dormitory.dormID ASC",
    //	           nativeQuery = true)
        @Query(value = "SELECT d.dormID, d.dorm_name, d.address, z.zonename \n"
                + "FROM dormitory d \n"
                + "JOIN zone z ON z.zoneid = d.zoneid \n"
                + "ORDER BY d.dormID DESC",
                nativeQuery = true)
            List<Object> adminMain();
        
        
        @Query(value = "SELECT dormID,dorm_name, detail,month_price FROM dormitory  \n"
                + "WHERE zoneid=1",
                nativeQuery = true)
            List<Object> mainScreenFindMueang();
}
