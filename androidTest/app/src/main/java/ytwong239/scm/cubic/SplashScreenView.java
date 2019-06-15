package ytwong239.scm.cubic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.awt.font.TextAttribute;

/**
 * Created by DebbieWong on 20/2/2019.
 */

public class SplashScreenView extends View {

    private Bitmap logoPic;
    private Rect logoSrc;
    private Rect logoPos;

    private Bitmap pressPic;
    private Rect pressSrc;
    private Rect pressPos;
    private int pressAlpha = 255;

    private int canvasW;
    private int canvasH;

    private long preTime = 0;
    private long currTime = 0;

    private Paint p = new Paint();

    public SplashScreenView(Context context) {
        super(context);
        init();
    }

    public SplashScreenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SplashScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;

        logoPic = BitmapFactory.decodeResource(getResources(), R.drawable.logo_orangeshadow, opts);
        logoSrc = new Rect(0,0, logoPic.getWidth(), logoPic.getHeight());

        pressPic = BitmapFactory.decodeResource(getResources(), R.drawable.presstostart, opts);
        pressSrc = new Rect(0,0, pressPic.getWidth(), pressPic.getHeight());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasW = w;
        canvasH = h;

        int logoW = w / 5;
        int logoH = (logoPic.getHeight() * logoW) / logoPic.getWidth();
        int logoL = w / 2 - logoW / 2;
        int logoT = h / 2 - logoH / 2 - h / 8;
        int logoR = logoL + logoW;
        int logoB = logoT + logoH;
        logoPos = new Rect(logoL, logoT, logoR, logoB);

        int pressW = w / 3;
        int pressH = (pressPic.getHeight() * pressW) / pressPic.getWidth();
        int pressL = w / 2 - pressW / 2;
        int pressT = logoB + h / 10;
        int pressR = pressL + pressW;
        int pressB = pressT + pressH;
        pressPos = new Rect(pressL, pressT, pressR, pressB);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255, 197, 128);
        canvas.drawBitmap(logoPic, logoSrc, logoPos, null);
        p.setAlpha(pressAlpha);
        canvas.drawBitmap(pressPic, pressSrc, pressPos, p);

        currTime = System.currentTimeMillis();

        if(currTime - preTime > 700){
            if(pressAlpha == 255){
                pressAlpha = 0;
            }
            else if(pressAlpha == 0){
                pressAlpha = 255;
            }
            preTime = currTime;
        }

        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                if(x > 0 && x < canvasW && y > 0 && y < canvasH){
                    Intent intent = new Intent(getContext(), TurnOnBluetoothActivity.class);
                    getContext().startActivity(intent);
                }
                break;

        }

        invalidate();
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(logoPic != null && !logoPic.isRecycled()){
            logoPic.recycle();
            logoPic = null;
        }
        if(pressPic != null && !pressPic.isRecycled()){
            pressPic.recycle();
            pressPic = null;
        }
    }
}
