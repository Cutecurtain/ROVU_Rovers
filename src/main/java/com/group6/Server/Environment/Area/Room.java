package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;

public class Room extends Area implements INestable{

    public Room(int rewardPoints, Point2D position, IShape shape){
        super(true, rewardPoints, position, shape);
    }
}