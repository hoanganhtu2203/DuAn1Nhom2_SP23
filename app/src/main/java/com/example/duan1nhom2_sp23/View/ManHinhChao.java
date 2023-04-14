package com.example.duan1nhom2_sp23.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.duan1nhom2_sp23.Activity.LoginActivity;
import com.example.duan1nhom2_sp23.R;

public class ManHinhChao extends AppCompatActivity {
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        button = findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhChao.this, LoginActivity.class);
                startActivity(intent);

            }
        });



    }
}