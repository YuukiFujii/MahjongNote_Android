package com.ravious.mahjongnote;

import android.util.Log;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

/**
 * Created by yuuki on 7/31/16.
 */

public class InputKeyBoard implements View.OnClickListener{
    GameCell gameCell;
    View inputKeyBoard;
    int point;
    BootstrapButton oneButton;
    BootstrapButton twoButton;
    BootstrapButton threeButton;
    BootstrapButton fourButton;
    BootstrapButton fiveButton;
    BootstrapButton sixButton;
    BootstrapButton sevenButton;
    BootstrapButton eightButton;
    BootstrapButton nineButton;
    BootstrapButton zeroButton;
    BootstrapButton signButton;
    BootstrapButton cancelButton;

    InputKeyBoard(View viewInputKeyBoard, final GameCell gameCell){
        this.gameCell = gameCell;

        this.inputKeyBoard = viewInputKeyBoard;
        oneButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_one);
        oneButton.setOnClickListener(this);
        twoButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_two);
        twoButton.setOnClickListener(this);
        threeButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_three);
        threeButton.setOnClickListener(this);
        fourButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_four);
        fourButton.setOnClickListener(this);
        fiveButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_five);
        fiveButton.setOnClickListener(this);
        sixButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_six);
        sixButton.setOnClickListener(this);
        sevenButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_seven);
        sevenButton.setOnClickListener(this);
        eightButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_eight);
        eightButton.setOnClickListener(this);
        nineButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_nine);
        nineButton.setOnClickListener(this);
        zeroButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_zero);
        zeroButton.setOnClickListener(this);
        signButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_sign);
        signButton.setOnClickListener(this);
        cancelButton = (BootstrapButton) inputKeyBoard.findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v == signButton){
            GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn]
                    = -GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn];
            this.gameCell.setCellPoint(
                    gameCell.isSelectedLine,gameCell.isSelectedColumn,
                    GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn]);
        }else if(v == cancelButton){
            GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] = 0;
            this.gameCell.setBlank();
        }else {
            GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn]*=10;
            if (v == oneButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 1;
                Log.d("hello",String.valueOf(GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn]));
            } else if (v == twoButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 2;
            } else if (v == threeButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 3;
            } else if (v == fourButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 4;
            } else if (v == fiveButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 5;
            } else if (v == sixButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 6;
            } else if (v == sevenButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 7;
            } else if (v == eightButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 8;
            } else if (v == nineButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 9;
            } else if (v == zeroButton) {
                GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn] += 0;
            }
            this.gameCell.setCellPoint(
                    gameCell.isSelectedLine,gameCell.isSelectedColumn,
                    GameFragment.Points.points[gameCell.isSelectedLine][gameCell.isSelectedColumn]);

        }
    }

}
