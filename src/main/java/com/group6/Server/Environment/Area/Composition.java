package com.group6.Server.Environment.Area;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Composition implements IShape{

    private List<IShape> shapes;

    public Composition() {
        this.shapes = new ArrayList<IShape>();
    }

    public Composition(List<IShape> shapes) {
        this.shapes = shapes;
    }

    public Composition(IShape shape) {
        this();
        this.shapes.add(shape);
    }

    public boolean isPosIn(Point point) {
        for (IShape shape : shapes) {
            if (shape.isPosIn(point))
                return true;
        }
        return false;
    }
}
