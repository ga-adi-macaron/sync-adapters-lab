package com.korbkenny.kennysyncadapterlab;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KorbBookProReturns on 12/8/16.
 */

public class StockRecyclerViewAdapter extends RecyclerView.Adapter<StockRecyclerViewAdapter.StockViewHolder> {
    private List<Stock> mStockList;

    public StockRecyclerViewAdapter(List<Stock> stockList) {
        mStockList = stockList;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new StockViewHolder(inflater.inflate(R.layout.stock_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.mName.setText(mStockList.get(position).getStockName());
        holder.mPrice.setText(mStockList.get(position).getStockPrice().toString());
        if(mStockList.get(position).getStockExchange().equals("NASDAQ")){
            holder.mName.setBackgroundColor(Color.parseColor("#7799FF"));
            holder.mPrice.setBackgroundColor(Color.parseColor("#7799FF"));
        }
    }

    @Override
    public int getItemCount() {
        return mStockList.size();
    }

    public void swapData(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Stock stock = new Stock(cursor.getString(cursor.getColumnIndex("stockname")),
                        cursor.getInt(cursor.getColumnIndex("quantity")),
                        cursor.getDouble(cursor.getColumnIndex("price")),
                        cursor.getString(cursor.getColumnIndex("exchange")));
                mStockList.add(stock);
                cursor.moveToNext();
            }
        }
        notifyDataSetChanged();
    }

    class StockViewHolder extends RecyclerView.ViewHolder{
        TextView mName, mPrice;

        public StockViewHolder(View itemView) {
            super(itemView);
            mName = (TextView)itemView.findViewById(R.id.stock_name);
            mPrice = (TextView)itemView.findViewById(R.id.stock_price);
        }
    }
}




