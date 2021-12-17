package com.earning.dekhai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LearningPageHome extends AppCompatActivity {
    Button btnLanguageLearning , btnSkillDevelopment,btnProfessionalCorses,btnJobRecruitmentCourses,btnCompetitiveExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_page_home_activity);
        btnLanguageLearning = findViewById(R.id.btnLanguageLearning);
        btnSkillDevelopment = findViewById(R.id.btnSkillDevelopment);
        btnProfessionalCorses = findViewById(R.id.btnProfessionalCorses);
        btnJobRecruitmentCourses = findViewById(R.id.btnJobRecruitmentCourses);
        btnCompetitiveExam = findViewById(R.id.btnCompetitiveExam);
        btnLanguageLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), LanguageLearningPage.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });
        btnSkillDevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), LearningSkillDevelopmentPage.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });
        btnJobRecruitmentCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), LearningJobRecruitmentPage.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });
        btnProfessionalCorses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();

            }
        });
        btnCompetitiveExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), LearningCompetitiveExam.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });
    }
}