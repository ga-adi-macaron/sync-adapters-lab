package drewmahrt.generalassemb.ly.investingportfolio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by jonlieblich on 12/8/16.
 */

public class StockSyncService extends Service {
    private static final Object sAdapterLock = new Object();
    private static SyncAdapter sAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sAdapterLock) {
            if(sAdapter == null) {
                sAdapter = new SyncAdapter(getApplicationContext(), false);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sAdapter.getSyncAdapterBinder();
    }
}
