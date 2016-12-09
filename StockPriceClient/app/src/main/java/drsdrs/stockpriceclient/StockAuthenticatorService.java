package drsdrs.stockpriceclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.Authenticator;

/**
 * Created by ds on 12/8/16.
 */

public class StockAuthenticatorService extends Service {

    // Instance field that stores the authenticator object
    private StockAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new StockAuthenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}

