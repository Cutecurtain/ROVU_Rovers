package com.group6.Server.Environment.Area;

import java.awt.*;

public interface IArea {

    boolean isPosIn(Point point);
    boolean isPhysical();
    int getRewardPoints();
}
