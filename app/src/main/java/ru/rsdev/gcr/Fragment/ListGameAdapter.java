package ru.rsdev.gcr.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.rsdev.gcr.R;

/**
 * Created by rsdev on 21.08.2015.
 */
public class ListGameAdapter extends ArrayAdapter {

    ArrayList<String> list;

    public ListGameAdapter(Context context, ArrayList listAnswer) {
        super(context, 0, listAnswer);
        this.list = listAnswer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (position % 2 == 0){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_left, null);
            }
            else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_right, null);
            }
        }
        TextView tvLabel = (TextView) convertView.findViewById(R.id.textInfo);
        tvLabel.setText(list.get(position));
        return convertView;
    }
}
