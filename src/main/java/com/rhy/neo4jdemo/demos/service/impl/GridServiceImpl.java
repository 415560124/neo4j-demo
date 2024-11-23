package com.rhy.neo4jdemo.demos.service.impl;

import com.rhy.neo4jdemo.demos.entity.GridL4;
import com.rhy.neo4jdemo.demos.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridServiceImpl implements GridService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void convertToNeo4j() {
        List<GridL4> gridL4s = mongoTemplate.find(new Query(), GridL4.class);
        gridL4s.forEach(gridL4 -> {
            System.out.println(gridL4.getId());
        });
    }
}
