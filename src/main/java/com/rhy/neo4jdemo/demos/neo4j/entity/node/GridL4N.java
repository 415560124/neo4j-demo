package com.rhy.neo4jdemo.demos.neo4j.entity.node;

import com.rhy.neo4jdemo.demos.neo4j.entity.relationship.Around;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node("GridL4")
public class GridL4N {

    @Id
    private String id;

    @Property("code")
    private String code;

    @Property("lon")
    private Double lon;

    @Property("lat")
    private Double lat;

    @Property("height")
    private Double height;

    @Property("bbox")
    private String bbox;

    @Property("level")
    private int level;

    @Relationship(type = "Around")
    private List<Around> arounds;
}
