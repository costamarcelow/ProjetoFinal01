package com.senai.projeto_eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.senai.projeto_eventos.database.entity.EventoEntity;
import com.senai.projeto_eventos.database.entity.LocalEntity;
import com.senai.projeto_eventos.modelo.Evento;
import com.senai.projeto_eventos.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalDAO {


    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public LocalDAO (Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Local local){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_LOCAL_NOME, local.getLocalNome());
        contentValues.put(LocalEntity.COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE, local.getCapacidade_pub());
        if (local.getId() > 0){
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME,
                    contentValues,
                    LocalEntity._ID + "=?",
                    new String []{String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME,
                null,
                contentValues) > 0;
    }

    public boolean excluir (Local local){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_LOCAL_NOME, local.getLocalNome());
        contentValues.put(LocalEntity.COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE, local.getCapacidade_pub());
        if (local.getId() > 0){
            return dbGateway.getDatabase().delete(LocalEntity.TABLE_NAME,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDatabase().replace("local", LocalEntity.TABLE_NAME, contentValues) > 0;
    }

    public List<Local> listar(){
        List<Local> locais = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String localNome = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_LOCAL_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            locais.add(new Local(id, localNome , bairro, cidade, capacidade));
        }
        cursor.close();
        return locais;
    }
}
