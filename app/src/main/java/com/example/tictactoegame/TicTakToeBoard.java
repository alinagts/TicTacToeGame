package com.example.tictactoegame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TicTakToeBoard extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winnerLineColor;

    private final Paint paint = new Paint();
    private int cellSize = getWidth()/3;

    public TicTakToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

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

        drawX(canvas, 1, 1);
        drawO(canvas, 0, 0);
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
}