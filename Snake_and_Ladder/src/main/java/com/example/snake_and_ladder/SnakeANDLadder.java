package com.example.snake_and_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

public class SnakeANDLadder extends Application {
    public static final int boardsize=40 ,width =10,height=10;

    public static final int buttonLine=height*boardsize + 50,infoline=buttonLine-30;
    private static Dices dice =new Dices();
    private player playerOne,playerTwo;
    private boolean gamestart=false , playeroneturn=false , playertwoturn=false;
    private Pane create(){
        Pane root=new Pane();
        root.setPrefSize(width*boardsize,height*boardsize+100);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(boardsize);
                tile.setTranslateX(j*boardsize);
                tile.setTranslateY(i*boardsize);
                root.getChildren().addAll(tile);
            }
        }
        Image img =new Image("C:\\Users\\parth\\Desktop\\Snake_and_Ladder\\src\\main\\download.jpg");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(height*boardsize);
        board.setFitWidth(width*boardsize);

        //button
        //p1 //p2
        Button player1=new Button("Player One");
        Button player2=new Button("Player Two");
        Button start=new Button("START");
        player1.setTranslateY(buttonLine);
        player1.setTranslateX(20);
        player1.setDisable(true);
        player2.setTranslateY(buttonLine);
        player2.setTranslateX(300);
        player2.setDisable(true);
        start.setTranslateY(buttonLine);
        start.setTranslateX(160);
        //info
        Label player1info=new Label("");
        Label player2info=new Label("");
        Label Dice=new Label("Start the Game");
        Dice.setStyle("-fx-font-weight:bold;");
        player1info.setTranslateY(infoline);
        player1info.setTranslateX(30);
        player2info.setTranslateY(infoline);
        player2info.setTranslateX(310);
        Dice.setTranslateY(infoline);
        Dice.setTranslateX(150);
        playerOne = new player(boardsize, Color.BLACK,"Player one");
        playerTwo = new player(boardsize-10, Color.WHITE,"Player Two");
        //payeraction
        player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart){
                    if(playeroneturn){
                        int dicevalue= dice.getrooldie();
                        Dice.setText("Dice Point :"+ dicevalue);
                        playerOne.moveplayer(dicevalue);
                        //winner condition
                        if(playerOne.iswinner()){
                            Dice.setText("Winner is "+playerOne.getName());
                            playeroneturn = false;

                            player1.setDisable(true);


                            //p2
                            playertwoturn = true;
                            player2.setDisable(true);
                            player2info.setText("");
                            player1info.setText("");
                            start.setDisable(false);
                            start.setText("Restart");
                            gamestart=false;
                        }
                        else {
                            //p1
                            playeroneturn = false;

                            player1.setDisable(true);
                            player1info.setText("");

                            //p2
                            playertwoturn = true;
                            player2.setDisable(false);
                            player2info.setText(playerTwo.getName()+"Turn");
                        }
                    }

                }

            }
        });
        player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart){
                    if(playertwoturn){
                        int dicevalue= dice.getrooldie();
                        Dice.setText("Dice Point :"+ dicevalue);
                        playerTwo.moveplayer(dicevalue);
                        //winner condition
                        if(playerTwo.iswinner()){
                            Dice.setText("Winner is "+playerTwo.getName());
                            playeroneturn = false;

                            player1.setDisable(true);


                            //p2
                            playertwoturn = true;
                            player2.setDisable(true);
                            player2info.setText("");
                            player1info.setText("");
                            start.setDisable(false);
                            start.setText("Restart");
                            gamestart=false;
                        }
                        else {
                            //p1
                            playeroneturn = true;

                            player1.setDisable(false);
                            player1info.setText(playerOne.getName()+"Turn");

                            //p2
                            playertwoturn = false;
                            player2.setDisable(true);
                            player2info.setText("");
                        }
                    }
                }

            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestart=true;
                Dice.setText("Game started");
                start.setDisable(true);
                playeroneturn=true;
                player1info.setText(""+ playerOne.getName());
                player1.setDisable(false);
                playerOne.reset();
                playertwoturn=false;
                player2info.setText("");
                player2.setDisable(true);
                playerTwo.reset();


            }
        });

        root.getChildren().addAll(board,player1,start,player2,player1info,player2info,Dice,playerOne.getCoin(),playerTwo.getCoin());
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(create());
        stage.setTitle("Snake and Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}