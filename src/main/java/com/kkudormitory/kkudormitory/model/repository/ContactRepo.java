package com.kkudormitory.kkudormitory.model.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.Contacts;
import jakarta.transaction.Transactional;
@Transactional
public interface ContactRepo extends CrudRepository<Contacts, Integer>  {
    @Modifying
    @Query(value = "DELETE FROM contacts WHERE dormid=:id", nativeQuery = true)
    void deleteIContact(@Param("id") Integer id);
}
