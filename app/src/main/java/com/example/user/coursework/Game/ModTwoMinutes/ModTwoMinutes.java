package com.example.user.coursework.Game.ModTwoMinutes;

import android.content.Context;
import android.graphics.Canvas;

import com.example.user.coursework.Game.GameView;

/**
 * Created by User on 26.03.2015.
 */
public class ModTwoMinutes extends GameView {
    private TimerForModTwoMin timer;
    public ModTwoMinutes(Context context) {
        super(context);
        this.speed = 3;
        this.complexityOfExpression = 2;
        this.mod = "time";
    }


    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        timer.draw(canvas);
    }

    @Override
    protected void doBeforeCreatingScenes(){
        super.doBeforeCreatingScenes();
        timer = new TimerForModTwoMin(top.getHeight(), this);
        timer.setPointsPaint();
    }

    @Override
    public void userError(){
        timer.minusTime();
    }

    public void stopGame(){
        endOfGame();
    }
}
