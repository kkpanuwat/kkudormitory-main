package com.kkudormitory.kkudormitory.model.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.ContractType;

import jakarta.transaction.Transactional;
@Transactional
public interface ContractRepo extends CrudRepository<ContractType, Integer> {
    @Modifying
    @Query(value = "DELETE FROM contract_type WHERE dormid=:id", nativeQuery = true)
    void deleteContract(@Param("id") Integer id);
}
