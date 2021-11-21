package com.example.iprovas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
  private ListView listView;

  private void onItemClickListView(AdapterView<?> parent, View view, int position, long id) {
    // Handle click
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("iProvas - Listagem de UAs");

    String[] valores = new String[]{
            "Inteligência Artificial", "Dispositivos Móveis", "Bancos de Dados", "Algoritmos"
    };

    listView = findViewById(R.id.uaListView);

    ArrayList<String> listaValores = new ArrayList<>(Arrays.asList(valores));
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaValores);

    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemClickListView(parent, view, position, id);
      }
    });
  }
}