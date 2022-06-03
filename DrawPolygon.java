package com.example.drawpolygon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class DrawPolygon extends Application {

    private Canvas canvas;
    private GraphicsContext g;
    private boolean cleared;
    private int count;

    public void start(Stage stage) {
        draw();


        canvas.setOnMousePressed(this::mousePressed);


        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Draw Polygon");
        stage.setResizable(false);
        stage.show();

    }
    private double[] xcoords = new double[50];
    private double[] ycoords = new double[50];


    public void mousePressed(MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        if(cleared) {


              xcoords[count] = x;
              ycoords[count] = y;

              g.setFill(Color.RED);
              g.setStroke(Color.BLACK);
              g.setLineWidth(3);
              g.strokeOval(x-1,y-1,2,2);

              count++;
              if(count > 2 && Math.abs(x - xcoords[0]) <= 7 && Math.abs(y - ycoords[0]) <= 7) { //did a very little change in th e 'if' statement
                                                                                                //just the if statement I had problems with... replaced 'xcoords[count]' with just 'x'
                      g.strokePolygon(xcoords, ycoords, count-1);
                      g.fillPolygon(xcoords, ycoords, count-1);

                  count = 0;

              }
       }

    }



    public void draw() {
        canvas = new Canvas(500,400);
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,500,400);
        cleared = true;
    }

    public static void main(String[] args) {
        launch();
    }

}