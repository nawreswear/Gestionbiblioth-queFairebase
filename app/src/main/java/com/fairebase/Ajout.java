package com.fairebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import com.fairebase.R;
public class Ajout extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText edTitre;
    private EditText edNbPage;
    private Button btnAjouter;
    private Button btnRetour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        initialiser();
        ecouteurs();
    }

    private void initialiser() {
        db = FirebaseFirestore.getInstance();
        edTitre = findViewById(R.id.edTitre);
        edNbPage = findViewById(R.id.edNbPage);
        btnAjouter = findViewById(R.id.btnAjouter);
        btnRetour = findViewById(R.id.btnRetour);
    }

    private void ecouteurs() {
        btnAjouter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ajouterLivre();
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void ajouterLivre() {
        Map<String,Object> data =new HashMap<>();
        data.put("titre",edTitre.getText().toString());
        data.put("nbpage",Integer.parseInt(edNbPage.getText().toString()));
        db.collection("livres").add(data).addOnSuccessListener(new  OnSuccessListener<DocumentReference>(){

                    public  void  onSuccess(DocumentReference docref){
                        edTitre.setText("");
                        edNbPage.setText("");
                        edTitre.requestFocus();
                        Toast.makeText(getApplicationContext(),"livre ajouté avec succés",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    public void onFailure( Exception e) {
                        Toast.makeText(getApplicationContext(),"erreur lors de l'ajout",Toast.LENGTH_SHORT).show();

                    }
                });
    }

}