package com.rhy.neo4jdemo.demos.controller;

import com.rhy.neo4jdemo.demos.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grid")
public class GridController {
    @Autowired
    private GridService mongoService;

    @PostMapping("convert/neo4j")
    public ResponseEntity convertToNeo4j() {
        mongoService.convertToNeo4j();
        return ResponseEntity.ok("success");
    }
}
