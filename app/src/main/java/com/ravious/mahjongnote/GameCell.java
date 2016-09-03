package com.ravious.mahjongnote;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by yuuki on 7/31/16.
 */

public class GameCell {

    Activity activity;
    ViewGroup gameTable;
    View gameRows;
    TableRow gameRow[] = new TableRow[50];
    TextView cell[][] = new TextView[50][5];


    int cellHeight;
    int isSelectedLine=0;
    int isSelectedColumn=0;
    int amountOfNowRows =0;

    GameCell(Activity activity, ViewGroup gameTable, View gameRows){
        this.activity = activity;
        this.gameTable = gameTable;
        this.gameRows = gameRows;

        // 初期の列数はthis.amoutOfRow(=4)
        this.getDisplayHeight(this.activity);

        // アカウント行と4ゲームまで初期作成
        for(int i=0;i<5;i++){
            addRow();
        }
        ((TextView)gameRow[0].getChildAt(0)).setText("");
        ((TextView)gameRow[0].getChildAt(1)).setText("あなた");
    }

    public void getDisplayHeight(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        cellHeight = (int)Math.floor(p.y/10);
    }


    public void addRow(){
        this.activity.getLayoutInflater().inflate(R.layout.row_games,gameTable);
        this.configrateCells();
        this.amountOfNowRows++;
    }

    // 新しい行が追加された時に、各セルをオブジェクト化(タグをつけてクリック時の)
    // 各セルに対して、高さとかの設定をする
    public void configrateCells(){
        gameRow[this.amountOfNowRows] = (TableRow)gameTable.getChildAt(this.amountOfNowRows);
        ((TextView)gameRow[this.amountOfNowRows].getChildAt(0)).setText(String.valueOf(this.amountOfNowRows));
        for(int i=0;i<5;i++){
            cell[this.amountOfNowRows][i] = ((TextView)gameRow[this.amountOfNowRows].getChildAt(i));
            cell[this.amountOfNowRows][i].setTag(R.string.line,amountOfNowRows);
            cell[this.amountOfNowRows][i].setTag(R.string.column,i);
            cell[this.amountOfNowRows][i].setHeight(this.cellHeight);
            cell[this.amountOfNowRows][i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if((int)v.getTag(R.string.line)==isSelectedLine
                                &&(int)v.getTag(R.string.column)==isSelectedColumn){
                        }else{
                            // 色を元に戻す
                            cell[isSelectedLine][isSelectedColumn]
                                    .setBackgroundResource(R.drawable.background_game_cell);

                            // 3マス入れたら、自動で4マス目埋める
                            checkBlank(isSelectedLine);

                            // クリックされたセルの位置を更新
                            isSelectedLine = (int)v.getTag(R.string.line);
                            isSelectedColumn = (int)v.getTag(R.string.column);

                            // 色をつける
                            cell[isSelectedLine][isSelectedColumn]
                                    .setBackgroundResource(R.drawable.background_game_cell_isselected);

                        }
                    } catch (ClassCastException e) {
                        throw new ClassCastException("エラー");
                    }
                }
            });;
        }

    }
    public void setCellPoint(int line,int column, int value){

        // 色の設定
        if(value>=0){
            this.cell[line][column].setTextColor(
                    activity.getResources().getColor(R.color.colorPrimaryDark)
            );
        }else if(value<0){
            this.cell[line][column].setTextColor(
                    activity.getResources().getColor(R.color.colorAccent)
            );
        }
        this.cell[line][column].setText(String.valueOf(value));

    }
    public void setBlank(){
        this.cell[isSelectedLine][isSelectedColumn].setText("");
    }

    public void checkBlank(int line){
        int blankCount=4;
        int blankColumn=0;
        int points=0;
        // 他の3つが埋まっている場合をチェック
        for(int i=1;i<5;i++){
            if(!this.cell[line][i].getText().equals("")){
                blankCount--;
            }else{
                blankColumn=i;
            }
        }
        if(blankCount==1){
            for(int i=1;i<5;i++){
                if(i!=blankColumn){
                    points += GameFragment.Points.points[line][i];
                }
            }
            Log.d("line",String.valueOf(line));
            Log.d("column",String.valueOf(blankColumn));

            GameFragment.Points.points[line][blankColumn] = -points;
            setCellPoint(line,blankColumn,GameFragment.Points.points[line][blankColumn]);
        }
    }

 }
