package hu.ace.geaapp.ui.view.inspection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private Paint paint;

    private Rect rectangle;

    private float cX;
    private float cY;
    private int circleColor;


    public CustomView(Context context) {
        super(context);
        initCircle();

        // create the Paint and set its color
//        paint = new Paint();
//        paint.setColor(Color.GRAY);
    }

    private void initCircle(){
        paint = new Paint();
        //paint.setColor(Color.RED);
    }

    private void initRectangle(){
        float x = getcX();
        int x_int = (int) getcX();
        float y=  getcY();
        int y_int = (int) getcY();
        int sideLength = 10;
        rectangle = new Rect(x_int,y_int,sideLength,sideLength);

    }

    public void setCoordinates(float coordX, float coordY){
        int radius = 10;
        this.cX = coordX;
        this.cY = coordY;
    }



    public void setColor(int circleColor){
        this.circleColor = circleColor;
        paint.setColor(circleColor);
    }

    public int getColor(){
       // System.out.println(" CustomView.getColor = "+paint.getColor());
        return paint.getColor();
    }

    public float getcX(){
        return this.cX;
    }

    public float getcY(){
        return this.cY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // canvas.drawColor(Color.BLUE);
        //canvas.drawCircle(200, 200, 100, paint);
//        canvas.drawCircle(getcX(), getcY(), 10, paint);
        canvas.drawCircle(getcX(), getcY(), 10, paint);
        //radius in float = 10 ==> R/2 = 5 ==> középpont és a kör széle közötti távolság 5 float
    }
}
