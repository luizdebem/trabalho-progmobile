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
import com.example.iprovas.db.UADao;
import com.example.iprovas.models.UAModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private void onItemClickListView(AdapterView<?> parent, View view, int position, long id, UAModel ua) {
    Intent intent = new Intent(this, EditarUA.class);
    intent.putExtra(UAMetadata.UA_ID, ua.getId());
    intent.putExtra(UAMetadata.UA_NAME, ua.getName());
    startActivity(intent);
  }

  private void openCadastroUA(View view) {
    Intent intent = new Intent(this, CadastroUA.class);
    startActivity(intent);
  }

  private void openUA(View v, UAModel ua) {
    Intent intent = new Intent(this, UA.class);
    intent.putExtra(UAMetadata.UA_ID, ua.getId());
    intent.putExtra(UAMetadata.UA_NAME, ua.getName());
    startActivity(intent);
  }

  private void openHelp(View v) {
    Intent intent = new Intent(this, InfoActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("Listagem de UAs");
  }

  @Override
  protected void onStart() {
    super.onStart();
    ListView listView;
    FloatingActionButton fabAddUA;
    FloatingActionButton fabHelp;

    listView = findViewById(R.id.uaListView);

    UADao dao = new UADao(getBaseContext());
    List<UAModel> uas = dao.listar();
    ArrayList<UAModel> listaValores = new ArrayList<UAModel>(uas);
    ArrayAdapter<UAModel> adapter = new ArrayAdapter<UAModel>(this, android.R.layout.simple_list_item_1, listaValores);

    listView.setAdapter(adapter);

    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        onItemClickListView(parent, view, position, id, uas.get(position));
        return false;
      }
    });

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
        openUA(v, uas.get(pos));
      }
    });

    fabAddUA = findViewById(R.id.fabAddUA);
    fabAddUA.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openCadastroUA(v);
      }
    });

    fabHelp = findViewById(R.id.fabHelp);
    fabHelp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openHelp(v);
      }
    });
  }
}