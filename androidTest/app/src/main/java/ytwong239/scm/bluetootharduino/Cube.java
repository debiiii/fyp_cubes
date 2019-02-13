package ytwong239.scm.bluetootharduino;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by DebbieWong on 29/1/2019.
 */

public class Cube {

    static final int COORDS_PER_VERTEX = 3;
    static final int VALUES_PER_COLOR = 4;

    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    private ByteBuffer drawListBuffer;

    private final int mProgram;

    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    private final int vertexCount = cubeCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;
    private final int colorStride = VALUES_PER_COLOR * 4;

    private static float cubeCoords[] = {
            -0.1f, -0.1f, -0.1f,
            0.1f, -0.1f, -0.1f,
            0.1f, 0.1f, -0.1f,
            -0.1f, 0.1f, -0.1f,
            -0.1f, -0.1f, 0.1f,
            0.1f, -0.1f, 0.1f,
            0.1f, 0.1f, 0.1f,
            -0.1f, 0.1f, 0.1f
    };

    private static final float color[] = {
            0.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f, 1.0f,
    };

    private static final byte drawOrder[] = {
            0, 1, 3, 3, 1, 2, // Front face.
            0, 1, 4, 4, 5, 1, // Bottom face.
            1, 2, 5, 5, 6, 2, // Right face.
            2, 3, 6, 6, 7, 3, // Top face.
            3, 7, 4, 4, 3, 0, // Left face.
            4, 5, 7, 7, 6, 5, // Rear face.
    };

    private static final String vertexShaderCode =
                    "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec4 vColor;" +
                    "varying vec4 vColorVarying;" +
                    "void main() {" +
                    "  vColorVarying = vColor;" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private static final String fragmentShaderCode =
                    "precision mediump float;" +
                    "varying vec4 vColorVarying;" +
                    "void main() {" +
                    "  gl_FragColor = vColorVarying;" +
                    "}";

    public Cube(){
        ByteBuffer bb = ByteBuffer.allocateDirect(
                cubeCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(cubeCoords);
        vertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(
                color.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(color);
        colorBuffer.position(0);

        drawListBuffer = ByteBuffer.allocateDirect(
                drawOrder.length);
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        int vertexShader = OpenglTestRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = OpenglTestRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "vColor");
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
    }

    public void draw(float[] mvpMatrix) {

        GLES20.glUseProgram(mProgram);

        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);


        GLES20.glEnableVertexAttribArray(mColorHandle);
        GLES20.glVertexAttribPointer(mColorHandle, VALUES_PER_COLOR,
                GLES20.GL_FLOAT, false,
                colorStride, colorBuffer);

        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 36, GLES20.GL_UNSIGNED_BYTE, drawListBuffer);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mColorHandle);
    }


}
