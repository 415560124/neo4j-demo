package com.rhy.neo4jdemo.demos.neo4j.service.impl;

import com.rhy.neo4jdemo.demos.neo4j.entity.node.GridL4N;
import com.rhy.neo4jdemo.demos.neo4j.mapper.GridL4Repository;
import com.rhy.neo4jdemo.demos.neo4j.service.IGridL4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GridL4ServiceImpl implements IGridL4Service {

    @Autowired
    private GridL4Repository gridL4Repository;
    @Override
    public boolean save(GridL4N gridL4N){
        GridL4N res = gridL4Repository.save(gridL4N);
        return res != null;
    }

    @Override
    public GridL4N getById(String id) {
        return gridL4Repository.getById(id);
    }
}
