package br.com.zup.hackatontimesheet.utils.generic_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.zup.hackatontimesheet.R;
import br.com.zup.hackatontimesheet.application.TimesheetApplication;
import br.com.zup.hackatontimesheet.login.ui.LoginActivity;

public class LoggedActivity extends BaseActivity {


    protected boolean isLogged() {
        return ((TimesheetApplication)getApplication()).isLogged();
    }

    protected void onUserNotLogged() {
        startActivity(new Intent(LoggedActivity.this, LoginActivity.class));
        this.finish();
    }
}
