package com.senai.projeto_eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.senai.projeto_eventos.database.LocalDAO;
import com.senai.projeto_eventos.modelo.Local;

public class ListarLocalActivity extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<Local> adapterLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_local);
        listViewLocais = findViewById(R.id.listView_local);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<Local>(ListarLocalActivity.this,
                android.R.layout.simple_list_item_1,
                localDAO.listar());
        listViewLocais.setAdapter(adapterLocais);
    }

    private void definirOnClickListenerListView() {
        listViewLocais.setOnItemClickListener((parent, view, position, id) -> {
            Local localClicado = adapterLocais.getItem(position);
            Intent intent = new Intent(ListarLocalActivity.this, CadastroLocalActivity.class);
            intent.putExtra("localEdicao", localClicado);
            startActivity(intent);
        });

    }

    public void onClickNovoLocal(View v){
        Intent intent = new Intent(ListarLocalActivity.this, CadastroLocalActivity.class);
        startActivity(intent);
    }

    public void onClickLocal(View v){
        Intent intent = new Intent(ListarLocalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
