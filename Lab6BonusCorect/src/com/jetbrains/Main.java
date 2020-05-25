package com.jetbrains;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

import java.awt.*;

//from  w w  w. j av a2 s. c o  m
public class Main {


    public static void main(String[] args) {
        int[][] matrix= {
                {0,1,1,1,0,1,1,0},
                {0,0,0,1,0,1,0,0},
                {0,0,1,1,1,1,1,1},
                {0,0,1,1,0,0,0,0},
                {0,0,0,0,0,1,0,1},
                {0,0,0,0,0,1,0,1},
                {0,0,0,0,0,1,0,1},
                {0,0,0,0,0,1,0,1},
        };
        int[][] matrix2= {
                {0,1,1,1},
                {0,0,1,1},
                {0,0,1,1},
                {0,0,0,1},

        };

        int[][] matrix3={
                {0,1,1},
                {0,0,1},
                {0,0,0}
        };
        new Thread(() -> Application.launch(Graph.class)).start();

        Graph graph = Graph.waitForGraph(matrix3);
    }
}