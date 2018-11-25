package ytwong239.scm.bluetootharduino;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DebbieWong on 19/11/2018.
 */

public class MainPage extends View {
    static final int MAXBASESNUM = 3;
    Base[] bases = new Base[MAXBASESNUM];
    String[] ardString = new String[MAXBASESNUM];

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

    }

    public void getArdString(String[] val){
        for(int i = 0; i < MAXBASESNUM; i++){
            ardString[i] = val[i];
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);


    }

}
