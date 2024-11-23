package com.rhy.neo4jdemo.demos.neo4j.service;

import com.rhy.neo4jdemo.demos.neo4j.entity.node.GridL4N;

public interface IGridL4Service {
    boolean save(GridL4N gridL4N);

    GridL4N getById(String id);
}
