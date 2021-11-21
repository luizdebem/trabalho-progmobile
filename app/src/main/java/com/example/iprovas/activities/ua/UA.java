package com.example.iprovas.activities.ua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iprovas.R;
import com.example.iprovas.UAMetadata;
import com.example.iprovas.activities.events.CadastroEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class UA extends AppCompatActivity {

  private ListView listView;
  private FloatingActionButton fabAddEvent;

  private void onItemClickListView(AdapterView<?> parent, View view, int position, long id, String uaName) {
    // @TODO tela de editar evento
  }

  private void openCadastroEvent(View view, String uaName) {
    Intent intent = new Intent(this, CadastroEvent.class);
    intent.putExtra(UAMetadata.UA_NAME, uaName);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String title = "Eventos - ";
    String uaName = "";

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      uaName = extras.getString(UAMetadata.UA_NAME);
      title += uaName;
    }

    setTitle(title);

    String[] valores = new String[]{
            "Prova 1", "Prova 2", "Prova 3", "Prova 4"
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

    fabAddEvent = findViewById(R.id.fabAddUA);
    String finalUaName = uaName;
    fabAddEvent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openCadastroEvent(v, finalUaName);
      }
    });

  }
}