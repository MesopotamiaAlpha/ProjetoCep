package com.example.projetocep;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaDePesquisas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pesquisas);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView recyclerResultado = findViewById(R.id.recycleResultado);
        Button btnVoltar = findViewById(R.id.btnVoltar);


        db.collection("historicoPesquisa")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<String> lst = new ArrayList();
                        if (task.isSuccessful()) {
                            //Nesta linha de baixo está sendo criado um hashmap para listar todos os dados

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //aqui em baixo ele esta usando o for para pegar todos os datas e colocar dentro desta lista
                                lst.add(String.valueOf(document.getData()));

                            }

                            //tentando fazer o recycler funcinar mas nao consigo,este dataset esta pegando do Customadapter linha 41
                            //recyclerResultado.setAdapter(new CustomAdapter(this, lst));

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaVoltar = new Intent(ListaDePesquisas.this, MainActivity.class);
                startActivity(telaVoltar);

            }
        });

    }
}