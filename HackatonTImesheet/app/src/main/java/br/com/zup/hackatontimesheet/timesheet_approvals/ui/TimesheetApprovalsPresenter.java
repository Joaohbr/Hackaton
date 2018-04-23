package br.com.zup.hackatontimesheet.timesheet_approvals.ui;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 13/04/2018.
 */

public class TimesheetApprovalsPresenter implements TimesheetApprovalsContract.Presenter {

    TimesheetApprovalsContract.View mView;

    public TimesheetApprovalsPresenter(TimesheetApprovalsContract.View view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void onAddTimesheet() {
        mView.showProjectDialog(new String[] {"ALGAR TELECOM S.A : ZUP_Fixed Price 2 - P&L", "BANCO PINE S.A : TESTE LANÇAMENTO HRS APP"});
    }

    @Override
    public void onProjectSelected(int index) {

    }

}
