package ytwong239.scm.bluetootharduino;

import android.graphics.PixelFormat;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class ViewGroupActivity extends AppCompatActivity {

    OpenglTestRenderer openglTestRenderer;
    OpenglTestSurfaceView openglTestSurfaceView;

    private ViewGroup.LayoutParams mOverlayViewParams;
    private TempPage tempPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);


        openglTestRenderer = new OpenglTestRenderer();
        openglTestSurfaceView = (OpenglTestSurfaceView) findViewById(R.id.glView);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) openglTestSurfaceView.getLayoutParams();
        params.topMargin = 20;
        params.rightMargin = 20;
        openglTestSurfaceView.setLayoutParams(params);
        openglTestSurfaceView.setEGLConfigChooser(true);
        openglTestSurfaceView.setEGLContextClientVersion(2);
        openglTestSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openglTestSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openglTestSurfaceView.setZOrderOnTop(true);
        openglTestSurfaceView.setRenderer(openglTestRenderer);

        tempPage = new TempPage(this);
        mOverlayViewParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // ADD Canvas view overlay
        addContentView(tempPage, mOverlayViewParams );



    }

    @Override
    public void onPause() {
        super.onPause();
        openglTestSurfaceView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        openglTestSurfaceView.onResume();
    }

}
