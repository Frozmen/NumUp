  package com.example.user.coursework.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coursework.Game.RecordOfGame;
import com.example.user.coursework.R;


  public class AfterGameActivity extends Activity {

      RecordOfGame record;
      TextView score;
      String mod;
      Boolean isAgain=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game);
        score = (TextView) findViewById(R.id.score);
        record = new RecordOfGame(this);
        int point = getIntent().getExtras().getInt("point");
         mod = getIntent().getExtras().getString("mod");
        score.setText(this.getString(R.string.yours_score)+ " " + point);
        if(record.isRecord(mod, point)){
           Toast toast =  Toast.makeText(this, "Это рекорд", Toast.LENGTH_LONG);
           toast.show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_after_game, menu);
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

      public void onAgainClick(View view) {
          Intent intent = new Intent(AfterGameActivity.this, MainGameActivity.class);
          intent.putExtra("Mod", mod);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          isAgain=true;
          this.startActivity(intent);
          this.finish();


      }

      public void onHomeClick(View view) {
          this.finish();

      }
      @Override
      public void finish(){
          if(!isAgain){
              Intent intent = new Intent(AfterGameActivity.this, MainActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              this.startActivity(intent);
          }
          super.finish();
      }

  }
