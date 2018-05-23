package vidal.sergi.getfit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Musculo;
import vidal.sergi.getfit.Objetos.Rutina;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class RutinasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnMostrarRutina;
    TextView tvNombreRutina;
    Intent intent;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);
    DatabaseReference rutina1 = database.getReference(FirebaseReferences.RUTINA1);
    DatabaseReference rutina2 = database.getReference(FirebaseReferences.RUTINA2);
    DatabaseReference rutina3 = database.getReference(FirebaseReferences.RUTINA3);
    List<Musculo> musculoList;
    List<Ejercicio> ejercicioList;
    List<Rutina> rutinaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);

        recyclerView = findViewById(R.id.rvRutina);
        tvNombreRutina = findViewById(R.id.tvNombreRutina);

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

        rutinaList = new ArrayList<>();
        final Rutina fibrar = new Rutina(randomBgColor(), "Fibrar", crearRutina1());
        final Rutina tonificar = new Rutina(randomBgColor(), "Tonificar", crearRutina2());
        final Rutina muscular = new Rutina(randomBgColor(), "Muscular", crearRutina3());
        rutinaList.add(fibrar);
        rutinaList.add(tonificar);
        rutinaList.add(muscular);

        RutinasListAdapter adapter = new RutinasListAdapter(rutinaList);
        recyclerView.setLayoutManager (new LinearLayoutManager(RutinasActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        rutinas.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("svm", dataSnapshot.getValue() + "");
//                rutinaList = (List<Rutina>) dataSnapshot.getValue();
//                recyclerView.setAdapter(new RutinasListAdapter(rutinaList));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }

    private List<Musculo> crearRutina1(){
        musculoList = new ArrayList<>();
        ejercicioList = new ArrayList<>();

        //Grupo 1
        Ejercicio pressBarra = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio(3, 15, 1.30);
        ejercicioList.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio(3, 12, 1.30);
        ejercicioList.add(peckDeck);

        Musculo pectoral = new Musculo(ejercicioList);
        musculoList.add(pectoral);

        rutinas.child(rutina1.getKey()).child("Pectoral").child("Press inclinado con barra").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Pectoral").child("Press banca con mancuernas").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child("Pectoral").child("Aperturas inclinadas 15º").setValue(ejercicioList.get(2));
        rutinas.child(rutina1.getKey()).child("Pectoral").child("Peck deck").setValue(ejercicioList.get(3));

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo(ejercicioList);
        musculoList.add(hombroFrontal);

        rutinas.child(rutina1.getKey()).child("Hombro Frontal").child("Press Frontal en Maquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Hombro Frontal").child("Elevaciones Laterales en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child("Hombro Frontal").child("Press Tras Nuca").setValue(ejercicioList.get(2));

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo(ejercicioList);
        musculoList.add(triceps);

        rutinas.child(rutina1.getKey()).child("Triceps").child("Triceps en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Triceps").child("Press Frances en Polea").setValue(ejercicioList.get(1));

        //Grupo 4
        ejercicioList = new ArrayList<>();

        Ejercicio jalonPecho = new Ejercicio(4, 12, 2);
        ejercicioList.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio(4, 10, 1.30);
        ejercicioList.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio(4, 10, 2);
        ejercicioList.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio(3, 12, 2);
        ejercicioList.add(jalonNuca);

        Musculo dorsal = new Musculo(ejercicioList);
        musculoList.add(dorsal);


        rutinas.child(rutina1.getKey()).child("Dorsal").child("Jalon al Pecho").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Dorsal").child("Remo Bajo en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child("Dorsal").child("Jalon al Pecho Invertido").setValue(ejercicioList.get(2));
        rutinas.child(rutina1.getKey()).child("Dorsal").child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo(ejercicioList);
        musculoList.add(hombrosPosterior);

        rutinas.child(rutina1.getKey()).child("Hombros Posterior").child("Pajaros en MAquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Hombros Posterior").child("Encogimientos con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo(ejercicioList);
        musculoList.add(biceps);

        rutinas.child(rutina1.getKey()).child("Biceps").child("Curl con Barra en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Biceps").child("Curl Alterno con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 7
        ejercicioList = new ArrayList<>();

        Ejercicio extensionCuadriceps = new Ejercicio(4, 15, 2);
        ejercicioList.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio(4, 12, 2);
        ejercicioList.add(sentadilla);
        Ejercicio prensa = new Ejercicio(3, 12, 2);
        ejercicioList.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio(3, 10, 0);
        ejercicioList.add(zancadasMultipower);

        Musculo cuadriceps = new Musculo(ejercicioList);
        musculoList.add(cuadriceps);

        rutinas.child(rutina1.getKey()).child("Cuadriceps").child("Extension de Cuadriceps").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Cuadriceps").child("Sentadillas").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child("Cuadriceps").child("Prensa").setValue(ejercicioList.get(2));
        rutinas.child(rutina1.getKey()).child("Cuadriceps").child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo(ejercicioList);
        musculoList.add(femoral);

        rutinas.child(rutina1.getKey()).child("Femoral").child("Femoral Tumbado").setValue(ejercicioList.get(0));

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo(ejercicioList);
        musculoList.add(gemelos);

        rutinas.child(rutina1.getKey()).child("Gemelos").child("Gemelos sentado en Maquina").setValue(ejercicioList.get(0));

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo(ejercicioList);
        musculoList.add(abdominales);

        rutinas.child(rutina1.getKey()).child("Abdominales").child("Elevaciones de Tronco").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child("Abdominales").child("Elevaciones de Piernas").setValue(ejercicioList.get(1));

//        Rutina rutina = new Rutina(rutina1.getKey(), musculoList);

        return musculoList;
    }

    private List<Musculo> crearRutina2(){
        musculoList = new ArrayList<>();
        ejercicioList = new ArrayList<>();

        //Grupo 1
        Ejercicio pressBarra = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio(3, 15, 1.30);
        ejercicioList.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio(3, 12, 1.30);
        ejercicioList.add(peckDeck);

        Musculo pectoral = new Musculo(ejercicioList);
        musculoList.add(pectoral);

        rutinas.child(rutina2.getKey()).child("Pectoral").child("Press inclinado con barra").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Pectoral").child("Press banca con mancuernas").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child("Pectoral").child("Aperturas inclinadas 15º").setValue(ejercicioList.get(2));
        rutinas.child(rutina2.getKey()).child("Pectoral").child("Peck deck").setValue(ejercicioList.get(3));

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo(ejercicioList);
        musculoList.add(hombroFrontal);

        rutinas.child(rutina2.getKey()).child("Hombro Frontal").child("Press Frontal en Maquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Hombro Frontal").child("Elevaciones Laterales en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child("Hombro Frontal").child("Press Tras Nuca").setValue(ejercicioList.get(2));

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo(ejercicioList);
        musculoList.add(triceps);

        rutinas.child(rutina2.getKey()).child("Triceps").child("Triceps en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Triceps").child("Press Frances en Polea").setValue(ejercicioList.get(1));

        //Grupo 4
        ejercicioList = new ArrayList<>();

        Ejercicio jalonPecho = new Ejercicio(4, 12, 2);
        ejercicioList.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio(4, 10, 1.30);
        ejercicioList.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio(4, 10, 2);
        ejercicioList.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio(3, 12, 2);
        ejercicioList.add(jalonNuca);

        Musculo dorsal = new Musculo(ejercicioList);
        musculoList.add(dorsal);


        rutinas.child(rutina2.getKey()).child("Dorsal").child("Jalon al Pecho").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Dorsal").child("Remo Bajo en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child("Dorsal").child("Jalon al Pecho Invertido").setValue(ejercicioList.get(2));
        rutinas.child(rutina2.getKey()).child("Dorsal").child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo(ejercicioList);
        musculoList.add(hombrosPosterior);

        rutinas.child(rutina2.getKey()).child("Hombros Posterior").child("Pajaros en MAquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Hombros Posterior").child("Encogimientos con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo(ejercicioList);
        musculoList.add(biceps);

        rutinas.child(rutina2.getKey()).child("Biceps").child("Curl con Barra en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Biceps").child("Curl Alterno con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 7
        ejercicioList = new ArrayList<>();

        Ejercicio extensionCuadriceps = new Ejercicio(4, 15, 2);
        ejercicioList.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio(4, 12, 2);
        ejercicioList.add(sentadilla);
        Ejercicio prensa = new Ejercicio(3, 12, 2);
        ejercicioList.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio(3, 10, 0);
        ejercicioList.add(zancadasMultipower);

        Musculo cuadriceps = new Musculo(ejercicioList);
        musculoList.add(cuadriceps);

        rutinas.child(rutina2.getKey()).child("Cuadriceps").child("Extension de Cuadriceps").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Cuadriceps").child("Sentadillas").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child("Cuadriceps").child("Prensa").setValue(ejercicioList.get(2));
        rutinas.child(rutina2.getKey()).child("Cuadriceps").child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo(ejercicioList);
        musculoList.add(femoral);

        rutinas.child(rutina2.getKey()).child("Femoral").child("Femoral Tumbado").setValue(ejercicioList.get(0));

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo(ejercicioList);
        musculoList.add(gemelos);

        rutinas.child(rutina2.getKey()).child("Gemelos").child("Gemelos sentado en Maquina").setValue(ejercicioList.get(0));

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo(ejercicioList);
        musculoList.add(abdominales);

        rutinas.child(rutina2.getKey()).child("Abdominales").child("Elevaciones de Tronco").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child("Abdominales").child("Elevaciones de Piernas").setValue(ejercicioList.get(1));

//        Rutina rutina = new Rutina(rutina2.getKey(), musculoList);

        return musculoList;
    }

    private List<Musculo> crearRutina3(){
        musculoList = new ArrayList<>();
        ejercicioList = new ArrayList<>();

        //Grupo 1
        Ejercicio pressBarra = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio(3, 15, 1.30);
        ejercicioList.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio(3, 12, 1.30);
        ejercicioList.add(peckDeck);

        Musculo pectoral = new Musculo(ejercicioList);
        musculoList.add(pectoral);

        rutinas.child(rutina3.getKey()).child("Pectoral").child("Press inclinado con barra").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Pectoral").child("Press banca con mancuernas").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child("Pectoral").child("Aperturas inclinadas 15º").setValue(ejercicioList.get(2));
        rutinas.child(rutina3.getKey()).child("Pectoral").child("Peck deck").setValue(ejercicioList.get(3));

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo(ejercicioList);
        musculoList.add(hombroFrontal);

        rutinas.child(rutina3.getKey()).child("Hombro Frontal").child("Press Frontal en Maquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Hombro Frontal").child("Elevaciones Laterales en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child("Hombro Frontal").child("Press Tras Nuca").setValue(ejercicioList.get(2));

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo(ejercicioList);
        musculoList.add(triceps);

        rutinas.child(rutina3.getKey()).child("Triceps").child("Triceps en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Triceps").child("Press Frances en Polea").setValue(ejercicioList.get(1));

        //Grupo 4
        ejercicioList = new ArrayList<>();

        Ejercicio jalonPecho = new Ejercicio(4, 12, 2);
        ejercicioList.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio(4, 10, 1.30);
        ejercicioList.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio(4, 10, 2);
        ejercicioList.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio(3, 12, 2);
        ejercicioList.add(jalonNuca);

        Musculo dorsal = new Musculo(ejercicioList);
        musculoList.add(dorsal);


        rutinas.child(rutina3.getKey()).child("Dorsal").child("Jalon al Pecho").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Dorsal").child("Remo Bajo en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child("Dorsal").child("Jalon al Pecho Invertido").setValue(ejercicioList.get(2));
        rutinas.child(rutina3.getKey()).child("Dorsal").child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo(ejercicioList);
        musculoList.add(hombrosPosterior);

        rutinas.child(rutina3.getKey()).child("Hombros Posterior").child("Pajaros en MAquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Hombros Posterior").child("Encogimientos con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo(ejercicioList);
        musculoList.add(biceps);

        rutinas.child(rutina3.getKey()).child("Biceps").child("Curl con Barra en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Biceps").child("Curl Alterno con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 7
        ejercicioList = new ArrayList<>();

        Ejercicio extensionCuadriceps = new Ejercicio(4, 15, 2);
        ejercicioList.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio(4, 12, 2);
        ejercicioList.add(sentadilla);
        Ejercicio prensa = new Ejercicio(3, 12, 2);
        ejercicioList.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio(3, 10, 0);
        ejercicioList.add(zancadasMultipower);

        Musculo cuadriceps = new Musculo(ejercicioList);
        musculoList.add(cuadriceps);

        rutinas.child(rutina3.getKey()).child("Cuadriceps").child("Extension de Cuadriceps").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Cuadriceps").child("Sentadillas").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child("Cuadriceps").child("Prensa").setValue(ejercicioList.get(2));
        rutinas.child(rutina3.getKey()).child("Cuadriceps").child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo(ejercicioList);
        musculoList.add(femoral);

        rutinas.child(rutina3.getKey()).child("Femoral").child("Femoral Tumbado").setValue(ejercicioList.get(0));

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo(ejercicioList);
        musculoList.add(gemelos);

        rutinas.child(rutina3.getKey()).child("Gemelos").child("Gemelos sentado en Maquina").setValue(ejercicioList.get(0));

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo(ejercicioList);
        musculoList.add(abdominales);

        rutinas.child(rutina3.getKey()).child("Abdominales").child("Elevaciones de Tronco").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child("Abdominales").child("Elevaciones de Piernas").setValue(ejercicioList.get(1));

//        Rutina rutina = new Rutina(rutina3.getKey(), musculoList);

        return musculoList;
    }


    private int randomBgColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
