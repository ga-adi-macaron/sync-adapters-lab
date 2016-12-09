package shuvalov.nikita.stocksyncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by NikitaShuvalov on 12/8/16.
 */

public class StockSyncService extends Service {
    private static StockSyncAdapter sStockSyncAdapter = null;
    private static final Object sSyncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock){
            if(sStockSyncAdapter ==null){
                sStockSyncAdapter = new StockSyncAdapter(getApplicationContext(),true);
            }
        }
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sStockSyncAdapter.getSyncAdapterBinder();
    }
}
