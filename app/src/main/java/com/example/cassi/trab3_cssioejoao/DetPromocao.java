package com.example.cassi.trab3_cssioejoao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
