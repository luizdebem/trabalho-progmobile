package com.example.iprovas.activities.ua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iprovas.R;
import com.example.iprovas.UAMetadata;
import com.example.iprovas.db.UADao;
import com.example.iprovas.models.UAModel;

import java.util.UUID;

public class EditarUA extends AppCompatActivity {

  EditText inputEditar;
  Button btnEditarUA;
  Button btnExcluirUA;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editar_ua);
    setTitle("Edição de UA");

    UADao dao = new UADao(getBaseContext());

    inputEditar = findViewById(R.id.inputEditarUA);
    btnEditarUA = findViewById(R.id.btnEditarUA);
    btnExcluirUA = findViewById(R.id.btnExcluirUA);
    UAModel ua = null;

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      ua = new UAModel(extras.getString(UAMetadata.UA_ID), extras.getString(UAMetadata.UA_NAME));
      inputEditar.setText(ua.getName());
    }

    UAModel finalUa = ua;
    btnEditarUA.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finalUa.setName(inputEditar.getText().toString());
        dao.salvar(finalUa);
        finish();
      }
    });

    btnExcluirUA.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dao.excluir(finalUa.getId());
        finish();
      }
    });
  }
}