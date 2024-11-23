package com.rhy.neo4jdemo.demos.mongo.controller;

import com.rhy.neo4jdemo.demos.mongo.service.IGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grid")
public class GridController {
    @Autowired
    private IGridService gridService;

    @PostMapping("convert/neo4j")
    public ResponseEntity convertToNeo4j() {
        gridService.convertToNeo4j();
        return ResponseEntity.ok("success");
    }
}
