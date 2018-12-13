package com.group6.Server.Environment.Area;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Division extends Area<Composition<Rect>> {

    public Division(int rewardPoints, Point2D position, List<Room> rooms) {
        super(true, rewardPoints, position);
        initShapes(rooms);
    }

    private void initShapes(List<Room> rooms) {
        List<Rect> shapes = new ArrayList<Rect>();
        for (Room room : rooms)
            shapes.add(room.getShape());
        Composition<Rect> composition = new Composition<Rect>(shapes);
        super.addShape(composition);
    }

}
