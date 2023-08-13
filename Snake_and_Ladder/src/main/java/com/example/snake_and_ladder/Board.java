package com.example.snake_and_ladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    public ArrayList<Pair<Integer,Integer>> posistion;
    public ArrayList<Integer>snakeladerpos;
    public Board(){
        posistion=new ArrayList<>();
        posistioncordinate();
        snakeladder();
    }
    private void posistioncordinate(){
        posistion.add(new Pair<>(0,0));
        for(int i=0;i<SnakeANDLadder.height;i++){
            for(int j = 0; j < SnakeANDLadder.width; j++) {
                //x cor
                int x=0;
                if(i%2==0){
                    x=j * SnakeANDLadder.boardsize + (SnakeANDLadder.boardsize/2);
                }
                else{
                    x= SnakeANDLadder.boardsize * SnakeANDLadder.height - (j*SnakeANDLadder.boardsize) - (SnakeANDLadder.boardsize/2);
                }
                //y cor
                int y = SnakeANDLadder.boardsize * SnakeANDLadder.height - (i*SnakeANDLadder.boardsize) - (SnakeANDLadder.boardsize/2);
                posistion.add(new Pair<>(x,y));
            }
        }
    }

    private void snakeladder(){
        snakeladerpos=new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeladerpos.add(i);
        }
        snakeladerpos.set(4,25);
        snakeladerpos.set(13,46);
        snakeladerpos.set(33,49);
        snakeladerpos.set(50,69);
        snakeladerpos.set(42,63);
        snakeladerpos.set(62,81);
        snakeladerpos.set(74,92);
        snakeladerpos.set(27,5);
        snakeladerpos.set(40,3);
        snakeladerpos.set(43,18);
        snakeladerpos.set(54,31);
        snakeladerpos.set(66,45);
        snakeladerpos.set(76,58);
        snakeladerpos.set(89,53);
        snakeladerpos.set(99,41);

    }
    public int newposistion(int currentpos){
        if(currentpos>0 && currentpos<=100){
            return snakeladerpos.get(currentpos);
        }
        return -1;
    }
    int getxcord(int posistion1 ){
        if(posistion1>=1 && posistion1<=100){
            return posistion.get(posistion1).getKey();
        }
        return -1;
    }

    int getycord(int posistion2 ) {
        if (posistion2 >=1 && posistion2 <=100) {
            return posistion.get(posistion2).getValue();
        }
        return -1;
    }


}
