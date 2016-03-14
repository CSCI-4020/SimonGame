package edu.apsu.csci.simongame;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class GameActivity extends Activity implements View.OnClickListener{

    final static int maxSequence = 50;
    int[] sequence = new int[maxSequence];
    private UpdateTask updateTask;
    int logger =0;
    int currentScore =0;
    int highScore =0;
    int i=0;
    int temp =1;
    private final String highScoreFile = "highscore.txt";
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv = (TextView) findViewById(R.id.message_textView);
        tv.setText("Press anywhere to start!");


        setUnClickAble();
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainlayout);
        rl.setOnClickListener(this);

        currentScore=0;
        TextView tc = (TextView)findViewById(R.id.current_textView);
        tc.setText(Integer.toString(currentScore));
        highScore = readHighScore();
        setHighScore(highScore);
        //simonFunction();




    }

    private int readHighScore() {
        int temp = 0;


        try {
            FileInputStream fis = openFileInput(highScoreFile);
            Scanner scanner = new Scanner(fis);

            temp = scanner.nextInt();

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return temp;
    }

    private void writeHighScore() {
        if (currentScore > highScore) {
            try {
                FileOutputStream fos = openFileOutput(highScoreFile, Context.MODE_PRIVATE);
                OutputStreamWriter os = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(os);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(currentScore);
                TextView tv = (TextView) findViewById(R.id.highscore_textView);
                tv.setText(Integer.toString(currentScore));
                pw.close();
            } catch (FileNotFoundException e) {
                Log.e("WRITE", "Cannot Save Data" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void setHighScore(int hs) {
        TextView tv = (TextView) findViewById(R.id.highscore_textView);
        tv.setText(Integer.toString(hs));
    }

    @Override
    public void onClick(View v) {



            if (v.getId() == R.id.red_imageButton) {
                if (sequence[logger] != 1) {
                    Log.i("OnClick", "Red button pressed");
                    lostGame();
                }else{
                    Log.i("OnClick", "Red button pressed");
                    logger++;
                }
            } else if (v.getId() == R.id.green_imageButton) {
                if (sequence[logger] != 2) {
                    Log.i("OnClick", "Green button pressed");
                    lostGame();
                }else{
                    Log.i("OnClick", "Green button pressed");
                    logger++;
                }

            } else if (v.getId() == R.id.yellow_imageButton) {
                if (sequence[logger] != 3) {
                    Log.i("OnClick", "Yellow button pressed");
                    lostGame();

                }else{
                    Log.i("OnClick", "Yellow button pressed");
                    logger++;
                }

            } else if (v.getId() == R.id.blue_imageButton) {
                if (sequence[logger] != 4) {
                    Log.i("OnClick", "Blue button pressed");
                   lostGame();
                }else{
                    Log.i("OnClick", "Blue button pressed");
                    logger++;
                }

            }else if (v.getId() == R.id.mainlayout){
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainlayout);
                rl.setEnabled(false);
                simonFunction();
            }

            Log.i("Logger", String.valueOf(logger));
             temp++;


            if(sequence[logger]==0){
                logger=0;

                Log.i("if","sequence[logger] == 0 ");
                Log.i("if logger value", String.valueOf(logger));
                updateScore();
                simonFunction();


            }






    }

    public void lostGame(){
        Log.i("lostGame()", "you lost");
        writeHighScore();


        Toast.makeText(GameActivity.this, "You Lost!!! You Lasted "+ currentScore + " rounds", Toast.LENGTH_SHORT).show();
        //**********************Put file writing function here *****************



        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();





    }


    public void updateScore(){
        currentScore=currentScore+1;
        TextView tc = (TextView)findViewById(R.id.current_textView);
        tc.setText(Integer.toString(currentScore));



    }

    public int randomNumber() {
        // creates a number between 0 and 3 and returns it
        Random random = new Random();
        int randomNum;
        randomNum = random.nextInt(4);
        return randomNum;
    }

    public void simonFunction()  {
        showPattern();

        ImageButton redButton =(ImageButton) findViewById(R.id.red_imageButton);
        ImageButton greenButton =(ImageButton) findViewById(R.id.green_imageButton);
        ImageButton yellowButton =(ImageButton) findViewById(R.id.yellow_imageButton);
        ImageButton blueButton =(ImageButton) findViewById(R.id.blue_imageButton);

        redButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);


    }

    public void showPattern(){
        TextView tv = (TextView) findViewById(R.id.message_textView);






            tv.setText("Watch the Sequence");
            Log.i("Int i", Integer.toString(i));
            sequence[i]= randomNumber() + 1;
            Log.i("Sequence", Integer.toString(sequence[i]));

            if(updateTask != null && updateTask.getStatus() == AsyncTask.Status.FINISHED){
                updateTask=null;
                Log.i("updateTask","Reset to null should not be running");
            }

            if(updateTask==null) {
                updateTask = new UpdateTask();
                updateTask.execute();
            }else{
                Log.i("update task","Already running update task");
            }

        tv.setText("Enter the Sequence");


            if(updateTask != null && updateTask.getStatus() == AsyncTask.Status.FINISHED){
                updateTask=null;
                Log.i("updateTask","Reset to null should not be running");
            }
            i++;


       /*

       ******* For Testing Purpose of the array population ********
            for(int it =0; it<sequence.length; it++){
                Log.i("Array output", Integer.toString(sequence[it]));
        }*/

    }







    class UpdateTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {



            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setUnClickAble();
                }
            });

            int index=0;

                while (index < temp) {
                    if (sequence[index] == 1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                selectorRedButton();
                            }
                        });


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

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setClickAble();
                            }
                        });
                        Log.i("DIB","Return now");

                       return null;
                    }
                    index++;
                }








            Log.i("DIB","Should Kill now ");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setClickAble();
                }
            });
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

        yellowButton.setEnabled(true);
        greenButton.setEnabled(true);
        blueButton.setEnabled(true);
        redButton.setEnabled(true);

        Log.i("setClickAble","click able");

    }

    public void setUnClickAble(){
        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellow_imageButton);
        ImageButton greenButton = (ImageButton) findViewById(R.id.green_imageButton);
        ImageButton blueButton = (ImageButton) findViewById(R.id.blue_imageButton);
        ImageButton redButton = (ImageButton) findViewById(R.id.red_imageButton);

        yellowButton.setEnabled(false);
        greenButton.setEnabled(false);
        blueButton.setEnabled(false);
        redButton.setEnabled(false);

        Log.i("setUnClickAble", "not click able");


    }

}
