package br.com.zup.hackatontimesheet.home.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.home.di.HomeComponent;
import br.com.zup.hackatontimesheet.login.ui.LoginActivity;
import br.com.zup.hackatontimesheet.refund_approvals.ui.RefundApprovalsListFragment;
import br.com.zup.hackatontimesheet.timesheet_approvals.ui.TimesheetApprovalsFragment;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private BottomNavigationView mNavigation;
    private HomeContract.Presenter mPresenter;

    private HomeComponent mHomeComponent;

    public HomeComponent getmHomeComponent() {
        return mHomeComponent;
    }

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

        mHomeComponent = ((TimesheetApplication)getApplication())
                .getUserComponent()
                .getHomeComponentBuilder()
                .view(this)
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_log_out) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        startActivity(LoginActivity.getSignOutIntent(this));
        finish();
    }

}
