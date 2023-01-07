package com.example.tictactoegame;

public class Gamelogic {
    private int[][] gameBoard;

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

            return true;
        } else {
            return false;
        }
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
