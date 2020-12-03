package com.rifa.mrrifa.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rifa.mrrifa.R;
import com.rifa.mrrifa.model.Humano;

import java.util.List;

public class AdapterNome extends RecyclerView.Adapter<AdapterNome.MyViewHolder> {

    private List<Humano> humanos;
    private Context context;

    public AdapterNome(List<Humano> humanos, Context context) {
            this.humanos = humanos;
            this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nome, parent, false);
            return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Humano humano = humanos.get(position);
            holder.informacoes.setText("Nome: "+ humano.getNome() + "\n"
                    + "Número: " + humano.getWpp() + "\n"
                    + "Quantidade: " + humano.getQtde());

            holder.vendedor.setText(humano.getVenderdor());
    }

    @Override
    public int getItemCount() {
            return humanos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView informacoes, vendedor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            informacoes = itemView.findViewById(R.id.textInformacoes);
            vendedor = itemView.findViewById(R.id.textNome);
        }
    }
}
