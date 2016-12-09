package com.joelimyx.myapplication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private StockAdapter mStockAdapter;
    private RecyclerView mRecyclerView;
    private TextView mTimeText;
    public static final int STOCK_LOADER = 1;
    public static final String ACCOUNT_TYPE = "example.com";
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimeText = (TextView) findViewById(R.id.time);

        mAccount = createSyncAccount(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mStockAdapter = new StockAdapter(new ArrayList<Stock>());
        mRecyclerView.setAdapter(mStockAdapter);

        getSupportLoaderManager().initLoader(STOCK_LOADER,null,this);

        ContentResolver.setSyncAutomatically(mAccount,StockPortfolioContract.AUTHORITY,true);
        ContentResolver.addPeriodicSync(mAccount,StockPortfolioContract.AUTHORITY,Bundle.EMPTY,10);
    }

    public static Account createSyncAccount(Context context){
        Account newAccount = new Account("default account", ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(newAccount,null,null)){

        }else {

        }
        return newAccount;

    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case STOCK_LOADER:
                return new CursorLoader(this,
                        StockPortfolioContract.Stocks.CONTENT_URI,
                        null,
                        null,
                        null
                ,null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mTimeText.setText(String.valueOf(DateFormat.getDateTimeInstance().format(new Date())));
        mStockAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mStockAdapter.swapData(null);
    }
}
