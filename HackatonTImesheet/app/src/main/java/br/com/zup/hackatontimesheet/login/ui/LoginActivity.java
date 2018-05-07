package br.com.zup.hackatontimesheet.login.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.home.ui.HomeActivity;
import br.com.zup.hackatontimesheet.login.di.LoginComponent;
import br.com.zup.hackatontimesheet.utils.generic_activities.BaseActivity;
import br.com.zup.multistatelayout.MultiStateLayout;

/**
 * Created by joaoh on 13/04/2018.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String IS_LOG_OUT = "is_logout";
    private static final int RC_SIGN_IN = 100;

    private MultiStateLayout mMultiStateLayout;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    LoginContract.Presenter mPresenter;

    private LoginComponent loginComponent;

    public static Intent getSignOutIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(IS_LOG_OUT, true);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mMultiStateLayout = findViewById(R.id.multi_state_layout);
        signInButton = findViewById(R.id.sign_in_button);

        loginComponent = ((TimesheetApplication)getApplication()).getAppComponent()
                .loginComponentbuilder()
                .view(this)
                .build();

        loginComponent.inject(this);

        mMultiStateLayout.setViewForState(getLayoutInflater().inflate(R.layout.login_loading_view, mMultiStateLayout,false),
                MultiStateLayout.State.LOADING);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMultiStateLayout.setState(MultiStateLayout.State.LOADING);
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        if(getIntent().getBooleanExtra(IS_LOG_OUT,false)) {
            signOut();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void openHomeActivity() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }


    @Override
    public void enableLoading(boolean enable) {
        if(enable) {
            mMultiStateLayout.setState(MultiStateLayout.State.LOADING);
        } else {
            mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //TODO: Signed in successfully, show authenticated UI.
            mPresenter.signIn(account);

        } catch (ApiException e) {
            //TODO: The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mMultiStateLayout.setState(MultiStateLayout.State.LOADING);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mMultiStateLayout.setState(MultiStateLayout.State.CONTENT);
                        mPresenter.signOut();
                    }
                });
    }

}
