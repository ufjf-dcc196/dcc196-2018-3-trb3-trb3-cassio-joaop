package com.example.cassi.trab3_cssioejoao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetPromocao extends AppCompatActivity {
    private Button btnVoltar;
    private TextView txtFormPromocaoNome;
    private RecyclerView rclLivrosCadastrados, rclLivrosNaoCadastrados;
    private LivroAdapter LivroCadastrados, LivroNaoCadastrados;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_promocao);
        txtFormPromocaoNome = (TextView) findViewById(R.id.txt_formPromocaoNome);
        btnVoltar = (Button) findViewById(R.id.btn_formPromoVoltar);
        rclLivrosCadastrados = (RecyclerView) findViewById(R.id.rcl_LvrCadastrados);
        rclLivrosCadastrados.setLayoutManager(new LinearLayoutManager(this));

        rclLivrosNaoCadastrados = (RecyclerView) findViewById(R.id.rcl_LvrNaoCadastrados);
        rclLivrosNaoCadastrados.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundleExtras = getIntent().getExtras();
        if(bundleExtras!=null)
        {
            nome = bundleExtras.getString("nome");

            Cursor cursorCadastrados = getLivroCadastrado();
            Cursor cursorLivros = getLivros();
            LivroCadastrados = new LivroAdapter(cursorCadastrados);
            LivroNaoCadastrados = new LivroAdapter(cursorLivros);
            rclLivrosCadastrados.setAdapter(LivroCadastrados);
            rclLivrosNaoCadastrados.setAdapter(LivroNaoCadastrados);

            txtFormPromocaoNome.setText(nome);
        }
        LivroNaoCadastrados.setOnEventLongClickListener(new LivroAdapter.OnEventLongClickListener() {
            @Override
            public void onEventLongClick(View EventView, int position) {
                SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();

                TextView txtLivroTitulo = (TextView) EventView.findViewById(R.id.txt_layoutColumn1);
                valores.put(PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_LIVRO, txtLivroTitulo.getText().toString());
                valores.put(PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_PROMOCAO, nome);

                long id = db.insert(PromocaoLivroContract.PromocaoLivro.TABLE_NAME,null, valores);
                LivroCadastrados.setCursor(getLivroCadastrado());
                Log.i("DBINFO", "registro criado com id: "+id);
            }
        });
        LivroCadastrados.setOnEventLongClickListener(new LivroAdapter.OnEventLongClickListener() {
            @Override
            public void onEventLongClick(View LivroView, int position) {
                TextView txtTitulo = (TextView) LivroView.findViewById(R.id.txt_layoutColumn1);
                String titulo = txtTitulo.getText().toString();

                SQLiteDatabase db = MainActivity.dbHelper.getReadableDatabase();
                String restricoes = PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_LIVRO + " = ? AND " + PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_PROMOCAO + " = ?";
                String params[] = {titulo, nome};
                long id = db.delete(PromocaoLivroContract.PromocaoLivro.TABLE_NAME,restricoes,params);
                LivroCadastrados.setCursor(getLivroCadastrado());
                Log.i("DBINFO", "registro criado com id: "+id);
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }

    private Cursor getLivroCadastrado() {
        SQLiteDatabase db = MainActivity.dbHelper.getReadableDatabase();
        String []visao = {
                PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_LIVRO
        };
        String sort = PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_LIVRO + " ASC";
        String restricoes = PromocaoLivroContract.PromocaoLivro.COLUMN_NAME_PROMOCAO + " = ?";
        String params[] = {nome};
        return db.query(PromocaoLivroContract.PromocaoLivro.TABLE_NAME, visao,restricoes,params,null,null, sort);
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
