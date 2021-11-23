package com.example.iprovas.activities.ua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iprovas.EventMetadata;
import com.example.iprovas.R;
import com.example.iprovas.UAMetadata;
import com.example.iprovas.activities.events.CadastroEvent;
import com.example.iprovas.activities.events.EditarEvent;
import com.example.iprovas.db.EventDao;
import com.example.iprovas.db.UADao;
import com.example.iprovas.models.Event;
import com.example.iprovas.models.UAModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UA extends AppCompatActivity {

  private ListView listView;
  private FloatingActionButton fabAddEvent;

  private void onItemClickListView(AdapterView<?> parent, View view, int position, long id, Event event) {
    Intent intent = new Intent(this, EditarEvent.class);
    intent.putExtra(EventMetadata.EVENT_NAME, event.getName());
    intent.putExtra(EventMetadata.EVENT_ID, event.getId());
    intent.putExtra(EventMetadata.EVENT_UA_ID, event.getUaId());
    intent.putExtra(EventMetadata.EVENT_DATE, event.getDate());

    startActivity(intent);
  }

  private void openCadastroEvent(View view, String uaName, String uaId) {
    Intent intent = new Intent(this, CadastroEvent.class);
    intent.putExtra(UAMetadata.UA_NAME, uaName);
    intent.putExtra(UAMetadata.UA_ID, uaId);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    String title = "Eventos - ";
    String uaName = "";
    String uaId = null;

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      uaName = extras.getString(UAMetadata.UA_NAME);
      uaId = extras.getString(UAMetadata.UA_ID);
      title += uaName;
    }

    setTitle(title);

    listView = findViewById(R.id.uaListView);

    EventDao dao = new EventDao(getBaseContext());
    List<Event> events = dao.listar(uaId);
    ArrayList<Event> listaValores = new ArrayList<Event>(events);
    ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, listaValores);

    listView.setAdapter(adapter);

    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        onItemClickListView(parent, view, position, id, events.get(position));
        return false;
      }
    });

    fabAddEvent = findViewById(R.id.fabAddUA);
    String finalUaName = uaName;
    String finalUaId = uaId;
    fabAddEvent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openCadastroEvent(v, finalUaName, finalUaId);
      }
    });
  }
}