package com.example.tictactoegame;

import android.widget.Button;
import android.widget.TextView;

public class Gamelogic {
    private int[][] gameBoard;

    private String[] playerNames = {"Player 1", "Player 2"};

    private Button playAgainBtn, homeBtn;
    private TextView playerTurn;

    private int player = 1;

    Gamelogic() {
        gameBoard = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                gameBoard[j][i] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col) {
        if(gameBoard[row-1][col-1] == 0) {
            gameBoard[row-1][col-1] = player;

            if(player == 1) {
                playerTurn.setText(playerNames[1] + "'s turn");
            } else {
                playerTurn.setText(playerNames[0] + "'s turn");
            }

            return true;
        } else {
            return false;
        }
    }

    public void resetGame() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                gameBoard[j][i] = 0;
            }
        }
    }

    public void setPlayAgainBtn(Button playAgainBtn) {
        this.playAgainBtn = playAgainBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
