package drsdrs.stockpriceclient;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ds on 12/8/16.
 */

public class StockRecyclerViewAdapter extends RecyclerView.Adapter<StockRecyclerViewAdapter.StockViewHolder> {

    List<Stock> mStockList;

    public StockRecyclerViewAdapter(List<Stock> stockList) {
        mStockList = stockList;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new StockViewHolder(inflater.inflate(R.layout.stock_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final StockViewHolder holder, int position) {
        holder.mStockName.setText(mStockList.get(position).getStockName() + " (" + mStockList.get(position).getStockSymbol());
        holder.mStockPrice.setText(String.valueOf(mStockList.get(position).getStockPrice()));
        if (mStockList.get(position).getExchange().equalsIgnoreCase("NASDAQ")){
        holder.mStockContainer.setBackgroundColor(Color.MAGENTA);}
    }

    @Override
    public int getItemCount() {
        return mStockList.size();
    }

    public void swapData(Cursor cursor) {
        mStockList.clear();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String name = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCKNAME));
                String symbol = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL));
                Double price = cursor.getDouble(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_PRICE));
                String exchange = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_EXCHANGE));

                mStockList.add(new Stock(name, price, symbol, exchange));
                cursor.moveToNext();
            }
        }

        notifyDataSetChanged();
    }


    public class StockViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mStockContainer;
        public TextView mStockName, mStockPrice;

        public StockViewHolder(View itemView) {
            super(itemView);
            mStockName = (TextView) itemView.findViewById(R.id.stock_name);
            mStockPrice = (TextView) itemView.findViewById(R.id.stock_price);
            mStockContainer = (LinearLayout) itemView.findViewById(R.id.stock_container);
        }
    }
}
