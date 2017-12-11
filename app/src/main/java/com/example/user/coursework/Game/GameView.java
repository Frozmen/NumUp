package com.example.user.coursework.Game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import towe.def.GameView.GameThread;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.user.coursework.Activity.AfterGameActivity;
import com.example.user.coursework.R;

/**
 * Created by User on 04.03.2015.
 */
public class GameView extends SurfaceView {
    protected GameView gameView = this;
    protected Context context;
    protected Paint pointsPaint = new Paint();
    protected Task task;
    protected GameThread mThread;
    protected int points=0;
    protected int tempPointsSpeed;
    protected int tempPointsComplexity;
    protected int speed = 1;
    protected int complexityOfExpression=1; //cложность выражения(количество знаков)
    protected int touchX;
    protected int touchY;
    protected TimerGenerationBlocks timerGenerationBlocks;
    protected List<Block> myListOfBlock = new ArrayList<Block>();
    protected Bitmap background;
    protected Bitmap blockSprite;
    protected Bitmap footer;
    protected Bitmap top;
    protected String mod = "survival";
    protected SoundsForGame sound;

    protected boolean running = false;//поток рисования +/-

    //поток отрисовки
    private class GameThread extends Thread
    {
        private GameView view;
        public GameThread(GameView view)
        {
            this.view = view;
        }
        public void setRunning(boolean run)
        {
            running = run;
        }

        // Действия выполняемые в потоке
        public void run()
        {
            Canvas canvas;
            while (running)
            {
                canvas = null;
                try
                {
                    // подготовка Canvas-а
                    canvas = view.getHolder().lockCanvas();
                    synchronized (view.getHolder())
                    {
                        //  рисование
                        onDraw(canvas);
                    }
                }
                catch (Exception ignored) { }
                finally
                {
                    if (canvas != null)
                    {
                        view.getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    public GameView(Context context)
    {

        super(context);
        this.context=context;
        mThread = new GameThread(this);
        /*Рисуем все объекты */
        getHolder().addCallback(new SurfaceHolder.Callback()
        {
            /*** Уничтожение области рисования */
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                boolean retry = true;
                mThread.setRunning(false);
                while (retry)
                {
                    try
                    {
                        // ожидание завершение потока
                        mThread.join();
                        retry = false;
                    }
                    catch (InterruptedException ignored) { }
                }

            }

            /** Создание области рисования */
            public void surfaceCreated(SurfaceHolder holder)
            {

                doBeforeCreatingScenes();
                setPointsPaint();
                timerGenerationBlocks = new TimerGenerationBlocks(myListOfBlock, blockSprite, gameView);
                timerGenerationBlocks.setParameters(speed, complexityOfExpression);
                task = new Task(gameView, complexityOfExpression);
                mThread.setRunning(true);
                mThread.start();
                timerGenerationBlocks.startGenerationBlocks();
                //startGenerationBlocks();
                //resetTask();


            }

            /** Изменение области рисования */
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {
            }
        });



    }

    /**Функция рисующая все спрайты и фон*/
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLACK)
        canvas.drawBitmap(background, 0, 0, null);
        Iterator<Block> j = myListOfBlock.iterator();
        while(j.hasNext()) {
            Block b = j.next();
            if(b.getY()<=top.getHeight()){
                if(isRightBlock(b)){
                    userError();
                    j.remove();
                    startGenerationBlocks();
                }
            }
            if( b.getY() >= top.getHeight()-blockSprite.getHeight()) {
                b.drawBlock(canvas);

            } else {
                j.remove();
                points++;
                startGenerationBlocks();
            }
        }
        canvas.drawBitmap(footer, 0, this.getHeight()-footer.getHeight(), null);
        canvas.drawBitmap(top, 0, 0, null);
        task.drawTask(canvas);
        drawPoint(canvas);


    }

   /** Реакция на нажатие на экране*/
    public boolean onTouchEvent(MotionEvent e){
        touchX = (int) e.getX();
        touchY = (int) e.getY();

        if(e.getAction() == MotionEvent.ACTION_DOWN)
            checkClickingOnBlock();

        return true;
    }
    //Функция генерации блоков на экране

   /*
    protected Block createNewBlock(){

        return new Block(blockSprite, speed, complexityOfExpression, this);

    }

   protected void  startGenerationBlocks(){
        Random rand = new Random();
        timerGenerationBlocks = new Timer();
        randAmountOfBlocksOnView = rand.nextInt(6)+9;
        TimerTask myTimerTask = new TimerTask(){
            @Override
            public void run() {
                block.add(createNewBlock());
                randAmountOfBlocksOnView--;
                if(randAmountOfBlocksOnView==0){
                    timerGenerationBlocks.cancel();
                }
            }
        };
        timerGenerationBlocks.schedule(myTimerTask, 20, 2500);
    }*/
    //Задать размеры спрайтам в зависимости от размера игрового поля
    protected void doBeforeCreatingScenes(){

        background = BitmapFactory.decodeResource(getResources(), R.drawable.cleaback);
        blockSprite = BitmapFactory.decodeResource(getResources(), R.drawable.blocknest1);
        footer = BitmapFactory.decodeResource(getResources(), R.drawable.footer3);
        top = BitmapFactory.decodeResource(getResources(), R.drawable.top);
        int a = this.getWidth();
        int b = this.getHeight();
        background = Bitmap.createScaledBitmap(background, a, b, false);
        footer = Bitmap.createScaledBitmap(footer, a, Math.round(a/8.07f), false );
        top = Bitmap.createScaledBitmap(top, a, Math.round(a/3.90f), false );

        float hi = a/3;
        float wh = hi/2;
        blockSprite = Bitmap.createScaledBitmap(blockSprite, (int)hi, (int)wh, false);
        sound = new SoundsForGame(context);



    }

    public void onCorrectTouch(){
        sound.onCorrectTouch();
    }
    public void onWrongTouch(){
        sound.onWrongTouch();
    }
    //Проверка попал ли пользователь нажатием на блок
    protected boolean checkClickingOnBlock(){
        Iterator<Block> j = myListOfBlock.iterator();
        while(j.hasNext()) {
            Block b = j.next();
            if( touchX>=b.getX()& touchX<=b.getX()+blockSprite.getWidth()&
                    touchY>=b.getY()&touchY<=b.getY()+blockSprite.getHeight()) {
                if(isRightBlock(b)){
                    points++;
                    tempPointsSpeed++;
                    tempPointsComplexity++;
                    onCorrectTouch();
                }else{
                    userError();
                    onWrongTouch();
                }
                j.remove();
                startGenerationBlocks();
                return true;
            }
        }
        return false;
    }
    //Проверка соответствует ли значение нажатого блока заданию
    protected boolean isRightBlock(Block block){
        if(task.getX2()==0){
            if(task.isMore()){
                if(block.getValue()>=task.getX1()){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(block.getValue()<=task.getX1()){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            if(block.getValue()>=task.getX1() & block.getValue()<=task.getX2()){
                return true;
            } else{
                return false;
            }
        }
    }
    //Окончание игры
    protected void userError(){
        endOfGame();
    }

    protected void endOfGame(){
        running=false;
        Intent finishGame = new Intent(context, AfterGameActivity.class);
        finishGame.putExtra("point", points);
        finishGame.putExtra("mod", mod);
        context.startActivity(finishGame);
        //((Activity)context).finish();
    }
    //Вывод количества очков набраных пользователем
    protected void drawPoint(Canvas canvas){

        String a = context.getString(R.string.points);
        String b="" + points;
        canvas.drawText(a, this.getWidth()-20, top.getHeight()/3, pointsPaint);
        canvas.drawText(b, this.getWidth()-20, top.getHeight()/1.5f, pointsPaint);


    }
    //Задание параметров рисования очков
    private  void setPointsPaint(){
        points = 0;
        pointsPaint.setColor(Color.WHITE);
        pointsPaint.setTextSize(this.getHeight()/7.28f/3);
        pointsPaint.setTextAlign(Paint.Align.RIGHT);
        pointsPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
    }
    //генерация нового задания
    protected void resetTask(){
        task.setNewTask();
        changeComplexityOfGame();
        sound.onChangeTask();
    }
    //повышение сложности игры
    protected void  changeComplexityOfGame(){
        if(tempPointsSpeed>=8  & speed<=4){
            speed++;
            tempPointsSpeed=0;

        }
        if(tempPointsComplexity>=14 & complexityOfExpression<=3){
            complexityOfExpression++;
            tempPointsComplexity=0;
            tempPointsSpeed=0;
        }
    }
    private void startGenerationBlocks(){
        if(myListOfBlock.isEmpty() & !timerGenerationBlocks.isWorking()){
            resetTask();
            timerGenerationBlocks.setParameters(speed, complexityOfExpression);
            timerGenerationBlocks.startGenerationBlocks();
        }
    }
}

