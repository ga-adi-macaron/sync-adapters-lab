package com.scottlindley.syncadapterlab;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Scott Lindley on 12/8/2016.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder>{

    private List<Stock> mStocks;

    public MyRecyclerViewAdapter(List<Stock> stocks) {
        mStocks = stocks;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_items, parent, false);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.mStockName.setText(mStocks.get(position).getName());
        holder.mStockSymbol.setText("(" + mStocks.get(position).getSymbol() + ")");
        holder.mStockPrice.setText(
                "$"+String.valueOf(mStocks.get(position).getLastPrice()));
        if(mStocks.get(holder.getAdapterPosition()).isNasdaq()){
            holder.itemView.setBackgroundColor(Color.GREEN);
            Log.d(TAG, "onBindViewHolder: IS NASDAQ");
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
            Log.d(TAG, "onBindViewHolder: ISNT");
        }
    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }

    public void swapData(Cursor cursor){
        mStocks.clear();

        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Stock stock = new Stock();
                stock.setName(cursor.getString(cursor.getColumnIndex("stockname")));
                stock.setLastPrice(Double.parseDouble(cursor.getString(cursor.getColumnIndex("price"))));
                stock.setSymbol(cursor.getString(cursor.getColumnIndex("symbol")));
                if(cursor.getString(cursor.getColumnIndex("exchange")).equalsIgnoreCase("NASDAQ")){
                    stock.setNasdaq(true);
                }
                mStocks.add(stock);
                cursor.moveToNext();
            }
        }


        //Update data in the singleton class

        notifyDataSetChanged();
    }


    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView mStockName;
        public TextView mStockSymbol;
        public TextView mStockPrice;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            mStockName = (TextView)itemView.findViewById(R.id.stock_name);
            mStockSymbol = (TextView)itemView.findViewById(R.id.stock_symbol);
            mStockPrice = (TextView)itemView.findViewById(R.id.stock_price);
        }
    }
}
