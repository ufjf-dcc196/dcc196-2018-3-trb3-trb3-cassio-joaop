package com.example.cassi.trab3_cssioejoao;


import android.provider.BaseColumns;

public final class PromocaoContract {
    public final class Promocao implements BaseColumns {
        public final static String TABLE_NAME = "Promocao";
        public static final String COLUMN_NAME_ID = "_ID";
        public final static String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_VALOR = "valor";

        public final static String CREATE_PROMOCAO  = "CREATE TABLE "+Promocao.TABLE_NAME+" ("
                + Promocao.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Promocao.COLUMN_NAME_NOME + " TEXT UNIQUE, "
                + Promocao.COLUMN_NAME_VALOR + " TEXT"
                +")";

        public final static String DROP_PROMOCAO = "DROP TABLE IF EXISTS "+Promocao.TABLE_NAME;
    }
}