package br.com.zup.hackatontimesheet.refund_report.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.utils.generic_activities.ListAndFABActivity;
import br.com.zup.multistatelayout.MultiStateLayout;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundReportActivity extends ListAndFABActivity implements RefundReportContract.View {

    @Override
    protected View getEmptyView(LayoutInflater inflater, MultiStateLayout container) {
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
