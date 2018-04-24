package br.com.zup.hackatontimesheet.timesheet_approvals.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.home.ui.dialogs.SelectProjectDialog;
import br.com.zup.hackatontimesheet.utils.generic_fragments.list_and_fab.ListAndFABFragment;

/**
 * Created by joaoh on 13/04/2018.
 */

public class TimesheetApprovalsFragment extends ListAndFABFragment implements TimesheetApprovalsContract.View {

    private TimesheetApprovalsContract.Presenter mPresenter;

    public static TimesheetApprovalsFragment newInstance() {

        Bundle args = new Bundle();
        
        TimesheetApprovalsFragment fragment = new TimesheetApprovalsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new TimesheetApprovalsPresenter(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View getEmptyView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.empty_list_view, container, false);

        ImageView myImage = view.findViewById(R.id.my_image);
        TextView myDescription = view.findViewById(R.id.my_description);


        myImage.setImageResource(R.drawable.ic_alarm_black_48dp);
        myDescription.setText(R.string.description_time_empty_view);

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
}
