package com.rifa.mrrifa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.rifa.mrrifa.R;

public class OpcoesActivity extends AppCompatActivity {

    private Button btnVerNomes, btnColocarNome, btnPesquisarNome;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes);

        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        btnPesquisarNome = findViewById(R.id.btnPesquisaNome);
        btnVerNomes = findViewById(R.id.btnVerNomes);
        btnColocarNome = findViewById(R.id.btnColocarNome);
        btnVerNomes.setAnimation(animation);
        btnColocarNome.setAnimation(animation);
        btnPesquisarNome.setAnimation(animation);

        btnVerNomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVerNomes();
            }
        });

        btnColocarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirColocarNomes();
            }
        });

        btnPesquisarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPesquisarNomes();
            }
        });

    }

    private void abrirVerNomes() {
        startActivity(new Intent(this, VerNomesActivity.class));
    }

    private void abrirColocarNomes() {
        startActivity(new Intent(this, ColocarNomeActivity.class));
    }

    private void abrirPesquisarNomes() {
        startActivity(new Intent(this, ProcurarNomeActivity.class));
    }
}