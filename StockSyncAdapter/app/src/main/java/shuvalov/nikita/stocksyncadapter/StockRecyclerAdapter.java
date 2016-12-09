package shuvalov.nikita.stocksyncadapter;


import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NikitaShuvalov on 12/8/16.
 */

public class StockRecyclerAdapter extends RecyclerView.Adapter{
    List<Stock> mStockPortfolio;

    public StockRecyclerAdapter(List<Stock> stockPortfolio) {
        mStockPortfolio = stockPortfolio;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_stock,null);
            return new StockViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_nasdaq, null);
            return new NasdaqViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1){
            ((NasdaqViewHolder) holder).bindDataToViews(mStockPortfolio.get(position));
        }else{
            ((StockViewHolder) holder).bindDataToViews(mStockPortfolio.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mStockPortfolio.size();
    }

    @Override
    public int getItemViewType(int position) {
        Stock stock= mStockPortfolio.get(position);
        String exchange = stock.getExchange();
        if(mStockPortfolio.get(position).getExchange().equals("NASDAQ")){
            return 1;
        }else{
            return 0;
        }
    }
    public void swapData(Cursor cursor){
        mStockPortfolio.clear();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Stock stock = new Stock(cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCKNAME)),
                        cursor.getInt(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_QUANTITY)),
                        cursor.getLong(cursor.getColumnIndex(StockPortfolioContract.Stocks._ID)),
                        cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_EXCHANGE)),
                        cursor.getFloat(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_PRICE)));
                mStockPortfolio.add(stock);
                cursor.moveToNext();
            }
        }
        notifyDataSetChanged();
    }
}

class StockViewHolder extends RecyclerView.ViewHolder{
    TextView mName, mPrice;

    public StockViewHolder(View itemView) {
        super(itemView);
        mName = (TextView)itemView.findViewById(R.id.stock_name_text);
        mPrice = (TextView)itemView.findViewById(R.id.price_text);
    }
    public void bindDataToViews(Stock stock){
        mName.setText(stock.getStockName());
        mPrice.setText(String.valueOf(stock.getStockPrice()));

    }
}
class NasdaqViewHolder extends RecyclerView.ViewHolder{
    TextView mName, mPrice;

    public NasdaqViewHolder(View itemView) {
        super(itemView);
        mName = (TextView)itemView.findViewById(R.id.stock_name_text);
        mPrice = (TextView)itemView.findViewById(R.id.price_text);
    }
    public void bindDataToViews(Stock stock){
        mName.setText(stock.getStockName());
        mPrice.setText(String.valueOf(stock.getStockPrice()));
    }
}