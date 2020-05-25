package com.jetbrains;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static javafx.scene.paint.Color.rgb;

public class Graph extends Application {

    static int[][] adicencyMatrix;
    Group root = new Group();

    double orgSceneX, orgSceneY;



    private Circle createCircle(double x, double y, double r, Color color) {
        Circle circle = new Circle(x, y, r, color);

        circle.setCursor(Cursor.HAND);

        circle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Circle c = (Circle) (t.getSource());
            c.toFront();

        });

        circle.setOnMouseDragged((t) -> {

            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Circle c = (Circle) (t.getSource());

            c.setCenterX(c.getCenterX() + offsetX);
            c.setCenterY(c.getCenterY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            for(Circle circle1 :circleList)
                keepDistance(circle1);
        });
        return circle;
    }

    private Line connect(Circle c1, Circle c2) {
        Line line = new Line();

        line.startXProperty().bind(c1.centerXProperty());
        line.startYProperty().bind(c1.centerYProperty());

        line.endXProperty().bind(c2.centerXProperty());
        line.endYProperty().bind(c2.centerYProperty());

        line.setStrokeWidth(1);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.getStrokeDashArray().setAll(1.0, 4.0);

        return line;
    }

    public static final CountDownLatch latch = new CountDownLatch(1);
    public static Graph graph = null;

    public static Graph waitForGraph(int[][] matrix) {

        adicencyMatrix=matrix;
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public Graph(int[][] adicencyMatrix,Graph matrix) {
        Graph.adicencyMatrix = adicencyMatrix;
        graph = matrix;
        latch.countDown();
    }

    public Graph(){}
    Circle[] hitBoxList;
    Circle[] circleList;
    Line[] lineList;
    public void createComponents(){
       int nrOfVertices=adicencyMatrix.length;
       circleList = new Circle[adicencyMatrix.length];
//       hitBoxList = new Circle[adicencyMatrix.length];
       lineList = new Line[((adicencyMatrix.length-1)*adicencyMatrix.length)/2];

       for(int i =0;i<nrOfVertices;++i){
           Random r = new Random();
           Circle circle = createCircle(200, 200, 25, rgb(r.nextInt(254),r.nextInt(254),r.nextInt(254)));
           circleList[i]=circle;
           //hitBoxList[i]=addHitbox(circle);
           //circleList[i].centerXProperty().bind(hitBoxList[i].centerXProperty());
           //circleList[i].centerYProperty().bind(hitBoxList[i].centerYProperty());
           Line line = new Line();
           lineList[i]= line;
           root.getChildren().add(circleList[i]);
           //root.getChildren().add(hitBoxList[i]);
           circleList[i].toFront();
       }
       for(int i =0;i<nrOfVertices;++i){
           for(int j=i;j<nrOfVertices;++j){
                if(adicencyMatrix[i][j]!=0){
                    lineList[i] = connect(circleList[i],circleList[j]);
                    root.getChildren().add(lineList[i]);

                }
           }
       }
//        for(Circle circle : circleList){
//            setDragListeners(circle);
//        }
    }
    public void setDragListeners(final Shape block) {
        final CircleCollisionTester.Delta dragDelta = new CircleCollisionTester.Delta();

        block.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                block.setCursor(Cursor.HAND);
            }
        });
        block.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                block.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                block.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
                for (Circle block : circleList) {
                    keepDistance(block);
                }

                double offsetX = mouseEvent.getSceneX() - orgSceneX;
                double offsetY = mouseEvent.getSceneY() - orgSceneY;

                Circle c = (Circle) (mouseEvent.getSource());

                c.setCenterX(c.getCenterX() + offsetX);
                c.setCenterY(c.getCenterY() + offsetY);

                orgSceneX = mouseEvent.getSceneX();
                orgSceneY = mouseEvent.getSceneY();
            }
        });
        block.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {

            }
        });
    }

    private boolean checkShapeIntersection(Circle block) {
        for (Circle static_bloc : circleList) {
            if (static_bloc != block) {

                Shape intersect = Shape.intersect(block, static_bloc);
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    return true;
                }
            }
        }

       return false;
    }
    int multiplier=10;
    private void keepDistance(Circle block) {
        Random random = new Random();
        for (Circle static_bloc : circleList) {
            if (static_bloc != block) {
                double distance=getDistance(block.getCenterX(),block.getCenterY(),static_bloc.getCenterX(),static_bloc.getCenterY());
                if(checkShapeIntersection(static_bloc)){
                   static_bloc.setCenterX(random.nextInt(500-100)+100);
                   static_bloc.setCenterY(random.nextInt(500-100)+100);
                }
                while(distance>500){
                    distance=getDistance(block.getCenterX(),block.getCenterY(),static_bloc.getCenterX(),static_bloc.getCenterY());
                    if(distance>getDistance(block.getCenterX(),block.getCenterY(),static_bloc.getCenterX()+multiplier,static_bloc.getCenterY()+multiplier)){
                        static_bloc.setCenterX(static_bloc.getCenterX()+multiplier);
                        static_bloc.setCenterY(static_bloc.getCenterY()+multiplier);

                    }
                    else  if(distance>getDistance(block.getCenterX(),block.getCenterY(),static_bloc.getCenterX()+multiplier,static_bloc.getCenterY()-multiplier)){
                        static_bloc.setCenterX(static_bloc.getCenterX()+multiplier);
                        static_bloc.setCenterY(static_bloc.getCenterY()-multiplier);

                    }
                    else  if(distance>getDistance(block.getCenterX(),block.getCenterY(),static_bloc.getCenterX()-multiplier,static_bloc.getCenterY()+multiplier)){
                        static_bloc.setCenterX(static_bloc.getCenterX()-multiplier);
                        static_bloc.setCenterY(static_bloc.getCenterY()+multiplier);

                    }else  if(distance>getDistance(block.getCenterX(),block.getCenterY(),static_bloc.getCenterX()-multiplier,static_bloc.getCenterY()-multiplier)){
                        static_bloc.setCenterX(static_bloc.getCenterX()-multiplier);
                        static_bloc.setCenterY(static_bloc.getCenterY()-multiplier);

                    }


                }

            }
        }
        System.out.println("______________________________");
    }
    public static double getDistance(double x1, double y1, double x2, double y2)
    {
        double dx = x2 - x1;
        double dy = y2 - y1;

        System.out.println(x1);
        System.out.println(x2);

        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void start(Stage primaryStage) {
        createComponents();
        Scene scene = new Scene(root, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
