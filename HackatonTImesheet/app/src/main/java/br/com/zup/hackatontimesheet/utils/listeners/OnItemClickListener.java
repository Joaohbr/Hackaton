package br.com.zup.hackatontimesheet.utils.listeners;

import android.view.View;

/**
 * Created by joaoh on 03/05/2018.
 */

public interface OnItemClickListener<T> {
    void onItemClick(View view, T item, int position);
}
