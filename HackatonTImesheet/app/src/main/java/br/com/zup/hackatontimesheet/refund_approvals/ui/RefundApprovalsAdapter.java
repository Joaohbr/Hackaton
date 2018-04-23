package br.com.zup.hackatontimesheet.refund_approvals.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.refund_approvals.model.RefundEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 19/04/2018.
 */

public class RefundApprovalsAdapter extends RecyclerView.Adapter<RefundApprovalsAdapter.ViewHolder> {

    private List<RefundEntry> mRefunds;
    private RefundEntry lastRemovedItem;
    private int lastRemovedItemPosition;

    public RefundApprovalsAdapter(List<RefundEntry> refunds) {
        this.mRefunds = checkNotNull(refunds);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.refund_list_item, parent, false);

        return new RefundApprovalsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(mRefunds.get(position).getName());
        holder.value.setText(mRefunds.get(position).getValue());
        holder.date.setText(mRefunds.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return mRefunds.size();
    }

    public void removeItem(int position) {
        lastRemovedItem = mRefunds.get(position);
        lastRemovedItemPosition = position;

        mRefunds.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem() {
        if(lastRemovedItem != null) {
            mRefunds.add(lastRemovedItemPosition,lastRemovedItem);
            notifyItemInserted(lastRemovedItemPosition);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, value, date;
        ViewGroup foreground, background, positiveThumb, negativeThumb;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.refund_name);
            value = itemView.findViewById(R.id.refund_value);
            date = itemView.findViewById(R.id.refund_date);

            foreground = itemView.findViewById(R.id.refund_foreground_view);
            background = itemView.findViewById(R.id.refund_background_view);

            positiveThumb = itemView.findViewById(R.id.positive_thumb);
            negativeThumb = itemView.findViewById(R.id.negative_thumb);

        }
    }
}
