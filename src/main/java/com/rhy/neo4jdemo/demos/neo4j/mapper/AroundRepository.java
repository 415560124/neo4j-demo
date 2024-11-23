package com.rhy.neo4jdemo.demos.neo4j.mapper;

import com.rhy.neo4jdemo.demos.neo4j.entity.relationship.Around;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AroundRepository extends Neo4jRepository<Around, Long> {
}
