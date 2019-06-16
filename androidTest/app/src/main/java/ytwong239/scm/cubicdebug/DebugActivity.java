package ytwong239.scm.cubicdebug;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class DebugActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView connectedText;

    private SharedPreferences sharedPreferences2;
    private SharedPreferences.Editor editor2;
    private TextView playedRoundText;
    private Button resetBtn;

    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    private static String EXTRA_DEVICE_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        // Initialize array adapter for paired devices
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

        // Find and set up the ListView for paired devices
        ListView pairedListView = (ListView) findViewById(R.id.pairedList);
        pairedListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);


        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices and append to 'pairedDevices'
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // Add previosuly paired devices to the array
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            mPairedDevicesArrayAdapter.add("No devices have been paired");
        }

        sharedPreferences = getApplicationContext().getSharedPreferences(String.valueOf(R.string.bluetooth), Context.MODE_PRIVATE);
        connectedText = findViewById(R.id.connectedText);
        connectedText.setText(sharedPreferences.getString(String.valueOf(R.string.addressNum), "98:D3:61:F9:48:D1"));

        sharedPreferences2 = getApplicationContext().getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        playedRoundText = findViewById(R.id.playedRoundText);
        playedRoundText.setText(String.valueOf(sharedPreferences2.getInt(String.valueOf(R.integer.playedRound), 0)));
        resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor2 = sharedPreferences2.edit();
                editor2.putInt(String.valueOf(R.integer.playedRound), 0);
                editor2.commit();

                playedRoundText.setText(String.valueOf(sharedPreferences2.getInt(String.valueOf(R.integer.playedRound), 0)));
            }
        });

    }



    // Set up on-click listener for the list (nicked this - unsure)
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            editor = sharedPreferences.edit();
            editor.putString(String.valueOf(R.string.addressNum), address);
            editor.commit();

            connectedText.setText(sharedPreferences.getString(String.valueOf(R.string.addressNum), "98:D3:61:F9:48:D1"));

        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
