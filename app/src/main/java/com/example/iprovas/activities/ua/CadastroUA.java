package com.example.iprovas.activities.ua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iprovas.R;
import com.example.iprovas.db.UADao;
import com.example.iprovas.models.UAModel;

import java.util.UUID;

public class CadastroUA extends AppCompatActivity {

  EditText inputSalvarUA;
  Button btnSalvarUA;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_ua);
    setTitle("Cadastrar nova UA");
    inputSalvarUA = findViewById(R.id.inputSalvarUA);
    btnSalvarUA = findViewById(R.id.btnSalvarUA);
    UADao dao = new UADao(getBaseContext());

    btnSalvarUA.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        dao.salvar(new UAModel(UUID.randomUUID().toString().replace("-", ""), inputSalvarUA.getText().toString()));
        finish();
      }
    });

  }
}