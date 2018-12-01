package com.example.cassi.trab3_cssioejoao;


    import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class LivroDBHelper extends SQLiteOpenHelper {
        public final static int DATABASE_VERSION = 1;
        public final static String DATABASE_NAME = "Gerenciamento.db";

        public LivroDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(LivroContract.Livro.CREATE_LIVRO);
            db.execSQL(PromocaoContract.Promocao.CREATE_PROMOCAO);
            db.execSQL(PromocaoLivroContract.PromocaoLivro.CREATE_PROMOCAOLIVRO);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(LivroContract.Livro.DROP_LIVRO);
            db.execSQL(PromocaoContract.Promocao.DROP_PROMOCAO);
            db.execSQL(PromocaoLivroContract.PromocaoLivro.DROP_PROMOCAOLIVRO);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

