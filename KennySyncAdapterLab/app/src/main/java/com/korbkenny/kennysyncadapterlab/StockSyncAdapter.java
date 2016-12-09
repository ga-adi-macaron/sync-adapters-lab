package com.korbkenny.kennysyncadapterlab;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KorbBookProReturns on 12/8/16.
 */

public class StockSyncAdapter extends AbstractThreadedSyncAdapter {
    private ContentResolver mResolver;

    public StockSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mResolver = context.getContentResolver();
    }

    public StockSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {

        OkHttpClient client = new OkHttpClient();
        Cursor cursor = mResolver.query(Uri.parse("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="),
                null,null,null,null);
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Request request = new Request.Builder()
                        .url("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="
                                +cursor.getString(cursor.getColumnIndex("symbol")))
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if(!response.isSuccessful()){
                        throw new IOException("Hmm, that's strange..." +response);
                    }

                    JSONObject json = new JSONObject();
                    String name = json.getString("Name");
                    String[] nameString = {name};
                    Double price = Double.parseDouble(json.getString("LastPrice"));

                    ContentValues values = new ContentValues();
                    values.put("price",cursor.getDouble(cursor.getColumnIndex("price")));
                    mResolver.update(Uri.parse(StockPortfolioContract.Stocks.COLUMN_PRICE),values,"Name",nameString);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
