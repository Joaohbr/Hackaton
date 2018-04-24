package br.com.zup.hackatontimesheet.refund_approvals.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.home.ui.dialogs.SelectProjectDialog;
import br.com.zup.hackatontimesheet.refund_approvals.model.RefundEntry;
import br.com.zup.hackatontimesheet.refund_report.ui.RefundReportActivity;
import br.com.zup.hackatontimesheet.utils.generic_fragments.list_and_fab.ListAndFABFragment;
import br.com.zup.multistatelayout.MultiStateLayout;

/**
 * Created by joaoh on 16/04/2018.
 */

public class RefundApprovalsFragment extends ListAndFABFragment
        implements RefundApprovalsContract.View, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    RefundApprovalsContract.Presenter mPresenter;
    RefundApprovalsAdapter mAdapter;

    public static RefundApprovalsFragment newInstance() {

        Bundle args = new Bundle();

        RefundApprovalsFragment fragment = new RefundApprovalsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new RefundApprovalsPresenter(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.fetchApprovals();
    }

    @Override
    protected View getEmptyView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.empty_list_view, container,false);

        ImageView myImage = view.findViewById(R.id.my_image);
        TextView myDescription = view.findViewById(R.id.my_description);

        myImage.setImageResource(R.drawable.ic_content_paste_black_48dp);
        myDescription.setText(R.string.description_refund_empty_view);

        return view;
    }

    @Override
    protected FloatingActionButton.OnClickListener getFABClickListener() {
        return new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onAddTimesheet();
            }
        };
    }

    @Override
    public void showProjectDialog(String [] projects) {
        SelectProjectDialog.newInstance(projects, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onProjectSelected(which);
            }
        }).show(getActivity().getFragmentManager(),"");
    }

    @Override
    public void openRefundReport() {
        startActivity(new Intent(getActivity(), RefundReportActivity.class));
    }

    @Override
    public void showApprovals(List<RefundEntry> approvals) {
        mAdapter = new RefundApprovalsAdapter(approvals);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        if(approvals.isEmpty()) {
            mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);
        } else {
            mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);
        }
    }

    @Override
    public void notifyApprovedItem() {
        Snackbar.make(mCoordinatorLayout,R.string.snackbar_sent_message,Snackbar.LENGTH_SHORT).show();
        if(mAdapter.getItemCount() == 0) {
            mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);
        }
    }

    @Override
    public void notifyReprovedItem() {
        Snackbar.make(mCoordinatorLayout,R.string.snackbar_sent_message,Snackbar.LENGTH_SHORT).show();
        if(mAdapter.getItemCount() == 0) {
            mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);
        }
    }

    @Override
    public void notifyFailRemoveItem() {
        Snackbar.make(mCoordinatorLayout,R.string.snackbar_generic_error,Snackbar.LENGTH_SHORT).show();
        mAdapter.restoreItem();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        mAdapter.removeItem(viewHolder.getAdapterPosition());

        if(direction == ItemTouchHelper.LEFT) {
            mPresenter.onItemReproved(viewHolder.getAdapterPosition());
        } else if(direction == ItemTouchHelper.RIGHT) {
            mPresenter.onItemApproved(viewHolder.getAdapterPosition());
        }
    }

}
