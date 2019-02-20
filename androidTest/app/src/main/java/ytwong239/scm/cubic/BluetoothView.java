package ytwong239.scm.cubic;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DebbieWong on 20/2/2019.
 */

public class BluetoothView extends View {

    private Bitmap allowBTPic;
    private Rect allowBTSrc;
    private Rect allowBTPos;

    private Bitmap allowBTOKPic;
    private Rect allowBTOKSrc;
    private Rect allowBTOKPos;
    private Boolean allowBTOKHit = false;

    private int canvasW;
    private int canvasH;


    public BluetoothView(Context context) {
        super(context);
        init();
    }

    public BluetoothView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BluetoothView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;

        allowBTPic = BitmapFactory.decodeResource(getResources(), R.drawable.allowbluetooth, opts);
        allowBTSrc = new Rect(0,0, allowBTPic.getWidth(), allowBTPic.getHeight());

        allowBTOKPic = BitmapFactory.decodeResource(getResources(), R.drawable.allowbluetoothokbtn, opts);
        allowBTOKSrc = new Rect(0,0, allowBTOKPic.getWidth(), allowBTOKPic.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasW = w;
        canvasH = h;

        int width = w / 5;
        int height = (allowBTPic.getHeight() * width) / allowBTPic.getWidth();
        int left = w / 2 - width / 2;
        int top = h / 2 - height / 2 - h / 8;
        int right = left + width;
        int bottom = top + height;
        allowBTPos = new Rect(left, top, right, bottom);

        width = w / 5;
        height = (allowBTOKPic.getHeight() * width) / allowBTOKPic.getWidth();
        left = w / 2 - width / 2;
        top = allowBTPos.bottom - h / 8;
        right = left + width;
        bottom = top + height;
        allowBTOKPos = new Rect(left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255, 197, 128);
        canvas.drawBitmap(allowBTPic, allowBTSrc, allowBTPos, null);
        canvas.drawBitmap(allowBTOKPic, allowBTOKSrc, allowBTOKPos, null);
        invalidate();

    }

    public boolean isAllowBTOKHit(){
        return allowBTOKHit;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                if(allowBTOKPos.contains((int)x, (int)y)){

                }
                else{

                }
                break;

        }
        invalidate();
        return true;
    }


}
