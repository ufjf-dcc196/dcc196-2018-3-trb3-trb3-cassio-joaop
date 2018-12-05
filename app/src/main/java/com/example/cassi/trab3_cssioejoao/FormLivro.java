package com.example.cassi.trab3_cssioejoao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormLivro extends AppCompatActivity {

    private Button btnCadastraLivro,btnVoltar;
    private EditText titulo,autor, editora,versao,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_livro);

        btnCadastraLivro = (Button) findViewById(R.id.btnCadastraLivro);
        btnVoltar = (Button) findViewById(R.id.btn_formPromoVoltar);
        titulo = (EditText) findViewById(R.id.txtTitulo);
        autor = (EditText) findViewById(R.id.txtAutor);
        editora = (EditText) findViewById(R.id.txtEditora);
        versao = (EditText) findViewById(R.id.txtVersao);
        ano = (EditText) findViewById(R.id.txtAno);


        btnCadastraLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();

                valores.put(LivroContract.Livro.COLUMN_NAME_TITULO, titulo.getText().toString());
                valores.put(LivroContract.Livro.COLUMN_NAME_AUTOR, autor.getText().toString());
                valores.put(LivroContract.Livro.COLUMN_NAME_EDITORA, editora.getText().toString());
                valores.put(LivroContract.Livro.COLUMN_NAME_VERSAO, versao.getText().toString());
                valores.put(LivroContract.Livro.COLUMN_NAME_DISPONIBILIDADE, "Disponivel");
                valores.put(LivroContract.Livro.COLUMN_NAME_ANO, ano.getText().toString());

                long id = db.insert(LivroContract.Livro.TABLE_NAME,null, valores);
                Log.i("DBINFO", "registro criado com id: "+id);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }
}
