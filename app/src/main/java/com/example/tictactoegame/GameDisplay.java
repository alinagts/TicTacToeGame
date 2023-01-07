package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TicTakToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        Button playAgainBtn = findViewById(R.id.play_again_btn);
        Button homeBtn = findViewById(R.id.home_btn);
        TextView playerTurn = findViewById(R.id.player_turn);

        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        if(playerNames != null) {
            playerTurn.setText(playerNames[0] + "'s turn");
        }

        ticTacToeBoard = findViewById(R.id.ticTakToeBoard);

        ticTacToeBoard.setUpGame(playAgainBtn, homeBtn, playerTurn, playerNames);
    }

    public void playAgainButtonClick(View view) {
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}