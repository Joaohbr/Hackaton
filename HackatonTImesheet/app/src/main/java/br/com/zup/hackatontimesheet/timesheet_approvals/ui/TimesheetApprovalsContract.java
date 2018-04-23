package br.com.zup.hackatontimesheet.timesheet_approvals.ui;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface TimesheetApprovalsContract {
    interface View {
        void showProjectDialog(String [] projects);
    }

    interface Presenter {
        void onAddTimesheet();

        void onProjectSelected(int index);
    }
}
