package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Musculo;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class RutinasActivity extends AppCompatActivity {

    Button btnMostrarRutina;
    TextView tvNombreRutina, tvSeguidores;
    Intent intent;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);
    DatabaseReference rutina1 = database.getReference(FirebaseReferences.RUTINA1);
    DatabaseReference rutina2 = database.getReference(FirebaseReferences.RUTINA2);
    DatabaseReference rutina3 = database.getReference(FirebaseReferences.RUTINA3);
    List<Musculo> musculos;
    List<Ejercicio> ejercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
        crearRutina1();
        crearRutina2();
        crearRutina3();
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

        btnMostrarRutina = findViewById(R.id.btnMostrarRutina);
        tvNombreRutina = findViewById(R.id.tvNombreRutina);
        tvSeguidores = findViewById(R.id.tvSeguidores);




    }

    //Administra els components
   /* @Override
    public Context getContext() { return this; }

    //Al retornar els components....
    @Override
    public void updated(JsonResponse jsonResponse) {
        PlayerListAdapter adapter = new PlayerListAdapter(jsonResponse);
        rvPlayer.setLayoutManager (new LinearLayoutManager(RanquingActivity.this));
        rvPlayer.setItemAnimator(new DefaultItemAnimator());
        rvPlayer.setAdapter(adapter);

        //Bindejar info joc
        tvTitle.setText(jsonResponse.getGameInfo().getName());
        tvDescription.setText(jsonResponse.getGameInfo().getDescription());
        Picasso.with(getContext()).load(jsonResponse.getGameInfo().getImagePath()).into(imgGame);

    }*/

    private void crearRutina1(){
        //Grupo 1
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo pectoral = new Musculo("Pectoral");
        musculos.add(pectoral);
        Ejercicio pressBarra = new Ejercicio(4, 12, 2);
        ejercicios.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio(3, 10, 2);
        ejercicios.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio(3, 15, 1.30);
        ejercicios.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio(3, 12, 1.30);
        ejercicios.add(peckDeck);

        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Press inclinado con barra").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Press banca con mancuernas").setValue(ejercicios.get(1));
        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Aperturas inclinadas 15º").setValue(ejercicios.get(2));
        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Peck deck").setValue(ejercicios.get(3));

        //Grupo 2
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo hombroFrontal = new Musculo("Hombro Frontal");
        musculos.add(hombroFrontal);
        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicios.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicios.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicios.add(pressNuca);

        rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child("Press Frontal en Maquina").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child("Elevaciones Laterales en Polea").setValue(ejercicios.get(1));
        rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child("Press Tras Nuca").setValue(ejercicios.get(2));

        //Grupo 3
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo triceps = new Musculo("Triceps");
        musculos.add(triceps);
        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicios.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicios.add(pressFrances);

        rutinas.child(rutina1.getKey()).child(triceps.getNombre()).child("Triceps en Polea").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(triceps.getNombre()).child("Press Frances en Polea").setValue(ejercicios.get(1));

        //Grupo 4
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo dorsal = new Musculo("Dorsal");
        musculos.add(dorsal);
        Ejercicio jalonPecho = new Ejercicio(4, 12, 2);
        ejercicios.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio(4, 10, 1.30);
        ejercicios.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio(4, 10, 2);
        ejercicios.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio(3, 12, 2);
        ejercicios.add(jalonNuca);

        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Remo Bajo en Polea").setValue(ejercicios.get(1));
        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho Invertido").setValue(ejercicios.get(2));
        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Jalon tras Nuca").setValue(ejercicios.get(3));

        //Grupo 5
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo hombrosPosterior = new Musculo("Hombros Posterior");
        musculos.add(hombrosPosterior);
        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicios.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicios.add(encogimientosMancuernas);

        rutinas.child(rutina1.getKey()).child(hombrosPosterior.getNombre()).child("Pajaros en MAquina").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(hombrosPosterior.getNombre()).child("Encogimientos con Mancuernas").setValue(ejercicios.get(1));

        //Grupo 6
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo biceps = new Musculo("Biceps");
        musculos.add(biceps);
        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicios.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicios.add(curlMancuernas);

        rutinas.child(rutina1.getKey()).child(biceps.getNombre()).child("Curl con Barra en Polea").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(biceps.getNombre()).child("Curl Alterno con Mancuernas").setValue(ejercicios.get(1));

        //Grupo 7
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo cuadriceps = new Musculo("Cuadriceps");
        musculos.add(cuadriceps);
        Ejercicio extensionCuadriceps = new Ejercicio(4, 15, 2);
        ejercicios.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio(4, 12, 2);
        ejercicios.add(sentadilla);
        Ejercicio prensa = new Ejercicio(3, 12, 2);
        ejercicios.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio(3, 10, 0);
        ejercicios.add(zancadasMultipower);

        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Extension de Cuadriceps").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Sentadillas").setValue(ejercicios.get(1));
        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Prensa").setValue(ejercicios.get(2));
        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Jalon tras Nuca").setValue(ejercicios.get(3));

        //Grupo 8
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo femoral = new Musculo("Femoral");
        musculos.add(femoral);
        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicios.add(femoralTumbado);

        rutinas.child(rutina1.getKey()).child(femoral.getNombre()).child("Femoral Tumbado").setValue(ejercicios.get(0));

        //Grupo 9
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo gemelos = new Musculo("Gemelos");
        musculos.add(gemelos);
        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicios.add(gemelosMaquina);

        rutinas.child(rutina1.getKey()).child(gemelos.getNombre()).child("Gemelos sentado en Maquina").setValue(ejercicios.get(0));

        //Grupo 10
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo abdominales = new Musculo("Abdominales");
        musculos.add(abdominales);
        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicios.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicios.add(elevacionesPiernas);

        rutinas.child(rutina1.getKey()).child(abdominales.getNombre()).child("Elevaciones de Tronco").setValue(ejercicios.get(0));
        rutinas.child(rutina1.getKey()).child(abdominales.getNombre()).child("Elevaciones de Piernas").setValue(ejercicios.get(1));
    }

    private void crearRutina2(){
        //Grupo 1
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo pectoral = new Musculo("Pectoral");
        musculos.add(pectoral);
        Ejercicio pressBarra = new Ejercicio(4, 12, 2);
        ejercicios.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio(3, 10, 2);
        ejercicios.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio(3, 15, 1.30);
        ejercicios.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio(3, 12, 1.30);
        ejercicios.add(peckDeck);

        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Press inclinado con barra").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Press banca con mancuernas").setValue(ejercicios.get(1));
        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Aperturas inclinadas 15º").setValue(ejercicios.get(2));
        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Peck deck").setValue(ejercicios.get(3));

        //Grupo 2
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo hombroFrontal = new Musculo("Hombro Frontal");
        musculos.add(hombroFrontal);
        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicios.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicios.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicios.add(pressNuca);

        rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child("Press Frontal en Maquina").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child("Elevaciones Laterales en Polea").setValue(ejercicios.get(1));
        rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child("Press Tras Nuca").setValue(ejercicios.get(2));

        //Grupo 3
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo triceps = new Musculo("Triceps");
        musculos.add(triceps);
        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicios.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicios.add(pressFrances);

        rutinas.child(rutina2.getKey()).child(triceps.getNombre()).child("Triceps en Polea").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(triceps.getNombre()).child("Press Frances en Polea").setValue(ejercicios.get(1));

        //Grupo 4
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo dorsal = new Musculo("Dorsal");
        musculos.add(dorsal);
        Ejercicio jalonPecho = new Ejercicio(4, 12, 2);
        ejercicios.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio(4, 10, 1.30);
        ejercicios.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio(4, 10, 2);
        ejercicios.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio(3, 12, 2);
        ejercicios.add(jalonNuca);

        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Remo Bajo en Polea").setValue(ejercicios.get(1));
        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho Invertido").setValue(ejercicios.get(2));
        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Jalon tras Nuca").setValue(ejercicios.get(3));

        //Grupo 5
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo hombrosPosterior = new Musculo("Hombros Posterior");
        musculos.add(hombrosPosterior);
        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicios.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicios.add(encogimientosMancuernas);

        rutinas.child(rutina2.getKey()).child(hombrosPosterior.getNombre()).child("Pajaros en MAquina").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(hombrosPosterior.getNombre()).child("Encogimientos con Mancuernas").setValue(ejercicios.get(1));

        //Grupo 6
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo biceps = new Musculo("Biceps");
        musculos.add(biceps);
        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicios.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicios.add(curlMancuernas);

        rutinas.child(rutina2.getKey()).child(biceps.getNombre()).child("Curl con Barra en Polea").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(biceps.getNombre()).child("Curl Alterno con Mancuernas").setValue(ejercicios.get(1));

        //Grupo 7
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo cuadriceps = new Musculo("Cuadriceps");
        musculos.add(cuadriceps);
        Ejercicio extensionCuadriceps = new Ejercicio(4, 15, 2);
        ejercicios.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio(4, 12, 2);
        ejercicios.add(sentadilla);
        Ejercicio prensa = new Ejercicio(3, 12, 2);
        ejercicios.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio(3, 10, 0);
        ejercicios.add(zancadasMultipower);

        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Extension de Cuadriceps").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Sentadillas").setValue(ejercicios.get(1));
        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Prensa").setValue(ejercicios.get(2));
        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Jalon tras Nuca").setValue(ejercicios.get(3));

        //Grupo 8
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo femoral = new Musculo("Femoral");
        musculos.add(femoral);
        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicios.add(femoralTumbado);

        rutinas.child(rutina2.getKey()).child(femoral.getNombre()).child("Femoral Tumbado").setValue(ejercicios.get(0));

        //Grupo 9
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo gemelos = new Musculo("Gemelos");
        musculos.add(gemelos);
        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicios.add(gemelosMaquina);

        rutinas.child(rutina2.getKey()).child(gemelos.getNombre()).child("Gemelos sentado en Maquina").setValue(ejercicios.get(0));

        //Grupo 10
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo abdominales = new Musculo("Abdominales");
        musculos.add(abdominales);
        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicios.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicios.add(elevacionesPiernas);

        rutinas.child(rutina2.getKey()).child(abdominales.getNombre()).child("Elevaciones de Tronco").setValue(ejercicios.get(0));
        rutinas.child(rutina2.getKey()).child(abdominales.getNombre()).child("Elevaciones de Piernas").setValue(ejercicios.get(1));
    }

    private void crearRutina3(){
        //Grupo 1
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo pectoral = new Musculo("Pectoral");
        musculos.add(pectoral);
        Ejercicio pressBarra = new Ejercicio(4, 12, 2);
        ejercicios.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio(3, 10, 2);
        ejercicios.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio(3, 15, 1.30);
        ejercicios.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio(3, 12, 1.30);
        ejercicios.add(peckDeck);

        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Press inclinado con barra").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Press banca con mancuernas").setValue(ejercicios.get(1));
        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Aperturas inclinadas 15º").setValue(ejercicios.get(2));
        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Peck deck").setValue(ejercicios.get(3));

        //Grupo 2
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo hombroFrontal = new Musculo("Hombro Frontal");
        musculos.add(hombroFrontal);
        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicios.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicios.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicios.add(pressNuca);

        rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child("Press Frontal en Maquina").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child("Elevaciones Laterales en Polea").setValue(ejercicios.get(1));
        rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child("Press Tras Nuca").setValue(ejercicios.get(2));

        //Grupo 3
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo triceps = new Musculo("Triceps");
        musculos.add(triceps);
        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicios.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicios.add(pressFrances);

        rutinas.child(rutina3.getKey()).child(triceps.getNombre()).child("Triceps en Polea").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(triceps.getNombre()).child("Press Frances en Polea").setValue(ejercicios.get(1));

        //Grupo 4
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo dorsal = new Musculo("Dorsal");
        musculos.add(dorsal);
        Ejercicio jalonPecho = new Ejercicio(4, 12, 2);
        ejercicios.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio(4, 10, 1.30);
        ejercicios.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio(4, 10, 2);
        ejercicios.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio(3, 12, 2);
        ejercicios.add(jalonNuca);

        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Remo Bajo en Polea").setValue(ejercicios.get(1));
        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho Invertido").setValue(ejercicios.get(2));
        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Jalon tras Nuca").setValue(ejercicios.get(3));

        //Grupo 5
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo hombrosPosterior = new Musculo("Hombros Posterior");
        musculos.add(hombrosPosterior);
        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicios.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicios.add(encogimientosMancuernas);

        rutinas.child(rutina3.getKey()).child(hombrosPosterior.getNombre()).child("Pajaros en MAquina").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(hombrosPosterior.getNombre()).child("Encogimientos con Mancuernas").setValue(ejercicios.get(1));

        //Grupo 6
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo biceps = new Musculo("Biceps");
        musculos.add(biceps);
        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicios.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicios.add(curlMancuernas);

        rutinas.child(rutina3.getKey()).child(biceps.getNombre()).child("Curl con Barra en Polea").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(biceps.getNombre()).child("Curl Alterno con Mancuernas").setValue(ejercicios.get(1));

        //Grupo 7
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo cuadriceps = new Musculo("Cuadriceps");
        musculos.add(cuadriceps);
        Ejercicio extensionCuadriceps = new Ejercicio(4, 15, 2);
        ejercicios.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio(4, 12, 2);
        ejercicios.add(sentadilla);
        Ejercicio prensa = new Ejercicio(3, 12, 2);
        ejercicios.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio(3, 10, 0);
        ejercicios.add(zancadasMultipower);

        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Extension de Cuadriceps").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Sentadillas").setValue(ejercicios.get(1));
        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Prensa").setValue(ejercicios.get(2));
        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Jalon tras Nuca").setValue(ejercicios.get(3));

        //Grupo 8
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo femoral = new Musculo("Femoral");
        musculos.add(femoral);
        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicios.add(femoralTumbado);

        rutinas.child(rutina3.getKey()).child(femoral.getNombre()).child("Femoral Tumbado").setValue(ejercicios.get(0));

        //Grupo 9
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo gemelos = new Musculo("Gemelos");
        musculos.add(gemelos);
        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicios.add(gemelosMaquina);

        rutinas.child(rutina3.getKey()).child(gemelos.getNombre()).child("Gemelos sentado en Maquina").setValue(ejercicios.get(0));

        //Grupo 10
        musculos = new ArrayList<>();
        ejercicios = new ArrayList<>();

        Musculo abdominales = new Musculo("Abdominales");
        musculos.add(abdominales);
        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicios.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicios.add(elevacionesPiernas);

        rutinas.child(rutina3.getKey()).child(abdominales.getNombre()).child("Elevaciones de Tronco").setValue(ejercicios.get(0));
        rutinas.child(rutina3.getKey()).child(abdominales.getNombre()).child("Elevaciones de Piernas").setValue(ejercicios.get(1));
    }
}
