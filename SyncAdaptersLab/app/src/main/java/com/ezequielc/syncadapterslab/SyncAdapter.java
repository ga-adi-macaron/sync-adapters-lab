package com.ezequielc.syncadapterslab;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by student on 12/8/16.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "SyncAdapter";
    public static final String BASE_URL = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=";

    ContentResolver mContentResolver;

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
        Log.d(TAG, "onPerformSync: Syncing!");
        Cursor cursor = mContentResolver.query(MainActivity.BASE_CONTENT_URI, null, null, null, null);
        String symbol = cursor.getString(cursor.getColumnIndex("symbol"));

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(BASE_URL + symbol)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Gson gson = new Gson();
                    // Got Stuck On This Part
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
