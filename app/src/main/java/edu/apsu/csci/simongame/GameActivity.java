package edu.apsu.csci.simongame;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends Activity {

    final static int maxSequence = 50;
    int[] sequence = new int[maxSequence];
    // ArrayList<Integer> sequence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv = (TextView) findViewById(R.id.message_textView);
        tv.setText("Press anywhere to start!");

        //Click anywhere to begin anonomyous inner class
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainlayout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simonFunction();
            }
        });
    }

    public int randomNumber() {
        // creates a number between 0 and 3 and returns it
        Random random = new Random();
        int randomNum;
        randomNum = random.nextInt(4);
        return randomNum;
    }

    public void simonFunction() {
        TextView tv = (TextView) findViewById(R.id.message_textView);


        for (int i = 0; i <= 49; i++) {
            tv.setText(Integer.toString(i));
            Log.i("Count", Integer.toString(i));
            int temp = randomNumber() + 1;
            Log.i("temp", Integer.toString(temp));
            sequence[i] = temp;


            int index = 0;

            while (index < sequence.length) {
                if (sequence[index] == 1) {

                    //  tv.setText("Red!");

                    showRedButton();
                } else if (sequence[index] == 2) {
                    //  tv.setText("Green!");
                    showGreenButton();
                } else if (sequence[index] == 3) {
                    // tv.setText("Yellow!");
                    showYellowButton();
                } else if (sequence[index] == 4) {
                    // tv.setText("Blue!");
                    showBlueButton();
                } else {
                    break;
                }
                index++;
            }





        }


    }

    public void showRedButton() {

        ImageButton redButton = (ImageButton) findViewById(R.id.red_imageButton);
        redButton.setImageResource(R.drawable.selector);


        // redButton.setImageResource(R.drawable.redbuttonclear);

    }

    public void showBlueButton() {
        ImageButton blueButton = (ImageButton) findViewById(R.id.blue_imageButton);
        blueButton.setImageResource(R.drawable.selector);


        // blueButton.setImageResource(R.drawable.bluebuttonclear);

    }

    public void showGreenButton() {
        ImageButton greenButton = (ImageButton) findViewById(R.id.green_imageButton);
        greenButton.setImageResource(R.drawable.selector);


        //  greenButton.setImageResource(R.drawable.greenbuttonclear);

    }

    public void showYellowButton() {
        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellow_imageButton);
        yellowButton.setImageResource(R.drawable.selector);


        // yellowButton.setImageResource(R.drawable.yellowbuttonclear);

    }
}
