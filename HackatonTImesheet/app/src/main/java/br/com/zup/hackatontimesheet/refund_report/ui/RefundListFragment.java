package br.com.zup.hackatontimesheet.refund_report.ui;

import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.utils.generic_fragments.list_and_fab.ListAndFABFragment;

/**
 * Created by joaoh on 24/04/2018.
 */

public class RefundListFragment extends ListAndFABFragment {

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
        return null;
    }

}
