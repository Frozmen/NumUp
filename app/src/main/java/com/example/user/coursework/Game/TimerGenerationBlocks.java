package com.example.user.coursework.Game;

import android.graphics.Bitmap;

import com.example.user.coursework.Game.Block;
import com.example.user.coursework.Game.GameView;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 25.03.2015.
 */
public class TimerGenerationBlocks extends Timer {
    private List<Block> listOfBlocks;
    private Random rand = new Random();
    private int randAmountOfBlocksOnView;
    private Bitmap blockSprite;
    private GameView gameView;
    private int speed;
    private int complexityOfExpression;
    private boolean isWorking;
    private MyTimerTask myTimerTask;

    private class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            listOfBlocks.add(new Block(blockSprite, speed, complexityOfExpression, gameView));
            randAmountOfBlocksOnView--;
            if(randAmountOfBlocksOnView==0){
                isWorking = false;
                this.cancel();
            }
        }
    }
    public TimerGenerationBlocks(List<Block> listOfBlocks, Bitmap blockSprite, GameView gameView){
        this.listOfBlocks = listOfBlocks;
        this.blockSprite = blockSprite;
        this.gameView = gameView;
        isWorking = false;
    }


    /*TimerTask myTimerTask = new TimerTask(){
        @Override
        public void run() {
            listOfBlocks.add(new Block(blockSprite, speed, complexityOfExpression, gameView));
            randAmountOfBlocksOnView--;
            if(randAmountOfBlocksOnView==0){
                isWorking = false;
                this.cancel();
            }
        }
    };*/

    public void startGenerationBlocks(){
        myTimerTask = new MyTimerTask();
        randAmountOfBlocksOnView = rand.nextInt(8)+6;
        isWorking = true;
        long frequencyOfOccurrence=0;
        if(speed == 1){
            frequencyOfOccurrence=2500;
        }
        if(speed == 2){
            frequencyOfOccurrence=2000;
        }
        if(speed == 3){
            frequencyOfOccurrence=1500;
        }
        if(speed == 4){
            frequencyOfOccurrence=1250;
        }

        this.schedule(myTimerTask, 0, frequencyOfOccurrence);

    }

    public void setParameters(int speed, int complexityOfExpression){
        this.speed = speed;
        this.complexityOfExpression = complexityOfExpression;
    }

    public boolean isWorking(){
        return isWorking;
    }



}
