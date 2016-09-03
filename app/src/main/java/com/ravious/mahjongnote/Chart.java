package com.ravious.mahjongnote;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;

/**
 * Created by yuuki on 7/24/16.
 */

public class Chart extends ComboLineColumnChartData {

    ComboLineColumnChartData chartData;
    LineChartData lineChartData;
    ColumnChartData columnChartData;


    Chart(){
        //データの初期化を行う

        // 軸の設定
        //this.setAxis();

        // 棒グラフのデータをセット
        this.setColumnData();
        //this.chartData.setColumnChartData(this.columnChartData);

        // 折れ線グラフの値のセット
        this.setLineData();
        //this.chartData.setLineChartData(this.lineChartData);
    }

    // 軸の設定
    private void setAxis() {
        // Chart looks the best when line data and column data have similar maximum viewports.
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("日付");
        axisY.setName("成績");
        chartData.setAxisXBottom(axisX);
        chartData.setAxisYLeft(axisY);
    }

    // 折れ線グラフの値の取得と、メンバ変数へのセット
    private void setLineData() {

        List<Line> lines = new ArrayList<Line>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int j = 0; j < 10; ++j) {
            values.add(new PointValue(j, (float) Math.random() * 50f + 5));
        }

        Line line = new Line(values);
        line.setCubic(false);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        line.setColor(Color.argb(255,0,191,255));
        lines.add(line);

        this.lineChartData = new LineChartData(lines);

    }

    // 棒グラフの値の設定
    private void setColumnData() {
        int numSubcolumns = 1;
        int numColumns = 10;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50 + 5, ChartUtils.COLOR_GREEN));
            }

            columns.add(new Column(values));
        }
        this.columnChartData = new ColumnChartData(columns);
    }

}
