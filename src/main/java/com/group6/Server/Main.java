package com.group6.Server;

import com.group6.Server.Environment.Area.Area;
import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Rect;
import com.group6.Server.Environment.Environment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    Environment assaignment3;

    public static void main (String[] strings) {

    }

    public void main() {
        List<IArea> areas = new ArrayList<IArea>();
        IArea area1 = new Area(true, 1);

        // Creating four squares next to each other
        ((Area) area1).addShape(new Rect(new Point(0, 0), new Point(100, 100)));
        ((Area) area1).addShape(new Rect(new Point(100, 0), new Point(200, 100)));
        ((Area) area1).addShape(new Rect(new Point(0, 100), new Point(100, 200)));
        ((Area) area1).addShape(new Rect(new Point(100, 100), new Point(200, 200)));

        assaignment3 = new Environment(areas);
    }

    public void procedureA() {

    }

    public void procedureB() {

    }


}
