package hu.ace.geaapp.ui.view.inspection.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class RectangleView extends View {

    private float coordinateX,coordinateY;
    private Paint paint;
    private int rectangleColor;

    public RectangleView(Context context) {
        super(context);
        initRectangle();
    }

    public void setColor(int rectangleColor){
        this.rectangleColor = rectangleColor;
        paint.setColor(rectangleColor);
    }

    public void setCoordinates(float coordX, float coordY){
        int radius = 10;
        this.coordinateX = coordX;
        this.coordinateY = coordY;
    }

    private void initRectangle(){
        paint = new Paint();

        /*float x = getcX();
        int x_int = (int) getcX();
        float y=  getcY();
        int y_int = (int) getcY();
        int sideLength = 10;
        rectangle = new Rect(x_int,y_int,sideLength,sideLength);*/

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(coordinateX, coordinateY, 10,10, paint);
    }

}
