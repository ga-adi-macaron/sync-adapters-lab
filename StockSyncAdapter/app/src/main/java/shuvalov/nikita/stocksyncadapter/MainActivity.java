package shuvalov.nikita.stocksyncadapter;

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

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView mRecyclerView;
    public static final int STOCK_LOADER = 45;
    private StockRecyclerAdapter mAdapter;
    private TextView mTextView;

    // Account type
    public static final String ACCOUNT_TYPE = "dothething";
    // Account
    public static final String ACCOUNT = "default_account";

    Account mAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccount = createSyncAccount(this);
        mTextView = (TextView)findViewById(R.id.last_updated_text);

        getSupportLoaderManager().initLoader(STOCK_LOADER,null,this);


        setUpRecyclerView();


        ContentResolver.setSyncAutomatically(mAccount,
                StockPortfolioContract.AUTHORITY,
                true);
        ContentResolver.addPeriodicSync(mAccount,
                StockPortfolioContract.AUTHORITY,
                Bundle.EMPTY,
                10);




    }

    public void setUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.stock_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new StockRecyclerAdapter(new ArrayList<Stock>());


        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case STOCK_LOADER:
                return new CursorLoader(this,
                        StockPortfolioContract.Stocks.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapData(data);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        mTextView.setText("Last updated: "+currentDateTimeString);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapData(null);
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
}
