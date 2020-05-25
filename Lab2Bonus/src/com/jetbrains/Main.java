package com.jetbrains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Client c1 = new Client("Client 1",1);
        System.out.println(c1);
        Client c2 = new Client("Client 2", 2);
        System.out.println(c2);
        Client c3 =new Client("Client 3",3);

        int matrixVehicle1[][] = new int[][] {
                { 0, -1, 2, 5, 4},
                { -1, -1, -1, -1, -1},
                { 2, -1, 0, 5, 2},
                { 5, -1, 5, 0, -1},
                { 4, -1, 2, -1, 0}};

        int matrixVehicle2[][] = new int[][] {
                { 0, -1, 0, 0, 0},
                { -1, -1, -1, -1, -1},
                { 0, -1, 0, 7, 0},
                { 0, -1, 7, 0, 9},
                { 0, -1, 0, 9, 0}};

        int matrixVehicle3[][] = new int[][] {
                { -1, -1, -1, -1, -1},
                { -1, 0, 3, 5, 9},
                { -1, 3, 0, 7, 6},
                { -1, 5, 7, 0, 9},
                { -1, 9, 6, 9, 0}};

        Vehicle v1=new Car("Car1",10,matrixVehicle1);
        Vehicle v2=new Car("Car2",12,matrixVehicle1);
        Vehicle v3=new Car("Car3",20,matrixVehicle1);
        Vehicle v4=new Car("Car4",45,matrixVehicle1);
        Vehicle v5=new Car("Car5",100,matrixVehicle1);

        Depot d1,d2;

        d1=new Depot("D1");
        d2=new Depot("D2");

        d1.setVehicles(v1,v2,v3);
        d2.setVehicles(v4,v5);

        System.out.println(d1);



        int[][] graph2 = new int[][]{
                {0	,99	,50	,0,	0},
                {0	,0,	50,	50,	50},
                {0,	0	,0	,99,	0},
                {0	,0,	0,	0	,75},
                {0,	0	,0	,0,	0}

        };//garantat un DAG;

        Dijsktra dijsktra = new Dijsktra();

        int[][] graph = new int[][]{

                { -1, 4, -1, -1, -1, -1, -1, 8, -1 },
                { 4, -1, 8, -1, -1, -1, -1, 11, -1 },
                { -1, 8, -1, 7, -1, 4, -1, -1, 2 },
                { -1, -1, 7, -1, 9, 14, -1, -1, -1 },
                { -1, -1, -1, 9, -1, 10, -1, -1, -1 },
                { -1, -1, 4, 14, 10, -1, 2, -1, -1 },
                { -1, -1, -1, -1, -1, 2, -1, 1, 6 },
                { 8, 11, -1, -1, -1, -1, 1, -1, 7 },
                { -1, -1, 2, -1, -1, -1, 6, 7, -1 }
        };
        Problem pb  = new Problem(new Depot[]{d1,d2}, new Client[]{c1, c2, c3});
        pb.solveProblem(graph);

    }
}
