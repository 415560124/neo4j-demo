package com.rhy.neo4jdemo.demos.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document( collection = "grid_l4" )
public class GridL4 {
    @Id
    private String id;

    @Field
    private String code;

    @Field
    private String l1Code;

    @Field
    private String l2Code;

    @Field
    private String l3Code;

    @Field
    private GeoJsonPolygon geoJsonPolygon;
}
