package com.example.iprovas.models;

public class Event {
  String id;
  String name;
  String uaId;

  public Event(String id, String name, String uaId) {
    this.id = id;
    this.name = name;
    this.uaId = uaId;
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

  @Override
  public String toString() {
    return name;
  }
}
