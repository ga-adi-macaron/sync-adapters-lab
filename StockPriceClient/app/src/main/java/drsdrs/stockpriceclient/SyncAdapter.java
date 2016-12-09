package drsdrs.stockpriceclient;

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
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by ds on 12/8/16.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;
    Context mContext;


    /**
     * Set up the sync adapter
     */

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();

    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        Cursor cursor = mContentResolver.query(StockPortfolioContract.Stocks.CONTENT_URI, null, null, null, null, null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String symbol = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL));

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://dev.markitondemand.com/Api/v2/Quote/JSON?symbol=" + symbol)
                        .build();

                Response response = null;

                try {
                    response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Double lastPrice = jsonObject.getDouble("LastPrice");

                    ContentValues values = new ContentValues();
                    values.put(StockPortfolioContract.Stocks.COLUMN_PRICE,lastPrice);

                    mContentResolver.update(StockPortfolioContract.Stocks.CONTENT_URI,
                            values,
                            StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL +"=?", new String[]{symbol});


                } catch (IOException e) {
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                cursor.moveToNext();
            }
        }
    }
}

