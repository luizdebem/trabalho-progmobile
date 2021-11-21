package com.example.iprovas.models;

import java.util.List;

public class UA {
  String id;
  String name;
  List<Event> events;

  UA(String id, String name, List<Event> events) {
    this.id = id;
    this.name = name;
    this.events = events;
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

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }
}
