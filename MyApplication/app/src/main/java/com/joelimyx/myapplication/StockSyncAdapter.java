package com.joelimyx.myapplication;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joe on 12/8/16.
 */

public class StockSyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "StockSyncAdapter";
    private ContentResolver mContentResolver;
    private Context mContext;

    public StockSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
        mContentResolver= context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d(TAG, "onPerformSync: syncing");
        RequestQueue queue = Volley.newRequestQueue(mContext);
        Cursor cursor = mContentResolver.query(StockPortfolioContract.Stocks.CONTENT_URI,null,null,null,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                        "http://dev.markitondemand.com/Api/v2/Quote/json?symbol=" + cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL)),
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ContentValues values = new ContentValues();
                            Log.d(TAG, "onResponse: "+response.toString());
                            values.put(StockPortfolioContract.Stocks.COLUMN_PRICE,response.getDouble("LastPrice"));
                            mContentResolver.update(StockPortfolioContract.Stocks.CONTENT_URI,values,
                                    StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL+" = ?",
                                    new String[]{response.getString("Symbol")});
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Failed to get stocks", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                queue.add(request);
                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
