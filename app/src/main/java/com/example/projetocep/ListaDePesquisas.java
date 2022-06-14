package com.example.projetocep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;


public class ListaDePesquisas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pesquisas);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextView txtResultado = findViewById(R.id.txtResultado);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        db.collection("historicoPesquisa")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //Aqui estou tentando mapear todos os registros dentro do firestore e exibir em sequencia, mas nao estou estou conseguindo fazer como esta a baixo
                                Map<String, Object> listaData = document.getData();
                                txtResultado.setText(listaData.toString());

                            }
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