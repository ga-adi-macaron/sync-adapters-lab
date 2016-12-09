package com.colinbradley.syncadapterlab;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by colinbradley on 12/8/16.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter{
    public static final String TAG = "SyncAdapter";

    ContentResolver mContentResolver;
    Context mContext;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();
        mContext = context;
    }

    @Override
    public void onPerformSync(Account account,
                              Bundle bundle,
                              String s,
                              ContentProviderClient contentProviderClient,
                              SyncResult syncResult) {
        Log.d(TAG, "onPerformSync: Syncing Data...");

        RequestQueue queue = Volley.newRequestQueue(mContext);

        Cursor cursor = mContentResolver.query(
                StockContract.StockObject.CONTENT_URI,
                null,null,null,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                JsonObjectRequest jsonRequest = new JsonObjectRequest(
                        JsonObjectRequest.Method.GET,
                        "http://dev.markitondemand.com/Api/v2/Quote/json?symbol=" +
                                cursor.getString(cursor.getColumnIndex(StockContract.StockObject.COLUMN_STOCK_ABBREVIATION)),
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d(TAG, "onResponse: Recieved Response ---- " + response.toString());
                                    ContentValues values = new ContentValues();
                                    values.put(StockContract.StockObject.COLUMN_STOCK_PRICE, response.getDouble("LastPrice"));
                                    mContentResolver.update(StockContract.StockObject.CONTENT_URI, values,
                                            StockContract.StockObject.COLUMN_STOCK_ABBREVIATION + " = ?",
                                            new String[]{response.getString("Symbol")});
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Failed to Load Information", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onErrorResponse: ERROR--- " + error.toString());
                    }
                });
                queue.add(jsonRequest);
                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
