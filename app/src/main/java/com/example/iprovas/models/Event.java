package com.example.iprovas.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
  String id;
  String name;
  String uaId;
  long date;

  public Event(String id, String name, String uaId, long date) {
    this.id = id;
    this.name = name;
    this.uaId = uaId;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUaId() {
    return uaId;
  }

  public void setUaId(String uaId) {
    this.uaId = uaId;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return new SimpleDateFormat("dd/MM/YYYY").format(new Date(getDate())) + " - " + name;
  }
}
