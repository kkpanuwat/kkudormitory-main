package com.kkudormitory.kkudormitory.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;
import com.kkudormitory.kkudormitory.model.repository.*;

@RestController
@RequestMapping("api/search")
public class SearchApi {
    
    @Autowired
	private SearchRepository repo;

    // @GetMapping("/{search}")
    // public List<Object> testT(@RequestParam("search") String search){
        
    //     return repo.searchDorm(search);
    // }
}
