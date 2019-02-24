package ytwong239.scm.cubic;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;

/**
 * Created by DebbieWong on 22/2/2019.
 */

public class MainView extends View {

    private final static int MENUPAGE = 0;
    private final static int PRACTICEGAMEPAGE = 1;
    private final static int BATTLENUMPAGE = 2;
    private final static int BATTLEGAMEPAGE = 3;
    private final static int HOWTOPAGE1 = 4;
    private final static int HOWTOPAGE2 = 5;
    private final static int HOWTOPAGE3 = 6;
    private final static int INFOPAGE = 7;
    private int currPage = PRACTICEGAMEPAGE;

    private int canvasW;
    private int canvasH;

    private Bitmap practiceModePic;
    private Rect practiceModeSrc;
    private Rect practiceModePos;

    private Bitmap battleModePic;
    private Rect battleModeSrc;
    private Rect battleModePos;

    private Bitmap battleModeNumPic;
    private Rect battleModeNumSrc;
    private Rect battleModeNumPos;
    private Rect player2Pos;
    private Rect player3Pos;
    private Rect player4Pos;
    private int playerNum = 0;

    private Bitmap settingPic;
    private Rect settingSrc;
    private Rect settingPos;

    private Bitmap backPic;
    private Rect backSrc;
    private Rect backPos;

    private Bitmap howToTitlePic;
    private Rect howToTitleSrc;
    private Rect howToTitlePos;

    private Bitmap howTo1Pic;
    private Rect howTo1Src;
    private Rect howTo1Pos;

    private Bitmap howTo2Pic;
    private Rect howTo2Src;
    private Rect howTo2Pos;

    private Bitmap howTo3Pic;
    private Rect howTo3Src;
    private Rect howTo3Pos;

    private Bitmap howTo1WordPic;
    private Rect howTo1WordSrc;
    private Rect howTo1WordPos;

    private Bitmap howTo2WordPic;
    private Rect howTo2WordSrc;
    private Rect howTo2WordPos;

    private Bitmap howTo3WordPic;
    private Rect howTo3WordSrc;
    private Rect howTo3WordPos;

    private Bitmap nextPic;
    private Rect nextSrc;
    private Rect nextPos;

    private Bitmap skipPic;
    private Rect skipSrc;
    private Rect skipPos;

    private Bitmap dotWhitePic;
    private Rect dotWhiteSrc;
    private Bitmap dotOrangePic;
    private Rect dotOrangeSrc;
    private Rect dot1Pos;
    private Rect dot2Pos;
    private Rect dot3Pos;

    private Bitmap q0TitlePic;
    private Bitmap form3dmodelPic;
    private Rect qTitleSrc;
    private Rect qTitlePos;

    private Rect qFrontViewPos;
    private Rect qSideViewPos;
    private Rect qTopViewPos;

    private Bitmap qTopPic;
    private Rect qTopSrc;
    private Rect qTopPos;

    private Bitmap qSidePic;
    private Rect qSideSrc;
    private Rect qSidePos;

    private Bitmap qFrontPic;
    private Rect qFrontSrc;
    private Rect qFrontPos;

    private Bitmap timeBarPic;
    private Rect timeBarSrc;
    private Rect timeBarPos;
    private int timeTxtX, timeTxtY;

    private Bitmap answerPic;
    private Rect answerSrc;
    private Rect answerPos;

    private Bitmap modelPic;
    private Rect modelSrc;
    private Rect modelPos;

    private Bitmap Pic;
    private Rect Src;
    private Rect Pos;

    int lastX = 0;

    GameManager gameManager = new GameManager();

    Paint whiteStroke = new Paint();
    Paint lightOrange = new Paint();
    Paint darkOrange = new Paint();
    Paint white = new Paint();
    Paint font = new Paint();

    Typeface aldrich;

    public MainView(Context context) {
        super(context);

        AssetManager am = context.getApplicationContext().getAssets();
        aldrich = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "aldrich.ttf"));

        init();
        initBitmap();
    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        AssetManager am = context.getApplicationContext().getAssets();
        aldrich = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "aldrich.ttf"));

        init();
        initBitmap();
    }

    public MainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        AssetManager am = context.getApplicationContext().getAssets();
        aldrich = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "aldrich.ttf"));


        init();
        initBitmap();
    }

    private void init(){

        lightOrange.setColor(Color.rgb(255,197,128));

        darkOrange.setColor(Color.rgb(238,139,79));

        whiteStroke.setStyle(Paint.Style.STROKE);
        whiteStroke.setColor(Color.WHITE);
        whiteStroke.setStrokeWidth(10);

        white.setColor(Color.WHITE);

        font.setTypeface(aldrich);
        font.setColor(Color.WHITE);
        font.setTextSize(70);
        font.setTextAlign(Paint.Align.RIGHT);

    }

    private void initBitmap(){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;

        practiceModePic = BitmapFactory.decodeResource(getResources(), R.drawable.practicemode, opts);
        practiceModeSrc = new Rect(0,0, practiceModePic.getWidth(), practiceModePic.getHeight());

        battleModePic = BitmapFactory.decodeResource(getResources(), R.drawable.battlemode, opts);
        battleModeSrc = new Rect(0,0, battleModePic.getWidth(), battleModePic.getHeight());

        battleModeNumPic = BitmapFactory.decodeResource(getResources(), R.drawable.battlemodenum, opts);
        battleModeNumSrc = new Rect(0,0, battleModeNumPic.getWidth(), battleModeNumPic.getHeight());

        settingPic = BitmapFactory.decodeResource(getResources(), R.drawable.setting, opts);
        settingSrc = new Rect(0,0, settingPic.getWidth(), settingPic.getHeight());

        backPic = BitmapFactory.decodeResource(getResources(), R.drawable.back, opts);
        backSrc = new Rect(0,0, backPic.getWidth(), backPic.getHeight());

        howToTitlePic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaytitle, opts);
        howToTitleSrc = new Rect(0,0, howToTitlePic.getWidth(), howToTitlePic.getHeight());

        howTo1Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay1, opts);
        howTo1Src = new Rect(0,0, howTo1Pic.getWidth(), howTo1Pic.getHeight());

        howTo2Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay2, opts);
        howTo2Src = new Rect(0,0, howTo2Pic.getWidth(), howTo2Pic.getHeight());

        howTo3Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay3, opts);
        howTo3Src = new Rect(0,0, howTo3Pic.getWidth(), howTo3Pic.getHeight());

        howTo1WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay1word, opts);
        howTo1WordSrc = new Rect(0,0, howTo1WordPic.getWidth(), howTo1WordPic.getHeight());

        howTo2WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay2word, opts);
        howTo2WordSrc = new Rect(0,0, howTo2WordPic.getWidth(), howTo2WordPic.getHeight());

        howTo3WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay3word, opts);
        howTo3WordSrc = new Rect(0,0, howTo3WordPic.getWidth(), howTo3WordPic.getHeight());

        nextPic = BitmapFactory.decodeResource(getResources(), R.drawable.next, opts);
        nextSrc = new Rect(0,0, nextPic.getWidth(), nextPic.getHeight());

        skipPic = BitmapFactory.decodeResource(getResources(), R.drawable.skip, opts);
        skipSrc = new Rect(0,0, skipPic.getWidth(), skipPic.getHeight());

        dotWhitePic = BitmapFactory.decodeResource(getResources(), R.drawable.dotwhite, opts);
        dotWhiteSrc = new Rect(0,0, dotWhitePic.getWidth(), dotWhitePic.getHeight());

        dotOrangePic = BitmapFactory.decodeResource(getResources(), R.drawable.dotorange, opts);
        dotOrangeSrc = new Rect(0,0, dotOrangePic.getWidth(), dotOrangePic.getHeight());

        q0TitlePic = BitmapFactory.decodeResource(getResources(), R.drawable.q0title, opts);
        form3dmodelPic= BitmapFactory.decodeResource(getResources(), R.drawable.form3dmodel, opts);
        qTitleSrc = new Rect(0,0, q0TitlePic.getWidth(), q0TitlePic.getHeight());

        qTopPic = BitmapFactory.decodeResource(getResources(), R.drawable.top, opts);
        qTopSrc = new Rect(0,0, qTopPic.getWidth(), qTopPic.getHeight());

        qFrontPic = BitmapFactory.decodeResource(getResources(), R.drawable.front, opts);
        qFrontSrc = new Rect(0,0, qFrontPic.getWidth(), qFrontPic.getHeight());

        qSidePic = BitmapFactory.decodeResource(getResources(), R.drawable.side, opts);
        qSideSrc = new Rect(0,0, qSidePic.getWidth(), qSidePic.getHeight());

        timeBarPic = BitmapFactory.decodeResource(getResources(), R.drawable.timebar, opts);
        timeBarSrc = new Rect(0,0, timeBarPic.getWidth(), timeBarPic.getHeight());

        answerPic = BitmapFactory.decodeResource(getResources(), R.drawable.answer, opts);
        answerSrc = new Rect(0,0, answerPic.getWidth(), answerPic.getHeight());

        modelPic = BitmapFactory.decodeResource(getResources(), R.drawable.model, opts);
        modelSrc = new Rect(0,0, modelPic.getWidth(), modelPic.getHeight());

        Pic = BitmapFactory.decodeResource(getResources(), R.drawable.back, opts);
        Src = new Rect(0,0, Pic.getWidth(), Pic.getHeight());


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

        width = w / 3;
        height = (battleModeNumPic.getHeight() * width) / battleModeNumPic.getWidth();
        left = w / 2 + w / 20;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        battleModeNumPos = new Rect(left, top, right, bottom);

        width = (battleModeNumPos.width() - 160) / 3;
        height = (battleModeNumPos.height() - 100)/ 5;
        left = battleModeNumPos.left + 55;
        top = battleModeNumPos.bottom - height - 50;
        right = left + width;
        bottom = top + height;
        player2Pos = new Rect(left, top, right, bottom);

        width = (battleModeNumPos.width() - 160) / 3;
        height = (battleModeNumPos.height() - 100)/ 5;
        left = player2Pos.right + 25;
        top = battleModeNumPos.bottom - height - 50;
        right = left + width;
        bottom = top + height;
        player3Pos = new Rect(left, top, right, bottom);

        width = (battleModeNumPos.width() - 160) / 3;
        height = (battleModeNumPos.height() - 100)/ 5;
        left = player3Pos.right + 25;
        top = battleModeNumPos.bottom - height - 50;
        right = left + width;
        bottom = top + height;
        player4Pos = new Rect(left, top, right, bottom);

        width = w / 12;
        height = (settingPic.getHeight() * width) / settingPic.getWidth();
        left = w - width - 30;
        top = 30;
        right = left + width;
        bottom = top + height;
        settingPos = new Rect(left, top, right, bottom);

        width = w / 13;
        height = (backPic.getHeight() * width) / backPic.getWidth();
        left = 30;
        top = (settingPos.top + settingPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        backPos = new Rect(left, top, right, bottom);

        width = w / 3 + w / 30;
        height = (howToTitlePic.getHeight() * width) / howToTitlePic.getWidth();
        left = w / 2 - width / 2;
        top = (settingPos.top + settingPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        howToTitlePos = new Rect(left, top, right, bottom);

        height = h / 2;
        width = (howTo1Pic.getWidth() * height) / howTo1Pic.getHeight();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 - h / 20;
        right = left + width;
        bottom = top + height;
        howTo1Pos = new Rect(left, top, right, bottom);

        height = h / 2;
        width = (howTo2Pic.getWidth() * height) / howTo2Pic.getHeight();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 - h / 20;
        right = left + width;
        bottom = top + height;
        howTo2Pos = new Rect(left, top, right, bottom);

        height = h / 2;
        width = (howTo3Pic.getWidth() * height) / howTo3Pic.getHeight();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 - h / 20;
        right = left + width;
        bottom = top + height;
        howTo3Pos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo1WordPic.getWidth() * height) / howTo1WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howTo1Pos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo1WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo2WordPic.getWidth() * height) / howTo2WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howTo2Pos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo2WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo3WordPic.getWidth() * height) / howTo3WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howTo3Pos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo3WordPos = new Rect(left, top, right, bottom);

        width = w / 6 + w / 40;
        height = (nextPic.getHeight() * width) / nextPic.getWidth();
        left = settingPos.left - width + 20;
        top = h - height - 50;
        right = left + width;
        bottom = top + height;
        nextPos = new Rect(left, top, right, bottom);

        width = w / 6;
        height = (skipPic.getHeight() * width) / skipPic.getWidth();
        left = backPos.right - 50;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        skipPos= new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = w / 2 - width / 2;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot2Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot2Pos.left - w / 50 - width;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot1Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot2Pos.right + w / 50;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot3Pos = new Rect(left, top, right, bottom);

        width = w / 2 + w / 4;
        height = (q0TitlePic.getHeight() * width) / q0TitlePic.getWidth();
        left = w / 2 - width / 2;
        top = settingPos.top + settingPos.height() / 2;
        right = left + width;
        bottom = top + height;
        qTitlePos = new Rect(left, top, right, bottom);

        width = w / 15;
        height = width;
        left = qTitlePos.left + 5;
        top = h / 2 - height / 2 - height - w / 40;
        right = left + width;
        bottom = top + height;
        qTopViewPos = new Rect(left, top, right, bottom);

        width = w / 15;
        height = width;
        left = w / 2 - width / 2 - width;
        top = h / 2 - height / 2 - height - w / 40;
        right = left + width;
        bottom = top + height;
        qSideViewPos = new Rect(left, top, right, bottom);

        width = w / 15;
        height = width;
        left = qTitlePos.right - width * 3 - 5;
        top = h / 2 - height / 2 - height - w / 40;
        right = left + width;
        bottom = top + height;
        qFrontViewPos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (qTopPic.getWidth() * height) / qTopPic.getHeight();
        left = qTopViewPos.left + qTopViewPos.width() + qTopViewPos.width() / 2 - width / 2;
        top = qTopViewPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        qTopPos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (qSidePic.getWidth() * height) / qSidePic.getHeight();
        left = qSideViewPos.left + qSideViewPos.width() + qSideViewPos.width() / 2 - width / 2;
        top = qSideViewPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        qSidePos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (qFrontPic.getWidth() * height) / qFrontPic.getHeight();
        left = qFrontViewPos.left + qFrontViewPos.width() + qFrontViewPos.width() / 2 - width / 2;
        top = qFrontViewPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        qFrontPos = new Rect(left, top, right, bottom);

        width = w / 2 + w / 6;
        height = (timeBarPic.getHeight() * width) / timeBarPic.getWidth();
        left = qTitlePos.left;
        top = qFrontViewPos.bottom + qFrontViewPos.height() * 2 + h / 9;
        right = left + width;
        bottom = top + height;
        timeBarPos = new Rect(left, top, right, bottom);
        timeTxtX = qTitlePos.right;
        timeTxtY = timeBarPos.top + timeBarPos.height() + 10;

        height = h / 7;
        width = (answerPic.getWidth() * height) / answerPic.getHeight();
        left = w / 2 - width / 2;
        top = h - height - 50;
        right = left + width;
        bottom = top + height;
        answerPos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (modelPic.getWidth() * height) / modelPic.getHeight();
        left = 112221;
        top = 11222;
        right = left + width;
        bottom = top + height;
        modelPos = new Rect(left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255, 197, 128);

        switch (currPage){
            case MENUPAGE:
                drawMenuPage(canvas);
                break;
            case PRACTICEGAMEPAGE:
                drawPracticeGamePage(canvas);
                break;
            case BATTLENUMPAGE:
                drawBattleNumPage(canvas);
                break;
            case BATTLEGAMEPAGE:
                drawBattleGamePage(canvas);
                break;
            case HOWTOPAGE1:
                drawHowToPage1(canvas);
                break;
            case HOWTOPAGE2:
                drawHowToPage2(canvas);
                break;
            case HOWTOPAGE3:
                drawHowToPage3(canvas);
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

    private void drawBattleNumPage(Canvas canvas){
        canvas.drawBitmap(practiceModePic, practiceModeSrc, practiceModePos, null);
        canvas.drawBitmap(battleModeNumPic, battleModeNumSrc, battleModeNumPos, null);

//        Paint p = new Paint();
//        p.setColor(Color.BLUE);
//        canvas.drawRect(player2Pos,p);
//        Paint p2 = new Paint();
//        p2.setColor(Color.GREEN);
//        canvas.drawRect(player3Pos,p2);
//        Paint p3 = new Paint();
//        p3.setColor(Color.RED);
//        canvas.drawRect(player4Pos,p3);
    }

    private void drawHowToPage1(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo1Pic, howTo1Src, howTo1Pos, null);
        canvas.drawBitmap(howTo1WordPic, howTo1WordSrc, howTo1WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot1Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot3Pos, null);
    }

    private void drawHowToPage2(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo2Pic, howTo2Src, howTo2Pos, null);
        canvas.drawBitmap(howTo2WordPic, howTo2WordSrc, howTo2WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot2Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot3Pos, null);
    }

    private void drawHowToPage3(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo3Pic, howTo3Src, howTo3Pos, null);
        canvas.drawBitmap(howTo3WordPic, howTo3WordSrc, howTo3WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot3Pos, null);
    }

    private void drawPracticeGamePage(Canvas canvas){
        canvas.drawBitmap(q0TitlePic, qTitleSrc, qTitlePos, null);
        canvas.drawBitmap(form3dmodelPic, qTitleSrc, qTitlePos, null);

        canvas.drawBitmap(qTopPic, qTopSrc, qTopPos, null);
        canvas.drawBitmap(qSidePic, qSideSrc, qSidePos, null);
        canvas.drawBitmap(qFrontPic, qFrontSrc, qFrontPos, null);

        canvas.drawText("30", timeTxtX, timeTxtY, font);
        canvas.drawBitmap(timeBarPic, timeBarSrc, timeBarPos, null);

        canvas.drawBitmap(answerPic, answerSrc, answerPos, null);

        int row = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameManager.getQFrontView(row + j) == 1){
                    canvas.drawRect( 100 + j * 80, 1260 + i * 80, 100 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect( 100 + j * 80, 1260 + i * 80, 100 + j * 80 + 80, 1260 + i * 80 + 80, darkOrange);
                }
                else{
                    canvas.drawRect( qFrontViewPos.left + j * qFrontViewPos.width(), qFrontViewPos.top + i * qFrontViewPos.width(), qFrontViewPos.right + j * qFrontViewPos.width(), qFrontViewPos.bottom + i * qFrontViewPos.width() , whiteStroke);
                    canvas.drawRect(qFrontViewPos.left + j * qFrontViewPos.width(), qFrontViewPos.top + i * qFrontViewPos.width(), qFrontViewPos.right + j * qFrontViewPos.width(), qFrontViewPos.bottom + i * qFrontViewPos.width(), lightOrange);
                }

                if(gameManager.getQSideView(row + j) == 1){
                    canvas.drawRect( 400 + j * 80, 1260 + i * 80, 400 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect( 400 + j * 80, 1260 + i * 80, 400 + j * 80 + 80, 1260 + i * 80 + 80, darkOrange);
                }
                else{
                    canvas.drawRect( qSideViewPos.left + j * qSideViewPos.width(), qSideViewPos.top + i * qSideViewPos.width(), qSideViewPos.right + j * qSideViewPos.width(), qSideViewPos.bottom + i * qSideViewPos.width() , whiteStroke);
                    canvas.drawRect(qSideViewPos.left + j * qSideViewPos.width(), qSideViewPos.top + i * qSideViewPos.width(), qSideViewPos.right + j * qSideViewPos.width(), qSideViewPos.bottom + i * qSideViewPos.width(), lightOrange);
                }

                if(gameManager.getQTopView(row + j) == 1){
                    canvas.drawRect( 700 + j * 80, 1260 + i * 80, 700 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect( 700 + j * 80, 1260 + i * 80, 700 + j * 80 + 80, 1260 + i * 80 + 80, darkOrange);
                }
                else{
                    canvas.drawRect( qTopViewPos.left + j * qTopViewPos.width(), qTopViewPos.top + i * qTopViewPos.width(), qTopViewPos.right + j * qTopViewPos.width(), qTopViewPos.bottom + i * qTopViewPos.width() , whiteStroke);
                    canvas.drawRect(qTopViewPos.left + j * qTopViewPos.width(), qTopViewPos.top + i * qTopViewPos.width(), qTopViewPos.right + j * qTopViewPos.width(), qTopViewPos.bottom + i * qTopViewPos.width(), lightOrange);
                }

                if(j == 2){
                    row += 3;
                }
            }
        }
    }

    private void drawBattleGamePage(Canvas canvas){
        Paint p = new Paint(Color.BLACK);
        p.setTextSize(100);
        canvas.drawText("Battle", 100, 100, p);
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
                            playerNum = 1;
                            currPage = HOWTOPAGE1;
                        }
                        else if(battleModePos.contains(x, y)){
                            currPage = BATTLENUMPAGE;
                        }
                        break;
                    case PRACTICEGAMEPAGE:
                        break;
                    case BATTLENUMPAGE:
                        if(practiceModePos.contains(x, y)){
                            playerNum = 1;
                            currPage = HOWTOPAGE1;
                        }
                        else if(player2Pos.contains(x, y)){
                            playerNum = 2;
                            currPage = HOWTOPAGE1;
                        }
                        else if(player3Pos.contains(x, y)){
                            playerNum = 3;
                            currPage = HOWTOPAGE1;
                        }
                        else if(player4Pos.contains(x, y)){
                            playerNum = 4;
                            currPage = HOWTOPAGE1;
                        }
                        break;
                    case BATTLEGAMEPAGE:
                        break;
                    case HOWTOPAGE1:
                        if(backPos.contains(x, y)){
                            currPage = MENUPAGE;
                        }
                        else if(skipPos.contains(x, y)){
                            if(playerNum == 1){
                                currPage = PRACTICEGAMEPAGE;
                            }
                            else{
                                currPage = BATTLEGAMEPAGE;
                            }
                        }
                        else if(nextPos.contains(x, y)){
                            currPage = HOWTOPAGE2;
                        }
                        break;
                    case HOWTOPAGE2:
                        if(backPos.contains(x, y)){
                            currPage = MENUPAGE;
                        }
                        else if(skipPos.contains(x, y)){
                            if(playerNum == 1){
                                currPage = PRACTICEGAMEPAGE;
                            }
                            else{
                                currPage = BATTLEGAMEPAGE;
                            }
                        }
                        else if(nextPos.contains(x, y)){
                            currPage = HOWTOPAGE3;
                        }
                        break;
                    case HOWTOPAGE3:
                        if(backPos.contains(x, y)){
                            currPage = MENUPAGE;
                        }
                        else if(skipPos.contains(x, y)){
                            if(playerNum == 1){
                                currPage = PRACTICEGAMEPAGE;
                            }
                            else{
                                currPage = BATTLEGAMEPAGE;
                            }
                        }
                        else if(nextPos.contains(x, y)){
                            if(playerNum == 1){
                                currPage = PRACTICEGAMEPAGE;
                            }
                            else{
                                currPage = BATTLEGAMEPAGE;
                            }
                        }
                        break;
                    case INFOPAGE:
                        break;

                }


                if(settingPos.contains(x, y)){
                    Log.d("adfd", "setting");
                }

                //swipe
                lastX = x;
                break;

            case MotionEvent.ACTION_UP:
                switch (currPage){
                    case HOWTOPAGE1:
                        if(lastX - x > 100){
                            currPage = HOWTOPAGE2;
                        }
                        break;
                    case HOWTOPAGE2:
                        if(x - lastX> 100){
                            currPage = HOWTOPAGE1;
                        }
                        if(lastX - x > 100){
                            currPage = HOWTOPAGE3;
                        }
                        break;
                    case HOWTOPAGE3:
                        if(x - lastX > 100){
                            currPage = HOWTOPAGE2;
                        }
                        break;
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
        if(battleModeNumPic != null && !battleModeNumPic.isRecycled()){
            battleModeNumPic.recycle();
            battleModeNumPic = null;
        }
        if(settingPic != null && !settingPic.isRecycled()){
            settingPic.recycle();
            settingPic = null;
        }
        if(backPic != null && !backPic.isRecycled()){
            backPic.recycle();
            backPic = null;
        }
        if(howToTitlePic != null && !howToTitlePic.isRecycled()){
            howToTitlePic.recycle();
            howToTitlePic = null;
        }
        if(howTo1Pic != null && !howTo1Pic.isRecycled()){
            howTo1Pic.recycle();
            howTo1Pic = null;
        }
        if(howTo2Pic != null && !howTo2Pic.isRecycled()){
            howTo2Pic.recycle();
            howTo2Pic = null;
        }
        if(howTo3Pic != null && !howTo3Pic.isRecycled()){
            howTo3Pic.recycle();
            howTo3Pic = null;
        }
        if(howTo1WordPic != null && !howTo1WordPic.isRecycled()){
            howTo1WordPic.recycle();
            howTo1WordPic = null;
        }
        if(howTo2WordPic != null && !howTo2WordPic.isRecycled()){
            howTo2WordPic.recycle();
            howTo2WordPic = null;
        }
        if(howTo3WordPic != null && !howTo3WordPic.isRecycled()){
            howTo3WordPic.recycle();
            howTo3WordPic = null;
        }
        if(nextPic != null && !nextPic.isRecycled()){
            nextPic.recycle();
            nextPic = null;
        }
        if(skipPic != null && !skipPic.isRecycled()){
            skipPic.recycle();
            skipPic = null;
        }
        if(dotWhitePic != null && !dotWhitePic.isRecycled()){
            dotWhitePic.recycle();
            dotWhitePic = null;
        }
        if(dotOrangePic != null && !dotOrangePic.isRecycled()){
            dotOrangePic.recycle();
            dotOrangePic = null;
        }
        if(q0TitlePic != null && !q0TitlePic.isRecycled()){
            q0TitlePic.recycle();
            q0TitlePic = null;
        }
        if(form3dmodelPic != null && !form3dmodelPic.isRecycled()){
            form3dmodelPic.recycle();
            form3dmodelPic = null;
        }
        if(qTopPic != null && !qTopPic.isRecycled()){
            qTopPic.recycle();
            qTopPic = null;
        }
        if(qSidePic != null && !qSidePic.isRecycled()){
            qSidePic.recycle();
            qSidePic = null;
        }
        if(qFrontPic != null && !qFrontPic.isRecycled()){
            qFrontPic.recycle();
            qFrontPic = null;
        }
        if(timeBarPic != null && !timeBarPic.isRecycled()){
            timeBarPic.recycle();
            timeBarPic = null;
        }
        if(answerPic != null && !answerPic.isRecycled()){
            answerPic.recycle();
            answerPic = null;
        }

    }
}
