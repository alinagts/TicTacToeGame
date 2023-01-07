package com.example.tictactoegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTakToeBoard extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winnerLineColor;
    private final Gamelogic game;

    private boolean winningLine = false;

    private final Paint paint = new Paint();
    private int cellSize = getWidth()/3;

    public TicTakToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new Gamelogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTakToeBoard, 0, 0);

        try {
            boardColor = a.getInteger(R.styleable.TicTakToeBoard_boardColor, 0);
            XColor = a.getInteger(R.styleable.TicTakToeBoard_XColor, 0);
            OColor = a.getInteger(R.styleable.TicTakToeBoard_OColor, 0);
            winnerLineColor = a.getInteger(R.styleable.TicTakToeBoard_winnerLineColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension/3;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if (!winningLine) {
                if(game.updateGameBoard(row, col)) {
                    invalidate();

                    if (game.winnerCheck()) {
                        winningLine = true;
                        invalidate();
                    }

                    if(game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }

            invalidate();

            return true;
        }

        return false;
    }

    private void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int i = 1; i < 3; i ++) {
            canvas.drawLine(cellSize*i, 0, cellSize*i, canvas.getWidth(), paint);
        }

        for (int r = 1; r < 3; r ++) {
            canvas.drawLine(0, cellSize*r, canvas.getWidth(), cellSize*r, paint);
        }
    }

    private void drawMarkers(Canvas canvas) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (game.getGameBoard() [i][j] != 0) {
                    if(game.getGameBoard() [i][j] ==1) {
                        drawX(canvas, i, j);
                    } else {
                        drawO(canvas, i, j);
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int col) {
        paint.setColor(XColor);

        canvas.drawLine((float) ((col + 1) * cellSize - cellSize * 0.2),
                (float) (row * cellSize + cellSize * 0.2),
                (float) (col * cellSize + cellSize * 0.2),
                (float) ((row + 1) * cellSize - cellSize * 0.2),
                paint);

        canvas.drawLine((float) (col * cellSize + cellSize * 0.2),
                (float) (row * cellSize + cellSize * 0.2),
                (float) ((col + 1) * cellSize - cellSize * 0.2),
                (float) ((row + 1) * cellSize - cellSize * 0.2),
                paint);
    }

    private void drawO(Canvas canvas, int row, int col) {
        paint.setColor(OColor);

        canvas.drawOval((float)(col * cellSize + cellSize * 0.2),
                (float)(row * cellSize + cellSize * 0.2),
                (float)((col * cellSize + cellSize) - cellSize * 0.2),
                (float)((row * cellSize + cellSize) - cellSize * 0.2),
                paint);

    }

    public void setUpGame(Button playAgainBtn, Button homeBtn, TextView playerDisplay, String[] names) {
        game.setPlayAgainBtn(playAgainBtn);
        game.setHomeBtn(homeBtn);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);
    }

    public void resetGame() {
        game.resetGame();
        winningLine = false;
    }
}
