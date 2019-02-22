package ytwong239.scm.cubic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DebbieWong on 22/2/2019.
 */

public class MainView extends View {

    private final static int MENUPAGE = 0;
    private final static int PRACTICEPAGE = 1;
    private final static int BATTLEPAGE = 2;
    private final static int HOWTOPAGE = 3;
    private final static int INFOPAGE = 4;
    private int currPage = 0;


    private int canvasW;
    private int canvasH;

    private Bitmap practiceModePic;
    private Rect practiceModeSrc;
    private Rect practiceModePos;

    private Bitmap battleModePic;
    private Rect battleModeSrc;
    private Rect battleModePos;

    private Bitmap settingPic;
    private Rect settingSrc;
    private Rect settingPos;


    public MainView(Context context) {
        super(context);
        init();
        initBitmap();
    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initBitmap();
    }

    public MainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initBitmap();
    }

    private void init(){

    }

    private void initBitmap(){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;

        practiceModePic = BitmapFactory.decodeResource(getResources(), R.drawable.practicemode, opts);
        practiceModeSrc = new Rect(0,0, practiceModePic.getWidth(), practiceModePic.getHeight());

        battleModePic = BitmapFactory.decodeResource(getResources(), R.drawable.battlemode, opts);
        battleModeSrc = new Rect(0,0, battleModePic.getWidth(), battleModePic.getHeight());

        settingPic = BitmapFactory.decodeResource(getResources(), R.drawable.setting, opts);
        settingSrc = new Rect(0,0, settingPic.getWidth(), settingPic.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasW = w;
        canvasH = h;

        int width = w / 3;
        int height = (practiceModePic.getHeight() * width) / practiceModePic.getWidth();
        int left = w / 2 - w / 20 - width;
        int top = h / 2 - height / 2;
        int right = left + width;
        int bottom = top + height;
        practiceModePos = new Rect(left, top, right, bottom);

        width = w / 3;
        height = (battleModePic.getHeight() * width) / battleModePic.getWidth();
        left = w / 2 + w / 20;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        battleModePos = new Rect(left, top, right, bottom);

        width = w / 12;
        height = (settingPic.getHeight() * width) / settingPic.getWidth();
        left = w - width - 30;
        top = 30;
        right = left + width;
        bottom = top + height;
        settingPos = new Rect(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255, 197, 128);

        switch (currPage){
            case MENUPAGE:
                drawMenuPage(canvas);
                break;
            case PRACTICEPAGE:
                break;
            case BATTLEPAGE:
                break;
            case HOWTOPAGE:
                break;
            case INFOPAGE:
                break;

        }

        canvas.drawBitmap(settingPic, settingSrc, settingPos, null);
    }

    private void drawMenuPage(Canvas canvas){
        canvas.drawBitmap(practiceModePic, practiceModeSrc, practiceModePos, null);
        canvas.drawBitmap(battleModePic, battleModeSrc, battleModePos, null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:

                switch (currPage){
                    case MENUPAGE:
                        if(practiceModePos.contains(x, y)){
                            currPage = HOWTOPAGE;
                        }
                        else if(battleModePos.contains(x, y)){
                            currPage = BATTLEPAGE;
                        }
                        break;
                    case PRACTICEPAGE:
                        break;
                    case BATTLEPAGE:
                        break;
                    case HOWTOPAGE:
                        break;
                    case INFOPAGE:
                        break;

                }


                if(settingPos.contains(x, y)){
                    Log.d("adfd", "setting");
                }
                break;
        }


        invalidate();
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if(practiceModePic != null && !practiceModePic.isRecycled()){
            practiceModePic.recycle();
            practiceModePic = null;
        }
        if(battleModePic != null && !battleModePic.isRecycled()){
            battleModePic.recycle();
            battleModePic = null;
        }
        if(settingPic != null && !settingPic.isRecycled()){
            settingPic.recycle();
            settingPic = null;
        }
    }
}
