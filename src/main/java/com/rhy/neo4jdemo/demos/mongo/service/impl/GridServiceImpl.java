package com.rhy.neo4jdemo.demos.mongo.service.impl;

import com.kitegogo.ay.common.data.utils.SpaceGridUtils;
import com.rhy.neo4jdemo.demos.mongo.entity.GridL4;
import com.rhy.neo4jdemo.demos.mongo.service.IGridService;
import com.rhy.neo4jdemo.demos.neo4j.entity.node.GridL4N;
import com.rhy.neo4jdemo.demos.neo4j.entity.relationship.Around;
import com.rhy.neo4jdemo.demos.neo4j.service.IGridL4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GridServiceImpl implements IGridService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IGridL4Service gridL4Service;

    @Override
    public void convertToNeo4j() {
        List<GridL4> gridL4s = mongoTemplate.find(new Query(), GridL4.class);
        List<GridL4N> gridL4Ns = new ArrayList<>(gridL4s.size());
        gridL4s.forEach(gridL4 -> {
            System.out.println(gridL4.getId());
            GridL4N gridL4NNode = new GridL4N();
            gridL4NNode.setId(gridL4.getCode());
            gridL4NNode.setCode(gridL4.getCode());
            String bbox = getBbox(gridL4.getGeoJsonPolygon());
            gridL4NNode.setBbox(bbox);
            List<Double> center = getCenter(gridL4.getGeoJsonPolygon());
            gridL4NNode.setLon(center.get(0));
            gridL4NNode.setLat(center.get(1));
            gridL4NNode.setHeight(0D);
            gridL4NNode.setLevel(1);
            gridL4Service.save(gridL4NNode);
            gridL4Ns.add(gridL4NNode);
        });
        //查询周边网格码并建立连接
        gridL4Ns.forEach(gridL4N -> {
            List<String> aroundCode = null;
            try {
                aroundCode = SpaceGridUtils.getAroundCode(gridL4N.getCode());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            List<GridL4N> aroundObjs = new ArrayList<>();
            aroundCode.forEach(code -> {
                GridL4N gridL4NTemp = gridL4Service.getById(code);
                if(gridL4NTemp != null){
                    aroundObjs.add(gridL4NTemp);
                }
            });
            if(CollectionUtils.isEmpty(aroundObjs)){
                return;
            }
            List<Around> aroundShips = new ArrayList<>(aroundObjs.size());
            aroundObjs.forEach(gridL4Temp -> {
                Around around = new Around();
                around.setGridL4N(gridL4Temp);
                around.setLevel(gridL4Temp.getLevel());
                aroundShips.add(around);
            });
            gridL4N.setArounds(aroundShips);
            gridL4Service.save(gridL4N);
            aroundShips.clear();
        });
    }

    public String getBbox(GeoJsonPolygon geoJsonPolygon){
        List<Point> points = geoJsonPolygon.getPoints();
        return points.get(0).getX()+","+points.get(0).getY()+","+points.get(2).getX()+","+points.get(2).getY();
    }
    public List<Double> getCenter(GeoJsonPolygon geoJsonPolygon){
        List<Point> points = geoJsonPolygon.getPoints();
        BigDecimal lon = BigDecimal.valueOf(points.get(2).getX()).subtract(BigDecimal.valueOf(points.get(0).getX())).divide(BigDecimal.valueOf(2), 7, BigDecimal.ROUND_FLOOR).add(BigDecimal.valueOf(points.get(0).getX()));
        BigDecimal lat = BigDecimal.valueOf(points.get(2).getY()).subtract(BigDecimal.valueOf(points.get(0).getY())).divide(BigDecimal.valueOf(2), 7, BigDecimal.ROUND_FLOOR).add(BigDecimal.valueOf(points.get(0).getY()));
        return Arrays.asList(lon.doubleValue(),lat.doubleValue());
    }
}
