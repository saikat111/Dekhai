package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.earning.dekhai.R;

public class LanguageLearningPage extends AppCompatActivity {
    ConstraintLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_learning_page_activity);
       /* l = findViewById(R.id.j);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}