package ytwong239.scm.cubicdebug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class ConnectToyBluetoothActivity extends AppCompatActivity {

    private ImageButton connectOkPtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_toy_bluetooth);

        connectOkPtn = findViewById(R.id.connectOkBtn);

        connectOkPtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnectToyBluetoothActivity.this, BluetoothActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
