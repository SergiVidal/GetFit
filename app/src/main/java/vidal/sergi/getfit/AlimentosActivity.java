package vidal.sergi.getfit;

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

import vidal.sergi.getfit.Objetos.Alimento;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class AlimentosActivity extends AppCompatActivity {

    GridView gridView;
    static TextView tvNC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);

        gridView = findViewById(R.id.gvDieta);
        tvNC = findViewById(R.id.tvNC);

        final List<Alimento> alimentosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String nombreComida = getIntent().getExtras().getString("nombreComida");
        final String nombreDieta = getIntent().getExtras().getString("nombreDieta");
        Log.d("svm", nombreDieta);
        Log.d("svm", nombreComida);
        tvNC.setText(nombreComida);

        DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);
        dietas.child(nombreDieta).child(nombreComida).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    alimentosList.add(childDataSnapshot.getValue(Alimento.class));
                }

                for(Alimento alimento: alimentosList)
                    Log.d("svm", alimento.toString());

                AlimentosListAdaptar adapter = new AlimentosListAdaptar(this, alimentosList, nombreDieta);
                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

    }
}