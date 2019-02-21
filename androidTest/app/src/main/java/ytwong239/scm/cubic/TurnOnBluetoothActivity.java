package ytwong239.scm.cubic;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TurnOnBluetoothActivity extends AppCompatActivity {

    private ImageButton turnOnOkBtn;

    private BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_on_bluetooth);

        turnOnOkBtn = findViewById(R.id.turnOnOkBtn);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(getBaseContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        }
        else{
            //bluetooth is off, so turn it on
            if(!bluetoothAdapter.isEnabled()){

                turnOnOkBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bluetoothAdapter.enable();
                        Intent intent = new Intent(TurnOnBluetoothActivity.this, ConnectToyBluetoothActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            //bluetooth is on, so go to connect toy activity
            else{
                Intent intent = new Intent(TurnOnBluetoothActivity.this, ConnectToyBluetoothActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }
}
