package br.com.zup.hackatontimesheet.login.ui;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by joaoh on 13/04/2018.
 */

public interface LoginContract {
    interface View {
        void openHomeActivity();

        void onError();

        void enableLoading(boolean enable);
    }

    interface Presenter {
        void signIn(GoogleSignInAccount account);

        void signOut();
    }
}
