package br.com.zup.hackatontimesheet.utils.generic_fragments.list_and_fab;

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
import br.com.zup.hackatontimesheet.utils.generic_fragments.BaseFragment;
import br.com.zup.multistatelayout.MultiStateLayout;

/**
 * Created by joaoh on 17/04/2018.
 */

public abstract class ListAndFABFragment extends BaseFragment {

    protected CoordinatorLayout mCoordinatorLayout;
    protected MultiStateLayout mMultiStateLayout;
    protected RecyclerView mRecyclerView;
    protected FloatingActionButton mFAB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_and_fab_layout,container,false);

        mCoordinatorLayout = view.findViewById(R.id.coordinator_layout);
        mMultiStateLayout = view.findViewById(R.id.multi_state_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mFAB = view.findViewById(R.id.fab);

        mMultiStateLayout.setViewForState(getEmptyView(inflater, mMultiStateLayout), MultiStateLayout.State.EMPTY);
        mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);

        mFAB.setOnClickListener(getFABClickListener());

        return view;
    }
    protected abstract View getEmptyView(LayoutInflater inflater, ViewGroup container);

    protected abstract FloatingActionButton.OnClickListener getFABClickListener();
}
