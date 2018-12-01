package com.example.cassi.trab3_cssioejoao;

import android.provider.BaseColumns;

public class LivroContract{
    public final class Livro implements BaseColumns {
        public final static String TABLE_NAME = "Livros";
        public static final String COLUMN_NAME_ID = "_ID";
        public final static String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_AUTOR = "autor";
        public static final String COLUMN_NAME_EDITORA = "editora";
        public static final String COLUMN_NAME_VERSAO = "versao";
        public static final String COLUMN_NAME_DISPONIBILIDADE = "disponibilidade";
        public static final String COLUMN_NAME_ANO = "ano";

        public final static String CREATE_LIVRO  = "CREATE TABLE "+Livro.TABLE_NAME+" ("
                    + Livro.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Livro.COLUMN_NAME_TITULO+ " TEXT, "
                    + Livro.COLUMN_NAME_AUTOR+ " TEXT,"
                    + Livro.COLUMN_NAME_EDITORA+ " TEXT"
                    + Livro.COLUMN_NAME_VERSAO+ " TEXT, "
                    + Livro.COLUMN_NAME_AUTOR+ " TEXT,"
                    + Livro.COLUMN_NAME_DISPONIBILIDADE+ " TEXT"
                    + Livro.COLUMN_NAME_ANO+ " TEXT"
                    +")";
            public final static String DROP_LIVRO = "DROP TABLE IF EXISTS "+Livro.TABLE_NAME;
    }
}