package com.rhy.neo4jdemo.demos.neo4j.mapper;

import com.rhy.neo4jdemo.demos.neo4j.entity.node.GridL4N;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.support.CypherdslConditionExecutor;
import org.springframework.data.neo4j.repository.support.CypherdslStatementExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GridL4Repository extends Neo4jRepository<GridL4N, String> {

    @Query("MATCH (n:GridL4 {id:'{0}'}) RETURN n")
    GridL4N getById(String id);
}
