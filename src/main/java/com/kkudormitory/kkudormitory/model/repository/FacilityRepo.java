package com.kkudormitory.kkudormitory.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.Facilites;

import jakarta.transaction.Transactional;
@Transactional
public interface FacilityRepo extends CrudRepository<Facilites, Integer>{
    @Modifying
    @Query(value = "DELETE FROM facilites WHERE dormid=:id", nativeQuery = true)
    void deleteFac(@Param("id") Integer id);
}
