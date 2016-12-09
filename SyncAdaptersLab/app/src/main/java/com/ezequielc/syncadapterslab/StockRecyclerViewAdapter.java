package com.ezequielc.syncadapterslab;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by student on 12/8/16.
 */

public class StockRecyclerViewAdapter extends RecyclerView.Adapter<StockRecyclerViewAdapter.StockViewHolder> {
    private List<Stock> mStockLists;

    public StockRecyclerViewAdapter(List<Stock> stockLists) {
        mStockLists = stockLists;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.mStockName.setText(mStockLists.get(position).getStockName());
        holder.mStockSymbol.setText("(" + mStockLists.get(position).getStockSymbol() + ")");
        holder.mStockPrice.setText("$" + String.valueOf(mStockLists.get(position).getStockPrice()));
        if (mStockLists.get(holder.getAdapterPosition()).isNASDAQ()) {
            holder.mStockName.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return mStockLists.size();
    }

    public void swapData(Cursor cursor){
        mStockLists.clear();

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Stock stock = new Stock();
                stock.setStockName(cursor.getString(cursor.getColumnIndex("stockname")));
                stock.setStockSymbol(cursor.getString(cursor.getColumnIndex("symbol")));
                stock.setStockPrice(Double.parseDouble(cursor.getString(cursor.getColumnIndex("price"))));
                if (cursor.getString(cursor.getColumnIndex("exchange")).equals("NASDAQ")) {
                    stock.setNASDAQ(true);
                }
                mStockLists.add(stock);
                cursor.moveToNext();
            }
        }

        notifyDataSetChanged();
    }

    class StockViewHolder extends RecyclerView.ViewHolder{
        TextView mStockName;
        TextView mStockSymbol;
        TextView mStockPrice;

        public StockViewHolder(View itemView) {
            super(itemView);

            mStockName = (TextView) itemView.findViewById(R.id.stock_name);
            mStockSymbol = (TextView) itemView.findViewById(R.id.stock_symbol);
            mStockPrice = (TextView) itemView.findViewById(R.id.stock_price);
        }
    }
}
