package ytwong239.scm.bluetootharduino;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

    Boolean[][] isCubePresentFinal = new Boolean[MAXGRIDSNUM][MAXHEIGHTNUM];

    Paint red = new Paint();
    Paint gray = new Paint();
    Paint blue = new Paint();

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

        gray.setColor(Color.DKGRAY);
        gray.setTextSize(40);

        blue.setColor(Color.BLUE);
        blue.setTextSize(40);

        for(int i = 0; i < MAXBASESNUM; i++){
            bases[i] = new Base();
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                isCubePresentFinal[i][j] = false;
            }
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
        convertArdForBase();

        bases[0].update(ardBase0String);
        bases[1].update(ardBase1String);
        bases[2].update(ardBase2String);
        
        calFinal();

        debug(canvas);
    }

    private void convertArdForBase(){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            ardBase0String[i] = ardAllString[i];
            ardBase1String[i] = ardAllString[i + MAXGRIDSNUM];
            ardBase2String[i] = ardAllString[i + MAXGRIDSNUM * 2];
        }
    }

    private void calFinal() {
        //0,0
        if(bases[0].grids[0].isCubePresent(0) || bases[1].grids[0].isCubePresent(0) || bases[2].grids[0].isCubePresent(2)){
            isCubePresentFinal[0][0] = true;
        }
        else{
            isCubePresentFinal[0][0] = false;
        }

        //0,1
    }

    private void debug(Canvas canvas) {
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

}
