package com.example.user.coursework.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.user.coursework.Game.GameView;
import com.example.user.coursework.Game.ModTreeLives.ModThreeLives;
import com.example.user.coursework.Game.ModTwoMinutes.ModTwoMinutes;

/**
 * Created by User on 04.03.2015.
 */
public class MainGameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       String a = getIntent().getExtras().getString("Mod");
       if(a.equals("survival")){
           setContentView(new GameView(this));
       }
        if(a.equals("classic")){
           setContentView(new ModThreeLives(this));
        }
        if(a.equals("time")){
            setContentView(new ModTwoMinutes(this));
        }


    }
}
