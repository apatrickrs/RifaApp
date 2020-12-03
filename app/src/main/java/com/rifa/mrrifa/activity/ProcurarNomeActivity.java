package com.rifa.mrrifa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rifa.mrrifa.R;
import com.rifa.mrrifa.adapter.AdapterNome;
import com.rifa.mrrifa.config.ConfiguracaoFirebase;
import com.rifa.mrrifa.helper.RecyclerItemClickListener;
import com.rifa.mrrifa.model.Humano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcurarNomeActivity extends AppCompatActivity {

    private SearchView procuraNome;
    private RecyclerView recyclerPesquisaNomes;
    private AdapterNome adapterNome;

    private List<Humano> humanos = new ArrayList<>();

    private Humano humanoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_nome);

        procuraNome = findViewById(R.id.searchNameRifa);
        recyclerPesquisaNomes = findViewById(R.id.recyclerProcurarNome);

        recyclerPesquisaNomes.setLayoutManager(new LinearLayoutManager(this));
        recyclerPesquisaNomes.setHasFixedSize(true);

        adapterNome = new AdapterNome(humanos, this);

        recyclerPesquisaNomes.setAdapter(adapterNome);

        //recuperarNomes();

        //Configura o searchView
        procuraNome.setQueryHint("Buscar");
        procuraNome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String textoDigitado = newText.toUpperCase();
                pesquisarProdutos(textoDigitado);

                return true;
            }
        });

        recyclerPesquisaNomes.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerPesquisaNomes, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {
                humanoSelecionado = humanos.get(position);
                humanoSelecionado.remover();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    private void pesquisarProdutos(String produto) {
        humanos.clear();

        if (produto.length() > 1) {

            Query query = ConfiguracaoFirebase.getFirebaseDatabase()
                    .child("utteam").orderByChild("nomeChave").startAt(produto).endAt(produto + "\uf8ff");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    humanos.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        humanos.add(ds.getValue(Humano.class));
                    }
                    adapterNome.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}