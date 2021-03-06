package com.example.projetocep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.projetocep.callback.OnResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaDePesquisas extends AppCompatActivity {

    private OnResult onCompleteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pesquisas);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Button btnVoltar = findViewById(R.id.btnVoltar);
        ListView listView = findViewById(R.id.listView);
        List<String> lst = new ArrayList();
        String[] dados = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat"};

        Context ctx = this;

        db.collection("historicoPesquisa")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            //Nesta linha de baixo está sendo criado um hashmap para listar todos os dados

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //aqui em baixo ele esta usando o for para pegar todos os datas e colocar dentro desta lista
                                lst.add(String.valueOf(document.getData()));
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, lst);
                            listView.setAdapter(adapter);

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