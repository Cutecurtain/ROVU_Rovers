package com.group6.Server;

import com.group6.Server.Environment.Area.Area;
import com.group6.Server.Environment.Area.IArea;
import com.group6.Server.Environment.Area.Rect;
import com.group6.Server.Environment.Environment;
import com.group6.Server.Robot.IMission;
import com.group6.Server.Robot.Mission;

import java.awt.*;
import java.awt.geom.Point2D;
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
        ((Area) area1).addShape(new Rect(new Point(100, 100), new Point(200, 200)));
        ((Area) area1).addShape(new Rect(new Point(0, 100), new Point(100, 200)));

        assaignment3 = new Environment(areas);


        // Generate some test missions
        List<IMission> missions = generateMissions();
        
    }

    private List<IMission> generateMissions() {
        List<IMission> missions = new ArrayList<IMission>();

        // Robot 1:  Enter Room 1, then go to Room 2, then exit
        List<Point2D> mission1 = new ArrayList<Point2D>();
        mission1.add(new Point(50, 50));
        mission1.add(new Point(150, 50));
        mission1.add(new Point(250, 50));

        // Robot 2:  Enter Room 2, then go to Room 3, then exit
        List<Point2D> mission2 = new ArrayList<Point2D>();
        mission2.add(new Point(150, 50));
        mission2.add(new Point(150, 150));
        mission2.add(new Point(250, 150));


        // Robot 3:  Enter Room 3, then go to Room 4, then exit
        List<Point2D> mission3 = new ArrayList<Point2D>();
        mission3.add(new Point(150, 150));
        mission3.add(new Point(50, 150));
        mission3.add(new Point(-50, 150));


        // Robot 4:  Enter Room 4, then go to Room 1, then exit
        List<Point2D> mission4 = new ArrayList<Point2D>();
        mission4.add(new Point(50, 150));
        mission4.add(new Point(50, 50));
        mission4.add(new Point(-50, 50));

        // Instantiate the missions from the mission points
        IMission m1 = new Mission(mission1);
        IMission m2 = new Mission(mission2);
        IMission m3 = new Mission(mission3);
        IMission m4 = new Mission(mission4);

        missions.add(m1);
        missions.add(m2);
        missions.add(m3);
        missions.add(m4);

        return missions;
    }

    public void procedureA() {

    }

    public void procedureB() {

    }


}
