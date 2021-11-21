package com.example.iprovas.activities.events;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.iprovas.R;
import com.example.iprovas.UAMetadata;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class CadastroEvent extends AppCompatActivity {

  CalendarView calendarView;
  EditText inputEvent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_event);
    String title = "Novo evento - ";

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      String uaName = extras.getString(UAMetadata.UA_NAME);
      title += uaName;
    }

    setTitle(title);

    AtomicLong selectedDate = new AtomicLong(new Date().getTime());

    calendarView = findViewById(R.id.calendarView);
    inputEvent = findViewById(R.id.inputEvent);

    calendarView.setDate(selectedDate.get());

    calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
      selectedDate.set(view.getDate());
    });
  }
}