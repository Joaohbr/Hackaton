package br.com.zup.hackatontimesheet.utils.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.com.zup.hackatontimesheet.R;

/**
 * Created by joaoh on 24/04/2018.
 */

public class SimpleSpinnerAdapter extends ArrayAdapter<String> {

    public SimpleSpinnerAdapter(@NonNull Context context, @NonNull String[] objects) {
        super(context, android.R.layout.simple_spinner_item, objects);
    }

    @Override
    public boolean isEnabled(int position){
        if(position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if(position == 0){
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }
}
