package br.com.zup.hackatontimesheet.home.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import br.com.zup.hackatontimesheet.R;
/**
 * Created by joaoh on 17/04/2018.
 */

public class SelectProjectDialog extends DialogFragment {

    private DialogInterface.OnClickListener mListener;
    private String[] mProjects;

    private static final String KEY_PROJECTS = "projects";

    public static SelectProjectDialog newInstance(String[] projects, DialogInterface.OnClickListener listener) {
        Bundle args = new Bundle();
        args.putStringArray(KEY_PROJECTS, projects);

        SelectProjectDialog fragment = new SelectProjectDialog();
        fragment.setListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(getArguments() == null) {
            throw new IllegalArgumentException("SelectProjectDialog expects a parameter (String[]). Try to use newInstance()");
        }

        mProjects = getArguments().getStringArray(KEY_PROJECTS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ArrayAdapter<String> mAdapter = new ArrayAdapter(getActivity(),R.layout.projects_list_item, R.id.project_name);

        mAdapter.addAll(mProjects);

        builder.setTitle(R.string.title_project_dialog);
        builder.setAdapter(mAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null) {
                    mListener.onClick(dialog, which);
                }
            }
        });

        return builder.create();
    }

    public void setListener(DialogInterface.OnClickListener listener) {
        mListener = listener;
    }
}
