package edu.apsu.csci.simongame;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener{

    final static int maxSequence = 50;
    int[] sequence = new int[maxSequence];
    private UpdateTask updateTask;
    int temp =0;
    int currentScore =0;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv = (TextView) findViewById(R.id.message_textView);
        tv.setText("Press anywhere to start!");

        //Click anywhere to begin anonomyous inner class
        setUnClickAble();
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainlayout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simonFunction();
            }
        });

        ImageButton redButton =(ImageButton) findViewById(R.id.red_imageButton);
        ImageButton greenButton =(ImageButton) findViewById(R.id.green_imageButton);
        ImageButton yellowButton =(ImageButton) findViewById(R.id.yellow_imageButton);
        ImageButton blueButton =(ImageButton) findViewById(R.id.blue_imageButton);

        redButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);




    }



    @Override
    public void onClick(View v) {


        if(v.getId()== R.id.red_imageButton){
            if(sequence[temp]!=1){
                lostGame();
            }
        }else if(v.getId() == R.id.green_imageButton){
            if(sequence[temp]!=1){
                lostGame();
            }

        }else if(v.getId() == R.id.yellow_imageButton){
            if(sequence[temp]!=1){
                lostGame();
            }

        }else if(v.getId()== R.id.blue_imageButton){
            if(sequence[temp]!=1){
                lostGame();
            }

        }
        temp++;

        if(sequence[temp]==0){
            updateScore();
            simonFunction();

        }

    }

    public void lostGame(){
        setUnClickAble();
        temp=0;
        TextView tv = (TextView) findViewById(R.id.message_textView);
        tv.setText("You loose");
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainlayout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simonFunction();
            }
        });


    }

    public void updateScore(){
        currentScore=currentScore+1;
        TextView tv = (TextView)findViewById(R.id.current_textView);
        tv.setText(Integer.toString(currentScore));

    }

    public int randomNumber() {
        // creates a number between 0 and 3 and returns it
        Random random = new Random();
        int randomNum;
        randomNum = random.nextInt(4);
        return randomNum;
    }

    public void showPattern(){
        TextView tv = (TextView) findViewById(R.id.message_textView);


        int i=0;
        while (i < temp) {

            setUnClickAble();
            tv.setText("Watch the Sequence");
            Log.i("Count", Integer.toString(i));
            int temp = randomNumber() + 1;
            Log.i("temp", Integer.toString(temp));
            sequence[i] = temp;

            if(updateTask==null) {
                updateTask = new UpdateTask();
                updateTask.execute();
            }else{
                Log.i("update task","Already running update task");
            }

            tv.setText("Enter the Sequence");
            setClickAble();

            if(updateTask != null && updateTask.getStatus() == AsyncTask.Status.FINISHED){
                updateTask=null;
            }
            i++;
        }

    }


    public void simonFunction()  {
        showPattern();


    }




    class UpdateTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {




                int index = 0;


                while (index < sequence.length) {
                    if (sequence[index] == 1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                selectorRedButton();
                            }
                        });

                        //  tv.setText("Red!");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showRedButton();
                            }
                        });

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else if (sequence[index] == 2) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                selectorGreenButton();
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //  tv.setText("Green!");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showGreenButton();
                            }
                        });

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else if (sequence[index] == 3) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                selectorYellowButton();
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // tv.setText("Yellow!");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showYellowButton();
                            }
                        });

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else if (sequence[index] == 4) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                selectorBlueButton();
                            }


                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // tv.setText("Blue!");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showBlueButton();
                            }
                        });

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        break;
                    }
                    index++;
                }










            return null;

        }
    }

    public void selectorRedButton() {

        ImageButton redButton = (ImageButton) findViewById(R.id.red_imageButton);
        redButton.setImageResource(R.drawable.selector);
        Log.i("Red","Changed to Selector");



    }

    public void showRedButton(){
        ImageButton redButton = (ImageButton) findViewById(R.id.red_imageButton);
        redButton.setImageResource(R.drawable.redbuttonclear);
        Log.i("Red", "Changed to red");
    }

    public void selectorBlueButton(){
        ImageButton blueButton = (ImageButton) findViewById(R.id.blue_imageButton);
        blueButton.setImageResource(R.drawable.selector);
        Log.i("Blue", "Changed to selector");
    }

    public void showBlueButton() {
        ImageButton blueButton = (ImageButton) findViewById(R.id.blue_imageButton);


        blueButton.setImageResource(R.drawable.bluebuttonclear);
        Log.i("Blue", "Changed to blue");
    }

    public void selectorGreenButton(){
        ImageButton greenButton = (ImageButton) findViewById(R.id.green_imageButton);
        greenButton.setImageResource(R.drawable.selector);
        Log.i("Green", "Changed to selector");
    }

    public void showGreenButton() {

        ImageButton greenButton = (ImageButton) findViewById(R.id.green_imageButton);


        greenButton.setImageResource(R.drawable.greenbuttonclear);
        Log.i("Green", "Changed to green");
    }

    public void selectorYellowButton(){
        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellow_imageButton);
        yellowButton.setImageResource(R.drawable.selector);
        Log.i("Yellow", "Changed to selector");
    }

    public void showYellowButton() {

        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellow_imageButton);


        yellowButton.setImageResource(R.drawable.yellowbuttonclear);
        Log.i("Yellow", "Changed to yellow");

    }

    public void setClickAble(){
        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellow_imageButton);
        ImageButton greenButton = (ImageButton) findViewById(R.id.green_imageButton);
        ImageButton blueButton = (ImageButton) findViewById(R.id.blue_imageButton);
        ImageButton redButton = (ImageButton) findViewById(R.id.red_imageButton);

        yellowButton.setClickable(true);
        greenButton.setClickable(true);
        blueButton.setClickable(true);
        redButton.setClickable(true);

    }

    public void setUnClickAble(){
        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellow_imageButton);
        ImageButton greenButton = (ImageButton) findViewById(R.id.green_imageButton);
        ImageButton blueButton = (ImageButton) findViewById(R.id.blue_imageButton);
        ImageButton redButton = (ImageButton) findViewById(R.id.red_imageButton);

        yellowButton.setClickable(false);
        greenButton.setClickable(false);
        blueButton.setClickable(false);
        redButton.setClickable(false);


    }

}
