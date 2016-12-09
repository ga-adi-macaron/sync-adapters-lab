package drewmahrt.generalassemb.ly.investingportfolio;

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

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jonlieblich on 12/8/16.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private ContentResolver mResolver;
    private static final Uri THIS_URI = Uri.parse("content://drewmahrt.generalassemb.ly.investingportfolio.MyContentProvider/stocks/");
    private static final String URL = "http://dev.markitondemand.com/MODApis/api/v2/quote/json?symbol=";

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        OkHttpClient client = new OkHttpClient();
        Cursor cursor = mResolver.query(THIS_URI,
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Request req = new Request.Builder()
                        .url(URL+cursor.getString(cursor.getColumnIndex("symbol")))
                        .build();
                try {
                    Response response = client.newCall(req).execute();

                    Gson gson = new Gson();

                    Stock stock = gson.fromJson(response.body().string(), Stock.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
    }
}
