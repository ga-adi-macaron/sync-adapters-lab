package com.colinbradley.syncadapterlab;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by colinbradley on 12/8/16.
 */

public class StockContract {

    public static final String AUTHORITY = "drewmahrt.generalassemb.ly.investingportfolio.MyContentProvider";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static final class StockObject implements BaseColumns{
        public static final String TABLE_STOCKS = "stocks";
        public static final String COLUMN_STOCK_NAME = "stockname";
        public static final String COLUMN_STOCK_ABBREVIATION = "symbol";
        public static final String COLUMN_STOCK_PRICE = "price";
        public static final String COLUMN_EXCHANGE = "exchange";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, TABLE_STOCKS);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.ly.generassembly.stocks";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.ly.generalassemb.stocks";
    }
}

