package br.com.zup.hackatontimesheet.refund_approvals.ui;

import java.util.List;

import br.com.zup.hackatontimesheet.refund_approvals.model.RefundApprovalEntry;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface RefundApprovalsContract {
    interface View {
        void showProjectDialog(String [] projects);

        void openRefundReport(int selectedProject);

        void showApprovals(List<RefundApprovalEntry> approvals);

        void notifyApprovedItem();

        void notifyReprovedItem();

        void notifyFailRemoveItem();

        void onError();

        void enableLoading(boolean enable);

        void enableTopProgressBarLoading(boolean enable);
    }

    interface Presenter {
        void onAddRefundReport();

        void onProjectSelected(int index);

        void fetchApprovals();

        void onItemApproved(int position);

        void onItemReproved(int position);
    }
}
