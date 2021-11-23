package com.example.iprovas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.iprovas.models.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventDao {
  private final String TABLE_EVENT = "EVENT";
  private DbGateway gw;

  public EventDao(Context ctx){
    gw = DbGateway.getInstance(ctx);
  }

  public boolean incluir(String nome){
    String id = UUID.randomUUID().toString();
    ContentValues cv = new ContentValues();
    cv.put("id", id);
    cv.put("name", nome);
    return gw.getDatabase().insert(TABLE_EVENT, null, cv) > 0;
  }


  public List<Event> listar(String uaId){
    List<Event> events = new ArrayList<>();
    Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM EVENT WHERE uaId=?", new String[]{ uaId });
    while(cursor.moveToNext()){
      String id = cursor.getString(cursor.getColumnIndex("ID"));
      String name = cursor.getString(cursor.getColumnIndex("name"));
      String fk = cursor.getString(cursor.getColumnIndex("uaId"));
      long date = cursor.getLong(cursor.getColumnIndex("date"));
      events.add(new Event(id, name, fk, date));
    }
    cursor.close();
    return events;
  }

  public Event busca(String id) {
    Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM EVENT WHERE ID=?", new String[]{ id });
    Event event = null;
    while (cursor.moveToNext()) {
      String name = cursor.getString(cursor.getColumnIndex("name"));
      String uaId = cursor.getString(cursor.getColumnIndex("uaId"));
      long date = cursor.getLong(cursor.getColumnIndex("date"));
      event = new Event(id, name, uaId, date);
    }
    return event;
  }

  public boolean salvar(Event event){
    Event existente = busca(event.getId());

    if(existente != null) {
      ContentValues cv = new ContentValues();
      cv.put("name", event.getName());
      cv.put("date", event.getDate());
      return gw.getDatabase().update(TABLE_EVENT, cv, "ID=?", new String[]{ existente.getId() }) > 0;
    }
    else {
      ContentValues cv = new ContentValues();
      cv.put("id", event.getId());
      cv.put("name", event.getName());
      cv.put("date", event.getDate());
      cv.put("uaId", event.getUaId());
      return gw.getDatabase().insert(TABLE_EVENT, null, cv) > 0;
    }
  }

  public boolean excluir(String id){
    return gw.getDatabase().delete(TABLE_EVENT, "ID=?", new String[]{ id }) > 0;
  }
}
