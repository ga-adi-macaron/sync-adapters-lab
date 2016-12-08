package com.joelimyx.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Joe on 12/8/16.
 */

public class SyncAdapterService extends Service {

    private static StockSyncAdapter sStockSyncAdapter = null;
    private static final Object syncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (syncAdapterLock){
            if (sStockSyncAdapter == null){
                sStockSyncAdapter = new StockSyncAdapter(getApplicationContext(),true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sStockSyncAdapter.getSyncAdapterBinder();
    }
}
