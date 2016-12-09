package net.serkanbal.syncadapterlab;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Serkan on 08/12/16.
 */

public class PortfolioRecyclerAdapter extends RecyclerView.Adapter<PorfolioViewHolder> {
    public List<CompanyInfo> mList;

    public PortfolioRecyclerAdapter(List<CompanyInfo> list) {
        mList = list;
    }

    @Override
    public PorfolioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PorfolioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PorfolioViewHolder holder, int position) {
        holder.mCompanyName.setText(mList.get(position).getCompanyName() + " (" +
        mList.get(position).getSymbol() + ") ");
        if (mList.get(position).getExchange().equals("NASDAQ")) {
            holder.mCompanyName.setTextColor(Color.RED);
        }
        holder.mCompanyValue.setText("$"+mList.get(position).getCurrentStockPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void swapData(Cursor cursor){
        mList.clear();

        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                CompanyInfo companyInfo =
                        new CompanyInfo(cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCKNAME)),
                                cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_PRICE)),
                                cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_EXCHANGE)),
                                cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCK_SYMBOL)));
                mList.add(companyInfo);
                cursor.moveToNext();
            }
        }

        notifyDataSetChanged();
    }
}
