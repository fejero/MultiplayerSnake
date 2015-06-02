package com.fejero.snake;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import com.fejero.snake.beepers.Beeper;
import com.fejero.snake.beepers.MinusBeeper;
import com.fejero.snake.beepers.PlusBeeper;
import com.fejero.snake.engine.Direction;
import com.fejero.snake.engine.Snake;
import com.fejero.snake.engine.SnakeComponent;
import com.fejero.snake.engine.Wall;
import com.fejero.snake.engine.World;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.Vibrator;
import android.support.v4.view.GestureDetectorCompat;
import android.text.method.Touch;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class Game extends View {

    private int x, y;

    private Paint paint;

    private Bitmap downButton;
    private Bitmap upButton;
    private Bitmap leftButton;
    private Bitmap rightButton;
    private Bitmap background;
    private Bitmap gameover;

    private Canvas c;
    private boolean touch = false;

    private int screenWidth;
    private int screenHeight;
    private int buttonSize;
    private Context context;
    private World world;
    private Snake snake1;
    private Snake snake2;
    private int playHeight;
    private ArrayList<Beeper> beepers = new ArrayList<Beeper>();
    private Thread beeperCreatorThread;
    private Random generator = new Random();
    private GameState state;
    private Vibrator vibrator;

    private GestureDetectorCompat mDetector;

    public Game(Context context) {
        super(context);
        this.context = context;
        state = GameState.PLAYING;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);


        Rect dim = new Rect();
        getWindowVisibleDisplayFrame(dim);
        screenWidth = dim.width();
        screenHeight = dim.height();
        playHeight = screenHeight;
        int unusedHeight = playHeight % (screenWidth / 20);
        playHeight = playHeight - unusedHeight;


        world = new World(screenWidth, playHeight, screenWidth / 20, this);
        snake1 = new Snake(screenWidth / 20, 7, 7, world, this, 1);
        snake2 = new Snake(screenWidth / 20, 14, 14, world, this, 2);

        beeperCreatorThread = new Thread() {
            public void run() {
                while (state != GameState.QUIT) {
                    int mils = generator.nextInt(5000) + 2000;
                    try {
                        Thread.sleep(mils);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (beepers.size() <= 3) {
                        while (!addUniqueBeeper()) {

                        }
                    }
                }
            }

            ;
        };
        beeperCreatorThread.start();


        buttonSize = screenHeight / 10;

        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_up);
        upButton = Bitmap.createScaledBitmap(b2, buttonSize, buttonSize, true);


        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_down);
        downButton = Bitmap.createScaledBitmap(b, buttonSize, buttonSize, true);


        Bitmap b3 = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_left);
        leftButton = Bitmap.createScaledBitmap(b3, buttonSize, buttonSize, true);

        Bitmap b4 = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_right);
        rightButton = Bitmap.createScaledBitmap(b4, buttonSize, buttonSize, true);

        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.snakeskin);
        background = Bitmap.createScaledBitmap(bg, screenWidth, playHeight, true);

        Bitmap go = BitmapFactory.decodeResource(getResources(), R.drawable.game_over);
        gameover = Bitmap.createScaledBitmap(go, screenWidth, screenHeight, true);

        // TODO Auto-generated constructor stub

    }


    public Vibrator getVibrator() {
        return vibrator;
    }

    public int getPlayHeight() {
        return playHeight;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }


    public Thread getBeeperCreatorThread() {
        return beeperCreatorThread;
    }

    public Context getActivityContext() {
        return context;
    }

    private boolean addUniqueBeeper() {
        int col, row;

        col = generator.nextInt(world.getCols());
        row = generator.nextInt(world.getRows());

        for (Beeper beeper : beepers) {
            if (beeper.getCol() == col && beeper.getRow() == row)
                return false;
        }

        for (SnakeComponent component : snake1.getSnakeBody()) {
            if (component.getCol() == col && component.getRow() == row)
                return false;
        }

        for (SnakeComponent component : snake2.getSnakeBody()) {
            if (component.getCol() == col && component.getRow() == row)
                return false;
        }
        int i = generator.nextInt(3);
        if (i < 2) {
            beepers.add(new PlusBeeper(screenWidth / 20, col, row, this));
        } else
            beepers.add(new MinusBeeper(screenWidth / 20, col, row, this));
        return true;
    }

    public void update() {
        snake1.move();
        snake2.move();
        executeBeepers();
    }

    private void executeBeepers() {
        try {
            for (Beeper beeper : beepers) {
                if (beeper.getCol() == snake1.getSnakeHead().getCol() && beeper.getRow() == snake1.getSnakeHead().getRow()) {
                    beeper.execute(snake1);
                    beepers.remove(beeper);
                }

            }
        } catch (ConcurrentModificationException exp) {

        }
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Snake getSnake() {
        return snake1;
    }

    public Snake getSnake2() {
        return snake2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        c = canvas;
        //setbackground
        Rect cBackground = new Rect();
        cBackground.set(0, 0, canvas.getWidth(), playHeight);
        //paint.setColor(Color.BLUE);
        //canvas.drawRect(cBackground, paint);
        canvas.drawBitmap(background, 0, 0, paint);

        if (state == GameState.PLAYING) {
            drawSnake(canvas);
            drawBeepers(canvas);
            drawWalls(canvas);
            drawControllers(canvas);
            invalidate();
        } else if (state == GameState.QUIT) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            canvas.drawBitmap(gameover, 0, 0, paint);

        }

    }

    private void drawWalls(Canvas canvas) {
        for (Wall wall : world.getWallList()) {
            wall.drawWall(canvas, paint);
        }
    }

    private void drawBeepers(Canvas canvas) {
        for (Beeper beeper : beepers) {
            beeper.draw(canvas, paint);
        }
    }
    /*@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		
		
		if(state == GameState.PLAYING)
		{
			//this.mDetector.onTouchEvent(event);
			
			return super.onTouchEvent(event);
		}
		
		
		if(event.getY()>=playHeight && event.getY()<=playHeight + buttonSize && event.getX()>=screenWidth/2 - buttonSize/2 && event.getX()<=screenWidth/2 + buttonSize/2)
		{
			v.vibrate(30);
			if(snake.getSnakeHead().getDirection()!=Direction.SOUTH)
				snake.getSnakeBody().get(0).setDirection(Direction.NORTH);
		}
		else if(event.getY()>=playHeight + (int)(buttonSize*1.5) && event.getY()<=playHeight + (int)(buttonSize*1.5)+ buttonSize && event.getX()>=screenWidth/2 - buttonSize/2 && event.getX()<=screenWidth/2 + buttonSize/2)
		{
			v.vibrate(30);
			if(snake.getSnakeHead().getDirection()!=Direction.NORTH)
				snake.getSnakeBody().get(0).setDirection(Direction.SOUTH);
		}
		else if(event.getY()>=playHeight+(int)(buttonSize*0.7) && event.getY()<=playHeight+(int)(buttonSize*0.7) +buttonSize && event.getX()>=screenWidth/2 - buttonSize*2 && event.getX()<=screenWidth/2 - buttonSize*2 +buttonSize)
		{
			v.vibrate(30);
			if(snake.getSnakeHead().getDirection()!=Direction.EAST)
			snake.getSnakeBody().get(0).setDirection(Direction.WEST);
		}
		else if(event.getY()>=playHeight+(int)(buttonSize*0.7) && event.getY()<=playHeight+(int)(buttonSize*0.7) +buttonSize && event.getX()>=screenWidth/2 + buttonSize && event.getX()<=screenWidth/2 + buttonSize + buttonSize)
		{
			v.vibrate(30);
			if(snake.getSnakeHead().getDirection()!=Direction.WEST)
			snake.getSnakeBody().get(0).setDirection(Direction.EAST);
		}
		else {
			
		}
		 
		return super.onTouchEvent(event);
	        
	}*/


    private void drawSnake(Canvas canvas) {
        try {
            for (SnakeComponent component : snake1.getSnakeBody()) {
                component.draw(canvas, paint);
            }
            for (SnakeComponent component : snake2.getSnakeBody()) {
                component.draw(canvas, paint);
            }
        } catch (ConcurrentModificationException exp) {

        }
    }


    private void drawControllers(Canvas canvas) {
        int playHeight = (int) (this.playHeight - 2.5 * buttonSize);
        canvas.drawBitmap(upButton, screenWidth / 2 - buttonSize / 2, playHeight, paint);

        canvas.drawBitmap(downButton, screenWidth / 2 - buttonSize / 2, playHeight + (int) (buttonSize * 1.5), paint);

        canvas.drawBitmap(leftButton, canvas.getWidth() / 2 - buttonSize * 2, playHeight + (int) (buttonSize * 0.7), paint);

        canvas.drawBitmap(rightButton, canvas.getWidth() / 2 + buttonSize, playHeight + (int) (buttonSize * 0.7), paint);
    }
}
