package com.senai.projeto_eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.senai.projeto_eventos.database.EventoDAO;
import com.senai.projeto_eventos.database.LocalDAO;

import com.senai.projeto_eventos.modelo.Evento;
import com.senai.projeto_eventos.modelo.Local;

public class CadastroLocalActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextLocalNome;
    private EditText editText_bairro;
    private EditText editText_cidade;
    private EditText editText_capacidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro de Local");

        editTextLocalNome = findViewById(R.id.editTextLocalNome);
        editText_bairro = findViewById(R.id.editText_bairro);
        editText_cidade = findViewById(R.id.editText_cidade);
        editText_capacidade = findViewById(R.id.editText_capacidade);
        carregarLocal();
    }

    public void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("localEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");
            editText_bairro.setText(local.getCidade());
            editText_cidade.setText(local.getCidade());
            editText_capacidade.setText(local.getCapacidade_pub());
            id = local.getId();
        }


    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        editTextLocalNome = findViewById(R.id.editTextLocalNome);
        editText_bairro = findViewById(R.id.editText_bairro);
        editText_cidade = findViewById(R.id.editText_cidade);
        editText_capacidade = findViewById(R.id.editText_capacidade);

        String bairro = editText_bairro.getText().toString();
        String localNome = editTextLocalNome.getText().toString();
        String cidade = editText_cidade.getText().toString();
        String capacidade = editText_capacidade.getText().toString();


        Local local = new Local(id ,localNome, bairro, cidade, capacidade);
        LocalDAO localDAO = new LocalDAO((getBaseContext()));
        boolean salvou = localDAO.salvar(local);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickExcluir(View v) {
        editTextLocalNome = findViewById(R.id.editTextLocalNome);
        editText_bairro = findViewById(R.id.editText_bairro);
        editText_cidade = findViewById(R.id.editText_cidade);
        editText_capacidade = findViewById(R.id.editText_capacidade);


        String bairro = editText_bairro.getText().toString();
        String LocalNome = editTextLocalNome.getText().toString();
        String cidade = editText_cidade.getText().toString();
        String capacidade = editText_capacidade.getText().toString();

        Local local = new Local (id, LocalNome, bairro, cidade, capacidade);
        LocalDAO localDAO = new LocalDAO((getBaseContext()));
        boolean excluir = localDAO.excluir(local);
        if (excluir) {
            finish();
        } else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao excluir", Toast.LENGTH_LONG).show();
        }
    }
}