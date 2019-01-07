package com.group6.Server.Environment;

import java.awt.geom.Point2D;

public interface IActor {
    Point2D getPosition();
    void sleep();
    void halt();
    void start();
}
