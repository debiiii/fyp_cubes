package ytwong239.scm.cubic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


/**
 * Created by DebbieWong on 21/2/2019.
 */

public class BluetoothService {

    public BluetoothService(BluetoothAdapter bluetoothAdapter, Handler handler){
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice("98:D3:61:F9:48:D1");
        ConnectThread connectThread = new ConnectThread(bluetoothDevice, handler);
        connectThread.start();
    }


    private class ConnectThread extends Thread {

        private static final String TAG = "ConnectThread";
        private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        private Handler handlerConnectThread;

        public ConnectThread(BluetoothDevice device, Handler handler) {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's create() method failed", e);
            }
            mmSocket = tmp;

            handlerConnectThread = handler;
        }

        public void run() {

            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();
                Log.d("dffdsf", "connected!!!!");
            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                    Log.e(TAG, "Could not close the client socket", closeException);
                }
                return;
            }

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            ConnectedThread connectedThread = new ConnectedThread(mmSocket, handlerConnectThread);
            connectedThread.start();

        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the client socket", e);
            }
        }
    }


    private class ConnectedThread extends Thread {

        private static final String TAG = "ConnectedThread";
        private final int HANDLERSTATE = 0;

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        private Handler handlerConnectedThread;


        public ConnectedThread(BluetoothSocket socket, Handler handler) {
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

            handlerConnectedThread = handler;
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
                    handlerConnectedThread.obtainMessage(HANDLERSTATE, numBytes, -1, readMessage).sendToTarget();

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
