package vidal.sergi.getfit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class EjerciciosActivity extends AppCompatActivity {

    GridView gridView;
    static TextView tvNM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        gridView = findViewById(R.id.gvRutina);
        tvNM = findViewById(R.id.tvNM);

        final List<Ejercicio> ejerciciosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String nombreMusculo = getIntent().getExtras().getString("nombreMusculo");
        final String nombreRutina = getIntent().getExtras().getString("nombreRutina");
        Log.d("svm", nombreMusculo);
        Log.d("svm", nombreRutina);
        tvNM.setText(nombreMusculo);

        DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);
        rutinas.child(nombreRutina).child(nombreMusculo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    ejerciciosList.add(childDataSnapshot.getValue(Ejercicio.class));
                }

                for(Ejercicio ejercicio: ejerciciosList)
                    Log.d("svm", ejercicio.toString());

                EjerciciosListAdaptar adapter = new EjerciciosListAdaptar(this, ejerciciosList, nombreRutina);
                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

    }
    public static String getTextFromTextView1(TextView textView){
        try{
            return (String) textView.getText();
        }
        catch (Exception ex){
            Log.d("Exception","Exception of type"+ex.getMessage());
        }
        return "";
    }
}