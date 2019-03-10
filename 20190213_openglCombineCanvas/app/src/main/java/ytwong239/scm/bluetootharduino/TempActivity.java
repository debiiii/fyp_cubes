// SM4602 Pre-production & Prototype
// Assignment 6 - Phase I Final Demonstration, Presentation, Critique and Final Report
// Group 3
// Wong Yan Ting 54388100, Fu Hiu Mei 54402448

package ytwong239.scm.bluetootharduino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class TempActivity extends AppCompatActivity {

    //MainPage mainPage;
    TempPage tempPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mainPage = new MainPage(this);
        tempPage = new TempPage(this);
        //setContentView(R.layout.activity_temp);
        //setContentView(mainPage);
        setContentView(tempPage);


    }
}
