package com.kkudormitory.kkudormitory.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.Rules;

import jakarta.transaction.Transactional;
@Transactional
public interface RulesRepo extends CrudRepository<Rules, Integer>  {
    @Modifying
    @Query(value = "DELETE FROM rules WHERE dormid=:id", nativeQuery = true)
    void deleteRule(@Param("id") Integer id);
}
