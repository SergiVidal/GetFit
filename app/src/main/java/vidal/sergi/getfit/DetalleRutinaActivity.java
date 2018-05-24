package vidal.sergi.getfit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleRutinaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvNombreMusculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallerutina);

        recyclerView = findViewById(R.id.rvDetalleRutina);
        tvNombreMusculo = findViewById(R.id.tvNombreRutina);

        final List<String> musculosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nombreRutina = getIntent().getExtras().getString("nombreRutina");
        Log.v("svm",nombreRutina);
        DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);

        rutinas.child(nombreRutina).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    musculosList.add(childDataSnapshot.getKey());
                }
                fotosList.add(R.drawable.abdominales);
                fotosList.add(R.drawable.biceps);
                fotosList.add(R.drawable.cuadriceps);
                fotosList.add(R.drawable.dorsales);
                fotosList.add(R.drawable.femorales);
                fotosList.add(R.drawable.gemelos);
                fotosList.add(R.drawable.hombrofrontal);
                fotosList.add(R.drawable.hombroposterior);
                fotosList.add(R.drawable.pectoral);
                fotosList.add(R.drawable.triceps);

                DetalleRutinaListAdapter adapter = new DetalleRutinaListAdapter(musculosList, fotosList);
                recyclerView.setLayoutManager (new LinearLayoutManager(DetalleRutinaActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
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