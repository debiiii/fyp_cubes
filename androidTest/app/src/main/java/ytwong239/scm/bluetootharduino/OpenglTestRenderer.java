package ytwong239.scm.bluetootharduino;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.view.View.DRAWING_CACHE_QUALITY_HIGH;

/**
 * Created by DebbieWong on 23/1/2019.
 */

//public class OpenglTestRenderer implements GLSurfaceView.Renderer {
//
//    private Triangle mTriangle;
//    private Cube mCube;
//
//    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
//    private final float[] mMVPMatrix = new float[16];
//    private final float[] mProjectionMatrix = new float[16];
//    private final float[] mViewMatrix = new float[16];
//
//    private float[] mRotationMatrix = new float[16];
//
//    public volatile float mAngle;
//
//    @Override
//    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
//        // Set the background frame color
//        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
//
//        // initialize a triangle
//        mTriangle = new Triangle();
//        mCube = new Cube();
//    }
//
//    @Override
//    public void onDrawFrame(GL10 unused) {
//        float[] scratch = new float[16];
//
//        // Redraw background color
//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
//
//        // Set the camera position (View matrix)
//        //Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
//        Matrix.setLookAtM(mViewMatrix, 0, 2, 2, -6, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
//
//        // Calculate the projection and view transformation
//        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
//
//        //mTriangle.draw(mMVPMatrix);
//
//        // Create a rotation transformation for the triangle
////        long time = SystemClock.uptimeMillis() % 4000L;
////        float angle = 0.090f * ((int) time);
////        Matrix.setRotateM(mRotationMatrix, 0, angle, 0, 0, 1.0f);
//
//        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0f, 1f, 0f);
//
//        // Combine the rotation matrix with the projection and camera view
//        // Note that the mMVPMatrix factor *must be first* in order
//        // for the matrix multiplication product to be correct.
//        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);
//
//        // Draw triangle
//        //mTriangle.draw(scratch);
//        mCube.draw(scratch);
//    }
//
//    @Override
//    public void onSurfaceChanged(GL10 unused, int width, int height) {
//        GLES20.glViewport(0, 0, width, height);
//
//        float ratio = (float) width / height;
//
//        // this projection matrix is applied to object coordinates
//        // in the onDrawFrame() method
//        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
//    }
//
//    public static int loadShader(int type, String shaderCode){
//
//        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
//        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
//        int shader = GLES20.glCreateShader(type);
//
//        // add the source code to the shader and compile it
//        GLES20.glShaderSource(shader, shaderCode);
//        GLES20.glCompileShader(shader);
//
//        return shader;
//    }
//
//    public float getAngle() {
//        return mAngle;
//    }
//
//    public void setAngle(float angle) {
//        mAngle = angle;
//    }
//
//
//}


//https://www.programcreek.com/java-api-examples/index.php?source_dir=gdk-apidemo-sample-master/app/src/main/java/com/google/android/glass/sample/apidemo/opengl/Cube.java#
public class OpenglTestRenderer implements GLSurfaceView.Renderer {

    /** Rotation increment per frame. */
    private static final float CUBE_ROTATION_INCREMENT = 0.6f;

    /** The refresh rate, in frames per second. */
    private static final int REFRESH_RATE_FPS = 60;

    /** The duration, in milliseconds, of one frame. */
    private static final float FRAME_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1) / REFRESH_RATE_FPS;

    private final float[] mMVPMatrix;
    private final float[] mProjectionMatrix;
    private final float[] mViewMatrix;
    private final float[] mRotationMatrix;
    private final float[] mFinalMVPMatrix;
    private final float[] mFinalMVPMatrix2;
    private final float[] mFinalMVPMatrix3;
    private final float[] mFinalMVPMatrix4;

    private Cube mCube;
    private float mCubeRotation;
    private long mLastUpdateMillis;

    public OpenglTestRenderer(){
        mMVPMatrix = new float[16];
        mProjectionMatrix = new float[16];
        mViewMatrix = new float[16];
        mRotationMatrix = new float[16];
        mFinalMVPMatrix = new float[16];
        mFinalMVPMatrix2 = new float[16];
        mFinalMVPMatrix3 = new float[16];
        mFinalMVPMatrix4 = new float[16];

        // Set the fixed camera position (View matrix).
        Matrix.setLookAtM(mViewMatrix, 0, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);
        mCube = new Cube();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;

        GLES20.glViewport(0, 0, width, height);
        // This projection matrix is applied to object coordinates in the onDrawFrame() method.
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1.0f, 1.0f, 3.0f, 7.0f);
        // modelView = projection x view
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Apply the rotation.
        Matrix.setRotateM(mRotationMatrix, 0, mCubeRotation, 1.0f, 1.0f, 1.0f);
        // Combine the rotation matrix with the projection and camera view
        Matrix.multiplyMM(mFinalMVPMatrix, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        Matrix.multiplyMM(mFinalMVPMatrix2, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        Matrix.multiplyMM(mFinalMVPMatrix3, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        Matrix.multiplyMM(mFinalMVPMatrix4, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        // Draw cube.
        mCube.draw(mFinalMVPMatrix);

        Matrix.translateM(mFinalMVPMatrix2, 0, 0.2f, 0f, 0f);
        mCube.draw(mFinalMVPMatrix2);

        Matrix.translateM(mFinalMVPMatrix3, 0, 0.4f, 0f, 0f);
        mCube.draw(mFinalMVPMatrix3);

        Matrix.translateM(mFinalMVPMatrix4, 0, 0.2f, 0.2f, 0f);
        mCube.draw(mFinalMVPMatrix4);

        updateCubeRotation();
    }

    /** Updates the cube rotation. */
    private void updateCubeRotation() {
        if (mLastUpdateMillis != 0) {
            float factor = (SystemClock.elapsedRealtime() - mLastUpdateMillis) / FRAME_TIME_MILLIS;
            mCubeRotation += CUBE_ROTATION_INCREMENT * factor;
        }
        mLastUpdateMillis = SystemClock.elapsedRealtime();
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}

