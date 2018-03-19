package vidal.sergi.getfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class RutinasActivity extends AppCompatActivity {

    Button btnCoach;
    EditText edNivel;
    int nivel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
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
