package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    EditText email, password;
    TextView btnLogin, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnRegistrar = findViewById(R.id.registrar);
        btnLogin = findViewById(R.id.login);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON","onCLick()");
                String emailRegistro = email.getText().toString();
                String passwordRegistro = password.getText().toString();
                registrar(emailRegistro, passwordRegistro);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registrar(String email, String pass){
        Log.d("SESION", "registrar()");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Log.d("SESION", "Usuario creado correctamente");
                }else {
                    Log.d("SESION", task.getException().getMessage()+"");
                }
            }
        });
    }
}