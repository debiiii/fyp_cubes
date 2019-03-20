package ytwong239.scm.cubic;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by DebbieWong on 18/3/2019.
 */

public class OpenGLRenderer_SPType4_Choice0 implements GLSurfaceView.Renderer {
    private static final float CUBE_ROTATION_INCREMENT = 0.6f;
    private static final int REFRESH_RATE_FPS = 60;
    private static final float FRAME_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1) / REFRESH_RATE_FPS;

    private final float[] mMVPMatrix;
    private final float[] mProjectionMatrix;
    private final float[] mViewMatrix;
    private final float[] mRotationMatrix;
    private final float[][][] mFinalMVPMatrixs;

    private Cube_Orange mCubeOrange;
    private float mCubeRotation;
    private long mLastUpdateMillis;

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    QuestionBank_SPType4_Base questionBank_spType4_base = new QuestionBank_SPType4_Base();
    QuestionBank_SPType4_Ans questionBank_spType4_ans = new QuestionBank_SPType4_Ans();
    private static boolean canDraw = false;
    private static int choice;
    private static int rotateY;


    Random random = new Random();

    public OpenGLRenderer_SPType4_Choice0(){
        mMVPMatrix = new float[16];
        mProjectionMatrix = new float[16];
        mViewMatrix = new float[16];
        mRotationMatrix = new float[16];

        mFinalMVPMatrixs = new float[MAXGRIDSNUM][MAXHEIGHTNUM][1];
        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                mFinalMVPMatrixs[i][j] = new float[16];
            }
        }

        Matrix.setLookAtM(mViewMatrix, 0, -2f, 2f, 2.3f, 0f, 0f, 0f, 0f, 1f, 0f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glDisable(GLES20.GL_DITHER);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);
        mCubeOrange = new Cube_Orange();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float ratio = (float) width / height;
        GLES20.glViewport(0, 0, width, height);
        float size = 0.5f;
        float gap = ratio / 2.43f;
        float left = (-ratio / size) + gap;
        float right = (ratio / size) + gap;
        gap = ratio / 8.9f;
        float bottom = (-1f / size) + gap;
        float top = (1f / size) + gap;
        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, 3.0f, 7.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //Matrix.setRotateM(mRotationMatrix, 0, mCubeRotation, 1.0f, 1.0f, 1.0f);
        Matrix.multiplyMM(mFinalMVPMatrixs[0][0], 0, mMVPMatrix, 0, mRotationMatrix, 0);

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                Matrix.multiplyMM(mFinalMVPMatrixs[i][j], 0, mMVPMatrix, 0, mRotationMatrix, 0);
            }
        }

        Matrix.translateM(mFinalMVPMatrixs[0][0], 0, -0.2f, 0f, -0.2f);
        Matrix.translateM(mFinalMVPMatrixs[1][0], 0, 0f, 0f, -0.2f);
        Matrix.translateM(mFinalMVPMatrixs[2][0], 0, 0.2f, 0f, -0.2f);

        Matrix.translateM(mFinalMVPMatrixs[3][0], 0, -0.2f, 0f, 0f);
        Matrix.translateM(mFinalMVPMatrixs[5][0], 0, 0.2f, 0f, 0f);

        Matrix.translateM(mFinalMVPMatrixs[6][0], 0, -0.2f, 0f, 0.2f);
        Matrix.translateM(mFinalMVPMatrixs[7][0], 0, 0f, 0f, 0.2f);
        Matrix.translateM(mFinalMVPMatrixs[8][0], 0, 0.2f, 0f, 0.2f);

        Matrix.translateM(mFinalMVPMatrixs[0][1], 0, -0.2f, 0.2f, -0.2f);
        Matrix.translateM(mFinalMVPMatrixs[1][1], 0, 0f, 0.2f, -0.2f);
        Matrix.translateM(mFinalMVPMatrixs[2][1], 0, 0.2f, 0.2f, -0.2f);

        Matrix.translateM(mFinalMVPMatrixs[3][1], 0, -0.2f, 0.2f, 0f);
        Matrix.translateM(mFinalMVPMatrixs[4][1], 0, 0f, 0.2f, 0f);
        Matrix.translateM(mFinalMVPMatrixs[5][1], 0, 0.2f, 0.2f, 0f);

        Matrix.translateM(mFinalMVPMatrixs[6][1], 0, -0.2f, 0.2f, 0.2f);
        Matrix.translateM(mFinalMVPMatrixs[7][1], 0, 0f, 0.2f, 0.2f);
        Matrix.translateM(mFinalMVPMatrixs[8][1], 0, 0.2f, 0.2f, 0.2f);

        Matrix.translateM(mFinalMVPMatrixs[0][2], 0, -0.2f, 0.4f, -0.2f);
        Matrix.translateM(mFinalMVPMatrixs[1][2], 0, 0f, 0.4f, -0.2f);
        Matrix.translateM(mFinalMVPMatrixs[2][2], 0, 0.2f, 0.4f, -0.2f);

        Matrix.translateM(mFinalMVPMatrixs[3][2], 0, -0.2f, 0.4f, 0f);
        Matrix.translateM(mFinalMVPMatrixs[4][2], 0, 0f, 0.4f, 0f);
        Matrix.translateM(mFinalMVPMatrixs[5][2], 0, 0.2f, 0.4f, 0f);

        Matrix.translateM(mFinalMVPMatrixs[6][2], 0, -0.2f, 0.4f, 0.2f);
        Matrix.translateM(mFinalMVPMatrixs[7][2], 0, 0f, 0.4f, 0.2f);
        Matrix.translateM(mFinalMVPMatrixs[8][2], 0, 0.2f, 0.4f, 0.2f);


        if(canDraw){

            switch (choice){
                //ans
                case 0:
                    for(int i = 0; i < MAXGRIDSNUM; i++){
                        for(int j = 0; j < MAXHEIGHTNUM; j++){
                            if(questionBank_spType4_ans.getIsCubePresent(i, j)){
                                mCubeOrange.draw(mFinalMVPMatrixs[i][j]);
                            }
                        }
                    }
                    break;
                //base
                case 1:
                    mCubeRotation = rotateY;
                    Matrix.setRotateM(mRotationMatrix, 0, mCubeRotation, 0.0f, 1.0f, 0.0f);

                    for(int i = 0; i < MAXGRIDSNUM; i++){
                        for(int j = 0; j < MAXHEIGHTNUM; j++){
                            if(questionBank_spType4_base.getIsCubePresent(i, j)){
                                mCubeOrange.draw(mFinalMVPMatrixs[i][j]);
                            }
                        }
                    }
                    break;
                //base
                case 2:
                    for(int i = 0; i < MAXGRIDSNUM; i++){
                        for(int j = 0; j < MAXHEIGHTNUM; j++){
                            if(questionBank_spType4_base.getIsCubePresent(i, j)){
                                mCubeOrange.draw(mFinalMVPMatrixs[i][j]);
                            }
                        }
                    }
                    break;
                //base
                case 3:
                    for(int i = 0; i < MAXGRIDSNUM; i++){
                        for(int j = 0; j < MAXHEIGHTNUM; j++){
                            if(questionBank_spType4_base.getIsCubePresent(i, j)){
                                mCubeOrange.draw(mFinalMVPMatrixs[i][j]);
                            }
                        }
                    }
                    break;
            }
        }
        else{
            clearBuffers(true, true,true);
        }

        //updateCubeRotation();
    }

    private void clearBuffers(boolean color, boolean depth, boolean stencil) {
        int bits = 0;
        if (color) {
            bits = GLES20.GL_COLOR_BUFFER_BIT;
        }
        if (depth) {
            bits |= GLES20.GL_DEPTH_BUFFER_BIT;
        }
        if (stencil) {
            bits |= GLES20.GL_STENCIL_BUFFER_BIT;
        }
        if (bits != 0) {
            GLES20.glClear(bits);
        }
    }

    private void updateCubeRotation() {
        if (mLastUpdateMillis != 0) {
            float factor = (SystemClock.elapsedRealtime() - mLastUpdateMillis) / FRAME_TIME_MILLIS;
            mCubeRotation += CUBE_ROTATION_INCREMENT * factor;
        }
        mLastUpdateMillis = SystemClock.elapsedRealtime();
    }

    public static int loadShader(int type, String shaderCode){

        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static void setCanDraw(boolean val){
        canDraw = val;
    }

    public static void setChoice(int val){
        choice = val;
    }

    public static void setRotateY(int val){
        rotateY = val;
    }

}
