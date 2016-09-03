package com.ravious.mahjongnote;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;


/**
 * Created by yuuki on 7/17/16.
 */

public class MainResultFragment extends Fragment{

    View layout_main_result;
    ListView recent_result_list;
    private ComboLineColumnChartView result_chart;
    private ComboLineColumnChartData chart_data;

    TextView item_text_averageRank;
    TextView item_text_divident;
    TextView item_text_payment;

    BootstrapButton newGameButton;

    Chart test_chart;


    static MahjongNoteDbAdapter dbAdapter;
    static GameDataListAdapter game_data_list_adapter;
    static List<DbData> dataList = new ArrayList<DbData>();
    float[][] randomNumbersTab = new float[10][10];

    static ComboLineColumnChartData data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout_main_result = inflater.inflate(R.layout.fragment_main, null);
        newGameButton = (BootstrapButton) layout_main_result.findViewById(R.id.new_button);


        newGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.onNewGameButtonClicked();
                } catch (ClassCastException e) {
                    throw new ClassCastException("activity が OnOkBtnClickListener を実装していません.");
                }
            }
        });

        //リストビューの追加(ここから)
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
        // リストビューの追加(ここまで)



        //折れ線グラフの追加(ここから)
        result_chart = (ComboLineColumnChartView) layout_main_result.findViewById(R.id.chart);
        this.test_chart = new Chart();
        chart_data = new ComboLineColumnChartData(test_chart.columnChartData,test_chart.lineChartData);
        result_chart.setComboLineColumnChartData(chart_data);
       // result_chart.setComboLineColumnChartData(test_chart);
        //chart_data = new ComboLineColumnChartData();
        //generateValues();
        //generateData();


        // 折れ線グラフの追加(ここまで)

        //チャートの処理
        // チャートのクラスを作る(作るだけで初期化)
        // チャートクラスおデータをチャートクラスのメソッドで、
        // findbyidしたチャートにセットする





        // データベースアクセス(ここから)
        //dbAdapter = new MahjongNoteDbAdapter(getActivity());
        //game_data_list_adapter = new GameDataListAdapter();
        //recent_result_list.setAdapter(game_data_list_adapter);
        //loadNote();
        // データベースアクセス(ここまで)


        return layout_main_result;
    }


    protected void findViews() {
        recent_result_list = (ListView) layout_main_result.findViewById(R.id.result_list);
        item_text_averageRank = (TextView) recent_result_list.findViewById(R.id.item_text_averageRank);
        item_text_divident = (TextView) recent_result_list.findViewById(R.id.item_text_divident);
        item_text_payment = (TextView) recent_result_list.findViewById(R.id.item_text_payment);
    }

    protected void loadNote() {
        dataList.clear();
        // Read
        dbAdapter.open();
        Cursor c = dbAdapter.getAllNotes();
        getActivity().startManagingCursor(c);
        if (c.moveToFirst()) {
            do {
                DbData db_data
                        = new DbData(c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_DATE)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_TOTAL_DIVIDENT)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_GAME_PAYMENT)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_NUMBER_OF_GAMES)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_AVERAGE_RANK)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_FIRST)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_SECOND)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_THIRD)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_FOURTH)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_FIRST_PLAYER)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_SECOND_PLAYER)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_THIRD_PLAYER)),
                        c.getString(c.getColumnIndex(MahjongNoteDbAdapter.COL_FOURTH_PLAYER))
                        );
                dataList.add(db_data);
            } while (c.moveToNext());
        }
        getActivity().stopManagingCursor(c);
        dbAdapter.close();
        game_data_list_adapter.notifyDataSetChanged();
    }


    /*protected void saveItem() {
        dbAdapter.open();
        dbAdapter.saveNote(noteEditText.getText().toString());
        dbAdapter.close();
        noteEditText.setText("");
        loadNote();
    }

    protected void setListeners() {
        saveButton.setOnClickListener(this);
        itemListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, MENUITEM_ID_DELETE, 0, "Delete");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENUITEM_ID_DELETE:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ContactsContract.CommonDataKinds.Note note = noteList.get(menuInfo.position);
                final int noteId = note.getId();
                new AlertDialog.Builder(this).setIcon(R.drawable.icon).setTitle("Are you sure you want to delete this note?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbAdapter.open();
                        if (dbAdapter.deleteNote(noteId)) {
                            Toast.makeText(getBaseContext(), "The note was successfully deleted.", Toast.LENGTH_SHORT);
                            loadNote();
                        }
                        dbAdapter.close();
                    }
                }).setNegativeButton("Cancel", null).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
*/

    private class GameDataListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView noteTextView;
            TextView lastupdateTextView;
            View v = convertView;
            /*if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.row, null);
            }
            DbData note = (DbData) getItem(position);
            if (note != null) {
                noteTextView = (TextView) v.findViewById(R.id.noteTextView);
                lastupdateTextView = (TextView) v.findViewById(R.id.lastupdateTextView);
                noteTextView.setText(note.getNote());
                lastupdateTextView.setText(note.getLastupdate());
                v.setTag(R.id.noteid, note);
            }*/
            return v;
        }
    }



}
