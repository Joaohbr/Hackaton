package br.com.zup.hackatontimesheet.utils.generic_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.multistatelayout.MultiStateLayout;

/**
 * Created by joaoh on 19/04/2018.
 */

public abstract class ListAndFABActivity extends BaseActivity {

    protected CoordinatorLayout mCoordinatorLayout;
    protected MultiStateLayout mMultiStateLayout;
    protected RecyclerView mRecyclerView;
    protected FloatingActionButton mFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_and_fab_layout);

        mCoordinatorLayout = findViewById(R.id.coordinator_layout);
        mMultiStateLayout = findViewById(R.id.multi_state_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mFAB = findViewById(R.id.fab);


        mMultiStateLayout.setViewForState(getEmptyView(getLayoutInflater(), mMultiStateLayout), MultiStateLayout.State.EMPTY);
        mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);

        mFAB.setOnClickListener(getFABClickListener());
    }
    protected abstract View getEmptyView(LayoutInflater inflater, ViewGroup container);

    protected abstract FloatingActionButton.OnClickListener getFABClickListener();
}
