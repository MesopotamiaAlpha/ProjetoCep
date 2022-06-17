package com.example.projetocep;
//trocando para mysql
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        TextInputEditText editCep = findViewById(R.id.editCep);

        TextView txtLogradouro = findViewById(R.id.txtLogradouro);
        TextView txtBairro = findViewById(R.id.txtBairro);
        TextView txtComplemento = findViewById(R.id.txtComplemento);
        TextView txtUf = findViewById(R.id.txtUf);
        TextView txtLocalidade = findViewById(R.id.txtLocalidade);
        Button btnPesquisar = findViewById(R.id.btnPesquisar);
        Button btnHistorico = findViewById(R.id.btnHistorico);



        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String campoCep = editCep.getText().toString();

                Toast.makeText(MainActivity.this, "Clicado no botão de pesquisa", Toast.LENGTH_SHORT).show();

                Map<String, Object> historico = new HashMap<>();
                historico.put("pesquisaCliente", campoCep);


                //parte do get retrofit
                Call<CEP> call = new RetrofitConfig().getCEPService().buscarCEP(editCep.getText().toString());

                call.enqueue(new Callback<CEP>() {
                    @Override
                    public void onResponse(Call<CEP> call, Response<CEP> response) {
                        CEP cep = response.body();
                        txtLogradouro.setText("Nome da rua: " + cep.getLogradouro());
                        txtBairro.setText("Nome do bairro: " +cep.getBairro());
                        txtComplemento.setText("Complemento: " +cep.getComplemento());
                        txtUf.setText("Estado: " +cep.getUf());
                        txtLogradouro.setText("Cidade: " +cep.getLocalidade());
                        txtLogradouro.setTextColor(Color.GREEN);
                        txtBairro.setTextColor(Color.GREEN);
                        txtComplemento.setTextColor(Color.GREEN);
                        txtUf.setTextColor(Color.GREEN);
                        txtLocalidade.setTextColor(Color.GREEN);
                    }

                    @Override
                    public void onFailure(Call<CEP> call, Throwable t) {
                        Log.e("CEPService ", "Erro ao buscar o cep"+ t.getMessage() );
                    }
                });

            }
        });


        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaHistorico = new Intent(MainActivity.this, ListaDePesquisas.class);
                startActivity(telaHistorico);
            }
        });

     }



}

