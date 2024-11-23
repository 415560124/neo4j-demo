package com.rhy.neo4jdemo.demos.neo4j.entity.relationship;

import com.rhy.neo4jdemo.demos.neo4j.entity.node.GridL4N;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.UUID;

@Data
@RelationshipProperties
public class Around {

    @RelationshipId
    private Long id;

    @Property("level")
    private int level;

    @TargetNode
    private GridL4N gridL4N;
}
