package com.ravious.mahjongnote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.Arrays;


/**
 * Created by yuuki on 7/26/16.
 */

public class GameFragment extends Fragment {

    View layoutGameResult;
    View gameRow;

    ViewGroup gameTable;
    BootstrapButton addButton;
    TableLayout viewInputKeyBoard;

    InputKeyBoard inputKeyBoard;
    GameCell gameCells;
    Points point;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // DBからデータを入手
        // 数によって列の数を制御する
        // 何か入力があった場合はDBに登録する
        // レイアウトにセットする

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutGameResult = inflater.inflate(R.layout.fragment_game, null);
        gameRow = inflater.inflate(R.layout.row_games, null);

        gameTable = (ViewGroup) layoutGameResult.findViewById(R.id.game_table);
        addButton = (BootstrapButton) layoutGameResult.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    gameCells.addRow();
                    Log.d("クリック","検知！");
                } catch (ClassCastException e) {
                    throw new ClassCastException("activity が OnOkBtnClickListener を実装していません.");
                }
            }
        });

        point = new Points();
        point.fillPoints();

        gameCells = new GameCell(getActivity(), gameTable, gameRow);

        viewInputKeyBoard = (TableLayout) layoutGameResult.findViewById(R.id.input_keyboard);
        inputKeyBoard = new InputKeyBoard(viewInputKeyBoard, gameCells
        );


        return layoutGameResult;

    }
    public static class Points{
        static int[][] points = new int[50][5];

        public void fillPoints(){
            for(int i=0;i<50;i++){
                for(int j=0;j<5;j++){
                    points[i][j] = 0;
                }
            }
        }
    }


}
