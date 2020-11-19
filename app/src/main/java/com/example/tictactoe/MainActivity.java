package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    /*
    Player representation
    * 0 --> X
    * 1 --> O
    * */

    int activePlayer=0;

    int[] gameState={2,2,2,2,2,2,2,2,2};
    /*
    * 0 --> X
    * 1 --> O
    * 2--> Null
    *
    * Winning positions
    *
    * */

    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                         {0,3,6}, {1,4,7}, {2,5,8},
                         {0,4,8},{2,4,6}};

    public void tapTap(View view){
        ImageView img=(ImageView)view;
        int tappedImage=Integer.parseInt(img.getTag().toString());

        if(!gameActive){
            resetGame(view);
        }

        if(gameState[tappedImage]==2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status=findViewById(R.id.status);
                status.setText("O's turn - Tap tap");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status=findViewById(R.id.status);
                status.setText("X's turn - Tap tap");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }

        /*
        * Check if any player has won
        * */
        for(int[] win:winPositions){
            if(gameState[win[0]]==gameState[win[1]] && gameState[win[2]]==gameState[win[1]] && gameState[win[0]]!=2){
                /*
                * Somebody has won
                * Zinda pakadna hain insaan ko
                * */
                String winner;
                gameActive=false;
                if(gameState[win[0]]==0){
                    winner="X has won!!";
                }
                else{
                    winner="O has won";
                }
                /*
                * Update the status bar to the winner's name
                * */
                TextView status=findViewById(R.id.status);
                status.setText(winner);
            }
        }

    }

    public void resetGame(View view){
        gameActive=true;
        activePlayer=0;

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("X's turn - Tap tap");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}