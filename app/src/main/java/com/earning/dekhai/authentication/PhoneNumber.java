package com.earning.dekhai.authentication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.earning.dekhai.R;


public class PhoneNumber extends AppCompatActivity {
    private Spinner spinner;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);




        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        editText = findViewById(R.id.editTextPhone);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = editText.getText().toString().trim();
                String phoneNumber = "+" + code + number;
                if (number.isEmpty()) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }
                    Intent intent = new Intent(PhoneNumber.this, VerifyPhoneActivity.class);
                    intent.putExtra("phonenumber", phoneNumber);
                    startActivity(intent);
                }

        });
    }
}
