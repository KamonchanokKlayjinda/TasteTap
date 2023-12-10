package com.example.tastetap;

import android.provider.BaseColumns;

public interface ConstantsHistoryDB extends BaseColumns {
    public static final String TABLE_NAME = "History";
    public static final String COL_TIME = "Time";
    public static final String COL_NAME = "Name";
    public static final String COL_CATEGORY = "Category";
    public static final String COL_CAL = "Calories";
}
