package com.example.user.coursework.Game.ModTreeLives;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.user.coursework.Game.GameView;
import com.example.user.coursework.R;

/**
 * Created by User on 26.03.2015.
 */
public class ModThreeLives extends GameView {
    protected HealthBar healthBar;
    public ModThreeLives(Context context) {
        super(context);
        healthBar  = new HealthBar(this, BitmapFactory.decodeResource(getResources(), R.drawable.helth));
        this.mod = "classic";
    }

    @Override
    protected void doBeforeCreatingScenes(){
        super.doBeforeCreatingScenes();
        healthBar.setSize(this.top);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        healthBar.draw(canvas);
    }

    @Override
    protected void userError(){
        healthBar.minusHealth();
        if(healthBar.getAmountOfHealth()==0){
            endOfGame();
        }
    }

}
