package com.example.user.coursework.Game;

import android.content.Context;
import android.widget.Toast;

import com.example.user.coursework.Game.ModTreeLives.ModThreeLives;
import com.example.user.coursework.Game.ModTwoMinutes.ModTwoMinutes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by User on 29.03.2015.
 */
public class RecordOfGame implements Serializable {
    private static final long serialVersionUID = 2697078955548283583L;
    private int recordTreeLifeMod;
    private int recordSurvivalMod;
    private int recordTimeMod;
    private static String FILE_RECORDS = "records";
    private Context mContext;

    public RecordOfGame(Context mContext){
        this.mContext = mContext;

        try {
            FileInputStream fis = mContext.openFileInput(FILE_RECORDS);
            ObjectInputStream is = new ObjectInputStream(fis);
            recordTreeLifeMod = (int) is.readObject();
            recordSurvivalMod = (int) is.readObject();
            recordTimeMod = (int) is.readObject();
            is.close();
        } catch (Exception e) {
            Toast.makeText(mContext, "Произошла ошибка чтения таблицы рекордов", Toast.LENGTH_LONG);

        }
    }

    public void writeRecords(){
        try {
            FileOutputStream fos = mContext.openFileOutput(FILE_RECORDS, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(recordTreeLifeMod);
            os.writeObject(recordSurvivalMod);
            os.writeObject(recordTimeMod);
            os.close();
        } catch (Exception e) {
            Toast.makeText(mContext, "Произошла ошибка записи в таблицу рекордов", Toast.LENGTH_LONG);
        }
        return;
    }

    public void clearRecords(){
         recordTreeLifeMod = 0;
         recordSurvivalMod = 0;
         recordTimeMod = 0;
         writeRecords();
    }

    public boolean isRecord(String mod, int record){
        if(mod.equals("classic")){
            if (record > recordTreeLifeMod){
                recordTreeLifeMod=record;
                writeRecords();
                return true;
            }else{
                return false;
            }
        }
        if(mod.equals("time")){
            if (record > recordTimeMod){
                recordTimeMod=record;
                writeRecords();
                return true;
            }else{
                return false;
            }
        }
        if(mod.equals("survival")){
            if (record > recordSurvivalMod){
                recordSurvivalMod = record;
                writeRecords();
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public String getClassicRecord(){
        String str = ""+recordTreeLifeMod;
        return str;
    }
    public String getTimeRaceRecord(){
        String str = ""+recordTimeMod;
        return str;
    }
    public String getSurvivalRecord(){
        String str = ""+recordSurvivalMod;
        return str;
    }

}
