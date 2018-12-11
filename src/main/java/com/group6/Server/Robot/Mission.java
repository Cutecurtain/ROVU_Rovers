package com.group6.Server.Robot;

import com.sun.org.glassfish.gmbal.ParameterNames;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mission implements IMission{

    private List<Point2D> missionPoints;

    public Mission() {
        this.missionPoints = new ArrayList<Point2D>();
    }

    public Mission(List<Point2D> missionPoints) {
        this.missionPoints = missionPoints;
    }

    public List<Point2D> getMissionPoints() {
        return null;
    }

    
    public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist) {

        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<Integer>(numV);
        //Initialize V-S
        for(int i = 0; i < numV; i++) {
            if(i != start) {
                vMinusS.add(i);
            }
        }
        //Initialize pred and dist.
        for(int v : vMinusS) {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        //main loop
        while (vMinusS.size() != 0) {
            //Find the value u in V-S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for(int v : vMinusS) {
                if(dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            //remove u from vMinusS
            for (int v : vMinusS) {
                if(graph.isEdge(u, v)) {
                    double weight = graph.getEdge(u, v).getWeight();
                    if(dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}
