package com.example.cassi.trab3_cssioejoao;

import android.app.Activity;
import android.content.Intent;
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

public class Promocao extends AppCompatActivity {
    private Button btnVoltar, btnCadastrarPromo;
    private RecyclerView rclPromocoes;
    private promocaoAdapter PRAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocao);

        rclPromocoes = (RecyclerView) findViewById(R.id.rcl_listagemPromocoes);
        rclPromocoes.setLayoutManager(new LinearLayoutManager(this));

        btnVoltar = (Button) findViewById(R.id.btnListagemPromocaoVoltar);
        btnCadastrarPromo = (Button) findViewById(R.id.btnCadastrarPromocao) ;

        PRAdapter = new promocaoAdapter(getPromocoes());
        rclPromocoes.setAdapter(PRAdapter);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        btnCadastrarPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Promocao.this, FormPromocao.class);
                startActivityForResult(intent, MainActivity.CAD_PROMOCAO);
            }
        });
    }

    private Cursor getPromocoes()
    {
        SQLiteDatabase db = MainActivity.dbHelper.getReadableDatabase();
        String []visao = {
                PromocaoContract.Promocao.COLUMN_NAME_NOME
        };
        String sort = PromocaoContract.Promocao.COLUMN_NAME_NOME+ " ASC";
        return db.query(PromocaoContract.Promocao.TABLE_NAME, visao,null,null,null,null, sort);
    }
}
