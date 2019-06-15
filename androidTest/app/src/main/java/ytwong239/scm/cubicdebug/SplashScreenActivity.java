package ytwong239.scm.cubicdebug;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";

    SplashScreenView splashScreenView;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        splashScreenView = new SplashScreenView(this);
        setContentView(splashScreenView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    int step = 0;
    boolean step0 = false;
    boolean step1 = false;
    boolean step2 = false;
    boolean step3 = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_DOWN){

            switch (step){
                case 0:
                    switch (keyCode){
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            step0 = true;
                            step = 1;
                            return true;

                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            step0 = false;
                            step = 1;
                            return true;
                    }

                case 1:
                    switch (keyCode){
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            step1 = true;
                            step = 2;
                            return true;

                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            step1 = false;
                            step = 2;
                            return true;
                    }

                case 2:
                    switch (keyCode){
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            step2 = false;
                            step = 3;
                            return true;

                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            step2 = true;
                            step = 3;
                            return true;
                    }


                case 3:
                    switch (keyCode){
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            step3 = false;
                            step = 4;
                            return true;

                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            step3 = true;
                            step = 4;
                            return true;
                    }

                case 4:
                    switch (keyCode){
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            if(step0 && step1 && step2 && step3){
                                Log.d("dfsdfdssd", "sfsf");
                                step0 = false;
                                step1 = false;
                                step2 = false;
                                step3 = false;
                                step = 0;
                                splashScreenView.debugStart();
                            }
                            return true;

                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            step0 = false;
                            step1 = false;
                            step2 = false;
                            step3 = false;
                            step = 0;
                            return true;
                    }

            }

        }

        return super.onKeyDown(keyCode, event);
    }

}
