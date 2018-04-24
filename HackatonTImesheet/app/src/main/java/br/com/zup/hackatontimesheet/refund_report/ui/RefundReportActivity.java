package br.com.zup.hackatontimesheet.refund_report.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.zup.hackatontimesheet.R;
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

        mPresenter = new RefundReportPresenter(this);

        currencySpinner = findViewById(R.id.currency_spinner);
        totalTextView = findViewById(R.id.total);

        mPresenter.fetchData();
    }

    @Override
    public void setupCurrencySpinner(String[] currencies) {
        currencySpinner.setAdapter(new CurrencySpinnerAdapter(this,currencies));
    }
}
