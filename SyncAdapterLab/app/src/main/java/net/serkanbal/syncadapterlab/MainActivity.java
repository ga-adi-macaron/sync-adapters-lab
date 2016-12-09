package net.serkanbal.syncadapterlab;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "default_account";
    public static final int PORTFOLIO_LOADER = 0;
    private RecyclerView mPortfolioRecyclerView;
    private PortfolioRecyclerAdapter mAdapter;

    Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccount = createSyncAccount(this);

        mPortfolioRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mPortfolioRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        mAdapter = new PortfolioRecyclerAdapter(new ArrayList<CompanyInfo>());
        mPortfolioRecyclerView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(PORTFOLIO_LOADER, Bundle.EMPTY, this);

        //Manual sync
//        Bundle settingsBundle = new Bundle();
//        settingsBundle.putBoolean(
//                ContentResolver.SYNC_EXTRAS_MANUAL, true);
//        settingsBundle.putBoolean(
//                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
//        ContentResolver.requestSync(mAccount, StockPortfolioContract.AUTHORITY, settingsBundle);

        //Periodic sync
        ContentResolver.setSyncAutomatically(mAccount, StockPortfolioContract.AUTHORITY,true);
        ContentResolver.addPeriodicSync(
                mAccount,
                StockPortfolioContract.AUTHORITY,
                Bundle.EMPTY,
                60);

    }

    public static Account createSyncAccount(Context context) {
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);

        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);

        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
        } else {
        }
        return newAccount;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case PORTFOLIO_LOADER:
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
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //
    }
}
