package com.korbkenny.kennysyncadapterlab;

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

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    public static final int LOADER_STOCK = 12345;
    RecyclerView mRecyclerView;
    StockRecyclerViewAdapter mRecyclerViewAdapter;
    List<Stock> mStockList;
    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "default_account";
    Account mAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccount = createSyncAccount(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerViewAdapter = new StockRecyclerViewAdapter(mStockList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        ContentResolver.setSyncAutomatically(mAccount,
                StockPortfolioContract.AUTHORITY,
                true);
        ContentResolver.addPeriodicSync(mAccount,
                StockPortfolioContract.AUTHORITY,
                Bundle.EMPTY,
                60);

        getSupportLoaderManager().initLoader(LOADER_STOCK,null,this);



    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case LOADER_STOCK:
                return new CursorLoader(this,
                        StockPortfolioContract.Stocks.CONTENT_URI,
                        null,null,null,null);
            default:
                return null;
        }
    }

    public static Account createSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        return newAccount;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRecyclerViewAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mRecyclerViewAdapter.swapData(null);

    }


}
