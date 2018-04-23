package br.com.zup.hackatontimesheet.refund_approvals.ui;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.hackatontimesheet.refund_approvals.model.RefundEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by joaoh on 16/04/2018.
 */

public class RefundApprovalsPresenter implements RefundApprovalsContract.Presenter {

    private RefundApprovalsContract.View mView;
    private List<RefundEntry> mList = new ArrayList<>();


    public RefundApprovalsPresenter(RefundApprovalsContract.View view) {
        this.mView = checkNotNull(view);

        //TODO: remove mock
        mList.add(new RefundEntry("Alberto Rodrigues","R$100,00","12/01/2018"));

        mList.add(new RefundEntry("Marcos Silva","-R$50,00","15/04/2017"));

        mList.add(new RefundEntry("Gilberto Guedes","-R$350,00","22/03/2017"));

        mList.add(new RefundEntry("Jorge Filho","-R$1000,39","10/01/2017"));
    }

    @Override
    public void onAddTimesheet() {
        //TODO: remove mock
        mView.showProjectDialog(new String[] {"ALGAR TELECOM S.A : ZUP_Fixed Price 2 - P&L", "BANCO PINE S.A : TESTE LANÇAMENTO HRS APP"});
    }

    @Override
    public void onProjectSelected(int index) {
        mView.openRefundReport();
    }

    @Override
    public void fetchApprovals() {
        mView.showApprovals(mList);
    }

    @Override
    public void onItemApproved(int position) {
        mView.notifyApprovedItem();
    }

    @Override
    public void onItemReproved(int position) {
        mView.notifyReprovedItem();
    }
}
