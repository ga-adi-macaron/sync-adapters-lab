package net.serkanbal.syncadapterlab;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Serkan on 08/12/16.
 */

public class StockInfoUpdateService extends Service {
    private static SyncAdapter sSyncAdapter = null;  //we wanted it to be syncing one at a time; so modifier is static.
    private static final Object sSyncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) { //When you put a code inside synchronized; it is can only be accesible by one thread at a time.
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
