package com.example.iprovas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iprovas.activities.ua.CadastroUA;
import com.example.iprovas.activities.ua.EditarUA;
import com.example.iprovas.R;
import com.example.iprovas.activities.ua.UA;
import com.example.iprovas.UAMetadata;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
  private ListView listView;
  private FloatingActionButton fabAddUA;

  private void onItemClickListView(AdapterView<?> parent, View view, int position, long id, String uaName) {
    Intent intent = new Intent(this, EditarUA.class);
    intent.putExtra(UAMetadata.UA_NAME, uaName);
    startActivity(intent);
  }

  private void openCadastroUA(View view) {
    Intent intent = new Intent(this, CadastroUA.class);
    startActivity(intent);
  }

  private void openUA(View v, String uaName) {
    Intent intent = new Intent(this, UA.class);
    intent.putExtra(UAMetadata.UA_NAME, uaName);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("Listagem de UAs");

    String[] valores = new String[]{
            "Inteligência Artificial", "Dispositivos Móveis", "Bancos de Dados", "Algoritmos"
    };

    listView = findViewById(R.id.uaListView);

    ArrayList<String> listaValores = new ArrayList<>(Arrays.asList(valores));
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaValores);

    listView.setAdapter(adapter);

    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        onItemClickListView(parent, view, position, id, valores[position]);
        return false;
      }
    });

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
        openUA(v, valores[pos]);
      }
    });

    fabAddUA = findViewById(R.id.fabAddUA);
    fabAddUA.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openCadastroUA(v);
      }
    });
  }
}