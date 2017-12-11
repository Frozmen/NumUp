package com.example.user.coursework.Game;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.Random;

/**
 * Created by User on 02.03.2015.
 */
public class Block {
    private Paint textPaint;
    private int x;
    private int y;
    private int speed;
    private int value;
    private String Expression;
    private Bitmap picture;//блок
    private com.example.user.coursework.Game.Expression exp;
    private Matrix matrix;
    private Random random;

    public Block(Bitmap bmp, int speed, int complexity, GameView gameView) {
        picture = bmp;
        matrix = new Matrix();
        exp = new Expression(complexity);
        this.speed = speed;
        Expression = exp.getExpression();
        value = exp.getExpressionAnswer();
        this.x = random.nextInt(gameView.getWidth()-picture.getWidth())+1;
        this.y = gameView.getHeight();
        setPaintSettings();
        random = new Random();


    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getValue(){return value;
    }
    public void drawBlock(Canvas canvas){
        moveTop();
        canvas.drawBitmap(picture, x, y,  null);
        canvas.drawText(Expression, x+picture.getWidth()/2, y+picture.getHeight()/2, textPaint);

       String ab = "" + value;
       //canvas.drawText(ab, x, y, textPaint);  //число в блоке

    }
    private void moveTop(){
       //matrix.setTranslate(0, -speed);
        y-=speed;
    }
    private void setPaintSettings(){
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(picture.getHeight()/3);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
    }


}
