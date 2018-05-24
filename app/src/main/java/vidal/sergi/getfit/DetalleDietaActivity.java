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

public class DetalleDietaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvNombreComida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalledieta);

        recyclerView = findViewById(R.id.rvDetalleDieta);
        tvNombreComida = findViewById(R.id.tvNombreDieta);

        final List<String> comidasList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nombreRutina = getIntent().getExtras().getString("nombreDieta");
        DatabaseReference rutinas = database.getReference(FirebaseReferences.DIETAS);

        rutinas.child(nombreRutina).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    comidasList.add(childDataSnapshot.getKey());
                }
                fotosList.add(R.drawable.almuerzo);
                fotosList.add(R.drawable.cena);
                fotosList.add(R.drawable.comida);
                fotosList.add(R.drawable.desayuno);
                fotosList.add(R.drawable.merienda);

                Log.v("svm",""+ comidasList.toString());
                DetalleDietaListAdapter adapter = new DetalleDietaListAdapter(comidasList, fotosList);
                recyclerView.setLayoutManager (new LinearLayoutManager(DetalleDietaActivity.this, LinearLayoutManager.HORIZONTAL, false));
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
