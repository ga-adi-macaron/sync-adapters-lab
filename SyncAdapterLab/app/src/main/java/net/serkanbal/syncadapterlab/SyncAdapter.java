package net.serkanbal.syncadapterlab;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Serkan on 08/12/16.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter{
    private static final String TAG = "SyncAdapter";
    private List<String> mCompanyList = new ArrayList<>();
    private ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s,
                              ContentProviderClient contentProviderClient, SyncResult syncResult) {

        Log.d(TAG, "onPerformSync: " );

        Cursor cursor = mContentResolver.query(StockPortfolioContract.Stocks.CONTENT_URI,
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                mCompanyList.add(cursor.getString(cursor.
                        getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL)));
                cursor.moveToNext();
            }
        }

        Log.d(TAG, "onPerformSync: " + mCompanyList.get(0));

        for (int i = 0; i < mCompanyList.size(); i++) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="
                            + mCompanyList.get(i))
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    //Get the new stock value from the JSON.
                    //Update content provider with new info.
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
