package com.kkudormitory.kkudormitory.controller;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;
import com.kkudormitory.kkudormitory.model.repository.SearchRepo;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/search")
public class SearchApi {
    
    @GetMapping("/{search}")
    public List<Map<String, Object>> getDormitory(@PathVariable("search") String search) throws SQLException, ClassNotFoundException {
        SearchRepo se = new SearchRepo();
        return se.searchDorm(search);
    }
}
