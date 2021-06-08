package com.senai.projeto_eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.senai.projeto_eventos.database.EventoDAO;
import com.senai.projeto_eventos.database.LocalDAO;
import com.senai.projeto_eventos.modelo.Evento;
import com.senai.projeto_eventos.modelo.Local;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;
    private Spinner spinnerLocais;
    private ArrayAdapter<Local> locaisAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
        carregarEvento();

    }
//    private void carregarlocal(){
//        LocalDAO localDAO = new LoDAO(getBaseContext());
//        categoriaAdapter = new ArrayAdapter<Categoria>(CadastroEventoActivity.this,
//                android.R.layout.simple_spinner_item,
//                categoriaDAO.listar());
//        spinnerCategorias.setAdapter(categoriaAdapter);
//    }

    private void carregarEvento(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_bairro);
            EditText editTextData = findViewById(R.id.editText_date);
            editTextNome.setText(evento.getNome());
            editTextData.setText(String.valueOf(evento.getData()));
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);
            id = evento.getId();
        }

    }

    private int obterPosicaoLocal (Local local) {
        for (int posicao = 0; posicao < locaisAdapter.getCount(); posicao++) {
            if (locaisAdapter.getItem(posicao).getId() == local.getId()) {
                return posicao;
            }
        }
        return 0;
    }


    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        EditText editTextNome = findViewById(R.id.editText_bairro);
        EditText editTextData = findViewById(R.id.editText_date);


        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        int posicaoLocal = spinnerLocais.getSelectedItemPosition();
        Local local = (Local) locaisAdapter.getItem(posicaoLocal);

        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO((getBaseContext()));
        boolean salvou = eventoDAO.salvar(evento);
        if (salvou){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this,"Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickExcluir (View v){
        EditText editTextNome = findViewById(R.id.editText_bairro);
        EditText editTextData = findViewById(R.id.editText_date);



        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        int posicaoLocal = spinnerLocais.getSelectedItemPosition();
        Local local = (Local) locaisAdapter.getItem(posicaoLocal);

        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO((getBaseContext()));
        boolean excluir = eventoDAO.excluir(evento);
        if (excluir){
            finish();
        }else {
            Toast.makeText(CadastroEventoActivity.this,"Erro ao excluir", Toast.LENGTH_LONG).show();
        }

    }
}