package com.colinbradley.syncadapterlab;

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
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private StocksAdapter mAdapter;
    private RecyclerView mRV;
    private TextView mTimeStamp;
    public static final int STOCK_LOADER = 1;
    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "default_account";
    private Account mAccount;
    public List<StockObject> mStocksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeStamp = (TextView)findViewById(R.id.time_stamp);
        mAccount = createAccount(this);
        mStocksList = new ArrayList<>();

        mRV = (RecyclerView)findViewById(R.id.recyclerview);
        mRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new StocksAdapter(mStocksList);
        mRV.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(STOCK_LOADER, null, this);

        ContentResolver.setSyncAutomatically(mAccount, StockContract.AUTHORITY, true);
        ContentResolver.addPeriodicSync(mAccount, StockContract.AUTHORITY, Bundle.EMPTY, 60);
    }

    public static Account createAccount(Context context){
        Account account = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager)context.getSystemService(ACCOUNT_SERVICE);

        if (accountManager.addAccountExplicitly(account,null,null)){

        }else {

        }
        return account;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case STOCK_LOADER:
                return new CursorLoader(this,
                        StockContract.StockObject.CONTENT_URI,
                        null,null,null,null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mTimeStamp.setText(String.valueOf(DateFormat.getDateTimeInstance().format(new Date())));

        mAdapter.swapData(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapData(null);

    }
}
