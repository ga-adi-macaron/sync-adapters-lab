package com.joelimyx.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Joe on 12/8/16.
 */

public class AccountService extends Service {
    private AccountAuthenticator mAccountAuthenticator;

    @Override
    public void onCreate() {
        mAccountAuthenticator = new AccountAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAccountAuthenticator.getIBinder();
    }
}
