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
    TextView tvNombreEjercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        gridView = findViewById(R.id.gvRutina);
        tvNombreEjercicio = findViewById(R.id.tvNombreEjercicio);

        final List<Ejercicio> ejerciciosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nombreMusculo = getIntent().getExtras().getString("nombreMusculo");
        Log.d("svm", nombreMusculo);
        DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);
        DatabaseReference rutina = database.getReference(FirebaseReferences.RUTINA);

        rutinas.child(rutina.getKey()).child(nombreMusculo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    ejerciciosList.add(childDataSnapshot.getValue(Ejercicio.class));
                }

                for(Ejercicio ejercicio: ejerciciosList)
                    Log.d("svm", ejercicio.toString());

                EjerciciosListAdaptar adapter = new EjerciciosListAdaptar(this, ejerciciosList);
                gridView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

    }

    private int randomBgColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}