package com.example.iprovas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditarUA extends AppCompatActivity {

  EditText inputUA;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editar_ua);
    setTitle("iProvas - Edição de UA");

    inputUA = findViewById(R.id.inputUA);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      String uaName = extras.getString(UAMetadata.UA_NAME);
      inputUA.setText(uaName);
    }
  }
}