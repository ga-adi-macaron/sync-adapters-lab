package com.korbkenny.kennysyncadapterlab;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by KorbBookProReturns on 12/8/16.
 */

public class StockSyncService extends Service {
    private StockSyncAdapter sSyncAdapter;
    private static final Object sSyncLock = new Object();

    @Override
    public void onCreate() {
        synchronized (sSyncLock){
            if (sSyncAdapter == null){
                sSyncAdapter = new StockSyncAdapter(getApplicationContext(),true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
