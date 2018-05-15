package br.com.zup.hackatontimesheet.refund_approvals.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by joaoh on 19/04/2018.
 */

public class RefundApprovalsListRecyclerTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    private ColorDrawable backgoundThumbUp = new ColorDrawable(), backgoundThumbDown = new ColorDrawable();



    public RefundApprovalsListRecyclerTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            getDefaultUIUtil().onSelected(((RefundApprovalsAdapter.ViewHolder)viewHolder).foreground);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        getDefaultUIUtil().clearView(((RefundApprovalsAdapter.ViewHolder)viewHolder).foreground);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        if( dX < 0) {
            ((RefundApprovalsAdapter.ViewHolder) viewHolder).negativeThumb.setVisibility(View.VISIBLE);
            ((RefundApprovalsAdapter.ViewHolder) viewHolder).positiveThumb.setVisibility(View.GONE);
        } else {
            ((RefundApprovalsAdapter.ViewHolder) viewHolder).positiveThumb.setVisibility(View.VISIBLE);
            ((RefundApprovalsAdapter.ViewHolder) viewHolder).negativeThumb.setVisibility(View.GONE);
        }
        getDefaultUIUtil().onDraw(c, recyclerView, ((RefundApprovalsAdapter.ViewHolder)viewHolder).foreground, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
