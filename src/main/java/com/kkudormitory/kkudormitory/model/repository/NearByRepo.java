package com.kkudormitory.kkudormitory.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.NearByPlaces;

import jakarta.transaction.Transactional;
@Transactional
public interface NearByRepo extends CrudRepository<NearByPlaces, Integer>  {
    @Modifying
    @Query(value = "DELETE FROM near_by_places WHERE dormid=:id", nativeQuery = true)
    void deleteNear(@Param("id") Integer id);

}
