package com.ravious.mahjongnote;

/**
 * Created by yuuki on 7/17/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<ListData> result_list;

    public ResultListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setFoodList(ArrayList<ListData> foodList) {
        this.result_list = foodList;
    }

    @Override
    public int getCount() {
        return result_list.size();
    }

    @Override
    public Object getItem(int position) {
        return result_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*　このアダプターをとってきたときに入ってた
        @Override
        public long getItemId(int position) {
            return result_list.get(position).getId();
        }
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_recent_game,parent,false);

        ((TextView)convertView.findViewById(R.id.item_text_averageRank)).setText(result_list.get(position).getAverageRank());
        ((TextView)convertView.findViewById(R.id.item_text_divident)).setText(String.valueOf(result_list.get(position).getDivident()));

        return convertView;
    }
}