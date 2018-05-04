package br.com.zup.hackatontimesheet.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.refund_approvals.ui.RefundApprovalsListFragment;
import br.com.zup.hackatontimesheet.timesheet_approvals.ui.TimesheetApprovalsFragment;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private BottomNavigationView mNavigation;
    private HomeContract.Presenter mPresenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_timesheet_approvals:
                    openFragment(TimesheetApprovalsFragment. newInstance(),R.id.container);
                    return true;
                case R.id.navigation_refund_approvals:
                    openFragment(RefundApprovalsListFragment.newInstance(),R.id.container);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigation = findViewById(R.id.navigation);

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigation.setSelectedItemId(R.id.navigation_timesheet_approvals);

        mPresenter = new HomePresenter();
    }
}
