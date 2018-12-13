package com.group6.Server.Environment;

import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Room;
import simbad.sim.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Environment extends EnvironmentDescription implements IEnvironment{

    private static final Color DEFAULT_COLOR = Color.GRAY;

    private List<IArea> areas;

    private Color color;

    public Environment(List<IArea> areas, Color color) {
        super();
        this.areas = areas;
        this.color = color;
    }

    public Environment(List<IArea> areas) {
        this(areas, DEFAULT_COLOR);
    }

    public Environment(Color color) {
        this(new ArrayList<IArea>(), color);
    }

    public Environment() {
        this(new ArrayList<IArea>(), DEFAULT_COLOR);
    }

    public void addArea(IArea area) {
        this.areas.add(area);
    }

    public int logicalReward(Point2D point) {
        return collectReward(point, false);
    }

    public int physicalReward(Point2D point) {
        return collectReward(point, true);
    }

    public Boundary[] createBoundaries() {
        Boundary[] boundaries = new Boundary[4];
        float left = Float.MAX_VALUE;
        float right = Float.MIN_VALUE;
        float top = Float.MAX_VALUE;
        float bottom = Float.MIN_VALUE;
        for (IArea area : areas) {
            Point2D[] edges = area.getEdges();
            if (edges[0].getX() < left)
                left = (float) edges[0].getX();
            if (edges[1].getX() > right)
                right = (float) edges[1].getX();
            if (edges[0].getY() < top)
                top = (float) edges[0].getY();
            if (edges[1].getY() > bottom)
                bottom = (float) edges[1].getY();
        }

        boundaries[0] = new HorizontalBoundary(left, top, bottom, this, color);
        boundaries[1] = new HorizontalBoundary(right, top, bottom, this, color);
        boundaries[2] = new VerticalBoundary(bottom, left, right, this, color);
        boundaries[3] = new VerticalBoundary(top, left, right, this, color);

        return boundaries;
    }

    public List<AbstractWall> createWalls() {
        List<AbstractWall> walls = new ArrayList<AbstractWall>();

        for (IArea area : areas) {
            if (area instanceof Room) // Is this a good idea?
                walls.addAll(getWalls((Room) area));
        }

        return walls;
    }

    private List<AbstractWall> getWalls(Room room) {
        List<AbstractWall> abstractWalls = new ArrayList<AbstractWall>();
        Point2D[] edges = room.getEdges();
        abstractWalls.add(new HorizontalWall((float) edges[0].getX(), (float) edges[0].getY(), (float) edges[1].getY(), this, color));
        abstractWalls.add(new HorizontalWall((float) edges[1].getX(), (float) edges[0].getY(), (float) edges[1].getY(), this, color));
        abstractWalls.add(new VerticalWall((float) edges[1].getY(), (float) edges[0].getX(), (float) edges[1].getX(), this, color));
        abstractWalls.add(new VerticalWall((float) edges[0].getY(), (float) edges[0].getX(), (float) edges[1].getX(), this, color));
        return abstractWalls;
    }

    private int collectReward(Point2D point, boolean isPhysical) {
        int rewardPoints = 0;
        for (IArea area : areas) {
            if (area.isPhysical() == isPhysical && area.isPosIn(point))
                rewardPoints += area.getRewardPoints();
        }
        return rewardPoints;
    }

}
