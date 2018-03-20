package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class AjustesActivity extends AppCompatActivity{

    TextView editarPerfil, idioma, notificaciones, politica, anuncios, version, ayuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        editarPerfil = findViewById(R.id.editarPerfil);
        idioma = findViewById(R.id.idioma);
        notificaciones = findViewById(R.id.notificaciones);
        politica = findViewById(R.id.politica);
        anuncios = findViewById(R.id.anuncios);
        version = findViewById(R.id.version);
        ayuda = findViewById(R.id.ayuda);
        final String username = getIntent().getExtras().getString("user");


        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                intent.putExtra("user", username);
                startActivity(intent);
            }
        });
        idioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });
        notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });
        politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });
        anuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });
    }

}
