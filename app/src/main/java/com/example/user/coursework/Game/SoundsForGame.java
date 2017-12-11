package com.example.user.coursework.Game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.user.coursework.R;

/**
 * Created by User on 27.04.2015.
 */
public class SoundsForGame {
    private SoundPool mSoundPool;
    private int soundIdChangeTask;
    private int soundIdCorrectTouch;
    private int soundIdWrongTouch;
    public static boolean isONSound=true;

    public SoundsForGame(Context context){
        mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundIdChangeTask =  mSoundPool.load(context, R.raw.change_task, 1);
        soundIdCorrectTouch = mSoundPool.load(context, R.raw.correct_touch, 1);
        soundIdWrongTouch = mSoundPool.load(context, R.raw.wrong_touch, 1);

    }

    public void onCorrectTouch(){
        if(isONSound) {
            mSoundPool.play(soundIdCorrectTouch, 1, 1, 0, 0, 1);
        }else{
            return;
        }
    }
    public void onWrongTouch(){

        if(isONSound) {
            mSoundPool.play(soundIdWrongTouch, 1, 1, 0, 0, 1);
        }else{
            return;
        }
    }
    public void onChangeTask(){

        if(isONSound) {
            mSoundPool.play(soundIdChangeTask, 1, 1, 0, 0, 1);
        }else{
            return;
        }
    }

}
