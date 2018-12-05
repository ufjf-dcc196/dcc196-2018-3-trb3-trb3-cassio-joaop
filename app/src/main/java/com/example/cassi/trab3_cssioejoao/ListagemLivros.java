package com.example.cassi.trab3_cssioejoao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListagemLivros extends AppCompatActivity {

    private RecyclerView rclLivros;
    private LivroAdapter lvAdapter;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_livros);

        rclLivros = (RecyclerView) findViewById(R.id.rcl_listagemLivros);
        rclLivros.setLayoutManager(new LinearLayoutManager(this));

        btnVoltar = (Button) findViewById(R.id.btn_listagemVoltar);
        lvAdapter = new LivroAdapter(getLivros());
        rclLivros.setAdapter(lvAdapter);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        lvAdapter.setOnEventLongClickListener(new LivroAdapter.OnEventLongClickListener() {
            @Override
            public void onEventLongClick(View LivroView, int position) {
                TextView txtTitulo = (TextView) LivroView.findViewById(R.id.txt_layoutColumn1);
                String titulo = txtTitulo.getText().toString();

                SQLiteDatabase db = MainActivity.dbHelper.getReadableDatabase();
                String restricoes = LivroContract.Livro.COLUMN_NAME_TITULO + " = ?";
                String params[] = {titulo};
                db.delete(PromocaoLivroContract.PromocaoLivro.TABLE_NAME,restricoes,params);
                db.delete(LivroContract.Livro.TABLE_NAME,restricoes,params);
                lvAdapter.notifyItemRemoved(position);
            }
        });

    }





    
    
    
    private Cursor getLivros()
    {
        SQLiteDatabase db = MainActivity.dbHelper.getReadableDatabase();
        String []visao = {
                LivroContract.Livro.COLUMN_NAME_TITULO
        };
        String sort = LivroContract.Livro.COLUMN_NAME_TITULO+ " ASC";
        return db.query(LivroContract.Livro.TABLE_NAME, visao,null,null,null,null, sort);
    }

}
