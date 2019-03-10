package ytwong239.scm.bluetootharduino;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DebbieWong on 23/1/2019.
 */

public class OpenglTestSurfaceView extends GLSurfaceView{

    //private final OpenglTestRenderer mRenderer;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    public OpenglTestSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        //setEGLContextClientVersion(2);

        //mRenderer = new OpenglTestRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        //setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public OpenglTestSurfaceView(Context context, AttributeSet attribs) {
        super(context, attribs);

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }



//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        // MotionEvent reports input details from the touch screen
//        // and other input controls. In this case, you are only
//        // interested in events where the touch position changed.
//
//        float x = e.getX();
//        float y = e.getY();
//
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//
//                float dx = x - mPreviousX;
//                float dy = y - mPreviousY;
//
//                // reverse direction of rotation above the mid-line
//                if (y > getHeight() / 2) {
//                    dx = dx * -1 ;
//                }
//
//                // reverse direction of rotation to left of the mid-line
//                if (x < getWidth() / 2) {
//                    dy = dy * -1 ;
//                }
//
//                mRenderer.setAngle(
//                        mRenderer.getAngle() +
//                                ((dx + dy) * TOUCH_SCALE_FACTOR));
//                requestRender();
//        }
//
//        mPreviousX = x;
//        mPreviousY = y;
//        return true;
//    }

}
