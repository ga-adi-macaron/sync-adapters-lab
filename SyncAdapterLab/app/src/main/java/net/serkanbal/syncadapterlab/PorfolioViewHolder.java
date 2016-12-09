package net.serkanbal.syncadapterlab;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Serkan on 08/12/16.
 */

public class PorfolioViewHolder extends RecyclerView.ViewHolder{
    public TextView mCompanyName;
    public TextView mCompanyValue;

    public PorfolioViewHolder(View itemView) {
        super(itemView);
        mCompanyName = (TextView) itemView.findViewById(android.R.id.text1);
        mCompanyValue = (TextView) itemView.findViewById(android.R.id.text2);
    }
}
