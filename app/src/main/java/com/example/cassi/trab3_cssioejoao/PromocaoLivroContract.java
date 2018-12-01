package com.example.cassi.trab3_cssioejoao;

import android.provider.BaseColumns;

public class PromocaoLivroContract{
    public final class PromocaoLivro implements BaseColumns {
        public final static String TABLE_NAME = "PromocaoLivro";
        public final static String COLUMN_NAME_LIVRO = "titulo";
        public static final String COLUMN_NAME_PROMOCAO = "nome";

        public final static String CREATE_PARTICIPACAO  = "CREATE TABLE "+PromocaoLivro.TABLE_NAME+" ("
                + PromocaoLivro.COLUMN_NAME_LIVRO+ " TEXT, "
                + PromocaoLivro.COLUMN_NAME_PROMOCAO+ " TEXT,"
                + "PRIMARY KEY (livro,nome),"
                + "FOREIGN KEY(titulo) REFERENCES Livro(titulo), "
                + "FOREIGN KEY(nome) REFERENCES Promocao(nome)"
                +")";
        public final static String DROP_PARTICIPACAO = "DROP TABLE IF EXISTS "+PromocaoLivro.TABLE_NAME;
    }
}

