package ytwong239.scm.cubic;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {

    private final static int MENUPAGE = 0;
    private final static int PRACTICEGAMEPAGE = 1;
    private final static int BATTLENUMPAGE = 2;
    private final static int BATTLEGAMEPAGE = 3;
    private final static int HOWTOPAGE1 = 4;
    private final static int HOWTOPAGE2 = 5;
    private final static int HOWTOPAGE3 = 6;
    private final static int INFOPAGE = 7;
    private final static int PUZZLEPAGE = 8;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;

    private ConnectedThread connectedThread;

    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final static String TAG = "BluetoothActivity";

    private Handler bluetoothHandler;
    private final int HANDLERSTATE = 0;
    private StringBuilder stringBuilder = new StringBuilder();

    MainView mainView;

    OpenGLSurfaceView_3DModel openGLSurfaceView_3DModel;
    OpenGLRenderer_3DModel openGLRenderer_3DModel;

    OpenGLSurfaceView_SPType3_Base0 openGLSurfaceView_spType3_base0;
    OpenGLRenderer_SPType3_Base0 openGLRenderer_spType3_base0;

    OpenGLSurfaceView_SPType3_Base1 openGLSurfaceView_spType3_base1;
    OpenGLRenderer_SPType3_Base1 openGLRenderer_spType3_base1;

    OpenGLSurfaceView_SPType3_Quest openGLSurfaceView_spType3_quest;
    OpenGLRenderer_SPType3_Quest openGLRenderer_spType3_quest;

    OpenGLSurfaceView_SPType4_Base openGLSurfaceView_spType4_base;
    OpenGLRenderer_SPType4_Base openGLRenderer_spType4_base;

    OpenGLSurfaceView_SPType4_Choice0 openGLSurfaceView_spType4_choice0;
    OpenGLRenderer_SPType4_Choice0 openGLRenderer_spType4_choice0;

    OpenGLSurfaceView_SPType4_Choice1 openGLSurfaceView_spType4_choice1;
    OpenGLRenderer_SPType4_Choice1 openGLRenderer_spType4_choice1;

    OpenGLSurfaceView_SPType4_Choice2 openGLSurfaceView_spType4_choice2;
    OpenGLRenderer_SPType4_Choice2 openGLRenderer_spType4_choice2;

    OpenGLSurfaceView_SPType4_Choice3 openGLSurfaceView_spType4_choice3;
    OpenGLRenderer_SPType4_Choice3 openGLRenderer_spType4_choice3;

    OpenGLSurfaceView_Tips openGLSurfaceView_tips;
    OpenGLRenderer_Tips openGLRenderer_tips;

    OpenGLSurfaceView_DetectionCheck openGLSurfaceView_detectionCheck;
    OpenGLRenderer_DetectionCheck openGLRenderer_detectionCheck;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        //3d model
        openGLSurfaceView_3DModel = new OpenGLSurfaceView_3DModel(this);
        openGLRenderer_3DModel = new OpenGLRenderer_3DModel();

        openGLSurfaceView_3DModel.setEGLContextClientVersion(2);
        openGLSurfaceView_3DModel.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_3DModel.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_3DModel.setZOrderOnTop(true);
        openGLSurfaceView_3DModel.setRenderer(openGLRenderer_3DModel);

        addContentView(openGLSurfaceView_3DModel, params);

        //sp type 3 base 0
        openGLSurfaceView_spType3_base0 = new OpenGLSurfaceView_SPType3_Base0(this);
        openGLRenderer_spType3_base0 = new OpenGLRenderer_SPType3_Base0();

        openGLSurfaceView_spType3_base0.setEGLContextClientVersion(2);
        openGLSurfaceView_spType3_base0.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType3_base0.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType3_base0.setZOrderOnTop(true);
        openGLSurfaceView_spType3_base0.setRenderer(openGLRenderer_spType3_base0);

        addContentView(openGLSurfaceView_spType3_base0, params);

        //sp type 3 base 1
        openGLSurfaceView_spType3_base1 = new OpenGLSurfaceView_SPType3_Base1(this);
        openGLRenderer_spType3_base1 = new OpenGLRenderer_SPType3_Base1();

        openGLSurfaceView_spType3_base1.setEGLContextClientVersion(2);
        openGLSurfaceView_spType3_base1.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType3_base1.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType3_base1.setZOrderOnTop(true);
        openGLSurfaceView_spType3_base1.setRenderer(openGLRenderer_spType3_base1);

        addContentView(openGLSurfaceView_spType3_base1, params);

        //sp type 3 quest
        openGLSurfaceView_spType3_quest = new OpenGLSurfaceView_SPType3_Quest(this);
        openGLRenderer_spType3_quest = new OpenGLRenderer_SPType3_Quest();

        openGLSurfaceView_spType3_quest.setEGLContextClientVersion(2);
        openGLSurfaceView_spType3_quest.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType3_quest.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType3_quest.setZOrderOnTop(true);
        openGLSurfaceView_spType3_quest.setRenderer(openGLRenderer_spType3_quest);

        addContentView(openGLSurfaceView_spType3_quest, params);

        //sp type 4 base
        openGLSurfaceView_spType4_base = new OpenGLSurfaceView_SPType4_Base(this);
        openGLRenderer_spType4_base = new OpenGLRenderer_SPType4_Base();

        openGLSurfaceView_spType4_base.setEGLContextClientVersion(2);
        openGLSurfaceView_spType4_base.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType4_base.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType4_base.setZOrderOnTop(true);
        openGLSurfaceView_spType4_base.setRenderer(openGLRenderer_spType4_base);

        addContentView(openGLSurfaceView_spType4_base, params);

        //sp type 4 choice 0
        openGLSurfaceView_spType4_choice0 = new OpenGLSurfaceView_SPType4_Choice0(this);
        openGLRenderer_spType4_choice0 = new OpenGLRenderer_SPType4_Choice0();

        openGLSurfaceView_spType4_choice0.setEGLContextClientVersion(2);
        openGLSurfaceView_spType4_choice0.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType4_choice0.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType4_choice0.setZOrderOnTop(true);
        openGLSurfaceView_spType4_choice0.setRenderer(openGLRenderer_spType4_choice0);

        addContentView(openGLSurfaceView_spType4_choice0, params);

        //sp type 4 choice 1
        openGLSurfaceView_spType4_choice1 = new OpenGLSurfaceView_SPType4_Choice1(this);
        openGLRenderer_spType4_choice1 = new OpenGLRenderer_SPType4_Choice1();

        openGLSurfaceView_spType4_choice1.setEGLContextClientVersion(2);
        openGLSurfaceView_spType4_choice1.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType4_choice1.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType4_choice1.setZOrderOnTop(true);
        openGLSurfaceView_spType4_choice1.setRenderer(openGLRenderer_spType4_choice1);

        addContentView(openGLSurfaceView_spType4_choice1, params);

        //sp type 4 choice 2
        openGLSurfaceView_spType4_choice2 = new OpenGLSurfaceView_SPType4_Choice2(this);
        openGLRenderer_spType4_choice2 = new OpenGLRenderer_SPType4_Choice2();

        openGLSurfaceView_spType4_choice2.setEGLContextClientVersion(2);
        openGLSurfaceView_spType4_choice2.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType4_choice2.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType4_choice2.setZOrderOnTop(true);
        openGLSurfaceView_spType4_choice2.setRenderer(openGLRenderer_spType4_choice2);

        addContentView(openGLSurfaceView_spType4_choice2, params);

        //sp type 4 choice 3
        openGLSurfaceView_spType4_choice3 = new OpenGLSurfaceView_SPType4_Choice3(this);
        openGLRenderer_spType4_choice3 = new OpenGLRenderer_SPType4_Choice3();

        openGLSurfaceView_spType4_choice3.setEGLContextClientVersion(2);
        openGLSurfaceView_spType4_choice3.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_spType4_choice3.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_spType4_choice3.setZOrderOnTop(true);
        openGLSurfaceView_spType4_choice3.setRenderer(openGLRenderer_spType4_choice3);

        addContentView(openGLSurfaceView_spType4_choice3, params);

        //tips
        openGLSurfaceView_tips = new OpenGLSurfaceView_Tips(this);
        openGLRenderer_tips = new OpenGLRenderer_Tips();

        openGLSurfaceView_tips.setEGLContextClientVersion(2);
        openGLSurfaceView_tips.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_tips.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_tips.setZOrderOnTop(true);
        openGLSurfaceView_tips.setRenderer(openGLRenderer_tips);

        addContentView(openGLSurfaceView_tips, params);

        //detection check
        openGLSurfaceView_detectionCheck = new OpenGLSurfaceView_DetectionCheck(this);
        openGLRenderer_detectionCheck = new OpenGLRenderer_DetectionCheck();

        openGLSurfaceView_detectionCheck.setEGLContextClientVersion(2);
        openGLSurfaceView_detectionCheck.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        openGLSurfaceView_detectionCheck.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        openGLSurfaceView_detectionCheck.setZOrderOnTop(true);
        openGLSurfaceView_detectionCheck.setRenderer(openGLRenderer_detectionCheck);

        addContentView(openGLSurfaceView_detectionCheck, params);


        bluetoothHandler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg.what == HANDLERSTATE) {
                    String readMessage = (String) msg.obj;
                    stringBuilder.append(readMessage);
                    int endOfLineIndex = stringBuilder.indexOf("~");
                    if (endOfLineIndex > 0) {
                        String dataInPrint = stringBuilder.substring(0, endOfLineIndex);
                        int dataLength = dataInPrint.length();
                        if (stringBuilder.charAt(0) == '#')
                        {
                            String sensor[] = new String[27];
                            for(int i = 0, j = 1; i < 27 && j < 110; i++, j += 4){
                                //substring(0,2) will only get 0, 1
                                //not including 2!!!!
                                sensor[i] = stringBuilder.substring(j, j + 3);
                                //Log.d("df", sensor[i]);
                            }

                            mainView.updateArdAllString(sensor);

                        }
                        stringBuilder.delete(0, stringBuilder.length());
                        dataInPrint = " ";
                    }
                }
            }
        };

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter==null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (bluetoothAdapter.isEnabled()) {
            } else {
                bluetoothAdapter.enable();
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        openGLSurfaceView_3DModel.onResume();
        openGLSurfaceView_spType3_base0.onResume();
        openGLSurfaceView_spType3_base1.onResume();
        openGLSurfaceView_spType3_quest.onResume();
        openGLSurfaceView_spType4_base.onResume();
        openGLSurfaceView_spType4_choice0.onResume();
        openGLSurfaceView_spType4_choice1.onResume();
        openGLSurfaceView_spType4_choice2.onResume();
        openGLSurfaceView_spType4_choice3.onResume();
        openGLSurfaceView_tips.onResume();
        openGLSurfaceView_detectionCheck.onResume();


        bluetoothDevice = bluetoothAdapter.getRemoteDevice("98:D3:61:F9:48:D1");

        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID);
        }
        catch (IOException e) {
            Log.e(TAG, "Socket's create() method failed", e);
        }

        try {
            bluetoothSocket.connect();
            Log.d("dffdsf", "connected!!!!");
        } catch (IOException connectException) {
            try {
                bluetoothSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, "Could not close the client socket", closeException);
            }
            return;
        }

        connectedThread = new ConnectedThread(bluetoothSocket);
        connectedThread.start();
    }

    @Override
    public void onBackPressed() {
        switch (mainView.currPage){
            case PUZZLEPAGE:
                mainView.currPage = MENUPAGE;
                break;
            case BATTLEGAMEPAGE:
                mainView.currPage = MENUPAGE;
                break;
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        openGLSurfaceView_3DModel.onPause();
        openGLSurfaceView_spType3_base0.onPause();
        openGLSurfaceView_spType3_base1.onPause();
        openGLSurfaceView_spType3_quest.onPause();
        openGLSurfaceView_spType4_base.onPause();
        openGLSurfaceView_spType4_choice0.onPause();
        openGLSurfaceView_spType4_choice1.onPause();
        openGLSurfaceView_spType4_choice2.onPause();
        openGLSurfaceView_spType4_choice3.onPause();
        openGLSurfaceView_tips.onPause();
        openGLSurfaceView_detectionCheck.onPause();

        try
        {
            //Don't leave Bluetooth sockets open when leaving activity
            bluetoothSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
    }

    private class ConnectedThread extends Thread {

        private static final String TAG = "ConnectedThread";
        private final int HANDLERSTATE = 0;

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream


        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;

        }

        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer);
                    String readMessage = new String(mmBuffer, 0, numBytes);
                    bluetoothHandler.obtainMessage(HANDLERSTATE, numBytes, -1, readMessage).sendToTarget();

                } catch (IOException e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        }

        // Call this from the main activity to send data to the remote device.
        public void write(byte[] bytes) {
            byte[] msgBuffer = bytes;
            try {
                mmOutStream.write(bytes);

            } catch (IOException e) {
                Log.e(TAG, "Error occurred when sending data", e);
            }
        }

        // Call this method from the main activity to shut down the connection.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
            }
        }
    }
}
