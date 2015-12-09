package com.github.nickygiorgi.fooddiary.db;

import android.provider.BaseColumns;

public final class contract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public contract() {}

    public static abstract class DAT_Pages implements BaseColumns {
        public static final String TABLE_NAME = "DAT_Pages";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_FOOD = "food";
        public static final String COLUMN_FEELING_ID = "feeling_id";
    }

    public static abstract class X_Feelings implements BaseColumns {
        public static final String TABLE_NAME = "X_Feelings";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DESC = "desc";
    }

    public static abstract class X_Poo_Consistency implements BaseColumns {
        public static final String TABLE_NAME = "X_Poo_Consistency";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DESC = "desc";
    }

    public static abstract class DAT_Foods implements BaseColumns {
        public static final String TABLE_NAME = "DAT_Foods";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_PAGE_ID = "page_id";
    }

    public static abstract class DAT_Poos implements BaseColumns {
        public static final String TABLE_NAME = "DAT_Poos";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CONSISTENCY_ID = "consistency_id";
        public static final String COLUMN_IS_BLOOD = "is_blood";
        public static final String COLUMN_IS_MUCUS = "is_mucus";
        public static final String COLUMN_PAGE_ID = "page_id";
    }

    public static abstract class X_Meds implements BaseColumns {
        public static final String TABLE_NAME = "X_Meds";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DESC = "desc";
    }

    public static abstract class DAT_Meds implements BaseColumns {
        public static final String TABLE_NAME = "DAT_Meds";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PAGE_ID = "page_id";
        public static final String COLUMN_MED_ID = "med_id";
    }
}