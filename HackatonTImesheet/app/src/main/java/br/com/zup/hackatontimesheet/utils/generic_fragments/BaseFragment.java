package br.com.zup.hackatontimesheet.utils.generic_fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import br.com.zup.hackatontimesheet.R;

/**
 * Created by joaoh on 16/04/2018.
 */

public abstract class BaseFragment extends Fragment {

    public void onError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setTitle(R.string.title_oops);
        builder.setMessage(R.string.label_something_went_wrong);
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
