package com.example.iprovas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
  private ListView listView;
  private FloatingActionButton fabAddUA;

  private void onItemClickListView(AdapterView<?> parent, View view, int position, long id, String uaName) {
    //Recupera o intente para a tela 2
    Intent intent = new Intent(this, EditarUA.class);
    intent.putExtra(UAMetadata.UA_NAME, uaName);
    startActivity(intent);
  }

  private void openCadastroUA(View view) {
    Intent intent = new Intent(this, CadastroUA.class);
    startActivity(intent);
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

    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        onItemClickListView(parent, view, position, id, valores[position]);
        return false;
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