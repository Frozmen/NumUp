package com.example.user.coursework.Game.ModTwoMinutes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.user.coursework.Game.GameView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 26.03.2015.
 */
public class TimerForModTwoMin extends Timer {
    private boolean isWorking;
    private int min=2;
    private int sec=0;
    private ModTwoTimerTask timerTask;
    private Paint paint;
    private int heightOfToper;
    private ModTwoMinutes gameView;


    private class ModTwoTimerTask extends TimerTask{
        @Override
        public void run() {
            if(sec==0){
                if(min==0){
                    isWorking=false;
                    cancel();

                }
                min--;
                sec=60;
            }
            sec--;
        }
    }

    public TimerForModTwoMin(int hightOfToper, ModTwoMinutes gameView){
        this.heightOfToper = hightOfToper;
        this.gameView = gameView;
        startWorking();
    }
    private void startWorking(){
        isWorking=true;
        timerTask = new ModTwoTimerTask();
        schedule(timerTask, 0, 1000);
    }
    public void draw(Canvas canvas){
        if(!isWorking){
            gameView.stopGame();
            return;
        }
        String a = "";
        if(sec<=9){
            a = "" + min + " : 0"+sec;
        }else{
            a = "" + min + " : "+sec;
        }
        canvas.drawText(a, 20, heightOfToper /1.8f, paint);

    }
    public void setPointsPaint(){
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(heightOfToper /3);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
    }
    public void minusTime(){
        sec-=10;
        if(sec<0){
            if(min==0){
                isWorking=false;
                cancel();
            }
            min--;
            int a = sec;
            sec = 60+a;
        }

    }
}
