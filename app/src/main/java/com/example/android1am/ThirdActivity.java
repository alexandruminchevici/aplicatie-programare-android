package com.example.android1am;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextNume, editTextPrenume, editTextTelefon, editTextEmail, editTextPassword;
    private Button buttonSave, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Inițializare câmpuri
        editTextNume = findViewById(R.id.editTextNume);
        editTextPrenume = findViewById(R.id.editTextPrenume);
        editTextTelefon = findViewById(R.id.editTextTelefon);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSave = findViewById(R.id.buttonSave);
        buttonNext = findViewById(R.id.buttonNext);

        // Dezactivăm butoanele inițial
        buttonSave.setEnabled(false);
        buttonNext.setEnabled(false);

        // Adăugăm un TextWatcher pentru a activa butonul "Salvează informația"
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFields();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        // Aplicăm TextWatcher la fiecare câmp
        editTextNume.addTextChangedListener(textWatcher);
        editTextPrenume.addTextChangedListener(textWatcher);
        editTextTelefon.addTextChangedListener(textWatcher);
        editTextEmail.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);
    }

    private void checkFields() {
        // Verificăm dacă toate câmpurile sunt completate
        boolean allFieldsFilled = !editTextNume.getText().toString().trim().isEmpty() &&
                !editTextPrenume.getText().toString().trim().isEmpty() &&
                !editTextTelefon.getText().toString().trim().isEmpty() &&
                !editTextEmail.getText().toString().trim().isEmpty() &&
                !editTextPassword.getText().toString().trim().isEmpty();

        // Activăm sau dezactivăm butonul „Salvează informația”
        buttonSave.setEnabled(allFieldsFilled);
    }

    public void onSaveClick(View view) {
        // Activăm butonul „Următorul”
        buttonNext.setEnabled(true);

        // Afișăm un Toast (pop-up)
        Toast.makeText(this, "Înregistrarea efectuată cu succes!", Toast.LENGTH_SHORT).show();
    }

    public void onNextClick(View view) {
        // Trecem la pagina următoare (SecondActivity)
        startActivity(new Intent(this, SecondActivity.class));
    }
}
