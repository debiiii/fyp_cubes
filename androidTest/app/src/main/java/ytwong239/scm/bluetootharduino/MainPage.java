package ytwong239.scm.bluetootharduino;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DebbieWong on 19/11/2018.
 */

public class MainPage extends View {
    static final int MAXBASESNUM = 3;
    static final int MAXGRIDSNUM = 9;
    static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    static final int MAXHEIGHTNUM = 3;

    Base[] bases = new Base[MAXBASESNUM];

    //27 arduino value string
    String[] ardAllString = new String[MAXCUBESNUM];

    //divided to 3 arduino value string for 3 bases
    String[] ardBase0String = new String[MAXGRIDSNUM];
    String[] ardBase1String = new String[MAXGRIDSNUM];
    String[] ardBase2String = new String[MAXGRIDSNUM];

    Boolean[][] isCubePresentFinal = new Boolean [MAXGRIDSNUM][MAXHEIGHTNUM];

    Paint red = new Paint();
    Paint gray = new Paint();
    Paint blue = new Paint();
    Paint white = new Paint();
    Paint whiteStroke = new Paint();

    Bitmap bkg = null;
    Bitmap[][] cubePic = new Bitmap[MAXGRIDSNUM][MAXHEIGHTNUM];

    RectF debugBtnRect;
    Boolean debugVisible;

    Integer[] frontView = new Integer[MAXGRIDSNUM];
    Integer[] sideView = new Integer[MAXGRIDSNUM];
    Integer[] topView = new Integer[MAXGRIDSNUM];

    public MainPage(Context context) {
        super(context);
        init();
    }

    public MainPage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainPage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){
        red.setColor(Color.RED);
        red.setTextSize(40);

        gray.setColor(Color.rgb(38,38,38));
        gray.setTextSize(40);

        blue.setColor(Color.rgb(141,188,255));
        blue.setTextSize(40);

        white.setColor(Color.WHITE);
        white.setTextSize(40);

        whiteStroke.setStyle(Paint.Style.STROKE);
        whiteStroke.setColor(Color.WHITE);
        whiteStroke.setStrokeWidth(10);


        for(int i = 0; i < MAXBASESNUM; i++){
            bases[i] = new Base();
        }


        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                isCubePresentFinal[i][j] = false;
            }
        }

        bkg = BitmapFactory.decodeResource(getResources(), R.drawable.bkg);
        cubePic[0][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_0_0);
        cubePic[0][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_0_1);
        cubePic[0][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_0_2);

        cubePic[1][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_1_0);
        cubePic[1][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_1_1);
        cubePic[1][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_1_2);

        cubePic[2][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_2_0);
        cubePic[2][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_2_1);
        cubePic[2][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_2_2);

        cubePic[3][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_3_0);
        cubePic[3][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_3_1);
        cubePic[3][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_3_2);

        cubePic[4][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_4_0);
        cubePic[4][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_4_1);
        cubePic[4][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_4_2);

        cubePic[5][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_5_0);
        cubePic[5][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_5_1);
        cubePic[5][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_5_2);

        cubePic[6][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_6_0);
        cubePic[6][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_6_1);
        cubePic[6][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_6_2);

        cubePic[7][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_7_0);
        cubePic[7][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_7_1);
        cubePic[7][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_7_2);

        cubePic[8][0] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_8_0);
        cubePic[8][1] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_8_1);
        cubePic[8][2] = BitmapFactory.decodeResource(getResources(), R.drawable.cube_8_2);


        debugBtnRect = new RectF(940, 1700, 1040, 1800);
        debugVisible = false;

        for(int i = 0; i < MAXGRIDSNUM; i++){
            frontView[i] = 0;
            sideView[i] = 0;
            topView[i] = 0;
        }

    }

    public void updateArdAllString(String[] val){
        for(int i = 0; i < MAXCUBESNUM; i++){
            ardAllString[i] = val[i];
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(bkg, 0, 100, null);
        canvas.drawRect(debugBtnRect, gray);

        convertArdForBase();

        bases[0].update(ardBase0String);
        bases[1].update(ardBase1String);
        bases[2].update(ardBase2String);
        
        updateCubesFinal();
        updateView();

        drawCubes(canvas);
        drawView(canvas);

        if(debugVisible){
            drawDebug(canvas);
        }

    }

    private void convertArdForBase(){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            ardBase0String[i] = ardAllString[i];
            ardBase1String[i] = ardAllString[i + MAXGRIDSNUM];
            ardBase2String[i] = ardAllString[i + MAXGRIDSNUM * 2];
        }
    }

    private void updateCubesFinal() {

        /*-------------grid0-------------*/
//        //0,0
//        if(bases[0].grids[0].isCubePresent(0) || bases[1].grids[0].isCubePresent(0) || bases[2].grids[0].isCubePresent(2)){
//            isCubePresentFinal[0][0][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][0][0] = false;
//        }
//
//        //0,1
//        if(bases[0].grids[0].isCubePresent(1) || bases[1].grids[1].isCubePresent(0) || bases[2].grids[1].isCubePresent(2)){
//            isCubePresentFinal[0][0][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][0][1] = false;
//        }
//
//        //0,2
//        if(bases[0].grids[0].isCubePresent(2) || bases[1].grids[2].isCubePresent(0) || bases[2].grids[2].isCubePresent(2)){
//            isCubePresentFinal[0][0][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][0][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[0].isCubePresent(i) || bases[1].grids[i].isCubePresent(0) || bases[2].grids[i].isCubePresent(2)){
                isCubePresentFinal[0][i] = true;
            }
            else{
                isCubePresentFinal[0][i] = false;
            }
        }

        /*-------------grid1-------------*/

//        //1,0
//        if(bases[0].grids[1].isCubePresent(0) || bases[1].grids[3].isCubePresent(0) || bases[2].grids[0].isCubePresent(1)){
//            isCubePresentFinal[0][1][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][1][0] = false;
//        }
//
//        //1,1
//        if(bases[0].grids[1].isCubePresent(1) || bases[1].grids[4].isCubePresent(0) || bases[2].grids[1].isCubePresent(1)){
//            isCubePresentFinal[0][1][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][1][1] = false;
//        }
//
//        //1,2
//        if(bases[0].grids[1].isCubePresent(2) || bases[1].grids[5].isCubePresent(0) || bases[2].grids[2].isCubePresent(1)){
//            isCubePresentFinal[0][1][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][1][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[1].isCubePresent(i) || bases[1].grids[i + 3].isCubePresent(0) || bases[2].grids[i].isCubePresent(1)){
                isCubePresentFinal[1][i] = true;
            }
            else{
                isCubePresentFinal[1][i] = false;
            }
        }

        /*-------------grid2-------------*/

//        //2,0
//        if(bases[0].grids[2].isCubePresent(0) || bases[1].grids[6].isCubePresent(0) || bases[2].grids[0].isCubePresent(0)){
//            isCubePresentFinal[0][2][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][2][0] = false;
//        }
//
//        //2,1
//        if(bases[0].grids[2].isCubePresent(1) || bases[1].grids[7].isCubePresent(0) || bases[2].grids[1].isCubePresent(0)){
//            isCubePresentFinal[0][2][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][2][1] = false;
//        }
//
//        //2,2
//        if(bases[0].grids[2].isCubePresent(2) || bases[1].grids[8].isCubePresent(0) || bases[2].grids[2].isCubePresent(0)){
//            isCubePresentFinal[0][2][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][2][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[2].isCubePresent(i) || bases[1].grids[i + 3 + 3].isCubePresent(0) || bases[2].grids[i].isCubePresent(0)){
                isCubePresentFinal[2][i] = true;
            }
            else{
                isCubePresentFinal[2][i] = false;
            }
        }

        /*-------------grid3-------------*/

//        //3,0
//        if(bases[0].grids[3].isCubePresent(0) || bases[1].grids[0].isCubePresent(1) || bases[2].grids[3].isCubePresent(2)){
//            isCubePresentFinal[0][3][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][3][0] = false;
//        }
//
//        //3,1
//        if(bases[0].grids[3].isCubePresent(1) || bases[1].grids[1].isCubePresent(1) || bases[2].grids[4].isCubePresent(2)){
//            isCubePresentFinal[0][3][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][3][1] = false;
//        }
//
//        //3,2
//        if(bases[0].grids[3].isCubePresent(2) || bases[1].grids[2].isCubePresent(1) || bases[2].grids[5].isCubePresent(2)){
//            isCubePresentFinal[0][3][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][3][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[3].isCubePresent(i) || bases[1].grids[i].isCubePresent(1) || bases[2].grids[i + 3].isCubePresent(2)){
                isCubePresentFinal[3][i] = true;
            }
            else{
                isCubePresentFinal[3][i] = false;
            }
        }

        /*-------------grid4-------------*/

//        //4,0
//        if(bases[0].grids[4].isCubePresent(0) || bases[1].grids[3].isCubePresent(1) || bases[2].grids[3].isCubePresent(1)){
//            isCubePresentFinal[0][4][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][4][0] = false;
//        }
//
//        //4,1
//        if(bases[0].grids[4].isCubePresent(1) || bases[1].grids[4].isCubePresent(1) || bases[2].grids[4].isCubePresent(1)){
//            isCubePresentFinal[0][4][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][4][1] = false;
//        }
//
//        //4,2
//        if(bases[0].grids[4].isCubePresent(2) || bases[1].grids[5].isCubePresent(1) || bases[2].grids[5].isCubePresent(1)){
//            isCubePresentFinal[0][4][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][4][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[4].isCubePresent(i) || bases[1].grids[i + 3].isCubePresent(1) || bases[2].grids[i + 3].isCubePresent(1)){
                isCubePresentFinal[4][i] = true;
            }
            else{
                isCubePresentFinal[4][i] = false;
            }
        }

        /*-------------grid5-------------*/

//        //5,0
//        if(bases[0].grids[5].isCubePresent(0) || bases[1].grids[6].isCubePresent(1) || bases[2].grids[3].isCubePresent(0)){
//            isCubePresentFinal[0][5][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][5][0] = false;
//        }
//
//        //5,1
//        if(bases[0].grids[5].isCubePresent(1) || bases[1].grids[7].isCubePresent(1) || bases[2].grids[4].isCubePresent(0)){
//            isCubePresentFinal[0][5][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][5][1] = false;
//        }
//
//        //5,2
//        if(bases[0].grids[5].isCubePresent(2) || bases[1].grids[8].isCubePresent(1) || bases[2].grids[5].isCubePresent(0)){
//            isCubePresentFinal[0][5][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][5][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[5].isCubePresent(i) || bases[1].grids[i + 3 + 3].isCubePresent(1) || bases[2].grids[i + 3].isCubePresent(0)){
                isCubePresentFinal[5][i] = true;
            }
            else{
                isCubePresentFinal[5][i] = false;
            }
        }

        /*-------------grid6-------------*/

//        //6,0
//        if(bases[0].grids[6].isCubePresent(0) || bases[1].grids[0].isCubePresent(2) || bases[2].grids[6].isCubePresent(2)){
//            isCubePresentFinal[0][6][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][6][0] = false;
//        }
//
//        //6,1
//        if(bases[0].grids[6].isCubePresent(1) || bases[1].grids[1].isCubePresent(2) || bases[2].grids[7].isCubePresent(2)){
//            isCubePresentFinal[0][6][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][6][1] = false;
//        }
//
//        //6,2
//        if(bases[0].grids[6].isCubePresent(2) || bases[1].grids[2].isCubePresent(2) || bases[2].grids[8].isCubePresent(2)){
//            isCubePresentFinal[0][6][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][6][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[6].isCubePresent(i) || bases[1].grids[i].isCubePresent(2) || bases[2].grids[i + 3 + 3].isCubePresent(2)){
                isCubePresentFinal[6][i] = true;
            }
            else{
                isCubePresentFinal[6][i] = false;
            }
        }

        /*-------------grid7-------------*/

//        //7,0
//        if(bases[0].grids[7].isCubePresent(0) || bases[1].grids[3].isCubePresent(2) || bases[2].grids[6].isCubePresent(1)){
//            isCubePresentFinal[0][7][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][7][0] = false;
//        }
//
//        //7,1
//        if(bases[0].grids[7].isCubePresent(1) || bases[1].grids[4].isCubePresent(2) || bases[2].grids[7].isCubePresent(1)){
//            isCubePresentFinal[0][7][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][7][1] = false;
//        }
//
//        //7,2
//        if(bases[0].grids[7].isCubePresent(2) || bases[1].grids[5].isCubePresent(2) || bases[2].grids[8].isCubePresent(1)){
//            isCubePresentFinal[0][7][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][7][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[7].isCubePresent(i) || bases[1].grids[i + 3].isCubePresent(2) || bases[2].grids[i + 3 + 3].isCubePresent(1)){
                isCubePresentFinal[7][i] = true;
            }
            else{
                isCubePresentFinal[7][i] = false;
            }
        }

        /*-------------grid8-------------*/

//        //8,0
//        if(bases[0].grids[8].isCubePresent(0) || bases[1].grids[6].isCubePresent(2) || bases[2].grids[6].isCubePresent(0)){
//            isCubePresentFinal[0][8][0] = true;
//        }
//        else{
//            isCubePresentFinal[0][8][0] = false;
//        }
//
//        //8,1
//        if(bases[0].grids[8].isCubePresent(1) || bases[1].grids[7].isCubePresent(2) || bases[2].grids[7].isCubePresent(0)){
//            isCubePresentFinal[0][8][1] = true;
//        }
//        else{
//            isCubePresentFinal[0][8][1] = false;
//        }
//
//        //8,2
//        if(bases[0].grids[8].isCubePresent(2) || bases[1].grids[8].isCubePresent(2) || bases[2].grids[8].isCubePresent(0)){
//            isCubePresentFinal[0][8][2] = true;
//        }
//        else{
//            isCubePresentFinal[0][8][2] = false;
//        }

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[8].isCubePresent(i) || bases[1].grids[i + 3 + 3].isCubePresent(2) || bases[2].grids[i + 3 + 3].isCubePresent(0)){
                isCubePresentFinal[8][i] = true;
            }
            else{
                isCubePresentFinal[8][i] = false;
            }
        }

    }

    private void updateView(){

        //update front view
        for(int i = 2, j = 0; i >= 0 && j < 9; i--, j+=3){
            if(isCubePresentFinal[6][i]){
                frontView[j] = 1;
            }
            else if(isCubePresentFinal[3][i]){
                frontView[j] = 1;
            }
            else if(isCubePresentFinal[0][i]){
                frontView[j] = 1;
            }
            else{
                frontView[j] = 0;
            }

            if(isCubePresentFinal[7][i]){
                frontView[j + 1] = 1;
            }
            else if(isCubePresentFinal[4][i]){
                frontView[j + 1] = 1;
            }
            else if(isCubePresentFinal[1][i]){
                frontView[j + 1] = 1;
            }
            else{
                frontView[j + 1] = 0;
            }

            if(isCubePresentFinal[8][i]){
                frontView[j + 2] = 1;
            }
            else if(isCubePresentFinal[5][i]){
                frontView[j + 2] = 1;
            }
            else if(isCubePresentFinal[2][i]){
                frontView[j + 2] = 1;
            }
            else{
                frontView[j + 2] = 0;
            }
        }

        //update side view
        for(int i = 2, j = 0; i >= 0 && j < 9; i--, j+=3){
            if(isCubePresentFinal[0][i]){
                sideView[j] = 1;
            }
            else if(isCubePresentFinal[1][i]){
                sideView[j] = 1;
            }
            else if(isCubePresentFinal[2][i]){
                sideView[j] = 1;
            }
            else{
                sideView[j] = 0;
            }

            if(isCubePresentFinal[3][i]){
                sideView[j + 1] = 1;
            }
            else if(isCubePresentFinal[4][i]){
                sideView[j + 1] = 1;
            }
            else if(isCubePresentFinal[5][i]){
                sideView[j + 1] = 1;
            }
            else{
                sideView[j + 1] = 0;
            }

            if(isCubePresentFinal[6][i]){
                sideView[j + 2] = 1;
            }
            else if(isCubePresentFinal[7][i]){
                sideView[j + 2] = 1;
            }
            else if(isCubePresentFinal[8][i]){
                sideView[j + 2] = 1;
            }
            else{
                sideView[j + 2] = 0;
            }
        }

        //update top view
        for(int i = 0; i < 9; i++){
            if(isCubePresentFinal[i][2]){
                topView[i] = 1;
            }
            else if(isCubePresentFinal[i][1]){
                topView[i] = 1;
            }
            else if(isCubePresentFinal[i][0]){
                topView[i] = 1;
            }
            else{
                topView[i] = 0;
            }
        }
    }

    private void drawCubes(Canvas canvas) {
        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++) {
                if (isCubePresentFinal[i][j]) {
                    canvas.drawBitmap(cubePic[i][j], 0, 100, null);
                }
            }
        }

    }

    private void drawView(Canvas canvas) {
        canvas.drawText("Front view: ", 100, 1240, gray);
        canvas.drawText("Side view: ", 400, 1240, gray);
        canvas.drawText("Top view: ", 700, 1240, gray);
        int row = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(frontView[row + j] == 1){
                    canvas.drawRect( 100 + j * 80, 1260 + i * 80, 100 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect( 100 + j * 80, 1260 + i * 80, 100 + j * 80 + 80, 1260 + i * 80 + 80, blue);
                }
                else{
                    canvas.drawRect( 100 + j * 80, 1260 + i * 80, 100 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect(100 + j * 80, 1260 + i * 80, 100 + j * 80 + 80, 1260 + i * 80 + 80, gray);
                }

                if(sideView[row + j] == 1){
                    canvas.drawRect( 400 + j * 80, 1260 + i * 80, 400 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect( 400 + j * 80, 1260 + i * 80, 400 + j * 80 + 80, 1260 + i * 80 + 80, blue);
                }
                else{
                    canvas.drawRect( 400 + j * 80, 1260 + i * 80, 400 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect(400 + j * 80, 1260 + i * 80, 400 + j * 80 + 80, 1260 + i * 80 + 80, gray);
                }

                if(topView[row + j] == 1){
                    canvas.drawRect( 700 + j * 80, 1260 + i * 80, 700 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect( 700 + j * 80, 1260 + i * 80, 700 + j * 80 + 80, 1260 + i * 80 + 80, blue);
                }
                else{
                    canvas.drawRect( 700 + j * 80, 1260 + i * 80, 700 + j * 80 + 80, 1260 + i * 80 + 80, whiteStroke);
                    canvas.drawRect(700 + j * 80, 1260 + i * 80, 700 + j * 80 + 80, 1260 + i * 80 + 80, gray);
                }

                if(j == 2){
                    row += 3;
                }
            }
        }
    }

    private void drawDebug(Canvas canvas) {

        canvas.drawRect(0, 0, 450, 1980, white);

        for(int i = 0; i < MAXGRIDSNUM; i++){
            canvas.drawText("b" + i + ": " + ardBase0String[i], 10, 50 + i * 70, red);
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                canvas.drawText(String.valueOf(bases[0].grids[i].isCubePresent(j)), 150 + j * 100, 50 + i * 70, red);
                canvas.drawText(String.valueOf(bases[1].grids[i].isCubePresent(j)), 150 + j * 100, 670 + i * 70, gray);
                canvas.drawText(String.valueOf(bases[2].grids[i].isCubePresent(j)), 150 + j * 100, 1280 + i * 70, blue);
            }
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            canvas.drawText("b" + i + ": " + ardBase1String[i], 10, 670 + i * 70, gray);
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            canvas.drawText("b" + i + ": " + ardBase2String[i], 10, 1280 + i * 70, blue);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        if(debugBtnRect.contains(x,y)){
            debugVisible = true;
        }
        else{
            debugVisible = false;
        }



        invalidate();
        return true;
    }

}
