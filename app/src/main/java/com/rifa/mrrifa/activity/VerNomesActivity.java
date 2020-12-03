package com.rifa.mrrifa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.rifa.mrrifa.R;
import com.rifa.mrrifa.adapter.AdapterNome;
import com.rifa.mrrifa.config.ConfiguracaoFirebase;
import com.rifa.mrrifa.helper.RecyclerItemClickListener;
import com.rifa.mrrifa.model.Humano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VerNomesActivity extends AppCompatActivity {

    private RecyclerView recyclerVerNomes;
    private List<Humano> humanos = new ArrayList<>();
    private AdapterNome adapterNome;

    private TextView sorteado, infoNomeDeletar, qtdePessoasLista;
    private Button btnSortear, btnDeletar, btnCancelar;
    private ProgressBar progressBarLista;
    private ImageView imageView;

    private Animation animation, animationOut;

    private LinearLayout linearExcluir;

    private Humano humanoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nomes);

        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_splash);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        qtdePessoasLista = findViewById(R.id.qtdePessoasLista);
        infoNomeDeletar = findViewById(R.id.infoNomeDeletar);
        btnDeletar = findViewById(R.id.btnApagar);
        btnCancelar = findViewById(R.id.btnCancelar);
        linearExcluir = findViewById(R.id.linearApagar);
        imageView = findViewById(R.id.imageView);
        progressBarLista = findViewById(R.id.progressBarLista);
        recyclerVerNomes = findViewById(R.id.recyclerVerNomes);
        btnSortear = findViewById(R.id.buttonSortear);
        sorteado = findViewById(R.id.sorteadoVencedor);
        sorteado.setVisibility(View.INVISIBLE);
        linearExcluir.setVisibility(View.INVISIBLE);

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humanoSelecionado.remover();
                adapterNome.notifyDataSetChanged();
                linearExcluir.setVisibility(View.INVISIBLE);
                btnSortear.setVisibility(View.VISIBLE);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearExcluir.setVisibility(View.INVISIBLE);
                btnSortear.setVisibility(View.VISIBLE);
            }
        });

        btnSortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtdePessoasLista.setAnimation(animationOut);
                qtdePessoasLista.setVisibility(View.INVISIBLE);
                imageView.setAnimation(animationOut);
                imageView.setVisibility(View.INVISIBLE);
                recyclerVerNomes.setVisibility(View.INVISIBLE);
                sortear();
            }
        });

        //RECYCLERVIRE
        recyclerVerNomes.setLayoutManager(new LinearLayoutManager(this));
        recyclerVerNomes.setHasFixedSize(true);

        adapterNome = new AdapterNome(humanos, this);

        recyclerVerNomes.setAdapter(adapterNome);

        //Recuperar Anuncios
        recuperarAnuncios();

        //Adicionar evento de click
        recyclerVerNomes.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerVerNomes, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {
                linearExcluir.setVisibility(View.VISIBLE);
                humanoSelecionado = humanos.get(position);
                infoNomeDeletar.setText(humanoSelecionado.getNome());
                btnSortear.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    private void recuperarAnuncios() {

        ConfiguracaoFirebase.getFirebaseDatabase().child("utteam").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                humanos.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    humanos.add(ds.getValue(Humano.class));
                }
                progressBarLista.setVisibility(View.INVISIBLE);
                qtdePessoasLista.setText(humanos.size() + " NOMES NA LISTA");
                Collections.reverse(humanos);
                adapterNome.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sortear() {
        if (!humanos.isEmpty()) {
            Humano winner = humanos.get(new Random().nextInt(humanos.size()));
            sorteado.setText("O VENCEDOR É" + '\n' + '\n' + "Nome: " + winner.getNome() + '\n' +"Wpp: " + winner.getWpp() + '\n' + "Qtde: " + winner.getQtde() + '\n' + "Vendedor: " + winner.getVenderdor());
            sorteado.setAnimation(animation);
            sorteado.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            recyclerVerNomes.setVisibility(View.VISIBLE);
            progressBarLista.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "A lista está vazia !", Toast.LENGTH_SHORT).show();
        }
    }
}