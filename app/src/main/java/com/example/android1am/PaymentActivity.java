package com.example.android1am;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText editTextNume, editTextPrenume, editTextNumarCard, editTextDataExpirare, editTextCVV;
    private Button buttonPlateste, buttonUrmatorul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Inițializare câmpuri
        editTextNume = findViewById(R.id.editTextNume);
        editTextPrenume = findViewById(R.id.editTextPrenume);
        editTextNumarCard = findViewById(R.id.editTextNumarCard);
        editTextDataExpirare = findViewById(R.id.editTextDataExpirare);
        editTextCVV = findViewById(R.id.editTextCVV);
        buttonPlateste = findViewById(R.id.buttonPlateste);
        buttonUrmatorul = findViewById(R.id.buttonUrmatorul);

        // Dezactivează butoanele inițial
        buttonPlateste.setEnabled(false);
        buttonUrmatorul.setEnabled(false);

        // Adaugă un TextWatcher pentru a verifica dacă toate câmpurile sunt completate
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

        // Aplică TextWatcher la fiecare câmp de text
        editTextNume.addTextChangedListener(textWatcher);
        editTextPrenume.addTextChangedListener(textWatcher);
        editTextNumarCard.addTextChangedListener(textWatcher);
        editTextDataExpirare.addTextChangedListener(textWatcher);
        editTextCVV.addTextChangedListener(textWatcher);
    }

    private void checkFields() {
        // Verifică dacă toate câmpurile sunt completate
        boolean allFieldsFilled = !editTextNume.getText().toString().trim().isEmpty() &&
                !editTextPrenume.getText().toString().trim().isEmpty() &&
                !editTextNumarCard.getText().toString().trim().isEmpty() &&
                !editTextDataExpirare.getText().toString().trim().isEmpty() &&
                !editTextCVV.getText().toString().trim().isEmpty();

        // Activează sau dezactivează butonul „Plătește”
        buttonPlateste.setEnabled(allFieldsFilled);
    }

    public void onPayClick(View view) {
        if (buttonPlateste.isEnabled()) {
            // Afișează mesajul „Plata efectuată cu succes!”
            Toast toast = Toast.makeText(this, "Achitarea efectuată cu succes!", Toast.LENGTH_LONG);
            toast.show();

            // Dezactivează butonul „Plătește”
            buttonPlateste.setEnabled(false);

            // Activează butonul „Următorul”
            buttonUrmatorul.setEnabled(true);
        }
    }

    public void onNextClick(View view) {
        startActivity(new Intent(this, FourthActivity.class));
    }
}