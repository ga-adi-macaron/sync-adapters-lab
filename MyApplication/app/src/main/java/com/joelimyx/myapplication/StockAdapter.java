package com.joelimyx.myapplication;

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
 * Created by Joe on 12/8/16.
 */

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> {
    List<Stock> mStockList;

    public StockAdapter(List<Stock> stockList) {
        mStockList = stockList;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_stock,parent,false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.mStockNameText.setText(mStockList.get(position).getName());
        holder.mStockSymbolText.setText(mStockList.get(position).getSymbol());
        holder.mStockPriceText.setText(String.valueOf(mStockList.get(position).getPrice()));
        if (mStockList.get(position).getExchange().equalsIgnoreCase("NASDAQ")){
            holder.mLayout.setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public int getItemCount() {
        return mStockList.size();
    }

    public void swapData(Cursor cursor){
        mStockList.clear();

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCKNAME));
                String symbol= cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL));
                String exchange = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_EXCHANGE));
                double price = cursor.getDouble(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_PRICE));
                mStockList.add(new Stock(name,symbol,exchange,price));
                cursor.moveToNext();
            }
        }

        notifyDataSetChanged();
    }

    class StockViewHolder extends RecyclerView.ViewHolder{
        public TextView mStockNameText, mStockSymbolText, mStockPriceText;
        public LinearLayout mLayout;
        public StockViewHolder(View itemView) {
            super(itemView);
            mStockNameText = (TextView) itemView.findViewById(R.id.name);
            mStockSymbolText= (TextView) itemView.findViewById(R.id.symbol);
            mStockPriceText= (TextView) itemView.findViewById(R.id.price);
            mLayout= (LinearLayout) itemView.findViewById(R.id.layout);

        }
    }
}
