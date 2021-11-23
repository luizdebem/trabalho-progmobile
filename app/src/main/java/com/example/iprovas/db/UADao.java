package com.example.iprovas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.iprovas.models.UAModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UADao {
  private final String TABLE_UA = "UA";
  private DbGateway gw;

  public UADao(Context ctx){
    gw = DbGateway.getInstance(ctx);
  }

  public boolean incluir(String nome){
    String id = UUID.randomUUID().toString();
    ContentValues cv = new ContentValues();
    cv.put("id", id);
    cv.put("name", nome);
    return gw.getDatabase().insert(TABLE_UA, null, cv) > 0;
  }


  public List<UAModel> listar(){
    List<UAModel> uas = new ArrayList<>();
    Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM UA", null);
    while(cursor.moveToNext()){
      String id = cursor.getString(cursor.getColumnIndex("ID"));
      String name = cursor.getString(cursor.getColumnIndex("name"));
      uas.add(new UAModel(id, name));
    }
    cursor.close();
    return uas;
  }

  public UAModel busca(String id) {
    Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM UA WHERE ID=?", new String[]{ id });
    UAModel ua = null;
    while (cursor.moveToNext()) {
      String name = cursor.getString(cursor.getColumnIndex("name"));
      ua = new UAModel(id, name);
    }
    return ua;
  }

  public boolean salvar(UAModel ua){
    UAModel existente = busca(ua.getId());

    if(existente != null) {
      System.out.println(existente.getName());
      System.out.println(ua.getName());
      ContentValues cv = new ContentValues();
      cv.put("name", ua.getName());
      return gw.getDatabase().update(TABLE_UA, cv, "ID=?", new String[]{ existente.getId() }) > 0;
    }
    else {
      ContentValues cv = new ContentValues();
      cv.put("id", ua.getId());
      cv.put("name", ua.getName());
      return gw.getDatabase().insert(TABLE_UA, null, cv) > 0;
    }
  }

  public boolean excluir(String id){
    return gw.getDatabase().delete(TABLE_UA, "ID=?", new String[]{ id }) > 0;
  }
}
