package com.senai.projeto_eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.senai.projeto_eventos.R;
import com.senai.projeto_eventos.database.entity.EventoEntity;
import com.senai.projeto_eventos.database.entity.LocalEntity;
import com.senai.projeto_eventos.modelo.Evento;
import com.senai.projeto_eventos.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = " SELECT * FROM " + EventoEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCALIDADE +
            " = " + LocalEntity.TABLE_NAME + "." +  LocalEntity._ID;
    private DBGateway dbGateway;

    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar (Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCALIDADE, evento.getLocal().getId());
        if (evento.getId()> 0){
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;

    }

    public List<Evento> listar(){
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocalidade= cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCALIDADE));
            String localNome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            Local local = new Local(idLocalidade, localNome, bairro, cidade, capacidade);

            eventos.add(new Evento(id,nome,data, local));
        }
        cursor.close();
        return eventos;
    }

    public boolean excluir (Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCALIDADE, evento.getData());
        if (evento.getId()> 0){
            return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().replace("eventos", EventoEntity.TABLE_NAME, contentValues) > 0;

    }





}
