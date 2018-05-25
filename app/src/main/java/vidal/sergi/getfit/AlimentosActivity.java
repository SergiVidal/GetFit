package vidal.sergi.getfit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import vidal.sergi.getfit.Objetos.Alimento;
import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class AlimentosActivity extends AppCompatActivity {

    GridView gridView;
    TextView tvNombreAlimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);

        gridView = findViewById(R.id.gvDieta);
        tvNombreAlimento = findViewById(R.id.tvNombreAlimento);

        final List<Alimento> alimentosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nombreDieta = getIntent().getExtras().getString("nombreDieta");
        Log.d("svm", nombreDieta);
        DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);
        DatabaseReference dieta = database.getReference(FirebaseReferences.DIETA);

        dietas.child(dieta.getKey()).child(nombreDieta).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    alimentosList.add(childDataSnapshot.getValue(Alimento.class));
                }

                for(Alimento alimento: alimentosList)
                    Log.d("svm", alimento.toString());

                AlimentosListAdaptar adapter = new AlimentosListAdaptar(this, alimentosList);
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