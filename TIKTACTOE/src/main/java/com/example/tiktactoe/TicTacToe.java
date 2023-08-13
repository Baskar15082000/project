package com.example.tiktactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Button button[][]=new Button[3][3];
    private Label playerXscore ,playerOscore;
    private boolean check=true;
    private int Xscore=0,Oscore=0;
    private BorderPane create(){
        BorderPane root=new BorderPane();
        //Title
        Label title=new Label("Tic Tac Toe");
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(10));
        title.setStyle("-fx-font-size:29pt;-fx-text-fill: red;-fx-background-color: lightgreen;-fx-font-weight:bold;-fx-font-family: helvetica, arial, sans-serif;");
        root.setTop(title);
        BorderPane.setAlignment(title,Pos.CENTER);
        //Game board
        GridPane grid=new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button buttons=new Button();
                buttons.setStyle("-fx-font-size:60pt;-fx-font-weight:bold;");
                buttons.setPrefSize(180,180);
                buttons.setOnAction(event ->buttonaction(buttons) );
                button[i][j]=buttons;
                grid.add(buttons,i,j);
            }

        }
        root.setCenter(grid);
        grid.setAlignment(Pos.CENTER);
        //left





        //Score
        HBox scoreboard =new HBox(20);
        scoreboard.setAlignment(Pos.CENTER);
        playerXscore=new Label("Player X score: 0 ");
        playerXscore.setStyle("-fx-font-size:24pt;-fx-font-size:24pt;");

        playerOscore=new Label("Player O score: 0 ");
        playerOscore.setStyle("-fx-font-size:20pt;-fx-font-size:20pt;");
        scoreboard.getChildren().addAll(playerXscore , playerOscore);
        root.setBottom(scoreboard);


        return root;
    }
    private void buttonaction(Button buttons){
        if(buttons.getText().equals("")){
            if(check){
                buttons.setText("X");
            }
            else{
                buttons.setText("O");
            }
            checkwinner();
            check=!check;
        }
        return;
    }
    private void checkwinner(){
        //row and column
        boolean f1=false;
        if(button[0][0].getText().equals(button[1][1].getText()) && button[1][1].getText().equals(button[2][2].getText() ) && !button[0][0].getText().equals("")){
            String winner=button[0][0].getText();
            f1=true;
            informtoplayers(winner);
            updatescore(winner);
        }
        else if(button[0][2].getText().equals(button[1][1].getText()) && button[1][1].getText().equals(button[2][0].getText() ) && !button[0][2].getText().equals("")){
            String winner=button[0][2].getText();
            f1=true;
            informtoplayers(winner);
            updatescore(winner);
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (button[i][0].getText().equals(button[i][1].getText()) && button[i][1].getText().equals(button[i][2].getText()) && !button[i][0].getText().equals("")) {
                    String winner = button[i][0].getText();
                    f1=true;
                    informtoplayers(winner);
                    updatescore(winner);
                    break;
                }
                if (button[0][i].getText().equals(button[1][i].getText()) && button[1][i].getText().equals(button[2][i].getText()) && !button[0][i].getText().equals("")) {
                    String winner = button[0][i].getText();
                    f1=true;
                    informtoplayers(winner);
                    updatescore(winner);
                    break;
                }

            }
        }
        int tie=0;
        if(f1==false) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!button[i][j].getText().equals("")) {
                        tie++;
                    }
                }
            }
            if(tie==9){
                tiedialog();
                updatescore("");

            }
        }

    }
    private void informtoplayers(String winner){
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congratulations "+winner+"! You won the Game");
        alert.setHeaderText("Winner");
        alert.showAndWait();

    }
    private void tiedialog(){
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Game Over ! it's a Draw.");
        alert.setHeaderText("Winner X / O");
        alert.showAndWait();

    }
    private void updatescore(String winner){
        if(winner.equals("X")){
            Xscore++;
            playerXscore.setText("Player X score: "+Xscore);
            reset();
            check=false;
        }
        else if(winner.equals("O")){
            Oscore++;
            playerOscore.setText("Player O score: "+Oscore);
            check=false;
            reset();
        }
        else{
            check=false;
            reset();
        }
    }
    private void reset(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(create());
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}