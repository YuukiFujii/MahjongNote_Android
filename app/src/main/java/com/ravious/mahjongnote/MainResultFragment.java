package com.ravious.mahjongnote;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * Created by yuuki on 7/17/16.
 */

public class MainResultFragment extends Fragment {

    View layout_main_result;
    ListView recent_result_list;
    LineChartView result_chart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout_main_result = inflater.inflate(R.layout.main_result, null);
        recent_result_list = (ListView) layout_main_result.findViewById(R.id.result_list);

        ArrayList<ListData> result_list = new ArrayList<>();
        ResultListAdapter result_list_adapter = new ResultListAdapter(getContext());

        result_list_adapter.setFoodList(result_list);
        recent_result_list.setAdapter(result_list_adapter);


        ListData data = new ListData();
        data.setAverageRank(2);
        data.setFirst(23);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);
        result_list.add(data);

        result_chart = (LineChartView) layout_main_result.findViewById(R.id.chart);
        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));
        values.add(new PointValue(4, 2));
        values.add(new PointValue(5, 4));
        values.add(new PointValue(6, 3));
        values.add(new PointValue(7, 4));
        values.add(new PointValue(8, 2));
        values.add(new PointValue(9, 4));
        values.add(new PointValue(10, 3));
        values.add(new PointValue(11, 4));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.argb(255,0,204,255)).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data_chart = new LineChartData();
        data_chart.setLines(lines);

        result_chart.setLineChartData(data_chart);

        MahjongNoteDbHelper dbHelper = new MahjongNoteDbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return layout_main_result;
    }
}
