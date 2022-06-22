package com.example.projetocep;

//trocando para mysql
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
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
        DrawerLayout menuLateral = findViewById(R.id.drawerLayout);

       // BottomNavigationMenuView barraSuperior = findViewById(R.id.bottom_navigation);
        FloatingActionButton editarCores = findViewById(R.id.btnEditarCores);

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
                        txtLocalidade.setText("Cidade: " +cep.getLocalidade());
                        txtLogradouro.setTextColor(Color.GREEN);
                        txtBairro.setTextColor(Color.GREEN);
                        txtComplemento.setTextColor(Color.GREEN);
                        txtUf.setTextColor(Color.GREEN);
                        txtLocalidade.setTextColor(Color.GREEN);
                        //chamando o post do retrofit
                        sendPost(cep);
                    }

                    @Override
                    public void onFailure(Call<CEP> call, Throwable t) {
                        Log.e("CEPService ", "Erro ao buscar o cep"+ t.getMessage() );
                    }
                });

            }
            //app esta crashando desta parte para baixo
            public void sendPost(CEP cep) {
                new RetrofitConfigAxiosPost().getAxiosService().postAxios(cep).enqueue(new Callback<Axios>() {
                    @Override
                    public void onResponse(Call<Axios> call, Response<Axios> response) {
                        if (response.isSuccessful()) {
                            Log.i("Passando aqui? ", "Deu success===========");
                        }
                    }

                    @Override
                    public void onFailure(Call<Axios> call, Throwable t) {
                        Log.i("Passando no erro? ", "Deu erroo");
                    }
                });
               /* AxiosService.postAxios(cep).enqueue(new Callback<Axios>() {
                    @Override
                    public void onResponse(Call<Axios> call, Response<Axios> response) {

                        if(response.isSuccessful()) {

                            Log.i(TAG, "post submitted to API." + response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Axios> call, Throwable t) {
                        Log.e(TAG, "Unable to submit post to API.");
                    }
                }); */
            }


        });
/* Nesta linha estou tentando colocar a barra lateral, mas o setNavigation não está funcionando
        menuLateral.setNavigationOnClickListener {
            drawerLayout.open();
        }

        menuLateral.setNavigationItemSelectedListener { menuItem ->
                // Handle menu item selected
                menuItem.isChecked = true
            drawerLayout.close();
            true
        }
*/
        //Adicionando botão para mudar a cor do fundo
        editarCores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Função para editar as cores", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

