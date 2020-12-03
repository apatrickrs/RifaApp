package com.rifa.mrrifa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rifa.mrrifa.R;
import com.rifa.mrrifa.model.Humano;

import java.util.UUID;

public class ColocarNomeActivity extends AppCompatActivity {

    private EditText campoNome, campoWpp, campoQtde, campoVendedor;
    private Button btnSalvar, btnOkComprovante;

    private Humano humano;

    private int qtde;

    private LinearLayout linearComprovante;
    private TextView infoComprovante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocar_nome);

        infoComprovante = findViewById(R.id.infoComprovante);
        btnOkComprovante = findViewById(R.id.btnOkComprovante);
        linearComprovante = findViewById(R.id.linearComprovante);
        campoNome = findViewById(R.id.campoNome);
        campoWpp = findViewById(R.id.campoWpp);
        campoQtde = findViewById(R.id.campoQtde);
        campoVendedor = findViewById(R.id.campoVendedor);
        btnSalvar = findViewById(R.id.btnSalvar);
        linearComprovante.setVisibility(View.INVISIBLE);

        btnOkComprovante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearComprovante.setVisibility(View.INVISIBLE);
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSalvar.setVisibility(View.INVISIBLE);
                String textoNome = campoNome.getText().toString();
                String textoWpp = campoWpp.getText().toString();
                String textoQtde = campoQtde.getText().toString();
                String textoVendedor = campoVendedor.getText().toString();

                if (!textoNome.isEmpty()) {
                    if (!textoWpp.isEmpty()) {
                        if (!textoQtde.isEmpty()) {
                            if (!textoVendedor.isEmpty()) {
                                humano = new Humano();

                                qtde = Integer.parseInt(campoQtde.getText().toString());
                                for (int i = 1; i <= qtde; i++) {
                                    String id = UUID.randomUUID().toString();
                                    humano.setId(id);
                                    humano.setNome(textoNome);
                                    humano.setQtde(textoQtde);
                                    humano.setWpp(textoWpp);
                                    humano.setVenderdor(textoVendedor);
                                    humano.salvar();

                                    linearComprovante.setVisibility(View.VISIBLE);
                                    infoComprovante.setText("CONCORRENTE:" + '\n' + '\n' + "Nome: " + humano.getNome()
                                            + '\n' +"Wpp: " + humano.getWpp()
                                            + '\n' + "Qtde: " + humano.getQtde()
                                            + '\n' + "Vendedor(a): " + humano.getVenderdor());
                                }
                            } else {
                                linearComprovante.setVisibility(View.INVISIBLE);
                                btnSalvar.setVisibility(View.VISIBLE);
                                Toast.makeText(ColocarNomeActivity.this, "OBRIGATÓRIO UM VENDEDOR", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            linearComprovante.setVisibility(View.INVISIBLE);
                            btnSalvar.setVisibility(View.VISIBLE);
                            Toast.makeText(ColocarNomeActivity.this, "OBRIGATÓRIO UMA QUANTIDADE", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        linearComprovante.setVisibility(View.INVISIBLE);
                        btnSalvar.setVisibility(View.VISIBLE);
                        Toast.makeText(ColocarNomeActivity.this, "OBRIGATÓRIO UM CONTATO", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    linearComprovante.setVisibility(View.INVISIBLE);
                    btnSalvar.setVisibility(View.VISIBLE);
                    Toast.makeText(ColocarNomeActivity.this, "OBRIGATÓRIO UM NOME", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}