package br.com.zup.hackatontimesheet.refund_report.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.refund_report.model.RefundEntry;
import br.com.zup.hackatontimesheet.utils.listeners.OnItemClickListener;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 25/04/2018.
 */

public class RefundListAdapter extends RecyclerView.Adapter<RefundListAdapter.ViewHolder> {

    private List<RefundEntry> mList;
    private OnItemClickListener<RefundEntry> mListener;
    private RefundEntry lastRemovedItem;
    private int lastRemovedItemPosition;

    public RefundListAdapter(List<RefundEntry> list, OnItemClickListener listener) {
        mList = checkNotNull(list);
        mListener = checkNotNull(listener);
    }

    public void addItem(RefundEntry entry) {
        mList.add(0,entry);
        notifyItemInserted(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.refund_list_item, parent, false);

        final ViewHolder viewHolder = new RefundListAdapter.ViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                mListener.onItemClick(v, mList.get(position), position);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.description.setText(mList.get(position).getDescription());
        holder.value.setText(mList.get(position).getValue());
        holder.date.setText(mList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void removeItem(int position) {
        lastRemovedItem = mList.get(position);
        lastRemovedItemPosition = position;

        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem() {
        if(lastRemovedItem != null) {
            mList.add(lastRemovedItemPosition,lastRemovedItem);
            notifyItemInserted(lastRemovedItemPosition);
        }
    }

    public void updateItem(RefundEntry item, int position) {
        mList.add(position, item);
        notifyItemChanged(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView description, value, date;
        ViewGroup foreground, background;

        public ViewHolder(View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.refund_description);
            value = itemView.findViewById(R.id.refund_value);
            date = itemView.findViewById(R.id.refund_date);
            foreground = itemView.findViewById(R.id.refund_list_item_foreground);
            background = itemView.findViewById(R.id.refund_list_item_background);
        }
    }
}
