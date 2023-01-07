package com.example.tictactoegame;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Gamelogic {
    private int[][] gameBoard;

    private String[] playerNames = {"Player 1", "Player 2"};

    private int[] winType = {-1, -1, -1};

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

    public boolean winnerCheck() {
        boolean isWinner = false;

        //horizontal
        for (int i = 0; i < 3; i++) {
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] &&
            gameBoard[i][0] != 0) {
                winType = new int[] {i, 0, 1};
                isWinner = true;
            }
        }

        //vertical
        for (int i = 0; i < 3; i++) {
            if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[2][i] == gameBoard[0][i] &&
                    gameBoard[0][i] != 0) {
                winType = new int[] {0, i, 2};
                isWinner = true;
            }
        }

        //diagonal neg
        for (int i = 0; i < 3; i++) {
            if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] &&
                    gameBoard[0][0] != 0) {
                winType = new int[] {0, 2, 3};
                isWinner = true;
            }
        }

        //diagonal pos
        for (int i = 0; i < 3; i++) {
            if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] &&
                    gameBoard[2][0] != 0) {
                winType = new int[] {2, 2, 4};
                isWinner = true;
            }
        }

        int boardFilled = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (gameBoard[i][j] != 0 ) {
                    boardFilled += 1;
                }
            }
        }

        if (isWinner) {
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText(playerNames[player-1] + " Won!");
            return true;
        } else if(boardFilled == 9) {
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText("The score is equal!");
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

        player = 1;

        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        playerTurn.setText(playerNames[0] + "'s turn");
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

    public int[] getWinType() {
        return winType;
    }
}
