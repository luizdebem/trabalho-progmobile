package com.example.iprovas.activities.events;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.iprovas.R;
import com.example.iprovas.UAMetadata;
import com.example.iprovas.db.EventDao;
import com.example.iprovas.models.Event;
import com.example.iprovas.models.UAModel;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CadastroEvent extends AppCompatActivity {

  CalendarView calendarView;
  EditText inputEvent;
  Button btnSalvarEvent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_event);
    String title = "Novo evento - ";

    String uaName = null;
    String uaId = null;

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      uaName = extras.getString(UAMetadata.UA_NAME);
      uaId = extras.getString(UAMetadata.UA_ID);
      title += uaName;
    }

    setTitle(title);

    EventDao dao = new EventDao(getBaseContext());

    AtomicLong selectedDate = new AtomicLong(new Date().getTime());

    calendarView = findViewById(R.id.calendarView);
    inputEvent = findViewById(R.id.inputEvent);
    btnSalvarEvent = findViewById(R.id.btnSalvarEvent);

    calendarView.setDate(selectedDate.get());

    calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
      selectedDate.set(view.getDate());
    });

    String finalUaId = uaId;
    btnSalvarEvent.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        dao.salvar(new Event(UUID.randomUUID().toString().replace("-", ""), inputEvent.getText().toString(), finalUaId));
        finish();
      }
    });
  }
}