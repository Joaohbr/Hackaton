package br.com.zup.hackatontimesheet.refund_report.ui;

import android.app.Activity;
import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.refund_entry.ui.RefundEntryActivity;
import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;
import br.com.zup.hackatontimesheet.utils.generic_fragments.list_and_fab.ListAndFABFragment;
import br.com.zup.hackatontimesheet.commons.listeners.OnItemClickListener;
import br.com.zup.multistatelayout.MultiStateLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 24/04/2018.
 */
public class RefundListFragment extends ListAndFABFragment
        implements RefundReportContract.ChildView, RefundListRecyclerTouchHelper.RecyclerItemTouchHelperListener {

    private static final int OPEN_ENTRY = 100;

    @Inject
    RefundReportContract.Presenter mPresenter;
    private RefundListAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == OPEN_ENTRY && resultCode == Activity.RESULT_OK) {
            if(data.hasExtra(RefundEntryActivity.REFUND_ENTRY)) {
                RefundEntry entry = data.getParcelableExtra(RefundEntryActivity.REFUND_ENTRY);
                mPresenter.onNewRefundEntry(entry);
            }
        }
    }

    @Override
    protected View getEmptyView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.empty_list_view, container,false);

        ImageView myImage = view.findViewById(R.id.my_image);
        TextView myDescription = view.findViewById(R.id.my_description);

        myImage.setImageResource(R.drawable.ic_content_paste_black_48dp);
        myDescription.setText(R.string.description_refund_report_empty_view);

        return view;
    }

    @Override
    protected FloatingActionButton.OnClickListener getFABClickListener() {
        return new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onAddRefundEntry();
            }
        };
    }

    @Override
    public void bindPresenter(RefundReportContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showRefundEntries(List<RefundEntry> list) {
        mAdapter = new RefundListAdapter(list, getItemClickListener());

        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RefundListRecyclerTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        if(list.isEmpty()) {
            mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);
        } else {
            mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);
        }
    }

    @Override
    public void openRefundEntry(RefundEntry entry) {
        startActivityForResult(RefundEntryActivity.newIntent(getContext(), entry), OPEN_ENTRY);
    }

    @Override
    public void updateRefundEntries(RefundEntry entry, int position) {
        mAdapter.updateItem(entry,position);
        mPresenter.onRefundEntryChanged(mAdapter.getTotalSum());
        mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);
    }

    @Override
    public void addRefundEntry(RefundEntry entry) {
        mAdapter.addItem(entry);
        mPresenter.onRefundEntryChanged(mAdapter.getTotalSum());
        mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);
    }

    @Override
    public void enableLoading(boolean enable) {
        mMultiStateLayout.setState(enable ? MultiStateLayout.State.LOADING : MultiStateLayout.State.CONTENT, true);
    }

    @Override
    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setNegativeButton(R.string.action_ack, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setTitle(R.string.title_success);
        builder.setMessage(R.string.label_refund_report_success);
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void cleanAdapter() {
        mAdapter.setList(new ArrayList<RefundEntry>());
        mPresenter.onRefundEntryChanged(mAdapter.getTotalSum());
        mMultiStateLayout.setState(MultiStateLayout.State.EMPTY);
    }

    @Override
    public List<RefundEntry> getEntries() {
        return mAdapter.getList();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        mAdapter.removeItem(viewHolder.getAdapterPosition());
        mPresenter.onRefundEntryChanged(mAdapter.getTotalSum());
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, getString(R.string.snackbar_removing_item), Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.action_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.restoreItem();
                mPresenter.onRefundEntryChanged(mAdapter.getTotalSum());
            }
        });
        snackbar.show();
    }

    private OnItemClickListener getItemClickListener() {
        return new OnItemClickListener<RefundEntry>() {
            @Override
            public void onItemClick(View view, RefundEntry item, int position) {
                mPresenter.onRefundEntryClick(item, position);
            }
        };
    }
}
