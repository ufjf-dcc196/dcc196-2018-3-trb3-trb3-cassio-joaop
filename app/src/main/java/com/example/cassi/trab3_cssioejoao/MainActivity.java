package com.example.cassi.trab3_cssioejoao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int CADS_LIVRO = 1, VER_LIVROS = 2, CAD_PROMOCAO = 3, VER_PROMOCAO = 4, VER_LIVRO = 5;
    public static LivroDBHelper dbHelper;

    Button btnCadastra,btnExibe,btnPromocao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new LivroDBHelper(getApplicationContext());
        btnCadastra = (Button) findViewById(R.id.btnCadastra);
        btnExibe = (Button) findViewById(R.id.btnExibe);
        btnPromocao = (Button) findViewById(R.id.btnPromocao);

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormLivro.class);
                startActivityForResult(intent, MainActivity.CADS_LIVRO);
            }
        });


        btnExibe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListagemLivros.class);
                startActivityForResult(intent, MainActivity.VER_LIVROS);
            }
        });


        btnPromocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Promocao.class);
                startActivityForResult(intent, MainActivity.VER_PROMOCAO);
            }
        });




    }
}
