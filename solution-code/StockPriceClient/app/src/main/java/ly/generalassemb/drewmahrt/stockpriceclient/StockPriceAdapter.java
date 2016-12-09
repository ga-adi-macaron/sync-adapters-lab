package ly.generalassemb.drewmahrt.stockpriceclient;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by drewmahrt on 12/5/16.
 */

public class StockPriceAdapter extends RecyclerView.Adapter<StockPriceAdapter.StockViewHolder>{
    private static final String TAG = "StockPriceAdapter";
    private List<Stock> mStocks;

    public StockPriceAdapter(List<Stock> stocks) {
        mStocks = stocks;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new StockViewHolder(inflater.inflate(android.R.layout.simple_list_item_2,parent,false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        Stock current = mStocks.get(position);


        if(current.getExchange().equals("NASDAQ"))
            holder.mContainer.setBackgroundColor(Color.GREEN);
        else
            holder.mContainer.setBackgroundColor(Color.argb(0,0,0,0));

        holder.mNameText.setText(current.getName()+" ("+current.getSymbol()+")");
        if(current.getPrice() != null)
            holder.mPriceText.setText("$"+current.getPrice());
        else
            holder.mPriceText.setText("Syncing...");
    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }

    public void swapData(Cursor cursor){
        mStocks.clear();

        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                String name = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCKNAME));
                String symbol = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL));
                String price = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_PRICE));
                String exchange = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_EXCHANGE));


                mStocks.add(new Stock(name,symbol,price,exchange));

                cursor.moveToNext();
            }
        }

        notifyDataSetChanged();
    }

    class StockViewHolder extends RecyclerView.ViewHolder{
        TextView mNameText, mPriceText;
        View mContainer;

        public StockViewHolder(View itemView) {
            super(itemView);
            mNameText = (TextView)itemView.findViewById(android.R.id.text1);
            mPriceText = (TextView)itemView.findViewById(android.R.id.text2);
            mContainer = itemView;
        }
    }
}
