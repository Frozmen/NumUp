package com.example.user.coursework.Game.ModTreeLives;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.user.coursework.Game.GameView;

/**
 * Created by User on 26.03.2015.
 */
public class HealthBar {
    private Bitmap spriteHealth;
    private GameView gameView;
    private int amountOfHealth;
    private int Y;
    private int X;
    private int gapBetweenBlocks;

    public HealthBar(GameView gameView, Bitmap spriteBitmap){
        this.gameView = gameView;
        this.spriteHealth = spriteBitmap;
        amountOfHealth=3;


    }

    public void setSize(Bitmap top){
        spriteHealth = Bitmap.createScaledBitmap(spriteHealth, Math.round(top.getWidth()/13.81f),
                Math.round(top.getHeight()/3.96f), false);
        this.Y = top.getHeight()/2 - spriteHealth.getHeight()/2;
        this.X =  Math.round(19.68f * top.getWidth()/100);
        gapBetweenBlocks = Math.round(top.getWidth()/66.3f);
    }

    public void draw(Canvas canvas){
        int x = X;
        for(int i=0; i<amountOfHealth; i++){
            canvas.drawBitmap(spriteHealth, x, Y, null);
            x = x - spriteHealth.getWidth() - gapBetweenBlocks;
        }

    }

    public int getAmountOfHealth(){
        return amountOfHealth;
    }

    public void minusHealth(){
        amountOfHealth--;
    }



}
