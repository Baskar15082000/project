package com.example.snake_and_ladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int boardsize){
        setWidth(boardsize);
        setHeight(boardsize);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);

    }
}
