package shuvalov.nikita.stocksyncadapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by NikitaShuvalov on 12/8/16.
 */

public class BrothSyncAdapter extends AbstractThreadedSyncAdapter {
    private Context mContext;

    public BrothSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
    }

    public BrothSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContext = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        OkHttpClient client = new OkHttpClient();
        Cursor cursor = null;
        try {
             cursor = contentProviderClient.query(StockPortfolioContract.Stocks.CONTENT_URI,
                    null,
                    null,
                    null,
                    null,
                    null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ArrayList<String> stockSymbols = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                stockSymbols.add(cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL)));
                cursor.moveToNext();
            }
        }
        Request request= new Request.Builder()
                .url("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="+symbol);
        client.newCall()

    }
}
