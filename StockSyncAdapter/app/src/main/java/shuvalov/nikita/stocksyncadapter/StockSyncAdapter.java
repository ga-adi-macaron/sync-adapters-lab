package shuvalov.nikita.stocksyncadapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by NikitaShuvalov on 12/8/16.
 */

public class StockSyncAdapter extends AbstractThreadedSyncAdapter {
    private Context mContext;

    public StockSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
    }

    public StockSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContext = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Cursor cursor=null;
        Log.d("OnSyncPerform", "s");
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

        for(String symbol: stockSymbols) {
            RequestQueue queue = Volley.newRequestQueue(mContext);
            String quoteUrl = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=" + symbol;

            final JsonObjectRequest quoteJsonRequest = new JsonObjectRequest
                    (Request.Method.GET, quoteUrl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(MainActivity.class.getName(), "Response2: " + response.toString());
                            try {
                                ContentResolver contentResolver = mContext.getContentResolver();
                                ContentValues values = new ContentValues();
                                values.put(StockPortfolioContract.Stocks.COLUMN_PRICE, response.getInt("LastPrice"));
                                values.put(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL,response.getString("Symbol"));
                                contentResolver.update(StockPortfolioContract.Stocks.CONTENT_URI,
                                        values,
                                        StockPortfolioContract.Stocks.COLUMN_STOCKNAME+"=?",
                                        new String[]{response.getString("Name")}
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(MainActivity.class.getName(), "Error occurred");
                        }
                    });
            String exchangeUrl = "http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=" + symbol;

            JsonArrayRequest exchangeJsonRequest = new JsonArrayRequest
                    (Request.Method.GET, exchangeUrl, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                ContentResolver contentResolver = mContext.getContentResolver();
                                ContentValues values = new ContentValues();
                                String exchange = ((JSONObject)response.get(0)).getString("Exchange");
                                String name = ((JSONObject)response.get(0)).getString("Name");

                                values.put(StockPortfolioContract.Stocks.COLUMN_EXCHANGE, exchange);
                                contentResolver.update(StockPortfolioContract.Stocks.CONTENT_URI,
                                        values,
                                        StockPortfolioContract.Stocks.COLUMN_STOCKNAME +"=?",
                                        new String[]{name}
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(MainActivity.class.getName(), "Error occurred");
                        }
                    });
            queue.add(quoteJsonRequest);
            queue.add(exchangeJsonRequest);
        }
    }
}
