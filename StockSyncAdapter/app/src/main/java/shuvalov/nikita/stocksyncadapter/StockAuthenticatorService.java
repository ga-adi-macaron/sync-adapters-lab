package shuvalov.nikita.stocksyncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by NikitaShuvalov on 12/8/16.
 */

public class StockAuthenticatorService extends Service {
    private StockAuthenticator mStockAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mStockAuthenticator = new StockAuthenticator(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStockAuthenticator.getIBinder();
    }
}


