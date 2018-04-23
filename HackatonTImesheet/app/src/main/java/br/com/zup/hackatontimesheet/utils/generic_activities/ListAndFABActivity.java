package br.com.zup.hackatontimesheet.utils.generic_activities;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.multistatelayout.MultiStateLayout;

/**
 * Created by joaoh on 19/04/2018.
 */

public abstract class ListAndFABActivity extends BaseActivity {

    protected MultiStateLayout mMultiStateLayout;
    protected RecyclerView mRecyclerView;
    protected FloatingActionButton mFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_and_fab_layout);

        mMultiStateLayout = findViewById(R.id.multi_state_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mFAB = findViewById(R.id.fab);

        mMultiStateLayout.setViewForState(getEmptyView(getLayoutInflater(), mMultiStateLayout), MultiStateLayout.State.EMPTY);
        mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);

        mFAB.setOnClickListener(getFABClickListener());
    }

    protected abstract View getEmptyView(LayoutInflater inflater, MultiStateLayout container);

    protected abstract FloatingActionButton.OnClickListener getFABClickListener();
}
