package com.group6.Server.Environment.Area;

import java.awt.*;
import java.awt.geom.Point2D;

public class Room extends Area<Rect> {

    private static final Color DEFAULT_COLOR = Color.GRAY;

    private Color color;

    private double x1DoorPos, x2DoorPos, y1DoorPos, y2DoorPos;
    private double x1DoorSize, x2DoorSize, y1DoorSize, y2DoorSize;

    private void initDefaultDoors() {
        x1DoorPos = 0;
        x2DoorPos = 0;
        y1DoorPos = 0;
        y2DoorPos = 0;

        x1DoorSize = 0;
        x2DoorSize = 0;
        y1DoorSize = 0;
        y2DoorSize = 0;
    }

    public Room(int rewardPoints, Point2D position, Color color) {
        super(true, rewardPoints, position);
        this.color = color;
        initDefaultDoors();
    }

    public Room(int rewardPoints, Point2D position) {
        this(rewardPoints, position, DEFAULT_COLOR);
    }

    public Room(int rewardPoints, Point2D position, Rect rect, Color color) {
        super(true, rewardPoints, position, rect);
        this.color = color;
        initDefaultDoors();
    }

    public Room(int rewardPoints, Point2D position, Rect rect) {
        this(rewardPoints, position, rect, DEFAULT_COLOR);
    }

    public Color getColor() {
        return color;
    }

    public double[] getDoorX1() {
        return new double[] {x1DoorPos, x1DoorSize};
    }

    public double[] getDoorX2() {
        return new double[] {x2DoorPos, x2DoorSize};
    }

    public double[] getDoorY1() {
        return new double[] {y1DoorPos, y1DoorSize};
    }

    public double[] getDoorY2() {
        return new double[] {y2DoorPos, y2DoorSize};
    }

    public void setX1Door(double size, double position) {
        x1DoorSize = size;
        x1DoorPos = position;
    }

    public void setX1Door(double size) {
        x1DoorSize = size;
        Point2D[] edges = this.getEdges();
        x1DoorPos = getDoorPos(size, edges[0].getY(), edges[1].getY());
    }

    public void setX2Door(double size, double position) {
        x2DoorSize = size;
        x2DoorPos = position;
    }

    public void setX2Door(double size) {
        x2DoorSize = size;
        Point2D[] edges = this.getEdges();
        x2DoorPos = getDoorPos(size, edges[0].getY(), edges[1].getY());
    }

    public void setY1Door(double size, double position) {
        y1DoorSize = size;
        y1DoorPos = position;
    }

    public void setY1Door(double size) {
        y1DoorSize = size;
        Point2D[] edges = this.getEdges();
        y1DoorPos = getDoorPos(size, edges[0].getX(), edges[1].getX());
    }

    public void setY2Door(double size, double position) {
        y2DoorSize = size;
        y2DoorPos = position;
    }

    public void setY2Door(double size) {
        y2DoorSize = size;
        Point2D[] edges = this.getEdges();
        y2DoorPos = getDoorPos(size, edges[0].getX(), edges[1].getX());
    }

    private double getDoorPos(double size, double v1, double v2) {
        double length = v2 - v1;
        return (length - size) / 2;
    }

}