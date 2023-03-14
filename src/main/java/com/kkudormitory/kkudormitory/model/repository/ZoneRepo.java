package com.kkudormitory.kkudormitory.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.kkudormitory.kkudormitory.model.bean.*;

public interface ZoneRepo extends JpaRepository<Zone, Integer> {
    
    // @Query(value = "SELECT d.dormID, d.dorm_name, d.address, z.zonename FROM dormitory d JOIN zone z ON z.zoneid = d.zoneid ORDER BY d.dormID DESC",
    //             nativeQuery = true)
    //         List<Object> adminMain();

    @Query(value = "SELECT COUNT(*) FROM zone", nativeQuery = true)
    Integer getCount();
}
