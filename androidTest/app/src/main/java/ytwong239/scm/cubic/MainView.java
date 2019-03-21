package ytwong239.scm.cubic;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

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
    protected int currPage = PRACTICEGAMEPAGE;

    private static final int MAXQUESTNUM = 6;

    private final static int MAXVIEWQUESTNUM = 7;
    private final static int MAXSPTYPE3NUM = 2;
    private final static int MAXSPTYPE4NUM = 2;

    private static final int DRAWFRONTVIEW = 0;
    private static final int DRAWSIDEVIEW = 1;
    private static final int DRAWTOPVIEW = 2;
    private static final int BUILD3DMODEL = 3;
    private static final int SPTYPE3 = 4;
    private static final int SPTYPE4 = 5;

    static final int MAXBASESNUM = 3;
    static final int MAXGRIDSNUM = 9;
    static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    static final int MAXHEIGHTNUM = 3;

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

    private Bitmap[] qTitlePic = new Bitmap[MAXQUESTNUM];
    private Bitmap form3dmodelPic;
    private Bitmap formFrontViewPic;
    private Bitmap formSideViewPic;
    private Bitmap formTopViewPic;
    private Bitmap formAnsPic;
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

    private int timeTxtX, timeTxtY;
    private int timeCirX1, timeCirY1, timeCirX2, timeCirY2, timeCirR;
    private Rect timeRectPos;
    private int timeFullWidth;
    private int timeCurrWidth;
    private int timeRemapWidth;
    private boolean timeSetDone = false;


    private Bitmap answerPic;
    private Rect answerSrc;
    private Rect answerPos;

    private Bitmap modelPic;
    private Rect modelSrc;
    private Rect modelPos;

    private Bitmap qModelBkgPic;
    private Rect qModelBkgSrc;
    private Rect qModelPos;

    private Rect drawViewPos;
    private Rect drawViewFrontPos;
    private Rect drawViewSidePos;
    private Rect drawViewTopPos;

    private Rect spType4Pos_base;
    private Rect spType4Pos0;
    private Rect spType4Pos1;
    private Rect spType4Pos2;
    private Rect spType4Pos3;
    private int spType4PlayerChoice = 0;

    private Bitmap spType4QPic;
    private Rect spType4QSrc;
    private Rect spType4QPos;

    private Bitmap spType3Q0Pic;
    private Rect spType3Q0Src;
    private Rect spType3Q0Pos;

    private Bitmap spType3Q1Pic;
    private Rect spType3Q1Src;
    private Rect spType3Q1Pos;

    private Bitmap spType3Q2Pic;
    private Rect spType3Q2Src;
    private Rect spType3Q2Pos;

    private Bitmap qSpType3BkgPic;
    private Rect qspType3BkgSrc;
    private Rect spType3Pos_base0;
    private Rect spType3Pos_base1;
    private Rect spType3Pos_quest;

    private Bitmap tipIconPic;
    private Rect tipIconSrc;
    private Rect tipIconPos;

    private Bitmap Pic;
    private Rect Src;
    private Rect Pos;

    int lastX = 0;

    GameManager gameManager = new GameManager();
    QuestionBank_2D3D questionBank2D3D = new QuestionBank_2D3D();
    QuestionBank_SPType3_Ans questionBankSPType3 = new QuestionBank_SPType3_Ans();
    QuestionBank_SPType4_Ans questionBankSPType4 = new QuestionBank_SPType4_Ans();
    Arduino arduino = new Arduino();

    Paint whiteStroke = new Paint();
    Paint lightOrange = new Paint();
    Paint darkOrange = new Paint();
    Paint white = new Paint();
    Paint font = new Paint();
    Paint drawViewPaint[] = new Paint[MAXGRIDSNUM];
    Paint blackStroke = new Paint();

    Typeface aldrich;

    Boolean debugVisible = false;

    private Rect[] drawViewPos2 = new Rect[MAXGRIDSNUM];
    private boolean[] drawViewTouch = new boolean[MAXGRIDSNUM];
    private int drawViewTouchCount = 0;

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

        blackStroke.setStyle(Paint.Style.STROKE);
        blackStroke.setColor(Color.BLACK);
        blackStroke.setStrokeWidth(7);

        white.setColor(Color.WHITE);
        white.setTextSize(40);

        font.setTypeface(aldrich);
        font.setColor(Color.WHITE);
        font.setTextSize(70);
        font.setTextAlign(Paint.Align.RIGHT);

        for(int i = 0; i < drawViewPos2.length; i++){
            drawViewPos2[i] = new Rect(0,0,0,0);
            drawViewPaint[i] = new Paint();
            drawViewPaint[i].setColor(lightOrange.getColor());
            drawViewTouch[i] = false;
        }

    }

    private void initBitmap(){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        BitmapFactory.Options opts2 = new BitmapFactory.Options();
        opts2.inSampleSize = 4;

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

        qTitlePic[0] = BitmapFactory.decodeResource(getResources(), R.drawable.q0title, opts);
        qTitlePic[1] = BitmapFactory.decodeResource(getResources(), R.drawable.q1title, opts);
        qTitlePic[2] = BitmapFactory.decodeResource(getResources(), R.drawable.q2title, opts);
        qTitlePic[3] = BitmapFactory.decodeResource(getResources(), R.drawable.q3title, opts);
        qTitlePic[4] = BitmapFactory.decodeResource(getResources(), R.drawable.q4title, opts);
        qTitlePic[5] = BitmapFactory.decodeResource(getResources(), R.drawable.q5title, opts);
        form3dmodelPic= BitmapFactory.decodeResource(getResources(), R.drawable.form3dmodel, opts);
        formFrontViewPic= BitmapFactory.decodeResource(getResources(), R.drawable.frontview, opts);
        formSideViewPic= BitmapFactory.decodeResource(getResources(), R.drawable.sideview, opts);
        formTopViewPic= BitmapFactory.decodeResource(getResources(), R.drawable.topview, opts);
        formAnsPic= BitmapFactory.decodeResource(getResources(), R.drawable.formans, opts);
        qTitleSrc = new Rect(0,0, qTitlePic[0].getWidth(), qTitlePic[0].getHeight());

        qTopPic = BitmapFactory.decodeResource(getResources(), R.drawable.top, opts);
        qTopSrc = new Rect(0,0, qTopPic.getWidth(), qTopPic.getHeight());

        qFrontPic = BitmapFactory.decodeResource(getResources(), R.drawable.front, opts);
        qFrontSrc = new Rect(0,0, qFrontPic.getWidth(), qFrontPic.getHeight());

        qSidePic = BitmapFactory.decodeResource(getResources(), R.drawable.side, opts);
        qSideSrc = new Rect(0,0, qSidePic.getWidth(), qSidePic.getHeight());

        answerPic = BitmapFactory.decodeResource(getResources(), R.drawable.answer, opts);
        answerSrc = new Rect(0,0, answerPic.getWidth(), answerPic.getHeight());

        modelPic = BitmapFactory.decodeResource(getResources(), R.drawable.model, opts);
        modelSrc = new Rect(0,0, modelPic.getWidth(), modelPic.getHeight());

        qModelBkgPic = BitmapFactory.decodeResource(getResources(), R.drawable.modelbkg, opts);
        qModelBkgSrc = new Rect(0,0, qModelBkgPic.getWidth(), qModelBkgPic.getHeight());

        spType4QPic = BitmapFactory.decodeResource(getResources(), R.drawable.sptype4question, opts);
        spType4QSrc = new Rect(0,0, spType4QPic.getWidth(), spType4QPic.getHeight());

        spType3Q0Pic = BitmapFactory.decodeResource(getResources(), R.drawable.sptype3question0, opts);
        spType3Q0Src = new Rect(0,0, spType3Q0Pic.getWidth(), spType3Q0Pic.getHeight());

        spType3Q1Pic = BitmapFactory.decodeResource(getResources(), R.drawable.sptype3question1, opts);
        spType3Q1Src = new Rect(0,0, spType3Q1Pic.getWidth(), spType3Q1Pic.getHeight());

        spType3Q2Pic = BitmapFactory.decodeResource(getResources(), R.drawable.sptype3question2, opts);
        spType3Q2Src = new Rect(0,0, spType3Q2Pic.getWidth(), spType3Q2Pic.getHeight());

        qSpType3BkgPic = BitmapFactory.decodeResource(getResources(), R.drawable.modelbkg_noword, opts);
        qspType3BkgSrc = new Rect(0,0, qSpType3BkgPic.getWidth(), qSpType3BkgPic.getHeight());

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
        height = (qTitlePic[0].getHeight() * width) / qTitlePic[0].getWidth();
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

        timeCirR = 15;
        width = qTitlePos.width() - w / 10;
        timeFullWidth = width;
        timeCurrWidth = timeFullWidth;
        height = timeCirR * 2;
        left = qTitlePos.left + timeCirR;
        top = qFrontViewPos.bottom + qFrontViewPos.height() * 2 + h / 10;
        right = left + width;
        bottom = top + height;
        timeRectPos = new Rect(left, top, right, bottom);
        timeCirX1 = timeRectPos.left;
        timeCirY1 = timeRectPos.top + timeCirR;
        timeCirX2 = timeRectPos.right;
        timeCirY2 = timeRectPos.top + timeCirR;
        timeTxtX = qTitlePos.right;
        timeTxtY = timeRectPos.top + timeRectPos.height();


        height = h / 7;
        width = (answerPic.getWidth() * height) / answerPic.getHeight();
        left = w / 2 - width / 2;
        top = h - height - 50;
        right = left + width;
        bottom = top + height;
        answerPos = new Rect(left, top, right, bottom);

        int gap = w / 15;

        width = w / 5;
        height = (850 * width) / 850;
        left = w / 2 - gap - width;
        top = h / 2 - height / 2 - w / 40;
        right = left + width;
        bottom = top + height;
        qModelPos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (modelPic.getWidth() * height) / modelPic.getHeight();
        left = qModelPos.left + qModelPos.width() / 2 - width / 2;
        top = qModelPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        modelPos = new Rect(left, top, right, bottom);

        width = w / 15;
        height = width;
        left = w / 2 + gap + 5;
        top = h / 2 - height / 2 - height - w / 40;
        right = left + width;
        bottom = top + height;
        drawViewPos = new Rect(left, top, right, bottom);

        int row = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                drawViewPos2[row + j].left = drawViewPos.left + j * drawViewPos.width();
                drawViewPos2[row + j].top = drawViewPos.top + i * drawViewPos.width();
                drawViewPos2[row + j].right = drawViewPos.right + j * drawViewPos.width();
                drawViewPos2[row + j].bottom = drawViewPos.bottom + i * drawViewPos.width();
                if(j == 2){
                    row += 3;
                }
            }
        }

        height = h / 35;
        width = (qFrontPic.getWidth() * height) / qFrontPic.getHeight();
        left = drawViewPos.left + drawViewPos.width() + drawViewPos.width() / 2 - width / 2;
        top = drawViewPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        drawViewFrontPos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (qSidePic.getWidth() * height) / qSidePic.getHeight();
        left = drawViewPos.left + drawViewPos.width() + drawViewPos.width() / 2 - width / 2;
        top = drawViewPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        drawViewSidePos = new Rect(left, top, right, bottom);

        height = h / 35;
        width = (qTopPic.getWidth() * height) / qTopPic.getHeight();
        left = drawViewPos.left + drawViewPos.width() + drawViewPos.width() / 2 - width / 2;
        top = drawViewPos.top - w / 30;
        right = left + width;
        bottom = top + height;
        drawViewTopPos = new Rect(left, top, right, bottom);

        width = w / 5;
        height = (666 * width) / 666;
        left = qTitlePos.left;
        top = h / 2 - height / 2 - w / 40;
        right = left + width;
        bottom = top + height;
        spType4Pos_base = new Rect(left, top, right, bottom);

        int space = qTitlePos.right - spType4Pos_base.right;
        gap = w / 80;

        width = (space - gap * 4) / 4;
        height = (666 * width) / 666;
        left = spType4Pos_base.right + gap;
        right = left + width;
        bottom = spType4Pos_base.bottom;
        top = bottom - height;
        spType4Pos0 = new Rect(left, top, right, bottom);

        width = (space - gap * 4) / 4;
        height = (666 * width) / 666;
        left = spType4Pos_base.right + gap * 2 + width;
        right = left + width;
        bottom = spType4Pos_base.bottom;
        top = bottom - height;
        spType4Pos1 = new Rect(left, top, right, bottom);

        width = (space - gap * 4) / 4;
        height = (666 * width) / 666;
        left = spType4Pos_base.right + gap * 3 + width * 2;
        right = left + width;
        bottom = spType4Pos_base.bottom;
        top = bottom - height;
        spType4Pos2 = new Rect(left, top, right, bottom);

        width = (space - gap * 4) / 4;
        height = (666 * width) / 666;
        left = spType4Pos_base.right + gap * 4 + width * 3;
        right = left + width;
        bottom = spType4Pos_base.bottom;
        top = bottom - height;
        spType4Pos3 = new Rect(left, top, right, bottom);

        space = spType4Pos3.right - spType4Pos0.left;

        width = w / 2;
        height = (spType4QPic.getHeight() * width) / spType4QPic.getWidth();
        left = (spType4Pos0.left + space / 2) - width / 2;
        top = spType4Pos_base.top;
        right = left + width;
        bottom = top + height;
        spType4QPos = new Rect(left, top, right, bottom);

        height = h / 22;
        width = (spType3Q0Pic.getWidth() * height) / spType3Q0Pic.getHeight();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Q0Pos = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (666 * width) / 666;
        left = spType3Q0Pos.left - w / 40 - width;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Pos_base0 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (666 * width) / 666;
        left = spType3Q0Pos.right + w / 40;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Pos_base1 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (666 * width) / 666;
        left = w / 2 - width / 2;
        top = spType3Q0Pos.bottom + w / 35;
        right = left + width;
        bottom = top + height;
        spType3Pos_quest = new Rect(left, top, right, bottom);

        width = w / 4;
        height = (spType3Q1Pic.getHeight() * width) / spType3Q1Pic.getWidth();
        left = spType3Pos_quest.left - w / 40 - width;
        top = (spType3Pos_quest.top + spType3Pos_quest.height() / 2) - height / 2 + h / 30;
        right = left + width;
        bottom = top + height;
        spType3Q1Pos = new Rect(left, top, right, bottom);

        height = h / 22;
        width = (spType3Q2Pic.getWidth() * height) / spType3Q2Pic.getHeight();
        left = spType3Pos_quest.right + w / 40;
        top = (spType3Pos_quest.top + spType3Pos_quest.height() / 2) - height / 2 + h / 30;
        right = left + width;
        bottom = top + height;
        spType3Q2Pos = new Rect(left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255, 197, 128);

        switch (currPage){
            case MENUPAGE:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                drawMenuPage(canvas);
                break;
            case PRACTICEGAMEPAGE:
                drawPracticeGamePage(canvas);
                break;
            case BATTLENUMPAGE:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                drawBattleNumPage(canvas);
                break;
            case BATTLEGAMEPAGE:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                drawBattleGamePage(canvas);
                break;
            case HOWTOPAGE1:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                drawHowToPage1(canvas);
                break;
            case HOWTOPAGE2:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                drawHowToPage2(canvas);
                break;
            case HOWTOPAGE3:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                drawHowToPage3(canvas);
                break;
            case INFOPAGE:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                break;

        }

        canvas.drawBitmap(settingPic, settingSrc, settingPos, null);

        if(debugVisible){
            drawDebug(canvas);
        }


        forArduino();

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

        //question title
        canvas.drawBitmap(qTitlePic[gameManager.getCurrQuestNum()], qTitleSrc, qTitlePos, null);

        switch (gameManager.getCurrQuestMode()){
            case DRAWFRONTVIEW:
                OpenGLRenderer_3DModel.setCanDraw(true);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formFrontViewPic, qTitleSrc, qTitlePos, null);
                drawModel(canvas);
                break;
            case DRAWSIDEVIEW:
                OpenGLRenderer_3DModel.setCanDraw(true);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formSideViewPic, qTitleSrc, qTitlePos, null);
                drawModel(canvas);
                break;
            case DRAWTOPVIEW:
                OpenGLRenderer_3DModel.setCanDraw(true);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formTopViewPic, qTitleSrc, qTitlePos, null);
                drawModel(canvas);
                break;
            case BUILD3DMODEL:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(form3dmodelPic, qTitleSrc, qTitlePos, null);
                drawViews(canvas);
                break;
            case SPTYPE3:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(true);
                OpenGLRenderer_SPType3_Base1.setCanDraw(true);
                OpenGLRenderer_SPType3_Quest.setCanDraw(true);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formAnsPic, qTitleSrc, qTitlePos, null);
                drawSpType3(canvas);
                break;
            case SPTYPE4:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(true);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(true);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(true);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(true);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(true);
                canvas.drawBitmap(formAnsPic, qTitleSrc, qTitlePos, null);
                drawSpType4(canvas);
                break;

        }

        //timer
        if(!timeSetDone){
            gameManager.countDownTimer30s.start();
            timeSetDone = true;
        }

        canvas.drawText(String.valueOf((int)gameManager.getTimeLeft30s() / 1000), timeTxtX, timeTxtY, font);

        timeRemapWidth = (int)remap(gameManager.getTimeLeft30s(), gameManager.getTotalTime30s(), 0, timeFullWidth, 0);

        if(timeCurrWidth > timeRemapWidth){
            timeCurrWidth--;
        }
        else if(timeCurrWidth == timeRemapWidth){
            timeCurrWidth = timeRemapWidth;
        }

        if(timeCirX2 <= timeRectPos.left){
            timeCirX1 = timeCirX2;
            timeCirR -= 5;
            timeRectPos.right = 0;
        }else{
            timeRectPos.right = timeRectPos.left + timeCurrWidth;
            timeCirX2 = timeRectPos.right;
        }

        if(gameManager.getResetTimer()){
            resetTimer();
        }

        canvas.drawCircle(timeCirX1, timeCirY1, timeCirR, white);
        canvas.drawCircle(timeCirX2, timeCirY2, timeCirR, white);
        canvas.drawRect(timeRectPos, white);

        //answer btn
        canvas.drawBitmap(answerPic, answerSrc, answerPos, null);

        //restart the game
        if(gameManager.getRestart()){
            spType4PlayerChoice = 0;
            resetDrawView();
            resetTimer();
            currPage = MENUPAGE;
            gameManager.setRestartFalse();
        }

    }

    private float remap(float value, float from1, float to1, float from2, float to2){
        return (value - from1) / (to1 - from1) * (to2 - from2) + from2;
    }

    private void drawViews(Canvas canvas){
        //top, side, front word
        canvas.drawBitmap(qTopPic, qTopSrc, qTopPos, null);
        canvas.drawBitmap(qSidePic, qSideSrc, qSidePos, null);
        canvas.drawBitmap(qFrontPic, qFrontSrc, qFrontPos, null);

        //rectangle front side top view
        int row = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(questionBank2D3D.getFrontView(row + j) == 1){
                    canvas.drawRect( qFrontViewPos.left + j * qFrontViewPos.width(), qFrontViewPos.top + i * qFrontViewPos.width(), qFrontViewPos.right + j * qFrontViewPos.width(), qFrontViewPos.bottom + i * qFrontViewPos.width() , whiteStroke);
                    canvas.drawRect(qFrontViewPos.left + j * qFrontViewPos.width(), qFrontViewPos.top + i * qFrontViewPos.width(), qFrontViewPos.right + j * qFrontViewPos.width(), qFrontViewPos.bottom + i * qFrontViewPos.width(), darkOrange);
                }
                else{
                    canvas.drawRect( qFrontViewPos.left + j * qFrontViewPos.width(), qFrontViewPos.top + i * qFrontViewPos.width(), qFrontViewPos.right + j * qFrontViewPos.width(), qFrontViewPos.bottom + i * qFrontViewPos.width() , whiteStroke);
                    canvas.drawRect(qFrontViewPos.left + j * qFrontViewPos.width(), qFrontViewPos.top + i * qFrontViewPos.width(), qFrontViewPos.right + j * qFrontViewPos.width(), qFrontViewPos.bottom + i * qFrontViewPos.width(), lightOrange);
                }

                if(questionBank2D3D.getSideView(row + j) == 1){
                    canvas.drawRect( qSideViewPos.left + j * qSideViewPos.width(), qSideViewPos.top + i * qSideViewPos.width(), qSideViewPos.right + j * qSideViewPos.width(), qSideViewPos.bottom + i * qSideViewPos.width() , whiteStroke);
                    canvas.drawRect(qSideViewPos.left + j * qSideViewPos.width(), qSideViewPos.top + i * qSideViewPos.width(), qSideViewPos.right + j * qSideViewPos.width(), qSideViewPos.bottom + i * qSideViewPos.width(), darkOrange);
                }
                else{
                    canvas.drawRect( qSideViewPos.left + j * qSideViewPos.width(), qSideViewPos.top + i * qSideViewPos.width(), qSideViewPos.right + j * qSideViewPos.width(), qSideViewPos.bottom + i * qSideViewPos.width() , whiteStroke);
                    canvas.drawRect(qSideViewPos.left + j * qSideViewPos.width(), qSideViewPos.top + i * qSideViewPos.width(), qSideViewPos.right + j * qSideViewPos.width(), qSideViewPos.bottom + i * qSideViewPos.width(), lightOrange);
                }

                if(questionBank2D3D.getTopView(row + j) == 1){
                    canvas.drawRect( qTopViewPos.left + j * qTopViewPos.width(), qTopViewPos.top + i * qTopViewPos.width(), qTopViewPos.right + j * qTopViewPos.width(), qTopViewPos.bottom + i * qTopViewPos.width() , whiteStroke);
                    canvas.drawRect(qTopViewPos.left + j * qTopViewPos.width(), qTopViewPos.top + i * qTopViewPos.width(), qTopViewPos.right + j * qTopViewPos.width(), qTopViewPos.bottom + i * qTopViewPos.width(), darkOrange);
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

    private void drawModel(Canvas canvas){

        //model word
        canvas.drawBitmap(modelPic, modelSrc, modelPos, null);

        //3d model
        canvas.drawBitmap(qModelBkgPic, qModelBkgSrc, qModelPos, null);

        //draw view word label
        switch (gameManager.getCurrQuestMode()){
            case DRAWFRONTVIEW:
                canvas.drawBitmap(qFrontPic, qFrontSrc, drawViewFrontPos, null);
                break;
            case DRAWSIDEVIEW:
                canvas.drawBitmap(qSidePic, qSideSrc, drawViewSidePos, null);
                break;
            case DRAWTOPVIEW:
                canvas.drawBitmap(qTopPic, qTopSrc, drawViewTopPos, null);
                break;
        }

        //clear the player draw view when user hold the button
        if(drawViewTouchCount > 5){
            for(int i = 0; i < drawViewPos2.length; i++){
                drawViewTouch[i] = false;
            }
        }

        //player draw view rectangle
        for(int i = 0; i < drawViewPos2.length; i++){

            if(drawViewTouch[i]){
                drawViewPaint[i].setColor(darkOrange.getColor());
                switch (gameManager.getCurrQuestMode()){
                    case DRAWFRONTVIEW:
                        gameManager.setPlayerFrontView(i);
                        break;
                    case DRAWSIDEVIEW:
                        gameManager.setPlayerSideView(i);
                        break;
                    case DRAWTOPVIEW:
                        gameManager.setPlayerTopView(i);
                        break;
                }
            }
            else{
                drawViewPaint[i].setColor(lightOrange.getColor());
                switch (gameManager.getCurrQuestMode()){
                    case DRAWFRONTVIEW:
                        gameManager.clearPlayerFrontView(i);
                        break;
                    case DRAWSIDEVIEW:
                        gameManager.clearPlayerSideView(i);
                        break;
                    case DRAWTOPVIEW:
                        gameManager.clearPlayerTopView(i);
                        break;
                }
            }

            canvas.drawRect(drawViewPos2[i].left, drawViewPos2[i].top, drawViewPos2[i].right,  drawViewPos2[i].bottom, whiteStroke);
            canvas.drawRect(drawViewPos2[i].left, drawViewPos2[i].top, drawViewPos2[i].right,  drawViewPos2[i].bottom, drawViewPaint[i]);
        }




    }

    private void drawSpType3(Canvas canvas){
        //sp type 3 word
        canvas.drawBitmap(spType3Q0Pic, spType3Q0Src, spType3Q0Pos, null);
        canvas.drawBitmap(spType3Q1Pic, spType3Q1Src, spType3Q1Pos, null);
        canvas.drawBitmap(spType3Q2Pic, spType3Q2Src, spType3Q2Pos, null);

        //sp type 3 pic
        canvas.drawBitmap(qSpType3BkgPic, qspType3BkgSrc, spType3Pos_base0, null);
        canvas.drawBitmap(qSpType3BkgPic, qspType3BkgSrc, spType3Pos_base1, null);
        canvas.drawBitmap(qSpType3BkgPic, qspType3BkgSrc, spType3Pos_quest, null);
    }

    private void drawSpType4(Canvas canvas){
        //sp type 4 question
        canvas.drawBitmap(spType4QPic, spType4QSrc, spType4QPos, null);
        canvas.drawRect(spType4Pos_base.right, spType4Pos_base.top, spType4Pos_base.right + 5, spType4Pos_base.bottom, white);

        //player ans choice
        switch (spType4PlayerChoice){
            case 0:
                Point a = new Point(spType4Pos0.left + spType4Pos0.width() / 2, spType4Pos0.bottom + 10);
                Point b = new Point(a.x - 20, a.y + 30);
                Point c = new Point(a.x + 20, a.y + 30);
                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.moveTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);
                path.close();
                canvas.drawPath(path, white);
                break;
            case 1:
                Point a1 = new Point(spType4Pos1.left + spType4Pos1.width() / 2, spType4Pos1.bottom + 10);
                Point b1 = new Point(a1.x - 20, a1.y + 30);
                Point c1 = new Point(a1.x + 20, a1.y + 30);
                Path path1 = new Path();
                path1.setFillType(Path.FillType.EVEN_ODD);
                path1.moveTo(b1.x, b1.y);
                path1.lineTo(c1.x, c1.y);
                path1.lineTo(a1.x, a1.y);
                path1.close();
                canvas.drawPath(path1, white);
                break;
            case 2:
                Point a2 = new Point(spType4Pos2.left + spType4Pos2.width() / 2, spType4Pos2.bottom + 10);
                Point b2 = new Point(a2.x - 20, a2.y + 30);
                Point c2 = new Point(a2.x + 20, a2.y + 30);
                Path path2 = new Path();
                path2.setFillType(Path.FillType.EVEN_ODD);
                path2.moveTo(b2.x, b2.y);
                path2.lineTo(c2.x, c2.y);
                path2.lineTo(a2.x, a2.y);
                path2.close();
                canvas.drawPath(path2, white);
                break;
            case 3:
                Point a3 = new Point(spType4Pos3.left + spType4Pos3.width() / 2, spType4Pos3.bottom + 10);
                Point b3 = new Point(a3.x - 20, a3.y + 30);
                Point c3 = new Point(a3.x + 20, a3.y + 30);
                Path path3 = new Path();
                path3.setFillType(Path.FillType.EVEN_ODD);
                path3.moveTo(b3.x, b3.y);
                path3.lineTo(c3.x, c3.y);
                path3.lineTo(a3.x, a3.y);
                path3.close();
                canvas.drawPath(path3, white);
                break;
        }

    }

    private void drawTip1(Canvas canvas){

    }

    private void drawBattleGamePage(Canvas canvas){
        Paint p = new Paint(Color.BLACK);
        p.setTextSize(100);
        canvas.drawText("Battle", 100, 100, p);

    }

    private void drawDebug(Canvas canvas) {
        Paint red = new Paint();
        red.setColor(Color.RED);
        red.setTextSize(40);
        Paint gray = new Paint();
        gray.setColor(Color.DKGRAY);
        gray.setTextSize(40);
        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setTextSize(40);

        canvas.drawRect(0, 0, canvasW, (canvasH / 5 )* 3, white);

        int x1 = 10;
        int x2 = canvasW / 3;
        int x3 = x2 * 2;

        for(int i = 0; i < MAXGRIDSNUM; i++){
            canvas.drawText("b" + i + ": " + arduino.getArdBase0String(i), 10, 50 + i * 70, red);
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                canvas.drawText(String.valueOf(arduino.bases[0].grids[i].isCubePresent(j)), 150 + j * 100, 50 + i * 70, red);
                canvas.drawText(String.valueOf(arduino.bases[1].grids[i].isCubePresent(j)), x2 + 140 + j * 100, 50 + i * 70, gray);
                canvas.drawText(String.valueOf(arduino.bases[2].grids[i].isCubePresent(j)), x3 + 140 + j * 100, 50 + i * 70, blue);
            }
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            canvas.drawText("b" + i + ": " + arduino.getArdBase1String(i), x2, 50 + i * 70, gray);
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            canvas.drawText("b" + i + ": " + arduino.getArdBase2String(i), x3, 50 + i * 70, blue);
        }

    }

    //getting the long string from BluetoothActivity
    public void updateArdAllString(String[] val){
        for(int i = 0; i < MAXCUBESNUM; i++){
            arduino.setArdAllString(val);
        }
        invalidate();
    }

    private void forArduino(){
        arduino.run();
        invalidate();
    }

    private void resetDrawView(){
        for(int i = 0; i < drawViewPos2.length; i++){
            drawViewTouch[i] = false;
        }
    }

    private void resetTimer(){
        timeCurrWidth = timeFullWidth;
        timeRemapWidth = timeFullWidth;
        timeCirR = 15;
        timeRectPos.right = timeRectPos.left + timeCurrWidth;
        timeCirX2 = timeRectPos.right;
        timeSetDone = false;
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
                        if(answerPos.contains(x, y )){
                            if(gameManager.getCurrQuestNum() < MAXQUESTNUM){
                                GameManager.setSpType4PlayerAns(spType4PlayerChoice);
                                gameManager.compare();
                                resetDrawView();
                            }
//                            else{
//                                resetDrawView();
//                                resetTimer();
//                                gameManager.restart();
//                                currPage = MENUPAGE;
//                            }
                        }

                        if(gameManager.getCurrQuestMode() == DRAWFRONTVIEW ||
                                gameManager.getCurrQuestMode() == DRAWSIDEVIEW ||
                                gameManager.getCurrQuestMode() == DRAWTOPVIEW){
                            for(int i = 0; i < drawViewPos2.length; i++){
                                if(drawViewPos2[i].contains(x, y)){
                                    drawViewTouchCount = 0;
                                    drawViewTouch[i] = !drawViewTouch[i];
                                }
                            }
                        }

                        if(gameManager.getCurrQuestMode() == SPTYPE4){
                            if(spType4Pos0.contains(x, y)){
                                spType4PlayerChoice = 0;
                            }
                            else if(spType4Pos1.contains(x, y)){
                                spType4PlayerChoice = 1;
                            }
                            else if(spType4Pos2.contains(x, y)){
                                spType4PlayerChoice = 2;
                            }
                            else if(spType4Pos3.contains(x, y)){
                                spType4PlayerChoice = 3;
                            }
                        }

                        //------debug use-------
                        int xpos = canvasW - 200;
                        int ypos = canvasH - 200;
                        if(x < canvasW && x > xpos && y > ypos && y < canvasH){
                            if(gameManager.getCurrQuestNum() < MAXQUESTNUM - 1){
                                gameManager.nextQ();
                                resetDrawView();
                                resetTimer();
                            }
                            else{
                                resetDrawView();
                                resetTimer();
                                gameManager.restart();
                                currPage = MENUPAGE;
                            }
                        }
                        //-----------------------


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

                if(x > 0 && x < 200 && y > 0 && y < 200){
                    debugVisible = !debugVisible;
                }

                break;

            case MotionEvent.ACTION_MOVE:
                switch (currPage){
                    case PRACTICEGAMEPAGE:
                        if(gameManager.getCurrQuestMode() == DRAWFRONTVIEW ||
                                gameManager.getCurrQuestMode() == DRAWSIDEVIEW ||
                                gameManager.getCurrQuestMode() == DRAWTOPVIEW){
                            for(int i = 0; i < drawViewPos2.length; i++){
                                if(drawViewPos2[i].contains(x, y)){
                                    drawViewTouchCount++;
                                }
                            }
                        }
                        break;
                }



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
        for(int i = 0; i < MAXQUESTNUM; i++){
            if(qTitlePic[i] != null && !qTitlePic[i].isRecycled()){
                qTitlePic[i].recycle();
                qTitlePic[i] = null;
            }
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
        if(answerPic != null && !answerPic.isRecycled()){
            answerPic.recycle();
            answerPic = null;
        }
        if(modelPic != null && !modelPic.isRecycled()){
            modelPic.recycle();
            modelPic = null;
        }
        if(qModelBkgPic != null && !qModelBkgPic.isRecycled()){
            qModelBkgPic.recycle();
            qModelBkgPic = null;
        }
        if(spType4QPic != null && !spType4QPic.isRecycled()){
            spType4QPic.recycle();
            spType4QPic = null;
        }
        if(spType3Q0Pic != null && !spType3Q0Pic.isRecycled()){
            spType3Q0Pic.recycle();
            spType3Q0Pic = null;
        }
        if(spType3Q1Pic != null && !spType3Q1Pic.isRecycled()){
            spType3Q1Pic.recycle();
            spType3Q1Pic = null;
        }
        if(spType3Q2Pic != null && !spType3Q2Pic.isRecycled()){
            spType3Q2Pic.recycle();
            spType3Q2Pic = null;
        }


    }
}
