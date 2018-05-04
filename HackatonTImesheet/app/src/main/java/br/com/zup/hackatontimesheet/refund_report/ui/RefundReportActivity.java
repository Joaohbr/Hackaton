package br.com.zup.hackatontimesheet.refund_report.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.utils.adapters.SimpleSpinnerAdapter;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;

/**
 * Created by joaoh on 13/04/2018.
 */

public class RefundReportActivity extends BaseActivity implements RefundReportContract.View {

    private Spinner currencySpinner;
    private TextView totalTextView;
    private RefundReportContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_report);

        RefundReportContract.ChildView childView = (RefundReportContract.ChildView)getSupportFragmentManager().findFragmentById(R.id.refund_list_fragment);

        mPresenter = new RefundReportPresenter(this, childView);

        childView.bindPresenter(mPresenter);

        currencySpinner = findViewById(R.id.currency_spinner);
        totalTextView = findViewById(R.id.total);

        mPresenter.fetchData();
    }

    @Override
    public void setupCurrencySpinner(String[] currencies) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(this, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);
    }
}
