package com.example.user.coursework.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.coursework.Game.Block;
import com.example.user.coursework.Game.RecordOfGame;
import com.example.user.coursework.Game.SoundsForGame;
import com.example.user.coursework.R;


public class MainActivity extends Activity {
    private TextView textView;
    private RecordOfGame record;
    private ImageButton soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecordInTextView();
        SoundsForGame sounds = new SoundsForGame(this);
        soundButton = (ImageButton)findViewById(R.id.sound_button);
        if(sounds.isONSound){
            soundButton.setBackgroundResource(R.drawable.sound_on);
        }else{
            soundButton.setBackgroundResource(R.drawable.sound_off);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSurvClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainGameActivity.class);
        intent.putExtra("Mod", "survival");
        startActivity(intent);

    }

    public void onClassClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainGameActivity.class);
        intent.putExtra("Mod", "classic");
        startActivity(intent);

    }

    public void onRaceClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainGameActivity.class);
        intent.putExtra("Mod", "time");
        startActivity(intent);

    }

    public void setRecordInTextView(){
        textView = (TextView)findViewById(R.id.survRecord_textView);
        record = new RecordOfGame(this);
        textView.setText(record.getSurvivalRecord());
        textView = (TextView)findViewById(R.id.classRecord_textView);
        textView.setText(record.getClassicRecord());
        textView = (TextView)findViewById(R.id.raceRecord_textView);
        textView.setText(record.getTimeRaceRecord());
    }

    public void onSoundClick(View view) {
        if(SoundsForGame.isONSound){
            SoundsForGame.isONSound=false;
            soundButton.setBackgroundResource(R.drawable.sound_off);
        }else{
            SoundsForGame.isONSound=true;
            soundButton.setBackgroundResource(R.drawable.sound_on);
        }
    }

    public void onHelpClick(View view) {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
    }
}
