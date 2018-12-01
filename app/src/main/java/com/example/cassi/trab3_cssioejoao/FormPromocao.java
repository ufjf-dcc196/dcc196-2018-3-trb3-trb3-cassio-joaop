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

public class FormPromocao extends AppCompatActivity {
    private Button btnCadastrar,btnVoltar;
    private EditText nome,valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_promocao);

        btnCadastrar = (Button) findViewById(R.id.btnCadastraPromocao);
        nome = (EditText) findViewById(R.id.txtNome);
        valor = (EditText) findViewById(R.id.txtValor);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();

                valores.put(PromocaoContract.Promocao.COLUMN_NAME_NOME, nome.getText().toString());
                valores.put(PromocaoContract.Promocao.COLUMN_NAME_VALOR, valor.getText().toString());

                long id = db.insert(PromocaoContract.Promocao.TABLE_NAME,null, valores);
                Log.i("DBINFO", "registro criado com id: "+id);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
