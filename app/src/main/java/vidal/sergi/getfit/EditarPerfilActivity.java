package vidal.sergi.getfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Usuario;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class EditarPerfilActivity extends AppCompatActivity {

    EditText edNombre, edApellidos, edEdad, edSexo, edPeso, edAltura;
    Button btnSave, btnIMC;
    String nombre, apellidos, sexo;
    int edad, altura;
    double peso, imc;
    Usuario usuario;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.USERS);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarperfil);
        final String username = getIntent().getExtras().getString("user");

        usersRef.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
//                Log.d("username", usuario.toString());
                edNombre.setText(usuario.getNombre());
                edApellidos.setText(usuario.getApellidos());
                edEdad.setText(String.valueOf(usuario.getEdad()));
                edSexo.setText(usuario.getSexo());
                edPeso.setText(String.valueOf(usuario.getPeso()));
                edAltura.setText(String.valueOf(usuario.getAltura()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

        Log.d("username", username);
        usuario = new Usuario();
        edNombre = findViewById(R.id.edNombre);
        edApellidos = findViewById(R.id.edApellidos);
        edEdad = findViewById(R.id.edEdad);
        edSexo = findViewById(R.id.edSexo);
        edPeso = findViewById(R.id.edPeso);
        edAltura = findViewById(R.id.edAltura);
        btnSave = findViewById(R.id.btnSave);
        btnIMC = findViewById(R.id.btnIMC);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edNombre.getText().toString();
                apellidos = edApellidos.getText().toString();
                edad = Integer.parseInt(edEdad.getText().toString());
                sexo = edSexo.getText().toString();
                peso = Double.parseDouble(edPeso.getText().toString());
                altura = Integer.parseInt(edAltura.getText().toString());

                usuario.setNombre(nombre);
                usuario.setApellidos(apellidos);
                usuario.setEdad(edad);
                usuario.setSexo(sexo);
                usuario.setPeso(peso);
                usuario.setAltura(altura);
                usersRef.child(username).child("nombre").setValue(usuario.getNombre());
                usersRef.child(username).child("apellidos").setValue(usuario.getApellidos());
                usersRef.child(username).child("edad").setValue(usuario.getEdad());
                usersRef.child(username).child("sexo").setValue(usuario.getSexo());
                usersRef.child(username).child("peso").setValue(usuario.getPeso());
                usersRef.child(username).child("altura").setValue(usuario.getAltura());


                Log.d("svm:", "Datos de usuario actualizados.");
                Toast.makeText(EditarPerfilActivity.this, "Datos de usuario actualizados.", Toast.LENGTH_SHORT).show();
            }
        });

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peso = Double.parseDouble(edPeso.getText().toString());
                altura = Integer.parseInt(edAltura.getText().toString());
                double alturaCM = (double)altura/100;
                imc = peso / (alturaCM * alturaCM);
                usuario.setPeso(peso);
                usuario.setAltura(altura);
                usuario.setImc(imc);
                usersRef.child(username).child("imc").setValue(imc);
                Toast.makeText(EditarPerfilActivity.this, "Tu IMC es: " + imc, Toast.LENGTH_SHORT).show();


            }
        });
    }
}
