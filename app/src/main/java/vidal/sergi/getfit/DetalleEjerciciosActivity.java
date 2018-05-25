package vidal.sergi.getfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleEjerciciosActivity extends AppCompatActivity {

    ImageView ivEjercicio;
    TextView tvNEjercicio, tvS, tvR, tvD;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ejercicios);

        ivEjercicio = findViewById(R.id.ivEjercicio);
        tvNEjercicio = findViewById(R.id.tvNEjercicio);
        tvS = findViewById(R.id.tvS);
        tvR = findViewById(R.id.tvR);
        tvD = findViewById(R.id.tvD);

        String nombreEjercicio = getIntent().getExtras().getString("nombreEjercicio");
        String nombreMusculo = getIntent().getExtras().getString("nombreMusculo");
        String nombreRutina = getIntent().getExtras().getString("nombreRutina");
        Log.d("svm", nombreMusculo);
        Log.d("svm", nombreEjercicio);
        Log.d("svm", nombreRutina);

        rutinas.child(nombreRutina).child(nombreMusculo).child(nombreEjercicio).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvNEjercicio.setText(dataSnapshot.child("nombre").getValue(String.class));
                tvS.setText(String.valueOf(dataSnapshot.child("series").getValue(Integer.class)));
                tvR.setText(String.valueOf(dataSnapshot.child("repeticiones").getValue(Integer.class)));
                tvD.setText(String.valueOf(dataSnapshot.child("descanso").getValue(Integer.class)));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

    }
}