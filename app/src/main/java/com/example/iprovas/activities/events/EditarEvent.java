package com.example.iprovas.activities.events;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iprovas.EventMetadata;
import com.example.iprovas.R;
import com.example.iprovas.UAMetadata;
import com.example.iprovas.db.EventDao;
import com.example.iprovas.db.UADao;
import com.example.iprovas.models.Event;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarEvent extends AppCompatActivity {

  EditText inputEditar;
  Button btnEditarEvent;
  Button btnExcluirEvent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editar_event);
    Bundle extras = getIntent().getExtras();
    String title = "Editar evento - ";

    inputEditar = findViewById(R.id.inputEditEvent);
    btnEditarEvent = findViewById(R.id.btnEditarEvent);
    btnExcluirEvent = findViewById(R.id.btnExcluirEvent);

    EventDao dao = new EventDao(getBaseContext());
    Event event = null;

    if (extras != null) {
      String eventName = extras.getString(EventMetadata.EVENT_NAME);
      String eventId = extras.getString(EventMetadata.EVENT_ID);
      String eventUaId = extras.getString(EventMetadata.EVENT_UA_ID);
      event = new Event(eventId, eventName, eventUaId);
      inputEditar.setText(eventName);
      title += eventName;
    }

    Event finalEvent = event;
    btnEditarEvent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finalEvent.setName(inputEditar.getText().toString());
        dao.salvar(finalEvent);
        finish();
      }
    });

    btnExcluirEvent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dao.excluir(finalEvent.getId());
        finish();
      }
    });

    setTitle(title);
  }
}