package com.kkudormitory.kkudormitory.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.Images;

import jakarta.transaction.Transactional;
@Transactional
public interface ImageRepo extends CrudRepository<Images, Integer> {
    @Modifying
    @Query(value = "DELETE FROM images WHERE dormid=:id", nativeQuery = true)
    void deleteImages(@Param("id") Integer id);
}
