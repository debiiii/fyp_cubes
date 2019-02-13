package ytwong239.scm.bluetootharduino;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class OpenglTestActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGLView = new OpenglTestSurfaceView(this);
        setContentView(mGLView);

        //setContentView(R.layout.activity_opengl_test);
    }
}
