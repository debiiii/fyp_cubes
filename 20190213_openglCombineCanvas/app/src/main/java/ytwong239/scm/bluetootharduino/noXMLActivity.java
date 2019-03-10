package ytwong239.scm.bluetootharduino;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class noXMLActivity extends AppCompatActivity {

    TempPage tempPage;
    OpenglTestSurfaceView openglTestSurfaceView;
    OpenglTestRenderer openglTestRenderer;
    ViewGroup.LayoutParams mOverlayViewParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        tempPage = new TempPage(this);
        setContentView(tempPage);

        openglTestRenderer = new OpenglTestRenderer();
        openglTestSurfaceView = new OpenglTestSurfaceView(this);
        mOverlayViewParams = new ViewGroup.LayoutParams(300,300);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(400, 200);
        openglTestSurfaceView.setLayoutParams(params);

        ConstraintLayout.LayoutParams params1 = (ConstraintLayout.LayoutParams) openglTestSurfaceView.getLayoutParams();
        params1.topMargin = 20;
        params1.rightMargin = 20;
        openglTestSurfaceView.setLayoutParams(params1);

        openglTestSurfaceView.setEGLContextClientVersion(2);
        openglTestSurfaceView.setRenderer(openglTestRenderer);


        addContentView(openglTestSurfaceView, params);




    }
}
