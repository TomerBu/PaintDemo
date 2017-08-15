package tomerbu.edu.paintdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dev on 9/29/2016.
 */
public class PaintView extends View {

    private Path path;
    private Paint paint;

    public PaintView(Context context) {
       super(context);
        init();
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
       super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PaintView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        path = AppManager.p;
        paint = new Paint();
        paint.setColor(0xff0f0f0f);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(20);

        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        int action = e.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                paint.setStyle(Paint.Style.FILL);
                path.addCircle(x, y, 1, Path.Direction.CW);
                paint.setStyle(Paint.Style.STROKE);

                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                //for (int i = 0; i < e.getHistorySize(); i++) {
                   // path.lineTo(e.getHistoricalX(i), e.getHistoricalY(i));
                //}
//                path.lineTo(x, y);
//                break;


                for (int i = 0; i < e.getHistorySize() - 2; i += 3) {

                    path.cubicTo(e.getHistoricalX(i), e.getHistoricalY(i),
                            e.getHistoricalX(i + 1), e.getHistoricalY(i + 1),
                            e.getHistoricalX(i + 2), e.getHistoricalY(i + 2));
                }
                path.lineTo(x, y);
                break;
        }


        //tell the view that we need to re-draw...
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
}


