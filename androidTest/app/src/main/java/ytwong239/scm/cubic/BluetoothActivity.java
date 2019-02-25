package ytwong239.scm.cubic;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {

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

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView);

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

                            //mainPage.updateArdAllString(sensor);

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
    public void onPause()
    {
        super.onPause();
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
