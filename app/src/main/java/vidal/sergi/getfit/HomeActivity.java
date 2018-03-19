package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sergi on 02/03/2018.
 */

public class HomeActivity extends AppCompatActivity {

    Button btnRutinas, btnAjustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final String username = getIntent().getExtras().getString("user");
        btnRutinas = findViewById(R.id.btnRutinas);
        btnAjustes = findViewById(R.id.btnAjustes);

        btnRutinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RutinasActivity.class);
                startActivity(intent);
            }
        });

        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AjustesActivity.class);
                intent.putExtra("user", username);
                startActivity(intent);
            }
        });
    }
}
