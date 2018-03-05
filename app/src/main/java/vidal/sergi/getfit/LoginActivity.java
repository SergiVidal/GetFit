package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sergi on 01/03/2018.
 */

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;
    FirebaseDatabase database;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.registrar);


        database = FirebaseDatabase.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailRegistro = email.getText().toString();
                String passwordRegistro = password.getText().toString();
                iniciarSesion(emailRegistro, passwordRegistro);

                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser(); //FirebaseAuth.getInstance().getCurrentUser();
                        if(user != null){
                            Log.d("SESION", "Sesion iniciada con email: " + user.getEmail());
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Log.d("SESION", "Sesion cerrada");
                        }
                    }
                };
            }
        });
    }



    private void iniciarSesion(String email, String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass);
    }
}
