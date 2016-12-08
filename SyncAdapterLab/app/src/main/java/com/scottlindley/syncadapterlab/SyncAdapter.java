package com.scottlindley.syncadapterlab;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Scott Lindley on 12/8/2016.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter{
    private ContentResolver mContentResolver;
    private static final Uri CONTENT_URI = Uri.parse("content://drewmahrt.generalassemb.ly.investingportfolio.MyContentProvider/stocks/");
    private static final String BASE_URL = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=";

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        //Clear current local data
        Log.d(TAG, "onPerformSync: ");
        OkHttpClient client = new OkHttpClient();
        Cursor cursor = mContentResolver.query(MainActivity.BASE_CONTENT_URI, null, null, null, null);
        //Make and execute a request for each stock
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()) {
                Request request = new Request.Builder()
                        .url(BASE_URL + cursor.getString(cursor.getColumnIndex("symbol")))
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if(!response.isSuccessful()){
                        throw new IOException("Unexpected code "+response);
                    }

                    Gson gson = new Gson();
                    Stock newStock = gson.fromJson(response.body().string(), Stock.class);
                    Log.d(TAG, "onPerformSync: "+cursor.getString(cursor.getColumnIndex("exchange")));
                    if(cursor.getString(cursor.getColumnIndex("exchange")).equalsIgnoreCase("NASDAQ")){
                        newStock.setNasdaq(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
