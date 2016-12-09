package com.colinbradley.syncadapterlab;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by colinbradley on 12/8/16.
 */

public class StocksAdapter extends RecyclerView.Adapter<StockViewHolder>{

    List<StockObject> mStocks;

    public StocksAdapter(List<StockObject> stocks){
        mStocks = stocks;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {

        holder.mStockName.setText(mStocks.get(position).getStockName());
        holder.mStockAbreviation.setText(mStocks.get(position).getStockAbbr());
        holder.mStockPrice.setText(String.valueOf(mStocks.get(position).getStockPrice()));

        if (mStocks.get(position).getExchange().equalsIgnoreCase("NASDAQ")){
            holder.mLayout.setBackgroundColor(Color.CYAN);
        }

    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }

    public void swapData(Cursor cursor){
        mStocks.clear();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex(StockContract.StockObject.COLUMN_STOCK_NAME));
                String abbr = cursor.getString(cursor.getColumnIndex(StockContract.StockObject.COLUMN_STOCK_ABBREVIATION));
                double price = cursor.getDouble(cursor.getColumnIndex(StockContract.StockObject.COLUMN_STOCK_PRICE));
                String exchange = cursor.getString(cursor.getColumnIndex(StockContract.StockObject.COLUMN_EXCHANGE));

                StockObject stock = new StockObject(name,abbr,price,exchange);
                mStocks.add(stock);
                cursor.moveToNext();
            }
        }
        notifyDataSetChanged();
    }


}




class StockViewHolder extends RecyclerView.ViewHolder{

    public TextView mStockName, mStockAbreviation, mStockPrice;
    public RelativeLayout mLayout;

    public StockViewHolder(View itemView) {
        super(itemView);

        mStockName = (TextView)itemView.findViewById(R.id.stock_name);
        mStockAbreviation = (TextView)itemView.findViewById(R.id.stock_abbreviation);
        mStockPrice = (TextView)itemView.findViewById(R.id.stock_price);
        mLayout = (RelativeLayout)itemView.findViewById(R.id.stock_item_layout);
    }
}
