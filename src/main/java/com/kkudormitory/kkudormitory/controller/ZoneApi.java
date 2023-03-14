package com.kkudormitory.kkudormitory.controller;

import java.sql.*;
import java.util.*;
import java.lang.Object;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkudormitory.kkudormitory.model.repository.*;
import com.kkudormitory.kkudormitory.model.bean.*;


@RestController
@RequestMapping("api/zone")
public class ZoneApi {

    @Autowired
	private ZoneRepo repo;

    @GetMapping("/{id}")
    public List<Map<String, Object>> getDormitory(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        DormDAO dorm = new DormDAO();
        return dorm.getDormitory(id);

    }

    @GetMapping("/all")
    public List<Map<String, Object>> allDormitory() throws SQLException, ClassNotFoundException{
        DormDAO dorm = new DormDAO();
        return dorm.allDormitory();
    }





}
