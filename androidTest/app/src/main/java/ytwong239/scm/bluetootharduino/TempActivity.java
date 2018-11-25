package ytwong239.scm.bluetootharduino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TempActivity extends AppCompatActivity {

    MainPage mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPage = new MainPage(this);
        //setContentView(R.layout.activity_temp);
        setContentView(mainPage);
    }
}
