package ytwong239.scm.cubic;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
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
    private final static int HOWTOPAGEPM1 = 4;
    private final static int HOWTOPAGEPM2 = 5;
    private final static int HOWTOPAGEPM3 = 6;
    private final static int HOWTOPAGEPM4 = 7;
    private final static int HOWTOPAGEPM5 = 8;
    private final static int HOWTOPAGEBM1 = 9;
    private final static int HOWTOPAGEBM2 = 10;
    private final static int HOWTOPAGEBM3 = 11;
    private final static int HOWTOPAGEBM4 = 12;
    private final static int PUZZLEPAGE = 13;
    private final static int HOWTOPAGESETTING = 14;
    private final static int CONTACTUSPAGE = 15;
    protected int currPage = MENUPAGE;
    private boolean isInGame = false;

    private static final int MAXQUESTNUM = 6;

    private static final int DRAWFRONTVIEW = 0;
    private static final int DRAWSIDEVIEW = 1;
    private static final int DRAWTOPVIEW = 2;
    private static final int BUILD3DMODEL = 3;
    private static final int SPTYPE3 = 4;
    private static final int SPTYPE4 = 5;

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private static final int PUZZLEPIECENUM = 8;

    private static final int QUESTSTAGE = 0;
    private static final int REDANSWERSTAGE = 1;
    private static final int BLUEANSWERSTAGE = 2;
    private static final int GREENANSWERSTAGE = 3;
    private static final int PURPLEANSWERSTAGE = 4;
    private static final int TIMESUPSTAGE = 5;
    private static final int RESULTSTAGE = 6;

    private static final int PM1 = 0;
    private static final int PM2 = 1;
    private static final int PM3 = 2;
    private static final int PM4 = 3;
    private static final int PM5 = 4;
    private static final int BM1 = 5;
    private static final int BM2 = 6;
    private static final int BM3 = 7;
    private static final int BM4 = 8;
    private int currHowToSetting = 0;

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

    private Bitmap puzzleModePic;
    private Rect puzzleModeSrc;
    private Rect puzzleModePos;

    private Bitmap disablePic;
    private Rect disableSrc;

    private Bitmap settingPic;
    private Bitmap settingCancelPic;
    private Rect settingSrc;
    private Rect settingPos0;

    private Bitmap settingHowToPic;
    private Bitmap settingHvSoundPic;
    private Bitmap settingNoSoundPic;
    private Bitmap settingInfoPic;
    private Bitmap settingLeavePic;
    private Rect settingPos1;
    private Rect settingPos2;
    private Rect settingPos3;
    private boolean settingIsShown = false;

    private Bitmap contactUsTitlePic;
    private Rect contactUsTitleSrc;
    private Rect contactUsTitlePos;

    private Bitmap contactUsPic;
    private Rect contactUsSrc;
    private Rect contactUsPos;

    private Bitmap BModeTitlePic;
    private Rect BModeTitleSrc;
    private Rect BModeTitlePos;

    private Bitmap PModeTitlePic;
    private Rect PModeTitleSrc;
    private Rect PModeTitlePos;

    private Bitmap backPic;
    private Rect backSrc;
    private Rect backPos;

    private Bitmap howToTitlePic;
    private Rect howToTitleSrc;
    private Rect howToTitlePos;

    private Bitmap howTo1Pic;
    private Bitmap howTo2Pic;
    private Bitmap howTo3Pic;
    private Bitmap howTo4Pic;
    private Bitmap howTo5Pic;
    private Bitmap howToBM1Pic;
    private Bitmap howToBM2Pic;
    private Bitmap howToBM3Pic;
    private Rect howToSrc;
    private Rect howToPos;

    private Bitmap howTo1WordPic;
    private Rect howTo1WordSrc;
    private Rect howTo1WordPos;

    private Bitmap howTo2WordPic;
    private Rect howTo2WordSrc;
    private Rect howTo2WordPos;

    private Bitmap howTo3WordPic;
    private Rect howTo3WordSrc;
    private Rect howTo3WordPos;

    private Bitmap howTo4WordPic1;
    private Rect howTo4WordSrc1;
    private Rect howTo4WordPos1;

    private Bitmap howTo4WordPic2;
    private Rect howTo4WordSrc2;
    private Rect howTo4WordPos2;

    private Bitmap howTo5WordPic;
    private Rect howTo5WordSrc;
    private Rect howTo5WordPos;

    private Bitmap howToBM1WordPic1;
    private Rect howToBM1WordSrc1;
    private Rect howToBM1WordPos1;

    private Bitmap howToBM1WordPic2;
    private Rect howToBM1WordSrc2;
    private Rect howToBM1WordPos2;

    private Bitmap howToBM2WordPic;
    private Rect howToBM2WordSrc;
    private Rect howToBM2WordPos;

    private Bitmap howToBM3WordPic;
    private Rect howToBM3WordSrc;
    private Rect howToBM3WordPos;

    private Bitmap howToBM4WordPic;
    private Rect howToBM4WordSrc;
    private Rect howToBM4WordPos;

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
    private Rect dot4Pos;
    private Rect dot5Pos;
    private Rect dot6Pos;
    private Rect dot7Pos;
    private Rect dot8Pos;
    private Rect dot9Pos;

    private Bitmap[] qTitlePic = new Bitmap[MAXQUESTNUM];
    private Bitmap form3dmodelPic;
    private Bitmap formFrontViewPic;
    private Bitmap formSideViewPic;
    private Bitmap formTopViewPic;
    private Bitmap formAnsPic;
    private Bitmap selectAnsPic;
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
    private int answerCoolDown = 2;

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

    private Bitmap spType3Q3Pic;
    private Rect spType3Q3Src;
    private Rect spType3Q3Pos;

    private Bitmap spType3Q4Pic;
    private Rect spType3Q4Src;
    private Rect spType3Q4Pos;

    private Bitmap qSpType3BkgPic;
    private Rect qspType3BkgSrc;
    private Rect spType3Pos_base0;
    private Rect spType3Pos_base1;
    private Rect spType3Pos_quest;

    private Bitmap tipIconPic;
    private Rect tipIconSrc;
    private Rect tipIconPos;
    private Bitmap tipBoxPic;
    private Rect tipBoxSrc;
    private Rect tipBoxPos;
    private Bitmap tipBoxPic2;
    private Rect tipBoxSrc2;
    private Rect tipBoxPos2;
    private boolean tipIsShown = false;

    private Bitmap detectionIconPic;
    private Rect detectionIconSrc;
    private Rect detectionIconPos;
    private Bitmap detectionBoxPic;
    private Rect detectionBoxSrc;
    private Rect detectionBoxPos;
    private boolean detectionIsShown = false;
    private Bitmap detectionModelBkgPic;
    private Rect detectionModelBkgSrc;
    private Rect detectionModelBkgPos;

    private Bitmap puzzlePic;
    private Rect puzzleSrc;
    private Rect puzzlePos;
    private int puzzlePieceW = 0;
    private int puzzlePieceH = 0;
    private Rect[] puzzleCoverPos = new Rect[PUZZLEPIECENUM];
    private Bitmap puzzleTitlePic1;
    private Rect puzzleTitleSrc1;
    private Rect puzzleTitlePos1;
    private Bitmap puzzleTitlePic2;
    private Rect puzzleTitleSrc2;
    private Rect puzzleTitlePos2;
    private Bitmap puzzleTitlePic3;
    private Rect puzzleTitleSrc3;
    private Rect puzzleTitlePos3;

    private Bitmap redPlayerBtnLPic;
    private Rect redPlayerBtnLSrc;
    private Rect redPlayerBtnLPos;

    private Bitmap bluePlayerBtnLPic;
    private Rect bluePlayerBtnLSrc;
    private Rect bluePlayerBtnLPos;

    private Bitmap greenPlayerBtnLPic;
    private Rect greenPlayerBtnLSrc;
    private Rect greenPlayerBtnLPos;

    private Bitmap redPlayerBtnSPic;
    private Rect redPlayerBtnSSrc;
    private Rect redPlayerBtnSPos;

    private Bitmap bluePlayerBtnSPic;
    private Rect bluePlayerBtnSSrc;
    private Rect bluePlayerBtnSPos;

    private Bitmap greenPlayerBtnSPic;
    private Rect greenPlayerBtnSSrc;
    private Rect greenPlayerBtnSPos;

    private Bitmap purplePlayerBtnSPic;
    private Rect purplePlayerBtnSSrc;
    private Rect purplePlayerBtnSPos;

    private Bitmap timesUpPic;
    private Rect timesUpSrc;
    private Rect timesUpPos;

    private Bitmap redWinsPic;
    private Bitmap blueWinsPic;
    private Bitmap greenWinsPic;
    private Bitmap purpleWinsPic;
    private Bitmap itsADrawPic;
    private Rect winsSrc;
    private Rect winsPos;

    private Bitmap playerIconPic;
    private Rect playerIconSrc;
    private Rect playerIconPos0;
    private Rect playerIconPos1;
    private Rect playerIconPos2;
    private Rect playerIconPos3;
    private Rect playerIconPos4;
    private Rect playerIconPos5;
    private Rect playerIconPos6;
    private int playerIconH = 0;

    private Bitmap profilePic;
    private Rect profileSrc;
    private Rect profilePos;

    private Bitmap abilityPic0;
    private Rect abilitySrc0;
    private Rect abilityPos0;

    private Bitmap abilityPic1;
    private Rect abilitySrc1;
    private Rect abilityPos1;

    private Bitmap abilityPic2;
    private Rect abilitySrc2;
    private Rect abilityPos2;

    private Bitmap abilityPic3;
    private Rect abilitySrc3;
    private Rect abilityPos3;

    private Bitmap Pic;
    private Rect Src;
    private Rect Pos;

    private int lastX = 0;

    private GameManager_PracticeMode gameManagerPracticeMode = new GameManager_PracticeMode();
    private QuestionBank_2D3D questionBank2D3D = new QuestionBank_2D3D();
    private QuestionBank_SPType3_Ans questionBank_spType3_ans = new QuestionBank_SPType3_Ans();
    private Tips_SPType3 tips_spType3 = new Tips_SPType3();
    private Arduino arduino = new Arduino();
    private Puzzle puzzle = new Puzzle();
    private GameManager_BattleMode gameManagerBattleMode = new GameManager_BattleMode();

    private Paint whiteStroke = new Paint();
    private Paint lightOrange = new Paint();
    private Paint darkOrange = new Paint();
    private Paint white = new Paint();
    private Paint font = new Paint();
    private Paint tipFont = new Paint();
    private Paint scoreFont = new Paint();
    private Paint scoreGameFont = new Paint();
    private Paint drawViewPaint[] = new Paint[MAXGRIDSNUM];
    private Paint blackStroke = new Paint();
    private Paint coverPaint = new Paint();
    private Paint red = new Paint();
    private Paint blue = new Paint();
    private Paint green = new Paint();
    private Paint purple = new Paint();
    private Paint redStroke = new Paint();
    private Paint blueStroke = new Paint();
    private Paint greenStroke = new Paint();
    private Paint purpleStroke = new Paint();
    private Paint whiteStrokeThin = new Paint();
    private Paint darkOrangeStroke = new Paint();

    private Typeface aldrich;
    private Typeface myriad;

    private Boolean debugVisible = false;

    private Rect[] drawViewPos2 = new Rect[MAXGRIDSNUM];
    private boolean[] drawViewTouch = new boolean[MAXGRIDSNUM];
    private int drawViewTouchCount = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private float x0 = 0;
    private float y0 = 0;
    private float x1 = 0;
    private float y1 = 0;
    private float dx = 0;
    private float dy = 0;
    private float angle = 0;

    private int timesUpCountDown = 80;

    private int h0 = 0;
    private int h1 = 0;
    private int h2 = 0;
    private int h3 = 0;
    private int h4 = 0;
    private int h5 = 0;
    private int h6 = 0;

    private boolean musicIsPlay = true;

    private int lineLong = 0;
    private int lineMidX = 0;
    private int lineMidY = 0;

    private final Rect textBounds = new Rect();

    public MainView(Context context) {
        super(context);

        AssetManager am = context.getApplicationContext().getAssets();
        aldrich = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "aldrich.ttf"));
        myriad = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "myriadarabic.otf"));

        init();
        initBitmap();

        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        Puzzle.setPlayedRound(sharedPreferences.getInt(String.valueOf(R.integer.playedRound), 0));

    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        AssetManager am = context.getApplicationContext().getAssets();
        aldrich = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "aldrich.ttf"));
        myriad = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "myriadarabic.otf"));

        init();
        initBitmap();

        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        Puzzle.setPlayedRound(sharedPreferences.getInt(String.valueOf(R.integer.playedRound), 0));
    }

    public MainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        AssetManager am = context.getApplicationContext().getAssets();
        aldrich = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "aldrich.ttf"));
        myriad = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "myriadarabic.otf"));


        init();
        initBitmap();

        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        Puzzle.setPlayedRound(sharedPreferences.getInt(String.valueOf(R.integer.playedRound), 0));
    }

    private void init(){

        lightOrange.setColor(Color.rgb(255,197,128));

        darkOrange.setColor(Color.rgb(238,139,79));

        whiteStroke.setStyle(Paint.Style.STROKE);
        whiteStroke.setColor(Color.WHITE);
        whiteStroke.setStrokeWidth(10);

        whiteStrokeThin.setStyle(Paint.Style.STROKE);
        whiteStrokeThin.setColor(Color.WHITE);
        whiteStrokeThin.setStrokeWidth(5);

        darkOrangeStroke.setStyle(Paint.Style.STROKE);
        darkOrangeStroke.setColor(darkOrange.getColor());
        darkOrangeStroke.setStrokeWidth(10);

        blackStroke.setStyle(Paint.Style.STROKE);
        blackStroke.setColor(Color.BLACK);
        blackStroke.setStrokeWidth(7);

        white.setColor(Color.WHITE);
        white.setTextSize(40);

        coverPaint.setColor(Color.argb(250, 0, 0, 0));

        font.setTypeface(aldrich);
        font.setColor(Color.WHITE);
        font.setTextSize(70);
        font.setTextAlign(Paint.Align.RIGHT);

        tipFont.setTypeface(myriad);
        tipFont.setColor(darkOrange.getColor());
        tipFont.setTextSize(50);
        tipFont.setTextAlign(Paint.Align.CENTER);

        scoreFont.setTypeface(aldrich);
        scoreFont.setColor(Color.WHITE);
        scoreFont.setTextSize(50);

        scoreGameFont.setTypeface(aldrich);
        scoreGameFont.setColor(Color.WHITE);
        scoreGameFont.setTextSize(70);
        scoreGameFont.setTextAlign(Paint.Align.CENTER);

        for(int i = 0; i < drawViewPos2.length; i++){
            drawViewPos2[i] = new Rect(0,0,0,0);
            drawViewPaint[i] = new Paint();
            drawViewPaint[i].setColor(lightOrange.getColor());
            drawViewTouch[i] = false;
        }

        red.setColor(Color.rgb(224,78,78));
        blue.setColor(Color.rgb(82,167,199));
        green.setColor(Color.rgb(66,160,80));
        purple.setColor(Color.rgb(152,84,188));

        redStroke.setStyle(Paint.Style.STROKE);
        redStroke.setColor(red.getColor());
        redStroke.setStrokeWidth(40);

        blueStroke.setStyle(Paint.Style.STROKE);
        blueStroke.setColor(blue.getColor());
        blueStroke.setStrokeWidth(40);

        greenStroke.setStyle(Paint.Style.STROKE);
        greenStroke.setColor(green.getColor());
        greenStroke.setStrokeWidth(40);

        purpleStroke.setStyle(Paint.Style.STROKE);
        purpleStroke.setColor(purple.getColor());
        purpleStroke.setStrokeWidth(40);

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

        puzzleModePic = BitmapFactory.decodeResource(getResources(), R.drawable.puzzlemode, opts);
        puzzleModeSrc = new Rect(0,0, puzzleModePic.getWidth(), puzzleModePic.getHeight());

        disablePic = BitmapFactory.decodeResource(getResources(), R.drawable.disable, opts);
        disableSrc = new Rect(0,0, disablePic.getWidth(), disablePic.getHeight());

        settingPic = BitmapFactory.decodeResource(getResources(), R.drawable.setting, opts);
        settingCancelPic = BitmapFactory.decodeResource(getResources(), R.drawable.settingcancel, opts);
        settingHowToPic = BitmapFactory.decodeResource(getResources(), R.drawable.settinghowto, opts);
        settingHvSoundPic = BitmapFactory.decodeResource(getResources(), R.drawable.settinghvsound, opts);
        settingNoSoundPic = BitmapFactory.decodeResource(getResources(), R.drawable.settingnosound, opts);
        settingInfoPic = BitmapFactory.decodeResource(getResources(), R.drawable.settinginfo, opts);
        settingLeavePic = BitmapFactory.decodeResource(getResources(), R.drawable.settingleave, opts);
        settingSrc = new Rect(0,0, settingPic.getWidth(), settingPic.getHeight());

        contactUsTitlePic = BitmapFactory.decodeResource(getResources(), R.drawable.contactustitle, opts);
        contactUsTitleSrc = new Rect(0,0, contactUsTitlePic.getWidth(), contactUsTitlePic.getHeight());

        contactUsPic = BitmapFactory.decodeResource(getResources(), R.drawable.contactus, opts);
        contactUsSrc = new Rect(0,0, contactUsPic.getWidth(), contactUsPic.getHeight());

        PModeTitlePic = BitmapFactory.decodeResource(getResources(), R.drawable.practicemodetitle, opts);
        PModeTitleSrc = new Rect(0,0, PModeTitlePic.getWidth(), PModeTitlePic.getHeight());

        BModeTitlePic = BitmapFactory.decodeResource(getResources(), R.drawable.battlemodetitle, opts);
        BModeTitleSrc = new Rect(0,0, BModeTitlePic.getWidth(), BModeTitlePic.getHeight());

        backPic = BitmapFactory.decodeResource(getResources(), R.drawable.back, opts);
        backSrc = new Rect(0,0, backPic.getWidth(), backPic.getHeight());

        howToTitlePic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaytitle, opts);
        howToTitleSrc = new Rect(0,0, howToTitlePic.getWidth(), howToTitlePic.getHeight());

        howTo1Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay1, opts);
        howTo2Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay2, opts);
        howTo3Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay3, opts);
        howTo4Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay4, opts);
        howTo5Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay5, opts);
        howToBM1Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm1, opts);
        howToBM2Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm2, opts);
        howToBM3Pic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm3, opts);
        howToSrc = new Rect(0,0, howTo1Pic.getWidth(), howTo1Pic.getHeight());

        howTo1WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay1word, opts);
        howTo1WordSrc = new Rect(0,0, howTo1WordPic.getWidth(), howTo1WordPic.getHeight());

        howTo2WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay2word, opts);
        howTo2WordSrc = new Rect(0,0, howTo2WordPic.getWidth(), howTo2WordPic.getHeight());

        howTo3WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay3word, opts);
        howTo3WordSrc = new Rect(0,0, howTo3WordPic.getWidth(), howTo3WordPic.getHeight());

        howTo4WordPic1 = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay4word1, opts);
        howTo4WordSrc1 = new Rect(0,0, howTo4WordPic1.getWidth(), howTo4WordPic1.getHeight());

        howTo4WordPic2 = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay4word2, opts);
        howTo4WordSrc2 = new Rect(0,0, howTo4WordPic2.getWidth(), howTo4WordPic2.getHeight());

        howTo5WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplay5word, opts);
        howTo5WordSrc = new Rect(0,0, howTo5WordPic.getWidth(), howTo5WordPic.getHeight());

        howToBM1WordPic1 = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm1word1, opts);
        howToBM1WordSrc1= new Rect(0,0, howToBM1WordPic1.getWidth(), howToBM1WordPic1.getHeight());

        howToBM1WordPic2 = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm1word2, opts);
        howToBM1WordSrc2= new Rect(0,0, howToBM1WordPic2.getWidth(), howToBM1WordPic2.getHeight());

        howToBM2WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm2word, opts);
        howToBM2WordSrc= new Rect(0,0, howToBM2WordPic.getWidth(), howToBM2WordPic.getHeight());

        howToBM3WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm3word, opts);
        howToBM3WordSrc= new Rect(0,0, howToBM3WordPic.getWidth(), howToBM3WordPic.getHeight());

        howToBM4WordPic = BitmapFactory.decodeResource(getResources(), R.drawable.howtoplaybm4word, opts);
        howToBM4WordSrc= new Rect(0,0, howToBM4WordPic.getWidth(), howToBM4WordPic.getHeight());

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
        selectAnsPic= BitmapFactory.decodeResource(getResources(), R.drawable.selectans, opts);
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

        spType3Q3Pic = BitmapFactory.decodeResource(getResources(), R.drawable.sptype3question3, opts);
        spType3Q3Src = new Rect(0,0, spType3Q3Pic.getWidth(), spType3Q3Pic.getHeight());

        spType3Q4Pic = BitmapFactory.decodeResource(getResources(), R.drawable.sptype3question4, opts);
        spType3Q4Src = new Rect(0,0, spType3Q4Pic.getWidth(), spType3Q4Pic.getHeight());

        qSpType3BkgPic = BitmapFactory.decodeResource(getResources(), R.drawable.modelbkg_noword, opts);
        qspType3BkgSrc = new Rect(0,0, qSpType3BkgPic.getWidth(), qSpType3BkgPic.getHeight());

        tipIconPic = BitmapFactory.decodeResource(getResources(), R.drawable.tipsicon, opts);
        tipIconSrc = new Rect(0,0, tipIconPic.getWidth(), tipIconPic.getHeight());

        tipBoxPic = BitmapFactory.decodeResource(getResources(), R.drawable.tipsbox, opts);
        tipBoxSrc = new Rect(0,0, tipBoxPic.getWidth(), tipBoxPic.getHeight());

        tipBoxPic2 = BitmapFactory.decodeResource(getResources(), R.drawable.tipsbox2, opts);
        tipBoxSrc2 = new Rect(0,0, tipBoxPic2.getWidth(), tipBoxPic2.getHeight());

        detectionIconPic = BitmapFactory.decodeResource(getResources(), R.drawable.dectioncheckicon, opts);
        detectionIconSrc = new Rect(0,0, detectionIconPic.getWidth(), detectionIconPic.getHeight());

        detectionBoxPic = BitmapFactory.decodeResource(getResources(), R.drawable.detectioncheckbox, opts);
        detectionBoxSrc = new Rect(0,0, detectionBoxPic.getWidth(), detectionBoxPic.getHeight());

        detectionModelBkgPic = BitmapFactory.decodeResource(getResources(), R.drawable.modelbkg_orange, opts);
        detectionModelBkgSrc = new Rect(0,0, detectionModelBkgPic.getWidth(), detectionModelBkgPic.getHeight());

        puzzlePic = BitmapFactory.decodeResource(getResources(), R.drawable.puzzle, opts);
        puzzleSrc = new Rect(0,0, puzzlePic.getWidth(), puzzlePic.getHeight());

        puzzleTitlePic1 = BitmapFactory.decodeResource(getResources(), R.drawable.youhavecompleted, opts);
        puzzleTitleSrc1 = new Rect(0,0, puzzleTitlePic1.getWidth(), puzzleTitlePic1.getHeight());

        puzzleTitlePic2 = BitmapFactory.decodeResource(getResources(), R.drawable.rounds, opts);
        puzzleTitleSrc2 = new Rect(0,0, puzzleTitlePic2.getWidth(), puzzleTitlePic2.getHeight());

        puzzleTitlePic3 = BitmapFactory.decodeResource(getResources(), R.drawable.tworoundequal, opts);
        puzzleTitleSrc3 = new Rect(0,0, puzzleTitlePic3.getWidth(), puzzleTitlePic3.getHeight());

        redPlayerBtnLPic = BitmapFactory.decodeResource(getResources(), R.drawable.redl, opts);
        redPlayerBtnLSrc = new Rect(0,0, redPlayerBtnLPic.getWidth(), redPlayerBtnLPic.getHeight());

        bluePlayerBtnLPic = BitmapFactory.decodeResource(getResources(), R.drawable.bluel, opts);
        bluePlayerBtnLSrc = new Rect(0,0, bluePlayerBtnLPic.getWidth(), bluePlayerBtnLPic.getHeight());

        greenPlayerBtnLPic = BitmapFactory.decodeResource(getResources(), R.drawable.greenl, opts);
        greenPlayerBtnLSrc = new Rect(0,0, greenPlayerBtnLPic.getWidth(), greenPlayerBtnLPic.getHeight());

        redPlayerBtnSPic = BitmapFactory.decodeResource(getResources(), R.drawable.reds, opts);
        redPlayerBtnSSrc = new Rect(0,0, redPlayerBtnSPic.getWidth(), redPlayerBtnSPic.getHeight());

        bluePlayerBtnSPic = BitmapFactory.decodeResource(getResources(), R.drawable.blues, opts);
        bluePlayerBtnSSrc = new Rect(0,0, bluePlayerBtnSPic.getWidth(), bluePlayerBtnSPic.getHeight());

        greenPlayerBtnSPic = BitmapFactory.decodeResource(getResources(), R.drawable.greens, opts);
        greenPlayerBtnSSrc = new Rect(0,0, greenPlayerBtnSPic.getWidth(), greenPlayerBtnSPic.getHeight());

        purplePlayerBtnSPic = BitmapFactory.decodeResource(getResources(), R.drawable.purples, opts);
        purplePlayerBtnSSrc = new Rect(0,0, purplePlayerBtnSPic.getWidth(), purplePlayerBtnSPic.getHeight());

        timesUpPic = BitmapFactory.decodeResource(getResources(), R.drawable.timesup, opts);
        timesUpSrc = new Rect(0,0, timesUpPic.getWidth(), timesUpPic.getHeight());

        redWinsPic = BitmapFactory.decodeResource(getResources(), R.drawable.redwins, opts);
        blueWinsPic = BitmapFactory.decodeResource(getResources(), R.drawable.bluewins, opts);
        greenWinsPic = BitmapFactory.decodeResource(getResources(), R.drawable.greenwins, opts);
        purpleWinsPic = BitmapFactory.decodeResource(getResources(), R.drawable.purplewins, opts);
        itsADrawPic = BitmapFactory.decodeResource(getResources(), R.drawable.itsadraw, opts);
        winsSrc = new Rect(0,0, redWinsPic.getWidth(), redWinsPic.getHeight());

        playerIconPic = BitmapFactory.decodeResource(getResources(), R.drawable.playericon, opts);
        playerIconSrc = new Rect(0,0, playerIconPic.getWidth(), playerIconPic.getHeight());

        profilePic = BitmapFactory.decodeResource(getResources(), R.drawable.profile, opts);
        profileSrc = new Rect(0,0, profilePic.getWidth(), profilePic.getHeight());

        abilityPic0 = BitmapFactory.decodeResource(getResources(), R.drawable.dtoortho, opts);
        abilitySrc0 = new Rect(0,0, abilityPic0.getWidth(), abilityPic0.getHeight());

        abilityPic1 = BitmapFactory.decodeResource(getResources(), R.drawable.orthoto3d, opts);
        abilitySrc1 = new Rect(0,0, abilityPic1.getWidth(), abilityPic1.getHeight());

        abilityPic2 = BitmapFactory.decodeResource(getResources(), R.drawable.pvor, opts);
        abilitySrc2 = new Rect(0,0, abilityPic2.getWidth(), abilityPic2.getHeight());

        abilityPic3 = BitmapFactory.decodeResource(getResources(), R.drawable.mentalrotation, opts);
        abilitySrc3 = new Rect(0,0, abilityPic3.getWidth(), abilityPic3.getHeight());

        Pic = BitmapFactory.decodeResource(getResources(), R.drawable.back, opts);
        Src = new Rect(0,0, Pic.getWidth(), Pic.getHeight());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasW = w;
        canvasH = h;

        int space = w;
        int gap = w / 100;

        int width = w / 4 + w / 20;
        int height = (battleModePic.getHeight() * width) / battleModePic.getWidth();
        int left = w / 2 - width / 2;
        int top = h / 2 - height / 2;
        int right = left + width;
        int bottom = top + height;
        battleModePos = new Rect(left, top, right, bottom);

        width = w / 4 + w / 20;
        height = (battleModeNumPic.getHeight() * width) / battleModeNumPic.getWidth();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        battleModeNumPos = new Rect(left, top, right, bottom);

        width = w / 4 + w / 20;
        height = (practiceModePic.getHeight() * width) / practiceModePic.getWidth();
        left = battleModePos.left - gap - width;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        practiceModePos = new Rect(left, top, right, bottom);

        width = w / 4 + w / 20;
        height = (puzzleModePic.getHeight() * width) / puzzleModePic.getWidth();
        left = battleModePos.right + gap;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        puzzleModePos = new Rect(left, top, right, bottom);

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
        settingPos0 = new Rect(left, top, right, bottom);

        width = w / 12;
        height = (settingPic.getHeight() * width) / settingPic.getWidth();
        left = settingPos0.left - 100 - width;
        top = settingPos0.top;
        right = left + width;
        bottom = top + height;
        settingPos1 = new Rect(left, top, right, bottom);

        width = w / 12;
        height = (settingPic.getHeight() * width) / settingPic.getWidth();
        left = settingPos0.left;
        top = settingPos0.bottom + 100;
        right = left + width;
        bottom = top + height;
        settingPos3 = new Rect(left, top, right, bottom);

        width = w / 12;
        height = (settingPic.getHeight() * width) / settingPic.getWidth();
        left = settingPos1.right - 70;
        top = settingPos3.top - width + 70;
        right = left + width;
        bottom = top + height;
        settingPos2 = new Rect(left, top, right, bottom);

        width = w / 13;
        height = (backPic.getHeight() * width) / backPic.getWidth();
        left = 30;
        top = (settingPos0.top + settingPos0.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        backPos = new Rect(left, top, right, bottom);

        width = w / 3 + w / 30;
        height = (howToTitlePic.getHeight() * width) / howToTitlePic.getWidth();
        left = w / 2 - width / 2;
        top = (settingPos0.top + settingPos0.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        howToTitlePos = new Rect(left, top, right, bottom);

        height = h / 2;
        width = (howTo1Pic.getWidth() * height) / howTo1Pic.getHeight();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 - h / 20;
        right = left + width;
        bottom = top + height;
        howToPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo1WordPic.getWidth() * height) / howTo1WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo1WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo2WordPic.getWidth() * height) / howTo2WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo2WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo3WordPic.getWidth() * height) / howTo3WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo3WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo4WordPic1.getWidth() * height) / howTo4WordPic1.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 40;
        right = left + width;
        bottom = top + height;
        howTo4WordPos1 = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo4WordPic2.getWidth() * height) / howTo4WordPic2.getHeight();
        left = w / 2 - width / 2;
        top = howTo4WordPos1.bottom + h / 60;
        right = left + width;
        bottom = top + height;
        howTo4WordPos2 = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howTo5WordPic.getWidth() * height) / howTo5WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howTo5WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howToBM1WordPic1.getWidth() * height) / howToBM1WordPic1.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 40;
        right = left + width;
        bottom = top + height;
        howToBM1WordPos1 = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howToBM1WordPic2.getWidth() * height) / howToBM1WordPic2.getHeight();
        left = w / 2 - width / 2;
        top = howToBM1WordPos1.bottom + h / 60;
        right = left + width;
        bottom = top + height;
        howToBM1WordPos2 = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howToBM2WordPic.getWidth() * height) / howToBM2WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howToBM2WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howToBM3WordPic.getWidth() * height) / howToBM3WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howToBM3WordPos = new Rect(left, top, right, bottom);

        height = h / 23;
        width = (howToBM4WordPic.getWidth() * height) / howToBM4WordPic.getHeight();
        left = w / 2 - width / 2;
        top = howToPos.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        howToBM4WordPos = new Rect(left, top, right, bottom);

        width = w / 6 + w / 40;
        height = (nextPic.getHeight() * width) / nextPic.getWidth();
        left = settingPos0.left - width + 20;
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

        gap = w / 50;

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = w / 2 - width / 2;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot3Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot3Pos.left - gap - width;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot2Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot2Pos.left - gap - width;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot1Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot3Pos.right + gap;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot4Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot4Pos.right + gap;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot5Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = w / 2 - gap / 2 - width;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot7Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot7Pos.left - gap - width;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot6Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = w / 2 + gap / 2;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot8Pos = new Rect(left, top, right, bottom);

        width = w / 60;
        height = (dotWhitePic.getHeight() * width) / dotWhitePic.getWidth();
        left = dot8Pos.right + gap;
        top = (nextPos.top + nextPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        dot9Pos = new Rect(left, top, right, bottom);

        width = w / 2 + w / 4;
        height = (qTitlePic[0].getHeight() * width) / qTitlePic[0].getWidth();
        left = w / 2 - width / 2;
        top = settingPos0.top + settingPos0.height() / 2;
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

        x0 = answerPos.left + answerPos.width() / 2;
        y0 = answerPos.top;

        gap = w / 15;

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

        space = qTitlePos.right - spType4Pos_base.right;
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
        width = (spType3Q1Pic.getWidth() * height) / spType3Q1Pic.getHeight();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Q1Pos = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (666 * width) / 666;
        left = spType3Q1Pos.left - w / 40 - width;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Pos_base0 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (666 * width) / 666;
        left = spType3Q1Pos.right + w / 40;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Pos_base1 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (666 * width) / 666;
        left = w / 2 - width / 2;
        top = spType3Q1Pos.bottom + w / 35;
        right = left + width;
        bottom = top + height;
        spType3Pos_quest = new Rect(left, top, right, bottom);

        height = h / 22;
        width = (spType3Q0Pic.getWidth() * height) / spType3Q0Pic.getHeight();
        left = spType3Pos_base0.left - w / 30 - width;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Q0Pos = new Rect(left, top, right, bottom);

        height = h / 22;
        width = (spType3Q2Pic.getWidth() * height) / spType3Q2Pic.getHeight();
        left = spType3Pos_base1.right + w / 50;
        top = h / 2 - height / 2 - w / 13;
        right = left + width;
        bottom = top + height;
        spType3Q2Pos = new Rect(left, top, right, bottom);

        height = h / 22;
        width = (spType3Q3Pic.getWidth() * height) / spType3Q3Pic.getHeight();
        left = qTitlePos.left + ((spType3Pos_quest.left - qTitlePos.left) / 2) - width / 2;
        left = spType3Pos_quest.left - w / 40 - width;
        top = (spType3Pos_quest.top + spType3Pos_quest.height() / 2) - height / 2 + h / 30;
        right = left + width;
        bottom = top + height;
        spType3Q3Pos = new Rect(left, top, right, bottom);

        height = h / 22;
        width = (spType3Q4Pic.getWidth() * height) / spType3Q4Pic.getHeight();
        left = spType3Pos_quest.right + w / 40;
        top = (spType3Pos_quest.top + spType3Pos_quest.height() / 2) - height / 2 + h / 30;
        right = left + width;
        bottom = top + height;
        spType3Q4Pos = new Rect(left, top, right, bottom);

        width = w / 4;
        height = (tipBoxPic.getHeight() * width) / tipBoxPic.getWidth();
        left = 50;
        top = h - height - 30;
        right = left + width;
        bottom = top + height;
        tipBoxPos = new Rect(left, top, right, bottom);

        width = w / 4;
        height = (tipBoxPic2.getHeight() * width) / tipBoxPic2.getWidth();
        left = 50;
        top = h - height - 30;
        right = left + width;
        bottom = top + height;
        tipBoxPos2 = new Rect(left, top, right, bottom);

        width = w / 15;
        height = (tipIconPic.getHeight() * width) / tipIconPic.getWidth();
        left = 50;
        top = h - height - 50;
        right = left + width;
        bottom = top + height;
        tipIconPos = new Rect(left, top, right, bottom);

        width = w / 15;
        height = (detectionIconPic.getHeight() * width) / detectionIconPic.getWidth();
        left = w - width - 50;
        top = h - height - 50;
        right = left + width;
        bottom = top + height;
        detectionIconPos = new Rect(left, top, right, bottom);

        width = w / 4;
        height = (detectionBoxPic.getHeight() * width) / detectionBoxPic.getWidth();
        left = w - width - 50;
        top = h - height - 30;
        right = left + width;
        bottom = top + height;
        detectionBoxPos = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (850 * width) / 850;
        left = (detectionBoxPos.left + detectionBoxPos.width() / 2) - width / 2;
        top = detectionBoxPos.bottom - height - h / 20;
        right = left + width;
        bottom = top + height;
        detectionModelBkgPos = new Rect(left, top, right, bottom);

        width = w / 2;
        height = (puzzleTitlePic1.getHeight() * width) / puzzleTitlePic1.getWidth();
        left = w / 2 - width / 2;
        top = (settingPos0.top + settingPos0.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        puzzleTitlePos1 = new Rect(left, top, right, bottom);

        height = puzzleTitlePos1.height();
        width = (puzzleTitlePic2.getWidth() * height) / puzzleTitlePic2.getHeight();
        left = w / 2 - width / 2;
        top = puzzleTitlePos1.bottom + h / 10;
        right = left + width;
        bottom = top + height;
        puzzleTitlePos2 = new Rect(left, top, right, bottom);

        width = w / 2 + w / 30;
        height = (puzzlePic.getHeight() * width) / puzzlePic.getWidth();
        left = w / 2 - width / 2;
        top = puzzleTitlePos2.bottom + h / 20;
        right = left + width;
        bottom = top + height;
        puzzlePos = new Rect(left, top, right, bottom);

        width = w / 4;
        height = (puzzleTitlePic3.getHeight() * width) / puzzleTitlePic3.getWidth();
        left = puzzlePos.right - width;
        top = puzzlePos.bottom + h / 100;
        right = left + width;
        bottom = top + height;
        puzzleTitlePos3 = new Rect(left, top, right, bottom);

        puzzlePieceW = puzzlePos.width() / 4;
        puzzlePieceH = puzzlePos.height() / 2;

        for(int i = 0; i < PUZZLEPIECENUM / 2; i++){
            width = puzzlePieceW;
            height = puzzlePieceH;
            left = puzzlePos.left + puzzlePieceW * i;
            top = puzzlePos.top;
            right = left + width;
            bottom = top + height;
            puzzleCoverPos[i] = new Rect(left, top, right, bottom);
        }

        for(int i = PUZZLEPIECENUM / 2, j = 0; i < PUZZLEPIECENUM && j < PUZZLEPIECENUM / 2; i++, j++){
            width = puzzlePieceW;
            height = puzzlePieceH;
            left = puzzlePos.left + puzzlePieceW * j;
            top = puzzlePos.top + puzzlePieceH;
            right = left + width;
            bottom = top + height;
            puzzleCoverPos[i] = new Rect(left, top, right, bottom);
        }

        width = w / 11 + w / 4224;
        height = (redPlayerBtnLPic.getHeight() * width) / redPlayerBtnLPic.getWidth();
        left = 0;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        redPlayerBtnLPos = new Rect(left, top, right, bottom);

        width = w / 11 + w / 4224;
        height = (bluePlayerBtnLPic.getHeight() * width) / bluePlayerBtnLPic.getWidth();
        left = w - width;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        bluePlayerBtnLPos = new Rect(left, top, right, bottom);

        width = bluePlayerBtnLPos.height();
        height = (greenPlayerBtnLPic.getHeight() * width) / greenPlayerBtnLPic.getWidth();
        left = w / 2 - width / 2;
        //left = 0;
        top = h - height;
        right = left + width;
        bottom = top + height;
        greenPlayerBtnLPos = new Rect(left, top, right, bottom);

        width = w / 11 + w / 4224;
        height = (redPlayerBtnSPic.getHeight() * width) / redPlayerBtnSPic.getWidth();
        left = 0;
        top = qTitlePos.bottom;
        right = left + width;
        bottom = top + height;
        redPlayerBtnSPos = new Rect(left, top, right, bottom);

        width = w / 11 + w / 4224;
        height = (bluePlayerBtnSPic.getHeight() * width) / bluePlayerBtnSPic.getWidth();
        left = 0;
        top = h - height;
        right = left + width;
        bottom = top + height;
        bluePlayerBtnSPos = new Rect(left, top, right, bottom);

        width = w / 11 + w / 4224;
        height = (greenPlayerBtnSPic.getHeight() * width) / greenPlayerBtnSPic.getWidth();
        left = w - width;
        top = qTitlePos.bottom;
        right = left + width;
        bottom = top + height;
        greenPlayerBtnSPos = new Rect(left, top, right, bottom);

        width = w / 11 + w / 4224;
        height = (purplePlayerBtnSPic.getHeight() * width) / purplePlayerBtnSPic.getWidth();
        left = w - width;
        top = h - height;
        right = left + width;
        bottom = top + height;
        purplePlayerBtnSPos = new Rect(left, top, right, bottom);

        width = w / 5;
        height = (timesUpPic.getHeight() * width) / timesUpPic.getWidth();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        timesUpPos = new Rect(left, top, right, bottom);

        width = w / 2;
        height = (redWinsPic.getHeight() * width) / redWinsPic.getWidth();
        left = w / 2 - width / 2;
        top = (backPos.top + backPos.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        winsPos = new Rect(left, top, right, bottom);

        gap = w / 20;

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = w / 2 - gap / 2 - width;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos1 = new Rect(left, top, right, bottom);

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = playerIconPos1.left - gap - width;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos0 = new Rect(left, top, right, bottom);
        playerIconH = height;

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = w / 2 + gap / 2;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos2 = new Rect(left, top, right, bottom);

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = playerIconPos2.right + gap;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos3 = new Rect(left, top, right, bottom);

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = w / 2 - width / 2;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos5 = new Rect(left, top, right, bottom);

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = playerIconPos5.left - gap - width;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos4 = new Rect(left, top, right, bottom);

        width = w / 20;
        height = (playerIconPic.getHeight() * width) / playerIconPic.getWidth();
        left = playerIconPos5.right + gap;
        top = h / 2;
        right = left + width;
        bottom = top + height;
        playerIconPos6 = new Rect(left, top, right, bottom);

        width = w / 15;
        height = (PModeTitlePic.getHeight() * width) / PModeTitlePic.getWidth();
        left = 0;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        PModeTitlePos = new Rect(left, top, right, bottom);

        width = w / 15;
        height = (BModeTitlePic.getHeight() * width) / BModeTitlePic.getWidth();
        left = w - width;
        top = h / 2 - height / 2;
        right = left + width;
        bottom = top + height;
        BModeTitlePos = new Rect(left, top, right, bottom);

        height = howToTitlePos.height();
        width = (contactUsTitlePic.getWidth() * height) / contactUsTitlePic.getHeight();
        left = w / 2 - width / 2;
        top = (settingPos0.top + settingPos0.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        contactUsTitlePos = new Rect(left, top, right, bottom);

        width = w / 2 + w / 3;
        height = (contactUsPic.getHeight() * width) / contactUsPic.getWidth();
        left = w / 2 - width / 2;
        top = h / 2 - height / 2 + w / 30;
        right = left + width;
        bottom = top + height;
        contactUsPos = new Rect(left, top, right, bottom);

        height = howToTitlePos.height();
        width = (profilePic.getWidth() * height) / profilePic.getHeight();
        left = w / 2 - width / 2;
        top = (settingPos0.top + settingPos0.height() / 2) - height / 2;
        right = left + width;
        bottom = top + height;
        profilePos = new Rect(left, top, right, bottom);

        gap = canvasW / 40;
        lineLong = canvasH / 4;
        lineMidX = canvasW / 2;
        lineMidY = canvasH / 2 + canvasH / 20;

        width = w / 7;
        height = (abilityPic0.getHeight() * width) / abilityPic0.getWidth();
        left = lineMidX - lineLong - gap - width;
        top = lineMidY - height / 2;
        right = left + width;
        bottom = top + height;
        abilityPos0 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (abilityPic1.getHeight() * width) / abilityPic1.getWidth();
        left = lineMidX + lineLong + gap;
        top = lineMidY - height / 2;
        right = left + width;
        bottom = top + height;
        abilityPos1 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (abilityPic2.getHeight() * width) / abilityPic2.getWidth();
        left = lineMidX - width / 2;
        top = lineMidY + lineLong + gap;
        right = left + width;
        bottom = top + height;
        abilityPos2 = new Rect(left, top, right, bottom);

        width = w / 7;
        height = (abilityPic3.getHeight() * width) / abilityPic3.getWidth();
        left = lineMidX - width / 2;
        top = lineMidY - lineLong - gap - height;
        right = left + width;
        bottom = top + height;
        abilityPos3 = new Rect(left, top, right, bottom);

    }

    //https://stackoverflow.com/questions/4909367/how-to-align-text-vertically
    private void drawTextCentred(Canvas canvas, String text, float cx, float cy, Paint paint){
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
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
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
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
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawBattleNumPage(canvas);
                break;
            case BATTLEGAMEPAGE:
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawBattleGamePage(canvas);
                break;
            case HOWTOPAGEPM1:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToPMPage1(canvas);
                break;
            case HOWTOPAGEPM2:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToPMPage2(canvas);
                break;
            case HOWTOPAGEPM3:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToPMPage3(canvas);
                break;
            case HOWTOPAGEPM4:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToPMPage4(canvas);
                break;
            case HOWTOPAGEPM5:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToPMPage5(canvas);
                break;
            case HOWTOPAGEBM1:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToBMPage1(canvas);
                break;
            case HOWTOPAGEBM2:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToBMPage2(canvas);
                break;
            case HOWTOPAGEBM3:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToBMPage3(canvas);
                break;
            case HOWTOPAGEBM4:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToBMPage4(canvas);
                break;
            case PUZZLEPAGE:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawPuzzlePage(canvas);
                break;
            case HOWTOPAGESETTING:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawHowToSettingPage(canvas);
                break;
            case CONTACTUSPAGE:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                OpenGLRenderer_Tips.setCanDraw(false);
                OpenGLRenderer_DetectionCheck.setCanDraw(false);
                drawContactUsPage(canvas);
                break;

        }

        if(debugVisible){
            drawDebug(canvas);
        }

        forArduino();

        drawSettingPage(canvas);

        puzzle.update();
    }

    private void drawSettingPage(Canvas canvas){

        if(!settingIsShown){
            canvas.drawBitmap(settingPic, settingSrc, settingPos0, null);
        }
        if(settingIsShown){
            Paint whitebkg = new Paint();
            whitebkg.setColor(Color.argb(100, 255, 255, 255));
            canvas.drawRect(0, 0, canvasW, canvasH, whitebkg);
            canvas.drawBitmap(settingCancelPic, settingSrc, settingPos0, null);
            if(musicIsPlay){
                canvas.drawBitmap(settingNoSoundPic, settingSrc, settingPos1, null);
            }
            else if(!musicIsPlay){
                canvas.drawBitmap(settingHvSoundPic, settingSrc, settingPos1, null);
            }
            if(!isInGame){
                canvas.drawBitmap(settingHowToPic, settingSrc, settingPos2, null);
            }
            else if(isInGame){
                canvas.drawBitmap(settingLeavePic, settingSrc, settingPos2, null);
            }
            canvas.drawBitmap(settingInfoPic, settingSrc, settingPos3, null);
        }
    }

    private void drawMenuPage(Canvas canvas){
        canvas.drawBitmap(practiceModePic, practiceModeSrc, practiceModePos, null);
        canvas.drawBitmap(battleModePic, battleModeSrc, battleModePos, null);
        canvas.drawBitmap(puzzleModePic, puzzleModeSrc, puzzleModePos, null);
        if(puzzle.isShowNoti()){
            canvas.drawCircle(puzzleModePos.right - puzzleModePos.width() / 10, puzzleModePos.top + puzzleModePos.width() / 10, 25, red);
        }
    }

    private void drawBattleNumPage(Canvas canvas){
        canvas.drawBitmap(practiceModePic, practiceModeSrc, practiceModePos, null);
        canvas.drawBitmap(disablePic, disableSrc, practiceModePos, null);
        canvas.drawBitmap(battleModeNumPic, battleModeNumSrc, battleModeNumPos, null);
        canvas.drawBitmap(puzzleModePic, puzzleModeSrc, puzzleModePos, null);
        canvas.drawBitmap(disablePic, disableSrc, puzzleModePos, null);
        if(puzzle.isShowNoti()){
            canvas.drawCircle(puzzleModePos.right - puzzleModePos.width() / 10, puzzleModePos.top + puzzleModePos.width() / 10, 25, red);
        }

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

    private void drawHowToPMPage1(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo1Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howTo1WordPic, howTo1WordSrc, howTo1WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot1Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot3Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
    }

    private void drawHowToPMPage2(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo2Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howTo2WordPic, howTo2WordSrc, howTo2WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot2Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot3Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
    }

    private void drawHowToPMPage3(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo3Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howTo3WordPic, howTo3WordSrc, howTo3WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot3Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
    }

    private void drawHowToPMPage4(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo4Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howTo4WordPic1, howTo4WordSrc1, howTo4WordPos1, null);
        canvas.drawBitmap(howTo4WordPic2, howTo4WordSrc2, howTo4WordPos2, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
        canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot3Pos, null);
        canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot4Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
    }

    private void drawHowToPMPage5(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo5Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howTo5WordPic, howTo5WordSrc, howTo5WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
        canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot3Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
        canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot5Pos, null);
    }

    private void drawHowToBMPage1(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howToBM1Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howToBM1WordPic1, howToBM1WordSrc1, howToBM1WordPos1, null);
        canvas.drawBitmap(howToBM1WordPic2, howToBM1WordSrc2, howToBM1WordPos2, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot6Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot7Pos, null);
        canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot8Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot9Pos, null);
    }

    private void drawHowToBMPage2(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howToBM2Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howToBM2WordPic, howToBM2WordSrc, howToBM2WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot6Pos, null);
        canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot7Pos, null);
        canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot8Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot9Pos, null);
    }

    private void drawHowToBMPage3(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howToBM3Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howToBM3WordPic, howToBM3WordSrc, howToBM3WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot6Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot7Pos, null);
        canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot8Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot9Pos, null);
    }

    private void drawHowToBMPage4(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(howTo5Pic, howToSrc, howToPos, null);
        canvas.drawBitmap(howToBM4WordPic, howToBM4WordSrc, howToBM4WordPos, null);

        canvas.drawBitmap(nextPic, nextSrc, nextPos, null);
        canvas.drawBitmap(skipPic, skipSrc, skipPos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot6Pos, null);
        canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot7Pos, null);
        canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot8Pos, null);
        canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot9Pos, null);
    }

    private void drawHowToSettingPage(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(howToTitlePic, howToTitleSrc, howToTitlePos, null);

        canvas.drawBitmap(BModeTitlePic, BModeTitleSrc, BModeTitlePos, null);
        canvas.drawBitmap(PModeTitlePic, PModeTitleSrc, PModeTitlePos, null);

        if(currHowToSetting >= PM1 && currHowToSetting <= PM5){
            Point a = new Point(PModeTitlePos.right + 30, PModeTitlePos.top + PModeTitlePos.height() / 2);
            Point b = new Point(a.x + 30, a.y - 20);
            Point c = new Point(a.x + 30, a.y + 20);
            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();
            canvas.drawPath(path, darkOrange);
        }
        else if(currHowToSetting >= BM1 && currHowToSetting <= BM4){
            Point a = new Point(BModeTitlePos.left - 30, BModeTitlePos.top + BModeTitlePos.height() / 2);
            Point b = new Point(a.x - 30, a.y - 20);
            Point c = new Point(a.x - 30, a.y + 20);
            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();
            canvas.drawPath(path, darkOrange);
        }

        switch (currHowToSetting){
            case PM1:
                canvas.drawBitmap(howTo1Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howTo1WordPic, howTo1WordSrc, howTo1WordPos, null);
                canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot1Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot3Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
                break;
            case PM2:
                canvas.drawBitmap(howTo2Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howTo2WordPic, howTo2WordSrc, howTo2WordPos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
                canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot2Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot3Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
                break;
            case PM3:
                canvas.drawBitmap(howTo3Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howTo3WordPic, howTo3WordSrc, howTo3WordPos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
                canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot3Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
                break;
            case PM4:
                canvas.drawBitmap(howTo4Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howTo4WordPic1, howTo4WordSrc1, howTo4WordPos1, null);
                canvas.drawBitmap(howTo4WordPic2, howTo4WordSrc2, howTo4WordPos2, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
                canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot3Pos, null);
                canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot4Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot5Pos, null);
                break;
            case PM5:
                canvas.drawBitmap(howTo5Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howTo5WordPic, howTo5WordSrc, howTo5WordPos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot1Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot2Pos, null);
                canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot3Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot4Pos, null);
                canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot5Pos, null);
                break;
            case BM1:
                canvas.drawBitmap(howToBM1Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howToBM1WordPic1, howToBM1WordSrc1, howToBM1WordPos1, null);
                canvas.drawBitmap(howToBM1WordPic2, howToBM1WordSrc2, howToBM1WordPos2, null);
                canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot6Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot7Pos, null);
                canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot8Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot9Pos, null);
                break;
            case BM2:
                canvas.drawBitmap(howToBM2Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howToBM2WordPic, howToBM2WordSrc, howToBM2WordPos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot6Pos, null);
                canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot7Pos, null);
                canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot8Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot9Pos, null);
                break;
            case BM3:
                canvas.drawBitmap(howToBM3Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howToBM3WordPic, howToBM3WordSrc, howToBM3WordPos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot6Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot7Pos, null);
                canvas.drawBitmap(dotOrangePic, dotOrangeSrc, dot8Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot9Pos, null);
                break;
            case BM4:
                canvas.drawBitmap(howTo5Pic, howToSrc, howToPos, null);
                canvas.drawBitmap(howToBM4WordPic, howToBM4WordSrc, howToBM4WordPos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot6Pos, null);
                canvas.drawBitmap(dotWhitePic, dotWhiteSrc, dot7Pos, null);
                canvas.drawBitmap(dotWhitePic, dotOrangeSrc, dot8Pos, null);
                canvas.drawBitmap(dotOrangePic, dotWhiteSrc, dot9Pos, null);
                break;
        }

    }

    private void drawPracticeGamePage(Canvas canvas){

        if(!gameManagerPracticeMode.getIsResultPage()){
            //question title
            canvas.drawBitmap(qTitlePic[gameManagerPracticeMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);

            //timer
            drawTimerPracticeMode(canvas);

            switch (gameManagerPracticeMode.getCurrQuestMode()){
                case DRAWFRONTVIEW:
                    if(!settingIsShown){
                        OpenGLRenderer_3DModel.setCanDraw(true);
                    }
                    else if(settingIsShown){
                        OpenGLRenderer_3DModel.setCanDraw(false);
                    }
                    OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                    OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                    OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                    OpenGLRenderer_SPType4_Base.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                    canvas.drawBitmap(formFrontViewPic, qTitleSrc, qTitlePos, null);
                    drawModelPracticeMode(canvas);
                    break;
                case DRAWSIDEVIEW:
                    if(!settingIsShown){
                        OpenGLRenderer_3DModel.setCanDraw(true);
                    }
                    else if(settingIsShown){
                        OpenGLRenderer_3DModel.setCanDraw(false);
                    }
                    OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                    OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                    OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                    OpenGLRenderer_SPType4_Base.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                    canvas.drawBitmap(formSideViewPic, qTitleSrc, qTitlePos, null);
                    drawModelPracticeMode(canvas);
                    break;
                case DRAWTOPVIEW:
                    if(!settingIsShown){
                        OpenGLRenderer_3DModel.setCanDraw(true);
                    }
                    else if(settingIsShown){
                        OpenGLRenderer_3DModel.setCanDraw(false);
                    }
                    OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                    OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                    OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                    OpenGLRenderer_SPType4_Base.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                    canvas.drawBitmap(formTopViewPic, qTitleSrc, qTitlePos, null);
                    drawModelPracticeMode(canvas);
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
                    drawViewsPracticeMode(canvas);
                    drawDetection(canvas);
                    break;
                case SPTYPE3:
                    OpenGLRenderer_3DModel.setCanDraw(false);
                    if(!settingIsShown){
                        OpenGLRenderer_SPType3_Base0.setCanDraw(true);
                        OpenGLRenderer_SPType3_Base1.setCanDraw(true);
                        OpenGLRenderer_SPType3_Quest.setCanDraw(true);
                    }
                    else if(settingIsShown){
                        OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                        OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                        OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                    }
                    OpenGLRenderer_SPType4_Base.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                    canvas.drawBitmap(formAnsPic, qTitleSrc, qTitlePos, null);
                    drawSpType3(canvas);
                    drawDetection(canvas);
                    break;
                case SPTYPE4:
                    OpenGLRenderer_3DModel.setCanDraw(false);
                    OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                    OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                    OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                    if(!settingIsShown){
                        OpenGLRenderer_SPType4_Base.setCanDraw(true);
                        OpenGLRenderer_SPType4_Choice0.setCanDraw(true);
                        OpenGLRenderer_SPType4_Choice1.setCanDraw(true);
                        OpenGLRenderer_SPType4_Choice2.setCanDraw(true);
                        OpenGLRenderer_SPType4_Choice3.setCanDraw(true);
                    }
                    else if(settingIsShown){
                        OpenGLRenderer_SPType4_Base.setCanDraw(false);
                        OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                        OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                        OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                        OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                    }
                    canvas.drawBitmap(selectAnsPic, qTitleSrc, qTitlePos, null);
                    drawSpType4PracticeMode(canvas);
                    break;

            }

            //answer btn
            canvas.drawBitmap(answerPic, answerSrc, answerPos, null);

            //tip icon
            drawTip(canvas);

            calTimeUsed();
        }
        else if(gameManagerPracticeMode.getIsResultPage()){
            drawPMResultPage(canvas);
        }

        //restart the game
        PMrestart();
    }

    private void calTimeUsed(){
        switch (gameManagerPracticeMode.getCurrQuestNum()){
            case 0:
                GameManager_PracticeMode.setTimeUsed(0, gameManagerPracticeMode.getTimeUsed(0) + 1);
                break;
            case 1:
                GameManager_PracticeMode.setTimeUsed(1, gameManagerPracticeMode.getTimeUsed(1) + 1);
                break;
            case 2:
                GameManager_PracticeMode.setTimeUsed(2, gameManagerPracticeMode.getTimeUsed(2) + 1);
                break;
            case 3:
                GameManager_PracticeMode.setTimeUsed(3, gameManagerPracticeMode.getTimeUsed(3) + 1);
                break;
            case 4:
                GameManager_PracticeMode.setTimeUsed(4, gameManagerPracticeMode.getTimeUsed(4) + 1);
                break;
            case 5:
                GameManager_PracticeMode.setTimeUsed(5, gameManagerPracticeMode.getTimeUsed(5) + 1);
                break;
        }
    }

    private void drawPMResultPage(Canvas canvas){
        OpenGLRenderer_3DModel.setCanDraw(false);
        OpenGLRenderer_SPType3_Base0.setCanDraw(false);
        OpenGLRenderer_SPType3_Base1.setCanDraw(false);
        OpenGLRenderer_SPType3_Quest.setCanDraw(false);
        OpenGLRenderer_SPType4_Base.setCanDraw(false);
        OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
        OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
        OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
        OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
        OpenGLRenderer_Tips.setCanDraw(false);
        OpenGLRenderer_DetectionCheck.setCanDraw(false);

        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(profilePic, profileSrc, profilePos, null);

        canvas.drawLine(lineMidX, lineMidY - lineLong, lineMidX, lineMidY + lineLong, whiteStrokeThin);
        canvas.drawLine(lineMidX - lineLong, lineMidY, lineMidX + lineLong, lineMidY, whiteStrokeThin);

        canvas.drawBitmap(abilityPic0, abilitySrc0, abilityPos0, null);
        canvas.drawBitmap(abilityPic1, abilitySrc1, abilityPos1, null);
        canvas.drawBitmap(abilityPic2, abilitySrc2, abilityPos2, null);
        canvas.drawBitmap(abilityPic3, abilitySrc3, abilityPos3, null);

        int abilityPt0 = (int)remap(gameManagerPracticeMode.getAbilityScore0(), 0, 100, 0, lineLong);
        int abilityPt1 = (int)remap(gameManagerPracticeMode.getAbilityScore1(), 0, 100, 0, lineLong);
        int abilityPt2 = (int)remap(gameManagerPracticeMode.getAbilityScore2(), 0, 100, 0, lineLong);
        int abilityPt3 = (int)remap(gameManagerPracticeMode.getAbilityScore3(), 0, 100, 0, lineLong);

        if(abilityPt0 == 0 && abilityPt1 == 0 && abilityPt2 == 0 && abilityPt3 == 0){
            canvas.drawCircle(lineMidX, lineMidY, 10, darkOrange);
        }
        else{
            Point a = new Point(lineMidX, lineMidY - abilityPt0);
            Point b = new Point(lineMidX - abilityPt1, lineMidY);
            Point c = new Point(lineMidX, lineMidY + abilityPt2);
            Point d = new Point(lineMidX + abilityPt3, lineMidY);
            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(d.x, d.y);
            path.close();
            canvas.drawPath(path, darkOrangeStroke);
        }

    }

    private void PMrestart(){
        if(gameManagerPracticeMode.getRestart()){
            //save data
            if(gameManagerPracticeMode.isCanGetPuzzle()){
                Puzzle.setPlayedRound(puzzle.getPlayedRound() + 1);
                editor = sharedPreferences.edit();
                editor.putInt(String.valueOf(R.integer.playedRound), puzzle.getPlayedRound());
                editor.commit();
                GameManager_PracticeMode.setCanGetPuzzleFalse();
            }

            tipIsShown = false;
            spType4PlayerChoice = 0;
            resetDrawView();
            resetTimer();

            OpenGLRenderer_Tips.setCanDraw(false);
            OpenGLRenderer_DetectionCheck.setCanDraw(false);

            isInGame = false;
            GameManager_PracticeMode.setIsResultPage(false);

            currPage = MENUPAGE;
            gameManagerPracticeMode.setRestartFalse();
        }
    }

    private void drawTimerPracticeMode(Canvas canvas){
        if(!timeSetDone){
            gameManagerPracticeMode.timer30sStart();
            timeSetDone = true;
        }

        canvas.drawText(String.valueOf((int)gameManagerPracticeMode.getTimeLeft30s() / 1000), timeTxtX, timeTxtY, font);
        timeRemapWidth = (int)remap(gameManagerPracticeMode.getTimeLeft30s(), gameManagerPracticeMode.getTotalTime30s(), -1, timeFullWidth, -1);

        //--more smooth timer. but will faster than word--
//        if(timeCurrWidth > timeRemapWidth){
//            timeCurrWidth--;
//        }
//        else if(timeCurrWidth == timeRemapWidth){
//            timeCurrWidth = timeRemapWidth;
//        }
//        if(timeCirX2 <= timeRectPos.left){
//            timeCirX1 = timeCirX2;
//            timeCirR -= 5;
//            timeRectPos.right = 0;
//        }else{
//            timeRectPos.right = timeRectPos.left + timeCurrWidth;
//            timeCirX2 = timeRectPos.right;
//        }
        //--more smooth timer. but will faster than word--

        if(timeRemapWidth == -1){
            timeCirR = 0;
            timeRectPos.right = 0;
        }else{
            timeRectPos.right = timeRectPos.left + timeRemapWidth;
            timeCirX2 = timeRectPos.right;
        }

        if(gameManagerPracticeMode.getResetTimer()){
            //close the tips and detection check when next question
            tipIsShown = false;
            OpenGLRenderer_Tips.setCanDraw(false);
            OpenGLRenderer_DetectionCheck.setCanDraw(false);
            resetTimer();
        }

        canvas.drawCircle(timeCirX1, timeCirY1, timeCirR, white);
        canvas.drawCircle(timeCirX2, timeCirY2, timeCirR, white);
        canvas.drawRect(timeRectPos, white);
    }

    private float remap(float value, float from1, float to1, float from2, float to2){
        return (value - from1) / (to1 - from1) * (to2 - from2) + from2;
    }

    private void drawViewsPracticeMode(Canvas canvas){
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

    private void drawModelPracticeMode(Canvas canvas){

        //model word
        canvas.drawBitmap(modelPic, modelSrc, modelPos, null);

        //3d model
        canvas.drawBitmap(qModelBkgPic, qModelBkgSrc, qModelPos, null);

        //draw view word label
        switch (gameManagerPracticeMode.getCurrQuestMode()){
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
                switch (gameManagerPracticeMode.getCurrQuestMode()){
                    case DRAWFRONTVIEW:
                        gameManagerPracticeMode.setPlayerFrontView(i);
                        break;
                    case DRAWSIDEVIEW:
                        gameManagerPracticeMode.setPlayerSideView(i);
                        break;
                    case DRAWTOPVIEW:
                        gameManagerPracticeMode.setPlayerTopView(i);
                        break;
                }
            }
            else{
                drawViewPaint[i].setColor(lightOrange.getColor());
                switch (gameManagerPracticeMode.getCurrQuestMode()){
                    case DRAWFRONTVIEW:
                        gameManagerPracticeMode.clearPlayerFrontView(i);
                        break;
                    case DRAWSIDEVIEW:
                        gameManagerPracticeMode.clearPlayerSideView(i);
                        break;
                    case DRAWTOPVIEW:
                        gameManagerPracticeMode.clearPlayerTopView(i);
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
        canvas.drawBitmap(spType3Q3Pic, spType3Q3Src, spType3Q3Pos, null);
        canvas.drawBitmap(spType3Q4Pic, spType3Q4Src, spType3Q4Pos, null);

        //sp type 3 pic
        canvas.drawBitmap(qSpType3BkgPic, qspType3BkgSrc, spType3Pos_base0, null);
        canvas.drawBitmap(qSpType3BkgPic, qspType3BkgSrc, spType3Pos_base1, null);
        canvas.drawBitmap(qSpType3BkgPic, qspType3BkgSrc, spType3Pos_quest, null);
    }

    private void drawSpType4PracticeMode(Canvas canvas){
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

    private void drawTip(Canvas canvas){

        if(timeRemapWidth == -1){
            if(tipIsShown){
                if(gameManagerPracticeMode.getCurrQuestMode() == SPTYPE4){
                    canvas.drawBitmap(tipBoxPic2, tipBoxSrc2, tipBoxPos2, null);
                }
                else{
                    canvas.drawBitmap(tipBoxPic, tipBoxSrc, tipBoxPos, null);
                }
                OpenGLRenderer_Tips.setCanDraw(true);

                switch (gameManagerPracticeMode.getCurrQuestMode()){
                    case SPTYPE3:
                        canvas.drawText(tips_spType3.getString(), tipBoxPos.centerX(), tipBoxPos.centerY(), tipFont);
                        break;

                    case SPTYPE4:
                        String ans = "";
                        String ans2 = "";
                        switch (gameManagerPracticeMode.getSpType4Ans()){
                            case 0:
                                ans = "1st";
                                ans2 = "4th";
                                break;
                            case 1:
                                ans = "2nd";
                                ans2 = "3rd";
                                break;
                            case 2:
                                ans = "1st";
                                ans2 = "3rd";
                                break;
                            case 3:
                                ans = "2nd";
                                ans2 = "4th";
                                break;
                        }
                        canvas.drawText(ans + " or " + ans2, tipBoxPos2.centerX(), tipBoxPos2.centerY() - 30, tipFont);
                        canvas.drawText("is the correct answer", tipBoxPos2.centerX(), tipBoxPos2.centerY() + 30, tipFont);
                        break;
                }
            }
            else if(!tipIsShown){
                canvas.drawBitmap(tipIconPic, tipIconSrc, tipIconPos, null);
                OpenGLRenderer_Tips.setCanDraw(false);
            }
        }
        else {
            OpenGLRenderer_Tips.setCanDraw(false);
        }

    }

    private void drawDetection(Canvas canvas){
        if(detectionIsShown){
            for(int i = 0; i < MAXGRIDSNUM; i++){
                for(int j = 0; j < MAXHEIGHTNUM; j++) {
                    if(arduino.getIsCubePresent(i,j)){
                        OpenGLRenderer_DetectionCheck.setIsCubePressentTrue(i, j);
                    }
                    else{
                        OpenGLRenderer_DetectionCheck.setIsCubePressentFalse(i, j);
                    }
                }
            }
            OpenGLRenderer_DetectionCheck.setCanDraw(true);
            canvas.drawBitmap(detectionBoxPic, detectionBoxSrc, detectionBoxPos, null);
            canvas.drawBitmap(detectionModelBkgPic, detectionModelBkgSrc, detectionModelBkgPos, null);
        }
        else if(!detectionIsShown){
            OpenGLRenderer_DetectionCheck.setCanDraw(false);
            canvas.drawBitmap(detectionIconPic, detectionIconSrc, detectionIconPos, null);
        }
    }

    private void drawBattleGamePage(Canvas canvas){

        switch (gameManagerBattleMode.getCurrStage()){
            case QUESTSTAGE:
                canvas.drawBitmap(qTitlePic[gameManagerBattleMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);
                switch (playerNum){
                    case 2:
                        canvas.drawBitmap(redPlayerBtnLPic, redPlayerBtnLSrc, redPlayerBtnLPos, null);
                        canvas.drawBitmap(bluePlayerBtnLPic, bluePlayerBtnLSrc, bluePlayerBtnLPos, null);
                        canvas.save();
                        canvas.rotate(90, redPlayerBtnLPos.centerX() - canvasW / 50, redPlayerBtnLPos.centerY() + canvasH / 20);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getRedScore()), redPlayerBtnLPos.centerX() - canvasW / 50, redPlayerBtnLPos.centerY() + canvasH / 20, scoreGameFont);
                        canvas.restore();
                        canvas.save();
                        canvas.rotate(-90, bluePlayerBtnLPos.centerX() + canvasW / 50, bluePlayerBtnLPos.centerY() - canvasH / 20);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getBlueScore()), bluePlayerBtnLPos.centerX() + canvasW / 50, bluePlayerBtnLPos.centerY() - canvasH / 20, scoreGameFont);
                        canvas.restore();
                        break;
                    case 3:
                        canvas.drawBitmap(redPlayerBtnLPic, redPlayerBtnLSrc, redPlayerBtnLPos, null);
                        canvas.drawBitmap(bluePlayerBtnLPic, bluePlayerBtnLSrc, bluePlayerBtnLPos, null);
                        canvas.drawBitmap(greenPlayerBtnLPic, greenPlayerBtnLSrc, greenPlayerBtnLPos, null);
                        canvas.save();
                        canvas.rotate(90, redPlayerBtnLPos.centerX() - canvasW / 50, redPlayerBtnLPos.centerY() + canvasH / 20);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getRedScore()), redPlayerBtnLPos.centerX() - canvasW / 50, redPlayerBtnLPos.centerY() + canvasH / 20, scoreGameFont);
                        canvas.restore();
                        canvas.save();
                        canvas.rotate(-90, bluePlayerBtnLPos.centerX() + canvasW / 50, bluePlayerBtnLPos.centerY() - canvasH / 20);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getBlueScore()), bluePlayerBtnLPos.centerX() + canvasW / 50, bluePlayerBtnLPos.centerY() - canvasH / 20, scoreGameFont);
                        canvas.restore();
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getGreenScore()), greenPlayerBtnLPos.centerX() + canvasW / 50, greenPlayerBtnLPos.centerY() + canvasH / 30, scoreGameFont);
                        break;
                    case 4:
                        canvas.drawBitmap(redPlayerBtnSPic, redPlayerBtnSSrc, redPlayerBtnSPos, null);
                        canvas.drawBitmap(bluePlayerBtnSPic, bluePlayerBtnSSrc, bluePlayerBtnSPos, null);
                        canvas.drawBitmap(greenPlayerBtnSPic, greenPlayerBtnSSrc, greenPlayerBtnSPos, null);
                        canvas.drawBitmap(purplePlayerBtnSPic, purplePlayerBtnSSrc, purplePlayerBtnSPos, null);
                        canvas.save();
                        canvas.rotate(90, redPlayerBtnSPos.centerX() - canvasW / 50, redPlayerBtnSPos.centerY() + canvasH / 30);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getRedScore()), redPlayerBtnSPos.centerX() - canvasW / 50, redPlayerBtnSPos.centerY() + canvasH / 30, scoreGameFont);
                        canvas.restore();
                        canvas.save();
                        canvas.rotate(90, bluePlayerBtnSPos.centerX() - canvasW / 50, bluePlayerBtnSPos.centerY() + canvasH / 30);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getBlueScore()), bluePlayerBtnSPos.centerX() - canvasW / 50, bluePlayerBtnSPos.centerY() + canvasH / 30, scoreGameFont);
                        canvas.restore();
                        canvas.save();
                        canvas.rotate(-90, greenPlayerBtnSPos.centerX() + canvasW / 50, greenPlayerBtnSPos.centerY() - canvasH / 30);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getGreenScore()), greenPlayerBtnSPos.centerX() + canvasW / 50, greenPlayerBtnSPos.centerY() - canvasH / 40, scoreGameFont);
                        canvas.restore();
                        canvas.save();
                        canvas.rotate(-90, purplePlayerBtnSPos.centerX() + canvasW / 50, purplePlayerBtnSPos.centerY() - canvasH / 30);
                        canvas.drawText(String.valueOf(gameManagerBattleMode.getPurpleScore()), purplePlayerBtnSPos.centerX() + canvasW / 50, purplePlayerBtnSPos.centerY() - canvasH / 40, scoreGameFont);
                        canvas.restore();
                        break;
                }
                answerCoolDown = 2;
                break;
            case REDANSWERSTAGE:
                canvas.drawBitmap(qTitlePic[gameManagerBattleMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);
                canvas.drawRect(0, 0, canvasW, canvasH, redStroke);
                canvas.drawBitmap(answerPic, answerSrc, answerPos, null);
                break;
            case BLUEANSWERSTAGE:
                canvas.drawBitmap(qTitlePic[gameManagerBattleMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);
                canvas.drawRect(0, 0, canvasW, canvasH, blueStroke);
                canvas.drawBitmap(answerPic, answerSrc, answerPos, null);
                break;
            case GREENANSWERSTAGE:
                canvas.drawBitmap(qTitlePic[gameManagerBattleMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);
                canvas.drawRect(0, 0, canvasW, canvasH, greenStroke);
                canvas.drawBitmap(answerPic, answerSrc, answerPos, null);
                if(answerCoolDown > 0){
                    answerCoolDown--;
                }
                else{
                    answerCoolDown = 0;
                }
                break;
            case PURPLEANSWERSTAGE:
                canvas.drawBitmap(qTitlePic[gameManagerBattleMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);
                canvas.drawRect(0, 0, canvasW, canvasH, purpleStroke);
                canvas.drawBitmap(answerPic, answerSrc, answerPos, null);
                break;
            case TIMESUPSTAGE:
                canvas.drawBitmap(qTitlePic[gameManagerBattleMode.getCurrQuestNum()], qTitleSrc, qTitlePos, null);
                switch (playerNum){
                    case 2:
                        canvas.drawBitmap(redPlayerBtnLPic, redPlayerBtnLSrc, redPlayerBtnLPos, null);
                        canvas.drawBitmap(bluePlayerBtnLPic, bluePlayerBtnLSrc, bluePlayerBtnLPos, null);
                        break;
                    case 3:
                        canvas.drawBitmap(redPlayerBtnLPic, redPlayerBtnLSrc, redPlayerBtnLPos, null);
                        canvas.drawBitmap(bluePlayerBtnLPic, bluePlayerBtnLSrc, bluePlayerBtnLPos, null);
                        canvas.drawBitmap(greenPlayerBtnLPic, greenPlayerBtnLSrc, greenPlayerBtnLPos, null);
                        break;
                    case 4:
                        canvas.drawBitmap(redPlayerBtnSPic, redPlayerBtnSSrc, redPlayerBtnSPos, null);
                        canvas.drawBitmap(bluePlayerBtnSPic, bluePlayerBtnSSrc, bluePlayerBtnSPos, null);
                        canvas.drawBitmap(greenPlayerBtnSPic, greenPlayerBtnSSrc, greenPlayerBtnSPos, null);
                        canvas.drawBitmap(purplePlayerBtnSPic, purplePlayerBtnSSrc, purplePlayerBtnSPos, null);
                        break;
                }
                break;
        }

        //timer
        drawTimerBattleMode(canvas);

        switch (gameManagerBattleMode.getCurrQuestMode()){
            case DRAWFRONTVIEW:
                if(!settingIsShown){
                    OpenGLRenderer_3DModel.setCanDraw(true);
                }
                else if(settingIsShown){
                    OpenGLRenderer_3DModel.setCanDraw(false);
                }
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formFrontViewPic, qTitleSrc, qTitlePos, null);
                drawModelBattleMode(canvas);
                break;
            case DRAWSIDEVIEW:
                if(!settingIsShown){
                    OpenGLRenderer_3DModel.setCanDraw(true);
                }
                else if(settingIsShown){
                    OpenGLRenderer_3DModel.setCanDraw(false);
                }
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formSideViewPic, qTitleSrc, qTitlePos, null);
                drawModelBattleMode(canvas);
                break;
            case DRAWTOPVIEW:
                if(!settingIsShown){
                    OpenGLRenderer_3DModel.setCanDraw(true);
                }
                else if(settingIsShown){
                    OpenGLRenderer_3DModel.setCanDraw(false);
                }
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formTopViewPic, qTitleSrc, qTitlePos, null);
                drawModelBattleMode(canvas);
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
                drawViewsBattleMode(canvas);
                if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE){
                    drawDetection(canvas);
                }
                break;
            case SPTYPE3:
                OpenGLRenderer_3DModel.setCanDraw(false);
                if(!settingIsShown){
                    OpenGLRenderer_SPType3_Base0.setCanDraw(true);
                    OpenGLRenderer_SPType3_Base1.setCanDraw(true);
                    OpenGLRenderer_SPType3_Quest.setCanDraw(true);
                }
                else if(settingIsShown){
                    OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                    OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                    OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                }
                OpenGLRenderer_SPType4_Base.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                canvas.drawBitmap(formAnsPic, qTitleSrc, qTitlePos, null);
                drawSpType3(canvas);
                if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE){
                    drawDetection(canvas);
                }
                break;
            case SPTYPE4:
                OpenGLRenderer_3DModel.setCanDraw(false);
                OpenGLRenderer_SPType3_Base0.setCanDraw(false);
                OpenGLRenderer_SPType3_Base1.setCanDraw(false);
                OpenGLRenderer_SPType3_Quest.setCanDraw(false);
                if(!settingIsShown){
                    OpenGLRenderer_SPType4_Base.setCanDraw(true);
                    OpenGLRenderer_SPType4_Choice0.setCanDraw(true);
                    OpenGLRenderer_SPType4_Choice1.setCanDraw(true);
                    OpenGLRenderer_SPType4_Choice2.setCanDraw(true);
                    OpenGLRenderer_SPType4_Choice3.setCanDraw(true);
                }
                else if(settingIsShown){
                    OpenGLRenderer_SPType4_Base.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice0.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice1.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice2.setCanDraw(false);
                    OpenGLRenderer_SPType4_Choice3.setCanDraw(false);
                }
                canvas.drawBitmap(selectAnsPic, qTitleSrc, qTitlePos, null);
                drawSpType4BattleMode(canvas);
                break;
            case -1:
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

        gameManagerBattleMode.setSpType4PlayerAns(spType4PlayerChoice);
        updateGM();

        //drawScoreAni(canvas);

        drawBTEndGame(canvas);

        //restart the game
        BMrestart();

    }

    private void drawBTEndGame(Canvas canvas){
        switch (gameManagerBattleMode.getCurrStage()){
            case TIMESUPSTAGE:
                Paint bkg = new Paint();
                bkg.setColor(Color.argb(100, 0, 0, 0));
                canvas.drawRect(0, 0, canvasW, canvasH, bkg);
                canvas.drawRect(0, canvasH / 4, canvasW, canvasH - canvasH / 4, white);
                canvas.drawBitmap(timesUpPic, timesUpSrc, timesUpPos, null);
                timesUpCountDown--;
                if(timesUpCountDown <= 0){
                    timesUpCountDown = 80;
                    GameManager_BattleMode.setCurrStage(RESULTSTAGE);
                }
                break;
            case RESULTSTAGE:
                canvas.drawBitmap(backPic, backSrc, backPos, null);
                int rectScoreGap = canvasH / 30;
                int extraGap = 5;
                int perRect = (canvasH - winsPos.bottom) / (24 + extraGap * 2);
                int lineGap = canvasW / 20;
                int lineX1 = playerIconPos1.left - lineGap;
                int lineY1 = canvasH - perRect * (6 + extraGap);
                int lineX2 = playerIconPos2.right + lineGap;
                int lineY2 = canvasH - perRect * (6 + extraGap);
                Rect tempRect = new Rect();
                switch (playerNum){
                    case 2:
                        if(gameManagerBattleMode.getRedScore() > gameManagerBattleMode.getBlueScore()){
                            canvas.drawBitmap(redWinsPic, winsSrc, winsPos, null);
                        }
                        else if(gameManagerBattleMode.getBlueScore() > gameManagerBattleMode.getRedScore()){
                            canvas.drawBitmap(blueWinsPic, winsSrc, winsPos, null);
                        }
                        else{
                            canvas.drawBitmap(itsADrawPic, winsSrc, winsPos, null);
                        }

                        if(gameManagerBattleMode.getRedScore() > 0){
                            h1 = Math.abs(gameManagerBattleMode.getRedScore()) * perRect;
                            tempRect.left = playerIconPos1.left;
                            tempRect.top = lineY1 - h1;
                            tempRect.right = playerIconPos1.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, red);
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos1.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getRedScore() < 0){
                            h1 = Math.abs(gameManagerBattleMode.getRedScore()) * perRect;
                            tempRect.left = playerIconPos1.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos1.right;
                            tempRect.bottom = tempRect.top + h1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, red);
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos1.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getRedScore() == 0){
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos1.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        if(gameManagerBattleMode.getBlueScore() > 0){
                            h2 = Math.abs(gameManagerBattleMode.getBlueScore()) * perRect;
                            tempRect.left = playerIconPos2.left;
                            tempRect.top = lineY1 - h2;
                            tempRect.right = playerIconPos2.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, blue);
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos2.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getBlueScore() < 0){
                            h2 = Math.abs(gameManagerBattleMode.getBlueScore()) * perRect;
                            tempRect.left = playerIconPos2.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos2.right;
                            tempRect.bottom = tempRect.top + h2;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, blue);
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos2.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getBlueScore() == 0){
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos2.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        canvas.drawLine(lineX1, lineY1, lineX2, lineY2, whiteStrokeThin);
                        scoreFont.setColor(white.getColor());
                        drawTextCentred(canvas, "0", lineX2 + rectScoreGap , lineY1, scoreFont);

                        break;
                    case 3:
                        if(gameManagerBattleMode.getRedScore() > gameManagerBattleMode.getBlueScore() &&
                                gameManagerBattleMode.getRedScore() > gameManagerBattleMode.getGreenScore()){
                            canvas.drawBitmap(redWinsPic, winsSrc, winsPos, null);
                        }
                        else if(gameManagerBattleMode.getBlueScore() > gameManagerBattleMode.getRedScore() &&
                                gameManagerBattleMode.getBlueScore() > gameManagerBattleMode.getGreenScore()){
                            canvas.drawBitmap(blueWinsPic, winsSrc, winsPos, null);
                        }
                        else if(gameManagerBattleMode.getGreenScore() > gameManagerBattleMode.getRedScore() &&
                                gameManagerBattleMode.getGreenScore() > gameManagerBattleMode.getBlueScore()){
                            canvas.drawBitmap(greenWinsPic, winsSrc, winsPos, null);
                        }
                        else{
                            canvas.drawBitmap(itsADrawPic, winsSrc, winsPos, null);
                        }

                        lineX1 = playerIconPos4.left - lineGap;
                        lineX2 = playerIconPos6.right + lineGap;

                        if(gameManagerBattleMode.getRedScore() > 0){
                            h4 = Math.abs(gameManagerBattleMode.getRedScore()) * perRect;
                            tempRect.left = playerIconPos4.left;
                            tempRect.top = lineY1 - h4;
                            tempRect.right = playerIconPos4.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, red);
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos4.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getRedScore() < 0){
                            h4 = Math.abs(gameManagerBattleMode.getRedScore()) * perRect;
                            tempRect.left = playerIconPos4.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos4.right;
                            tempRect.bottom = tempRect.top + h4;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, red);
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos4.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getRedScore() == 0){
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos4.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        if(gameManagerBattleMode.getBlueScore() > 0){
                            h5 = Math.abs(gameManagerBattleMode.getBlueScore()) * perRect;
                            tempRect.left = playerIconPos5.left;
                            tempRect.top = lineY1 - h5;
                            tempRect.right = playerIconPos5.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, blue);
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos5.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getBlueScore() < 0){
                            h5 = Math.abs(gameManagerBattleMode.getBlueScore()) * perRect;
                            tempRect.left = playerIconPos5.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos5.right;
                            tempRect.bottom = tempRect.top + h5;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, blue);
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos5.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getBlueScore() == 0){
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos5.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        if(gameManagerBattleMode.getGreenScore() > 0){
                            h6 = Math.abs(gameManagerBattleMode.getGreenScore()) * perRect;
                            tempRect.left = playerIconPos6.left;
                            tempRect.top = lineY1 - h6;
                            tempRect.right = playerIconPos6.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, green);
                            scoreFont.setColor(green.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getGreenScore()), playerIconPos6.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getGreenScore() < 0){
                            h6 = Math.abs(gameManagerBattleMode.getGreenScore()) * perRect;
                            tempRect.left = playerIconPos6.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos6.right;
                            tempRect.bottom = tempRect.top + h6;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, green);
                            scoreFont.setColor(green.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getGreenScore()), playerIconPos6.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getGreenScore() == 0){
                            scoreFont.setColor(green.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getGreenScore()), playerIconPos6.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        canvas.drawLine(lineX1, lineY1, lineX2, lineY2, whiteStrokeThin);
                        scoreFont.setColor(white.getColor());
                        drawTextCentred(canvas, "0", lineX2 + rectScoreGap , lineY1, scoreFont);

                        break;
                    case 4:
                        if(gameManagerBattleMode.getRedScore() > gameManagerBattleMode.getBlueScore() &&
                                gameManagerBattleMode.getRedScore() > gameManagerBattleMode.getGreenScore() &&
                                gameManagerBattleMode.getRedScore() > gameManagerBattleMode.getPurpleScore()){
                            canvas.drawBitmap(redWinsPic, winsSrc, winsPos, null);
                        }
                        else if(gameManagerBattleMode.getBlueScore() > gameManagerBattleMode.getRedScore() &&
                                gameManagerBattleMode.getBlueScore() > gameManagerBattleMode.getGreenScore() &&
                                gameManagerBattleMode.getBlueScore() > gameManagerBattleMode.getPurpleScore()){
                            canvas.drawBitmap(blueWinsPic, winsSrc, winsPos, null);
                        }
                        else if(gameManagerBattleMode.getGreenScore() > gameManagerBattleMode.getRedScore() &&
                                gameManagerBattleMode.getGreenScore() > gameManagerBattleMode.getBlueScore() &&
                                gameManagerBattleMode.getGreenScore() > gameManagerBattleMode.getPurpleScore()){
                            canvas.drawBitmap(greenWinsPic, winsSrc, winsPos, null);
                        }
                        else if(gameManagerBattleMode.getPurpleScore() > gameManagerBattleMode.getRedScore() &&
                                gameManagerBattleMode.getPurpleScore() > gameManagerBattleMode.getBlueScore() &&
                                gameManagerBattleMode.getPurpleScore() > gameManagerBattleMode.getGreenScore()){
                            canvas.drawBitmap(purpleWinsPic, winsSrc, winsPos, null);
                        }
                        else{
                            canvas.drawBitmap(itsADrawPic, winsSrc, winsPos, null);
                        }

                        lineX1 = playerIconPos0.left - lineGap;
                        lineX2 = playerIconPos3.right + lineGap;

                        if(gameManagerBattleMode.getRedScore() > 0){
                            h0 = Math.abs(gameManagerBattleMode.getRedScore()) * perRect;
                            tempRect.left = playerIconPos0.left;
                            tempRect.top = lineY1 - h0;
                            tempRect.right = playerIconPos0.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, red);
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos0.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getRedScore() < 0){
                            h0 = Math.abs(gameManagerBattleMode.getRedScore()) * perRect;
                            tempRect.left = playerIconPos0.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos0.right;
                            tempRect.bottom = tempRect.top + h0;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, red);
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos0.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getRedScore() == 0){
                            scoreFont.setColor(red.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getRedScore()), playerIconPos0.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        if(gameManagerBattleMode.getBlueScore() > 0){
                            h1 = Math.abs(gameManagerBattleMode.getBlueScore()) * perRect;
                            tempRect.left = playerIconPos1.left;
                            tempRect.top = lineY1 - h1;
                            tempRect.right = playerIconPos1.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, blue);
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos1.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getBlueScore() < 0){
                            h1 = Math.abs(gameManagerBattleMode.getBlueScore()) * perRect;
                            tempRect.left = playerIconPos1.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos1.right;
                            tempRect.bottom = tempRect.top + h1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, blue);
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos1.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getBlueScore() == 0){
                            scoreFont.setColor(blue.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getBlueScore()), playerIconPos1.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        if(gameManagerBattleMode.getGreenScore() > 0){
                            h2 = Math.abs(gameManagerBattleMode.getGreenScore()) * perRect;
                            tempRect.left = playerIconPos2.left;
                            tempRect.top = lineY1 - h2;
                            tempRect.right = playerIconPos2.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, green);
                            scoreFont.setColor(green.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getGreenScore()), playerIconPos2.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getGreenScore() < 0){
                            h2 = Math.abs(gameManagerBattleMode.getGreenScore()) * perRect;
                            tempRect.left = playerIconPos2.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos2.right;
                            tempRect.bottom = tempRect.top + h2;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, green);
                            scoreFont.setColor(green.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getGreenScore()), playerIconPos2.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getGreenScore() == 0){
                            scoreFont.setColor(green.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getGreenScore()), playerIconPos2.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        if(gameManagerBattleMode.getPurpleScore() > 0){
                            h3 = Math.abs(gameManagerBattleMode.getPurpleScore()) * perRect;
                            tempRect.left = playerIconPos3.left;
                            tempRect.top = lineY1 - h3;
                            tempRect.right = playerIconPos3.right;
                            tempRect.bottom = lineY1;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, purple);
                            scoreFont.setColor(purple.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getPurpleScore()), playerIconPos3.centerX(), tempRect.top - rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getPurpleScore() < 0){
                            h3 = Math.abs(gameManagerBattleMode.getPurpleScore()) * perRect;
                            tempRect.left = playerIconPos3.left;
                            tempRect.top = lineY1;
                            tempRect.right = playerIconPos3.right;
                            tempRect.bottom = tempRect.top + h3;
                            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, purple);
                            scoreFont.setColor(purple.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getPurpleScore()), playerIconPos3.centerX(), tempRect.bottom + rectScoreGap, scoreFont);
                        }
                        else if(gameManagerBattleMode.getPurpleScore() == 0){
                            scoreFont.setColor(purple.getColor());
                            drawTextCentred(canvas, String.valueOf(gameManagerBattleMode.getPurpleScore()), playerIconPos3.centerX(), lineY1 + rectScoreGap, scoreFont);
                        }

                        canvas.drawLine(lineX1, lineY1, lineX2, lineY2, whiteStrokeThin);
                        scoreFont.setColor(white.getColor());
                        drawTextCentred(canvas, "0", lineX2 + rectScoreGap , lineY1, scoreFont);

                        break;
                }
                break;
        }
    }

    private void BMrestart(){
        if(gameManagerBattleMode.getRestart()){
            spType4PlayerChoice = 0;
            resetDrawView();
            resetTimer();

            isInGame = false;

            currPage = MENUPAGE;
            gameManagerBattleMode.setRestartFalse();
        }
    }

    private void drawTimerBattleMode(Canvas canvas){
        if(gameManagerBattleMode.getCurrStage() == QUESTSTAGE){
            if(!timeSetDone){
                gameManagerBattleMode.timer10sStart();
                timeSetDone = true;
            }

            canvas.drawText(String.valueOf((int)gameManagerBattleMode.getTimeLeft10s() / 1000), timeTxtX, timeTxtY, font);

            timeRemapWidth = (int)remap(gameManagerBattleMode.getTimeLeft10s(), gameManagerBattleMode.getTotalTime10s(), -1, timeFullWidth, -1);

            if(timeRemapWidth == -1){
                timeCirR = 0;
                timeRectPos.right = 0;
            }else{
                timeRectPos.right = timeRectPos.left + timeRemapWidth;
                timeCirX2 = timeRectPos.right;
            }

            canvas.drawCircle(timeCirX1, timeCirY1, timeCirR, white);
            canvas.drawCircle(timeCirX2, timeCirY2, timeCirR, white);
            canvas.drawRect(timeRectPos, white);

            if(gameManagerBattleMode.getTimer10sFinished()){
                if(gameManagerBattleMode.getCurrQuestNum() < MAXQUESTNUM - 1){
                    gameManagerBattleMode.skip();
                }
                else{
                    GameManager_BattleMode.setCurrStage(TIMESUPSTAGE);
                    GameManager_BattleMode.setCurrQuestMode(-1);
                }
            }

        }
        else if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE){
            if(!timeSetDone){
                gameManagerBattleMode.timer120sStart();
                timeSetDone = true;
            }

            canvas.drawText(String.valueOf((int)gameManagerBattleMode.getTimeLeft120s() / 1000), timeTxtX, timeTxtY, font);

            timeRemapWidth = (int)remap(gameManagerBattleMode.getTimeLeft120s(), gameManagerBattleMode.getTotalTime120s(), -2, timeFullWidth, -2);

            if(timeRemapWidth == 0){
                timeCirR = 0;
                timeRectPos.right = 0;
            }else{
                timeRectPos.right = timeRectPos.left + timeRemapWidth;
                timeCirX2 = timeRectPos.right;
            }

            canvas.drawCircle(timeCirX1, timeCirY1, timeCirR, white);
            canvas.drawCircle(timeCirX2, timeCirY2, timeCirR, white);
            canvas.drawRect(timeRectPos, white);

            if(gameManagerBattleMode.getTimer120sFinished()){
                gameManagerBattleMode.compare();
            }
        }

        if(gameManagerBattleMode.getResetTimer10s()){
            resetTimer();
            resetDrawView();
        }

        if(gameManagerBattleMode.getResetTimer120s()){
            resetTimer();
            resetDrawView();
        }

        if(gameManagerBattleMode.getCurrQuestNum() == 2){
            resetDrawView();
        }

    }

    private void drawViewsBattleMode(Canvas canvas){
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

    private void drawModelBattleMode(Canvas canvas){
        //model word
        canvas.drawBitmap(modelPic, modelSrc, modelPos, null);

        //3d model
        canvas.drawBitmap(qModelBkgPic, qModelBkgSrc, qModelPos, null);

        //draw view word label
        switch (gameManagerBattleMode.getCurrQuestMode()){
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
                switch (gameManagerBattleMode.getCurrStage()){
                    case QUESTSTAGE:
                        drawViewPaint[i].setColor(darkOrange.getColor());
                        break;
                    case REDANSWERSTAGE:
                        drawViewPaint[i].setColor(red.getColor());
                        break;
                    case BLUEANSWERSTAGE:
                        drawViewPaint[i].setColor(blue.getColor());
                        break;
                    case GREENANSWERSTAGE:
                        drawViewPaint[i].setColor(green.getColor());
                        break;
                    case PURPLEANSWERSTAGE:
                        drawViewPaint[i].setColor(purple.getColor());
                        break;
                }
                switch (gameManagerBattleMode.getCurrQuestMode()){
                    case DRAWFRONTVIEW:
                        gameManagerBattleMode.setPlayerFrontView(i);
                        break;
                    case DRAWSIDEVIEW:
                        gameManagerBattleMode.setPlayerSideView(i);
                        break;
                    case DRAWTOPVIEW:
                        gameManagerBattleMode.setPlayerTopView(i);
                        break;
                }
            }
            else{
                drawViewPaint[i].setColor(lightOrange.getColor());
                switch (gameManagerBattleMode.getCurrQuestMode()){
                    case DRAWFRONTVIEW:
                        gameManagerBattleMode.clearPlayerFrontView(i);
                        break;
                    case DRAWSIDEVIEW:
                        gameManagerBattleMode.clearPlayerSideView(i);
                        break;
                    case DRAWTOPVIEW:
                        gameManagerBattleMode.clearPlayerTopView(i);
                        break;
                }
            }

            canvas.drawRect(drawViewPos2[i].left, drawViewPos2[i].top, drawViewPos2[i].right,  drawViewPos2[i].bottom, whiteStroke);
            canvas.drawRect(drawViewPos2[i].left, drawViewPos2[i].top, drawViewPos2[i].right,  drawViewPos2[i].bottom, drawViewPaint[i]);
        }
    }

    private void drawSpType4BattleMode(Canvas canvas){
        //sp type 4 question
        canvas.drawBitmap(spType4QPic, spType4QSrc, spType4QPos, null);
        canvas.drawRect(spType4Pos_base.right, spType4Pos_base.top, spType4Pos_base.right + 5, spType4Pos_base.bottom, white);

        Paint paint = new Paint();
        switch (gameManagerBattleMode.getCurrStage()){
            case REDANSWERSTAGE:
                paint.setColor(red.getColor());
                break;
            case BLUEANSWERSTAGE:
                paint.setColor(blue.getColor());
                break;
            case GREENANSWERSTAGE:
                paint.setColor(green.getColor());
                break;
            case PURPLEANSWERSTAGE:
                paint.setColor(purple.getColor());
                break;
        }

        //player ans choice
        if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE){
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
                    canvas.drawPath(path, paint);
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
                    canvas.drawPath(path1, paint);
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
                    canvas.drawPath(path2, paint);
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
                    canvas.drawPath(path3, paint);
                    break;
            }
        }

    }

    private float dist(float x0, float y0, float x1, float y1){
        return (float)Math.sqrt((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0));
    }

    private void drawScoreAni(Canvas canvas){

        switch (playerNum){
            case 2:
                switch (gameManagerBattleMode.getCurrStage()){
                    case REDANSWERSTAGE:
                        x1 = redPlayerBtnLPos.right;
                        y1 = redPlayerBtnLPos.top + redPlayerBtnLPos.height() / 2;
                        x0 = answerPos.left + answerPos.width() / 2;
                        y0 = answerPos.top;
                        break;
                    case BLUEANSWERSTAGE:
                        x1 = bluePlayerBtnLPos.left;
                        y1 = bluePlayerBtnLPos.top + bluePlayerBtnLPos.height() / 2;
                        x0 = answerPos.left + answerPos.width() / 2;
                        y0 = answerPos.top;
                        break;
                }
                break;
            case 3:
                switch (gameManagerBattleMode.getCurrStage()){
                    case REDANSWERSTAGE:
                        x1 = redPlayerBtnLPos.right;
                        y1 = redPlayerBtnLPos.top + redPlayerBtnLPos.height() / 2;
                        x0 = x1 + 400;
                        y0 = y1;
                        break;
                    case BLUEANSWERSTAGE:
                        x1 = bluePlayerBtnLPos.left;
                        y1 = bluePlayerBtnLPos.top + bluePlayerBtnLPos.height() / 2;
                        x0 = x1 - 400;
                        y0 = y1;
                        break;
                    case GREENANSWERSTAGE:
                        x1 = greenPlayerBtnLPos.left + greenPlayerBtnLPos.width() / 2;
                        y1 = greenPlayerBtnLPos.top;
                        x0 = x1;
                        y0 = y1 - 400;
                        break;
                }
                break;
            case 4:
                switch (gameManagerBattleMode.getCurrStage()){
                    case REDANSWERSTAGE:
                        x1 = redPlayerBtnSPos.right;
                        y1 = redPlayerBtnSPos.top + redPlayerBtnSPos.height() / 2;
                        x0 = answerPos.left + answerPos.width() / 2;
                        y0 = answerPos.top;
                        break;
                    case BLUEANSWERSTAGE:
                        x1 = bluePlayerBtnSPos.right;
                        y1 = bluePlayerBtnSPos.top + bluePlayerBtnSPos.height() / 2;
                        x0 = answerPos.left + answerPos.width() / 2;
                        y0 = answerPos.top;
                        break;
                    case GREENANSWERSTAGE:
                        x1 = greenPlayerBtnSPos.left;
                        y1 = greenPlayerBtnSPos.top + greenPlayerBtnSPos.height() / 2;
                        x0 = answerPos.left + answerPos.width() / 2;
                        y0 = answerPos.top;
                        break;
                    case PURPLEANSWERSTAGE:
                        x1 = purplePlayerBtnSPos.left;
                        y1 = purplePlayerBtnSPos.top + purplePlayerBtnSPos.height() / 2;
                        x0 = answerPos.left + answerPos.width() / 2;
                        y0 = answerPos.top;
                        break;
                }
                break;
        }

        if(gameManagerBattleMode.isAddScoreAni()){
            dx = x1 - x0;
            dy = y1 - y0;
            angle = (float)Math.atan2(dy, dx);

            if(dist(x0, y0, x1, y1) > 35){
                x0 += 35 * Math.cos(angle);
                y0 += 35 * Math.sin(angle);
                canvas.drawText("+3", x0, y0, font);
            }
            else{
                GameManager_BattleMode.setAddScoreAniFalse();
                x0 = answerPos.left + answerPos.width() / 2;
                y0 = answerPos.top;
            }
        }
        else if(gameManagerBattleMode.isDeductScoreAni()){
            dx = x1 - x0;
            dy = y1 - y0;
            angle = (float)Math.atan2(dy, dx);

            if(dist(x0, y0, x1, y1) > 35){
                x0 += 35 * Math.cos(angle);
                y0 += 35 * Math.sin(angle);
                canvas.drawText("-1", x0, y0, font);
            }
            else{
                GameManager_BattleMode.setDeductScoreAniFalse();
                x0 = answerPos.left + answerPos.width() / 2;
                y0 = answerPos.top;
            }
        }
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

    private void drawPuzzlePage(Canvas canvas){
        canvas.drawBitmap(puzzleTitlePic1, puzzleTitleSrc1, puzzleTitlePos1, null);
        canvas.drawBitmap(puzzleTitlePic2, puzzleTitleSrc2, puzzleTitlePos2, null);
        canvas.drawText(String.valueOf(puzzle.getPlayedRound()), canvasW /2, puzzleTitlePos1.bottom + canvasH / 15, font);
        canvas.drawBitmap(puzzleTitlePic3, puzzleTitleSrc3, puzzleTitlePos3, null);
        canvas.drawBitmap(puzzlePic, puzzleSrc, puzzlePos, null);
        for(int i = 0; i < PUZZLEPIECENUM; i++){
            if(puzzle.getCoverIsShown(i)){
                canvas.drawRect(puzzleCoverPos[i], coverPaint);
            }
        }
        canvas.drawBitmap(backPic, backSrc, backPos, null);

        Puzzle.setShowNoti(false);
    }

    private void updateGM(){
        switch (gameManagerPracticeMode.getCurrQuestMode()){
            case BUILD3DMODEL:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank2D3D.getIsCubePresent(i, j)){
                            gameManagerPracticeMode.setQuestIsCubePresentTrue(i, j);
                        }
                        else{
                            gameManagerPracticeMode.setQuestIsCubePresentFalse(i, j);
                        }
                    }
                }
                break;
            case SPTYPE3:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank_spType3_ans.getIsCubePresent(i, j)){
                            gameManagerPracticeMode.setQuestIsCubePresentTrue(i, j);
                        }
                        else{
                            gameManagerPracticeMode.setQuestIsCubePresentFalse(i, j);
                        }
                    }
                }
                break;
        }

        switch (gameManagerBattleMode.getCurrQuestMode()){
            case BUILD3DMODEL:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank2D3D.getIsCubePresent(i, j)){
                            gameManagerBattleMode.setQuestIsCubePresentTrue(i, j);
                        }
                        else{
                            gameManagerBattleMode.setQuestIsCubePresentFalse(i, j);
                        }
                    }
                }
                break;
            case SPTYPE3:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank_spType3_ans.getIsCubePresent(i, j)){
                            gameManagerBattleMode.setQuestIsCubePresentTrue(i, j);
                        }
                        else{
                            gameManagerBattleMode.setQuestIsCubePresentFalse(i, j);
                        }
                    }
                }
                break;
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                if(arduino.getIsCubePresent(i, j)){
                    gameManagerPracticeMode.setArdIsCubePresentTrue(i, j);
                    gameManagerBattleMode.setArdIsCubePresentTrue(i, j);
                }
                else{
                    gameManagerPracticeMode.setArdIsCubePresentFalse(i, j);
                    gameManagerBattleMode.setArdIsCubePresentFalse(i, j);
                }
            }
        }
    }

    private void drawContactUsPage(Canvas canvas){
        canvas.drawBitmap(backPic, backSrc, backPos, null);
        canvas.drawBitmap(contactUsTitlePic, contactUsTitleSrc, contactUsTitlePos, null);
        canvas.drawBitmap(contactUsPic, contactUsSrc, contactUsPos, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:

                if(!settingIsShown){
                    switch (currPage){
                        case MENUPAGE:
                            if(practiceModePos.contains(x, y)){
                                playerNum = 1;
                                currPage = HOWTOPAGEPM1;
                            }
                            else if(battleModePos.contains(x, y)){
                                currPage = BATTLENUMPAGE;
                            }
                            else if(puzzleModePos.contains(x, y)){
                                currPage = PUZZLEPAGE;
                            }
                            break;
                        case PRACTICEGAMEPAGE:
                            if(answerPos.contains(x, y )){
                                if(gameManagerPracticeMode.getCurrQuestNum() < MAXQUESTNUM){
                                    gameManagerPracticeMode.setSpType4PlayerAns(spType4PlayerChoice);
                                    updateGM();
                                    gameManagerPracticeMode.compare();
                                    resetDrawView();
                                }
                            }

                            if(gameManagerPracticeMode.getCurrQuestMode() == DRAWFRONTVIEW ||
                                    gameManagerPracticeMode.getCurrQuestMode() == DRAWSIDEVIEW ||
                                    gameManagerPracticeMode.getCurrQuestMode() == DRAWTOPVIEW){
                                for(int i = 0; i < drawViewPos2.length; i++){
                                    if(drawViewPos2[i].contains(x, y)){
                                        drawViewTouchCount = 0;
                                        drawViewTouch[i] = !drawViewTouch[i];
                                    }
                                }
                            }

                            if(gameManagerPracticeMode.getCurrQuestMode() == SPTYPE4){
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

                            if(timeRemapWidth == -1 && tipIconPos.contains(x, y)){
                                if(!tipIsShown){
                                    switch (gameManagerPracticeMode.getCurrQuestNum()){
                                        case 0:
                                            GameManager_PracticeMode.setTipUsed(0, gameManagerPracticeMode.getTipUsed(0) + 1);
                                            break;
                                        case 1:
                                            GameManager_PracticeMode.setTipUsed(1, gameManagerPracticeMode.getTipUsed(1) + 1);
                                            break;
                                        case 2:
                                            GameManager_PracticeMode.setTipUsed(2, gameManagerPracticeMode.getTipUsed(2) + 1);
                                            break;
                                        case 3:
                                            GameManager_PracticeMode.setTipUsed(3, gameManagerPracticeMode.getTipUsed(3) + 1);
                                            break;
                                        case 4:
                                            GameManager_PracticeMode.setTipUsed(4, gameManagerPracticeMode.getTipUsed(4) + 1);
                                            break;
                                        case 5:
                                            GameManager_PracticeMode.setTipUsed(5, gameManagerPracticeMode.getTipUsed(5) + 1);
                                            break;
                                    }
                                    tipIsShown = true;
                                }

                            }

                            if(timeRemapWidth == -1 && tipIsShown){
                                if(tipBoxPos.contains(x, y)){
                                    tipIsShown = true;
                                }
                                else{
                                    tipIsShown = false;
                                }
                            }

                            if(detectionIconPos.contains(x, y)){
                                detectionIsShown = true;
                            }

                            if(detectionIsShown){
                                if(detectionBoxPos.contains(x, y)){
                                    detectionIsShown = true;
                                }
                                else{
                                    detectionIsShown = false;
                                }
                            }

                            if(gameManagerPracticeMode.getIsResultPage()){
                                if(backPos.contains(x, y)){
                                    gameManagerPracticeMode.restart();
                                }
                            }

                            //------debug use-------
                            if(!gameManagerPracticeMode.getIsResultPage()){
                                if(x < canvasW && x > canvasW - 200 && y > canvasH / 2 - 200 && y < canvasH / 2 + 200){
                                    if(gameManagerPracticeMode.getCurrQuestNum() < MAXQUESTNUM - 1){
                                        gameManagerPracticeMode.nextQ();
                                        resetDrawView();
                                        //resetTimer();
                                    }
//                                    else{
//                                        resetDrawView();
//                                        resetTimer();
//                                        gameManagerPracticeMode.restart();
//                                        currPage = MENUPAGE;
//                                    }
                                }
                            }
                            //-----------------------


                            break;
                        case BATTLENUMPAGE:
                            if(battleModeNumPos.contains(x, y)){
                                if(player2Pos.contains(x, y)){
                                    playerNum = 2;
                                    currPage = HOWTOPAGEBM1;
                                }
                                else if(player3Pos.contains(x, y)){
                                    playerNum = 3;
                                    currPage = HOWTOPAGEBM1;
                                }
                                else if(player4Pos.contains(x, y)){
                                    playerNum = 4;
                                    currPage = HOWTOPAGEBM1;
                                }
                                resetTimer();
                            }
                            else{
                                currPage = MENUPAGE;
                            }
                            break;
                        case BATTLEGAMEPAGE:
                            switch (playerNum){
                                case 2:
                                    if(gameManagerBattleMode.getCurrStage() == QUESTSTAGE){
                                        if(redPlayerBtnLPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(REDANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                        else if(bluePlayerBtnLPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(BLUEANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                    }
                                    break;
                                case 3:
                                    if(gameManagerBattleMode.getCurrStage() == QUESTSTAGE){
                                        if(redPlayerBtnLPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(REDANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                        else if(bluePlayerBtnLPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(BLUEANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                        else if(greenPlayerBtnLPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(GREENANSWERSTAGE);
                                        }
                                    }
                                    break;
                                case 4:
                                    if(gameManagerBattleMode.getCurrStage() == QUESTSTAGE){
                                        if(redPlayerBtnSPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(REDANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                        else if(bluePlayerBtnSPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(BLUEANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                        else if(greenPlayerBtnSPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(GREENANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                        else if(purplePlayerBtnSPos.contains(x, y)){
                                            resetTimer();
                                            GameManager_BattleMode.setCurrStage(PURPLEANSWERSTAGE);
                                            answerCoolDown = 0;
                                        }
                                    }
                                    break;
                            }

                            if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE){
                                if(gameManagerBattleMode.getCurrQuestMode() == DRAWFRONTVIEW ||
                                        gameManagerBattleMode.getCurrQuestMode() == DRAWSIDEVIEW ||
                                        gameManagerBattleMode.getCurrQuestMode() == DRAWTOPVIEW){
                                    for(int i = 0; i < drawViewPos2.length; i++){
                                        if(drawViewPos2[i].contains(x, y)){
                                            drawViewTouchCount = 0;
                                            drawViewTouch[i] = !drawViewTouch[i];
                                        }
                                    }
                                }

                                if(gameManagerBattleMode.getCurrQuestMode() == SPTYPE4){
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

                                if(detectionIconPos.contains(x, y)){
                                    detectionIsShown = true;
                                }

                                if(detectionIsShown){
                                    if(detectionBoxPos.contains(x, y)){
                                        detectionIsShown = true;
                                    }
                                    else{
                                        detectionIsShown = false;
                                    }
                                }

                                if(answerPos.contains(x, y) && answerCoolDown <= 0){
                                    gameManagerBattleMode.compare();
                                }
                            }

                            if(gameManagerBattleMode.getCurrStage() == RESULTSTAGE){
                                if(backPos.contains(x, y)){
                                    gameManagerBattleMode.restart();
                                }
                            }


                            break;
                        case HOWTOPAGEPM1:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = PRACTICEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEPM2;
                            }
                            break;
                        case HOWTOPAGEPM2:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = PRACTICEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEPM3;
                            }
                            break;
                        case HOWTOPAGEPM3:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = PRACTICEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEPM4;
                            }
                            break;
                        case HOWTOPAGEPM4:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = PRACTICEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEPM5;
                            }
                            break;
                        case HOWTOPAGEPM5:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = PRACTICEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = PRACTICEGAMEPAGE;
                                isInGame = true;
                            }
                            break;
                        case HOWTOPAGEBM1:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = BATTLEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEBM2;
                            }
                            break;
                        case HOWTOPAGEBM2:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = BATTLEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEBM3;
                            }
                            break;
                        case HOWTOPAGEBM3:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = BATTLEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = HOWTOPAGEBM4;
                            }
                            break;
                        case HOWTOPAGEBM4:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            else if(skipPos.contains(x, y)){
                                currPage = BATTLEGAMEPAGE;
                                isInGame = true;
                            }
                            else if(nextPos.contains(x, y)){
                                currPage = BATTLEGAMEPAGE;
                                isInGame = true;
                            }
                            break;
                        case PUZZLEPAGE:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            break;
                        case HOWTOPAGESETTING:
                            if(PModeTitlePos.contains(x, y)){
                                currHowToSetting = PM1;
                            }
                            else if(BModeTitlePos.contains(x, y)){
                                currHowToSetting = BM1;
                            }
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            break;
                        case CONTACTUSPAGE:
                            if(backPos.contains(x, y)){
                                currPage = MENUPAGE;
                            }
                            break;

                    }

                    if(settingPos0.contains(x, y)){
                        settingIsShown = true;
                    }
                }
                else if(settingIsShown){
                    if(settingPos0.contains(x, y)){
                        settingIsShown = false;
                    }

                    if(settingPos1.contains(x, y) && musicIsPlay){
                        musicIsPlay = false;
                    }
//                    else if(settingPos1.contains(x, y) && !musicIsPlay){
//                        musicIsPlay = true;
//                    }

                    if(settingPos2.contains(x, y)){
                        if(!isInGame){
                            settingIsShown = false;
                            currPage = HOWTOPAGESETTING;
                        }
                        else if(isInGame){
                            if(playerNum == 1){
                                gameManagerPracticeMode.restart();
                                PMrestart();
                            }
                            else{
                                gameManagerBattleMode.restart();
                                BMrestart();
                            }
                            settingIsShown = false;
                            currPage = MENUPAGE;
                        }
                    }

                    if(settingPos3.contains(x, y)){
                        if(isInGame){
                            if(playerNum == 1){
                                gameManagerPracticeMode.restart();
                                PMrestart();
                            }
                            else{
                                gameManagerBattleMode.restart();
                                BMrestart();
                            }
                        }
                        settingIsShown = false;
                        currPage = CONTACTUSPAGE;
                    }

                }

                //practice mode: pause and play the timer
                if(settingPos0.contains(x, y) && currPage == PRACTICEGAMEPAGE){
                    if(gameManagerPracticeMode.getTimer30sIsRunning()){
                        gameManagerPracticeMode.timer30sPause();
                    }
                    else{
                        gameManagerPracticeMode.timer30sStart();
                    }
                }

                //battle mode: pause and play the timer
                if(settingPos0.contains(x, y) && currPage == BATTLEGAMEPAGE){
                    if(gameManagerBattleMode.getCurrStage() == QUESTSTAGE){
                        if(gameManagerBattleMode.getTimer10sIsRunning()){
                            gameManagerBattleMode.timer10sPause();
                        }
                        else{
                            gameManagerBattleMode.timer10sStart();
                        }
                    }
                    else if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE){
                        if(gameManagerBattleMode.getTimer120sIsRunning()){
                            gameManagerBattleMode.timer120sPause();
                        }
                        else{
                            gameManagerBattleMode.timer120sStart();
                        }
                    }
                }

                //swipe
                lastX = x;

                //------debug use-------
                if(x > canvasW / 2 - 200 && x < canvasW / 2 + 200 && y > 0 && y < 200){
                    debugVisible = !debugVisible;
                }
                //----------------------

                break;

            case MotionEvent.ACTION_MOVE:
                if(!settingIsShown){
                    switch (currPage){
                        case PRACTICEGAMEPAGE:
                            if(gameManagerPracticeMode.getCurrQuestMode() == DRAWFRONTVIEW ||
                                    gameManagerPracticeMode.getCurrQuestMode() == DRAWSIDEVIEW ||
                                    gameManagerPracticeMode.getCurrQuestMode() == DRAWTOPVIEW){
                                for(int i = 0; i < drawViewPos2.length; i++){
                                    if(drawViewPos2[i].contains(x, y)){
                                        drawViewTouchCount++;
                                    }
                                }
                            }
                            break;
                        case BATTLEGAMEPAGE:
                            if(gameManagerBattleMode.getCurrStage() >= REDANSWERSTAGE && gameManagerBattleMode.getCurrStage() <= PURPLEANSWERSTAGE) {
                                if(gameManagerBattleMode.getCurrQuestMode() == DRAWFRONTVIEW ||
                                        gameManagerBattleMode.getCurrQuestMode() == DRAWSIDEVIEW ||
                                        gameManagerBattleMode.getCurrQuestMode() == DRAWTOPVIEW){
                                    for(int i = 0; i < drawViewPos2.length; i++){
                                        if(drawViewPos2[i].contains(x, y)){
                                            drawViewTouchCount++;
                                        }
                                    }
                                }
                            }
                            break;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if(!settingIsShown){
                    switch (currPage){
                        case HOWTOPAGEPM1:
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEPM2;
                            }
                            break;
                        case HOWTOPAGEPM2:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEPM1;
                            }
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEPM3;
                            }
                            break;
                        case HOWTOPAGEPM3:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEPM2;
                            }
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEPM4;
                            }
                            break;
                        case HOWTOPAGEPM4:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEPM3;
                            }
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEPM5;
                            }
                            break;
                        case HOWTOPAGEPM5:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEPM4;
                            }
                            break;
                        case HOWTOPAGEBM1:
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEBM2;
                            }
                            break;
                        case HOWTOPAGEBM2:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEBM1;
                            }
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEBM3;
                            }
                            break;
                        case HOWTOPAGEBM3:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEBM2;
                            }
                            if(lastX - x > 100){
                                currPage = HOWTOPAGEBM4;
                            }
                            break;
                        case HOWTOPAGEBM4:
                            if(x - lastX > 100){
                                currPage = HOWTOPAGEBM3;
                            }
                            break;
                        case HOWTOPAGESETTING:
                            switch (currHowToSetting){
                                case PM1:
                                    if(lastX - x > 100){
                                        currHowToSetting = PM2;
                                    }
                                    break;
                                case PM2:
                                    if(x - lastX > 100){
                                        currHowToSetting = PM1;
                                    }
                                    if(lastX - x > 100){
                                        currHowToSetting = PM3;
                                    }
                                    break;
                                case PM3:
                                    if(x - lastX > 100){
                                        currHowToSetting = PM2;
                                    }
                                    if(lastX - x > 100){
                                        currHowToSetting = PM4;
                                    }
                                    break;
                                case PM4:
                                    if(x - lastX > 100){
                                        currHowToSetting = PM3;
                                    }
                                    if(lastX - x > 100){
                                        currHowToSetting = PM5;
                                    }
                                    break;
                                case PM5:
                                    if(x - lastX > 100){
                                        currHowToSetting = PM4;
                                    }
                                    break;
                                case BM1:
                                    if(lastX - x > 100){
                                        currHowToSetting = BM2;
                                    }
                                    break;
                                case BM2:
                                    if(x - lastX > 100){
                                        currHowToSetting = BM1;
                                    }
                                    if(lastX - x > 100){
                                        currHowToSetting = BM3;
                                    }
                                    break;
                                case BM3:
                                    if(x - lastX > 100){
                                        currHowToSetting = BM2;
                                    }
                                    if(lastX - x > 100){
                                        currHowToSetting = BM4;
                                    }
                                    break;
                                case BM4:
                                    if(x - lastX > 100){
                                        currHowToSetting = BM3;
                                    }
                                    break;
                            }
                            break;
                    }
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
        if(puzzleModePic != null && !puzzleModePic.isRecycled()){
            puzzleModePic.recycle();
            puzzleModePic = null;
        }
        if(disablePic != null && !disablePic.isRecycled()){
            disablePic.recycle();
            disablePic = null;
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
        if(howTo4Pic != null && !howTo4Pic.isRecycled()){
            howTo4Pic.recycle();
            howTo4Pic = null;
        }
        if(howTo5Pic != null && !howTo5Pic.isRecycled()){
            howTo5Pic.recycle();
            howTo5Pic = null;
        }
        if(howToBM1Pic != null && !howToBM1Pic.isRecycled()){
            howToBM1Pic.recycle();
            howToBM1Pic = null;
        }
        if(howToBM2Pic != null && !howToBM2Pic.isRecycled()){
            howToBM2Pic.recycle();
            howToBM2Pic = null;
        }
        if(howToBM3Pic != null && !howToBM3Pic.isRecycled()){
            howToBM3Pic.recycle();
            howToBM3Pic = null;
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
        if(howTo4WordPic1 != null && !howTo4WordPic1.isRecycled()){
            howTo4WordPic1.recycle();
            howTo4WordPic1 = null;
        }
        if(howTo4WordPic2 != null && !howTo4WordPic2.isRecycled()){
            howTo4WordPic2.recycle();
            howTo4WordPic2 = null;
        }
        if(howTo5WordPic != null && !howTo5WordPic.isRecycled()){
            howTo5WordPic.recycle();
            howTo5WordPic = null;
        }
        if(howToBM1WordPic1 != null && !howToBM1WordPic1.isRecycled()){
            howToBM1WordPic1.recycle();
            howToBM1WordPic1 = null;
        }
        if(howToBM1WordPic2 != null && !howToBM1WordPic2.isRecycled()){
            howToBM1WordPic2.recycle();
            howToBM1WordPic2 = null;
        }
        if(howToBM2WordPic != null && !howToBM2WordPic.isRecycled()){
            howToBM2WordPic.recycle();
            howToBM2WordPic = null;
        }
        if(howToBM3WordPic != null && !howToBM3WordPic.isRecycled()){
            howToBM3WordPic.recycle();
            howToBM3WordPic = null;
        }
        if(howToBM4WordPic != null && !howToBM4WordPic.isRecycled()){
            howToBM4WordPic.recycle();
            howToBM4WordPic = null;
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
        if(formFrontViewPic != null && !formFrontViewPic.isRecycled()){
            formFrontViewPic.recycle();
            formFrontViewPic = null;
        }
        if(formSideViewPic != null && !formSideViewPic.isRecycled()){
            formSideViewPic.recycle();
            formSideViewPic = null;
        }
        if(formTopViewPic != null && !formTopViewPic.isRecycled()){
            formTopViewPic.recycle();
            formTopViewPic = null;
        }
        if(formAnsPic != null && !formAnsPic.isRecycled()){
            formAnsPic.recycle();
            formAnsPic = null;
        }
        if(selectAnsPic != null && !selectAnsPic.isRecycled()){
            selectAnsPic.recycle();
            selectAnsPic = null;
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
        if(spType3Q3Pic != null && !spType3Q3Pic.isRecycled()){
            spType3Q3Pic.recycle();
            spType3Q3Pic = null;
        }
        if(spType3Q4Pic != null && !spType3Q4Pic.isRecycled()){
            spType3Q4Pic.recycle();
            spType3Q4Pic = null;
        }
        if(qSpType3BkgPic != null && !qSpType3BkgPic.isRecycled()){
            qSpType3BkgPic.recycle();
            qSpType3BkgPic = null;
        }
        if(tipIconPic != null && !tipIconPic.isRecycled()){
            tipIconPic.recycle();
            tipIconPic = null;
        }
        if(tipBoxPic != null && !tipBoxPic.isRecycled()){
            tipBoxPic.recycle();
            tipBoxPic = null;
        }
        if(tipBoxPic2 != null && !tipBoxPic2.isRecycled()){
            tipBoxPic2.recycle();
            tipBoxPic2 = null;
        }
        if(detectionIconPic != null && !detectionIconPic.isRecycled()){
            detectionIconPic.recycle();
            detectionIconPic = null;
        }
        if(detectionBoxPic != null && !detectionBoxPic.isRecycled()){
            detectionBoxPic.recycle();
            detectionBoxPic = null;
        }
        if(detectionModelBkgPic != null && !detectionModelBkgPic.isRecycled()){
            detectionModelBkgPic.recycle();
            detectionModelBkgPic = null;
        }
        if(puzzlePic != null && !puzzlePic.isRecycled()){
            puzzlePic.recycle();
            puzzlePic = null;
        }
        if(puzzleTitlePic1 != null && !puzzleTitlePic1.isRecycled()){
            puzzleTitlePic1.recycle();
            puzzleTitlePic1 = null;
        }
        if(puzzleTitlePic2 != null && !puzzleTitlePic2.isRecycled()){
            puzzleTitlePic2.recycle();
            puzzleTitlePic2 = null;
        }
        if(puzzleTitlePic3 != null && !puzzleTitlePic3.isRecycled()){
            puzzleTitlePic3.recycle();
            puzzleTitlePic3 = null;
        }
        if(redPlayerBtnLPic != null && !redPlayerBtnLPic.isRecycled()){
            redPlayerBtnLPic.recycle();
            redPlayerBtnLPic = null;
        }
        if(bluePlayerBtnLPic != null && !bluePlayerBtnLPic.isRecycled()){
            bluePlayerBtnLPic.recycle();
            bluePlayerBtnLPic = null;
        }
        if(greenPlayerBtnLPic != null && !greenPlayerBtnLPic.isRecycled()){
            greenPlayerBtnLPic.recycle();
            greenPlayerBtnLPic = null;
        }
        if(redPlayerBtnSPic != null && !redPlayerBtnSPic.isRecycled()){
            redPlayerBtnSPic.recycle();
            redPlayerBtnSPic = null;
        }
        if(bluePlayerBtnSPic != null && !bluePlayerBtnSPic.isRecycled()){
            bluePlayerBtnSPic.recycle();
            bluePlayerBtnSPic = null;
        }
        if(greenPlayerBtnSPic != null && !greenPlayerBtnSPic.isRecycled()){
            greenPlayerBtnSPic.recycle();
            greenPlayerBtnSPic = null;
        }
        if(purplePlayerBtnSPic != null && !purplePlayerBtnSPic.isRecycled()){
            purplePlayerBtnSPic.recycle();
            purplePlayerBtnSPic = null;
        }
        if(timesUpPic != null && !timesUpPic.isRecycled()){
            timesUpPic.recycle();
            timesUpPic = null;
        }
        if(redWinsPic != null && !redWinsPic.isRecycled()){
            redWinsPic.recycle();
            redWinsPic = null;
        }
        if(blueWinsPic != null && !blueWinsPic.isRecycled()){
            blueWinsPic.recycle();
            blueWinsPic = null;
        }
        if(greenWinsPic != null && !greenWinsPic.isRecycled()){
            greenWinsPic.recycle();
            greenWinsPic = null;
        }
        if(purpleWinsPic != null && !purpleWinsPic.isRecycled()){
            purpleWinsPic.recycle();
            purpleWinsPic = null;
        }
        if(itsADrawPic != null && !itsADrawPic.isRecycled()){
            itsADrawPic.recycle();
            itsADrawPic = null;
        }
        if(playerIconPic != null && !playerIconPic.isRecycled()){
            playerIconPic.recycle();
            playerIconPic = null;
        }
        if(profilePic != null && !profilePic.isRecycled()){
            profilePic.recycle();
            profilePic = null;
        }
        if(abilityPic0 != null && !abilityPic0.isRecycled()){
            abilityPic0.recycle();
            abilityPic0 = null;
        }
        if(abilityPic1 != null && !abilityPic1.isRecycled()){
            abilityPic1.recycle();
            abilityPic1 = null;
        }
        if(abilityPic2 != null && !abilityPic2.isRecycled()){
            abilityPic2.recycle();
            abilityPic2 = null;
        }
        if(abilityPic3 != null && !abilityPic3.isRecycled()){
            abilityPic3.recycle();
            abilityPic3 = null;
        }

    }
}
