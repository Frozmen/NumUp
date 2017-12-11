package com.example.user.coursework.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.user.coursework.Game.GameView;

import java.util.Random;

/**
 * Created by User on 09.03.2015.
 */
public class Task {
    protected int complexity;
    protected Paint textPaint;
    protected int x;
    protected int y;
    protected String task="";
    protected GameView view;
    protected boolean isMore;

    public Task(GameView view, int complexity){
        this.view = view;
        this.complexity = complexity;
        setTask();
        setNewTask();
    }

    private void setTask(){

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(view.getHeight()/7.28f/3);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));

    }

    public void setNewTask(){
        Random rand = new Random();
        int oper = rand.nextInt(3);
        switch (oper){
            case 0:{
                x = rand.nextInt(complexity*9-1)+2;
                y = rand.nextInt(complexity*9-1)+2+x;
                task = x + " ≤ " + "x"+" ≤ "+y;

            } break;
            case 1:{
                x = rand.nextInt(complexity*9-1)+2;
                y =0;
                task="x ≤ "+x;
                isMore = false;
            } break;
            case 2:{
                x = rand.nextInt(complexity*9-1)+2;
                y=0;
                task="x ≥ "+x;
                isMore = true;
            } break;

        }

    }

    public void drawTask(Canvas canvas){
        canvas.drawText(task, view.getWidth()/2, view.getHeight()/6f/2, textPaint);
    }

    public boolean isMore(){
        return isMore;
    }

    public int  getX1(){
        return x;
    }

    public int getX2(){
        return y;
    }

    public String getTask(){
        return task;
    }

}
