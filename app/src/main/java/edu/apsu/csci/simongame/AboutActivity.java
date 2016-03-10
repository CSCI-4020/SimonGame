package edu.apsu.csci.simongame;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class AboutActivity extends Activity {
    private String about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv = (TextView)findViewById(R.id.about_textView);
        tv.append("\n\n\n Created by Frank Miller and Micheal Scott");
    }

}
