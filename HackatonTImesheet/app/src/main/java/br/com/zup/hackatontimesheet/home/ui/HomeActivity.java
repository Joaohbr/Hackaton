package br.com.zup.hackatontimesheet.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.utils.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private TextView mTextMessage;
    private BottomNavigationView mNavigation;
    private HomeContract.Presenter mPresenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_projects:
                    mTextMessage.setText(R.string.title_projects);
                    return true;
                case R.id.navigation_timesheet_approvals:
                    mTextMessage.setText(R.string.title_timesheet_approvals);
                    return true;
                case R.id.navigation_refund_approvals:
                    mTextMessage.setText(R.string.title_refund_approvals);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = findViewById(R.id.message);
        mNavigation = findViewById(R.id.navigation);

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mPresenter = new HomePresenter();
    }

}
