package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class RutinasActivity extends AppCompatActivity {

    Intent intent;
    Button btnCoach;
    EditText edNivel;
    int nivel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
        final String username = getIntent().getExtras().getString("user");
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_rutinas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(RutinasActivity.this, HomeActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(RutinasActivity.this, RutinasActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        //Intent intent2 = new Intent(HomeActivity.this,DietasActivity.class);
                        //startActivity(intent2);
                        // Toast.makeText(HomeActivity.this,"Action remove clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_ajustes:
                        intent = new Intent(RutinasActivity.this, AjustesActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        btnCoach = findViewById(R.id.btnCoach);
        edNivel = findViewById(R.id.edNivel);
        btnCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nivel = Integer.parseInt(edNivel.getText().toString());
                Toast.makeText(RutinasActivity.this, "Tu nivel es: " + nivel + ", se te va ha asignar un Coach!", Toast.LENGTH_SHORT).show();
                if(nivel == 1){

                } else if(nivel == 2){

                }else if(nivel == 3){

                }
            }
        });

    }
}
