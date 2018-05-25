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

import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleAlimentosActivity extends AppCompatActivity {

    ImageView ivAlimento;
    TextView tvNALimento, tvC, tvG;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alimentos);

        ivAlimento = findViewById(R.id.ivAlimento);
        tvNALimento = findViewById(R.id.tvNALimento);
        tvC = findViewById(R.id.tvC);
        tvG = findViewById(R.id.tvG);

        String nombreAlimento = getIntent().getExtras().getString("nombreAlimento");
        String nombreComida = getIntent().getExtras().getString("nombreComida");
        String nombreDieta = getIntent().getExtras().getString("nombreDieta");
        Log.d("svm", nombreComida);
        Log.d("svm", nombreAlimento);
        Log.d("svm", nombreDieta);

        dietas.child(nombreDieta).child(nombreComida).child(nombreAlimento).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvNALimento.setText(dataSnapshot.child("nombre").getValue(String.class));
                tvC.setText(String.valueOf(dataSnapshot.child("calorias").getValue(Integer.class)));
                tvG.setText(String.valueOf(dataSnapshot.child("gramos").getValue(Integer.class)));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

    }
}
