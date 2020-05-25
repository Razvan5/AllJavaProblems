package com.jetbrains;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.TextArea;
import java.sql.*;
import java.time.temporal.ValueRange;

public class Main extends Application {

    Button startQuery;
    ChoiceBox chooseCriteria;
    RadioButton radioAsc,radioDesc,radioNone,radioEqual;
    Label selectString;
    TextField valueArea,orderArea;
    TableView table;
    static String selectComplet;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Baza de Date");

        Button startQuery = new Button();
        RadioButton radioAsc = new RadioButton(),radioDesc = new RadioButton(),radioNone = new RadioButton();
        ChoiceBox chooseCriteria = new ChoiceBox();
        TextField valueArea = new TextField(),orderArea = new TextField();
        final ToggleGroup groupCompare = new ToggleGroup();
        Label selectString = new Label();
        TableView table = new TableView();

        startQuery.setText("Start Query!");
        startQuery.setTranslateX(325);
        startQuery.setTranslateY(-300);

        radioNone.setText("None");
        radioNone.setTranslateX(112.5);
        radioNone.setTranslateY(-300);
        radioNone.setToggleGroup(groupCompare);

        radioDesc.setText("Descending");
        radioDesc.setTranslateX(0);
        radioDesc.setTranslateY(-300);
        radioDesc.setToggleGroup(groupCompare);

        radioAsc.setText("Ascending");
        radioAsc.setTranslateX(-112.5);
        radioAsc.setTranslateY(-300);
        radioAsc.setToggleGroup(groupCompare);

        chooseCriteria.getItems().addAll("NONE", "EQUAL TO","BIGGER THAN","SMALLER THAN");
        chooseCriteria.setTranslateX(-325);
        chooseCriteria.setTranslateY(-300);

        valueArea.setTranslateX(-210);
        valueArea.setTranslateY(-300);
        valueArea.setMaxWidth(100);

        orderArea.setTranslateX(212.5);
        orderArea.setTranslateY(-300);
        orderArea.setMaxWidth(100);

        selectString.setLabelFor(startQuery);
        selectString.setText("SELECT nume,nr_matricol,grupa,an,bursa FROM studenti where bursa");
        selectString.setTranslateX(-201);
        selectString.setTranslateY(-350);


        TableColumn nume = new TableColumn("Nume");
        nume.setCellValueFactory(new PropertyValueFactory<Person,String>("nume"));

        TableColumn nrMatricol = new TableColumn("Numar Matricol");
        nrMatricol.setCellValueFactory(new PropertyValueFactory<Person,String>("numarMatricol"));

        TableColumn grupa = new TableColumn("Grupa");
        grupa.setCellValueFactory(new PropertyValueFactory<Person,String>("grupa"));

        TableColumn an = new TableColumn("An");
        an.setCellValueFactory(new PropertyValueFactory<Person,String>("an"));

        TableColumn bursa = new TableColumn("Bursa");
        bursa.setCellValueFactory(new PropertyValueFactory<Person,String>("bursa"));


        table.getColumns().addAll(nume, nrMatricol,grupa,an,bursa);
        table.setTranslateX(0);
        table.setTranslateY(100);
        //table.getItems().add(new Person("Razvan","123","B6","1","1000"));

        startQuery.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                selectComplet="SELECT nume,nr_matricol,grupa,an,bursa FROM studenti";
                if(chooseCriteria.getValue()=="EQUAL TO"){
                    selectComplet+=" where bursa = "+valueArea.getText();
                }
                else if(chooseCriteria.getValue()=="SMALLER THAN"){
                    selectComplet+=" where bursa < "+valueArea.getText();
                }
                else if(chooseCriteria.getValue()=="BIGGER THAN"){
                    selectComplet+=" where bursa > "+valueArea.getText();
                }

                if(radioAsc.isSelected()){
                    selectComplet+=" ORDER BY "+orderArea.getText()+" ASC";
                }
                else if(radioDesc.isSelected()){
                    selectComplet+=" ORDER BY "+orderArea.getText()+" DESC";
                }
                System.out.println(selectComplet);
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection SqlConnection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
                    Statement statement=SqlConnection.createStatement();

                    ResultSet bazaDeDateStudenti=statement.executeQuery(selectComplet);
                    table.getItems().clear();
                    while(bazaDeDateStudenti.next()) {
                        int an = bazaDeDateStudenti.getInt(4);
                        int bursa = bazaDeDateStudenti.getInt(5);
                        table.getItems().add(new Person(bazaDeDateStudenti.getString(1),bazaDeDateStudenti.getString(2),bazaDeDateStudenti.getString(3),String.valueOf(an),String.valueOf(bursa)));
                    }
                    SqlConnection.close();

                }catch(Exception e){ System.out.println(e);}
            }
        });

        StackPane layout = new StackPane();

        layout.getChildren().add(startQuery);
        layout.getChildren().add(radioAsc);
        layout.getChildren().add(radioDesc);
        layout.getChildren().add(radioNone);
        layout.getChildren().add(chooseCriteria);
        layout.getChildren().add(valueArea);
        layout.getChildren().add(orderArea);
        layout.getChildren().add(selectString);
        layout.getChildren().add(table);

        Scene scene = new Scene(layout,800,750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}