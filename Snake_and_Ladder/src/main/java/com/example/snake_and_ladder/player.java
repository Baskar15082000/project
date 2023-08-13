package com.example.snake_and_ladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class player {
    private Circle coin;
    private int currposistion ;
    private String name;
    static Board gameBoard=new Board();
    public player(int boardsize, Color coincolor,String playername){
        coin = new Circle(boardsize/2);
        coin.setFill(coincolor);
        currposistion = 0;
        moveplayer(1);
        name = playername;
    }
    public void moveplayer(int dicevalue){
        if((currposistion + dicevalue) <=100){

            currposistion += dicevalue;
            TranslateTransition secondmove=null, firstmove = moveanimate(dicevalue);

            int newpos= gameBoard.newposistion(currposistion);
             if(newpos!=currposistion && newpos!=-1){
                currposistion = newpos;
                secondmove=moveanimate(6);
            }
             if(secondmove==null){
                 firstmove.play();
             }
             else{
                 SequentialTransition seqtrans=new SequentialTransition(firstmove,
                         new PauseTransition(Duration.millis(200*dicevalue)),secondmove );
                 seqtrans.play();
             }
        }
//        int x=gameBoard.getxcord(currposistion);
//        int y= gameBoard.getycord(currposistion);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }
    private TranslateTransition  moveanimate(int dicevalue){
        TranslateTransition animate=new TranslateTransition(Duration.millis(200*dicevalue),coin);
        animate.setToX(gameBoard.getxcord(currposistion));
        animate.setToY(gameBoard.getycord(currposistion));
        animate.setAutoReverse(false);
        return animate;
    }
    public void reset(){
        currposistion=0;
        moveplayer(1);
    }
    boolean iswinner(){
        if(currposistion==100){
            return true;
        }
        else{
            return false;
        }
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrposistion() {
        return currposistion;
    }

    public String getName() {
        return name;
    }
}
