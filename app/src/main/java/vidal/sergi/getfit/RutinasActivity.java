package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
        rutinaList = new ArrayList<>();

        final Rutina fibrar = new Rutina("Fibrar", crearRutina1());
        rutinaList.add(fibrar);

        final Rutina tonificar = new Rutina("Tonificar", crearRutina2());
        rutinaList.add(tonificar);

        final Rutina muscular = new Rutina("Muscular", crearRutina3());
        rutinaList.add(muscular);

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

        recyclerView = findViewById(R.id.rvRutina);
        tvNombreRutina = findViewById(R.id.tvNombreRutina);
        rutinas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rutinaList = (List<Rutina>) dataSnapshot.getValue();
                recyclerView.setAdapter(new RutinasListAdapter(rutinaList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        Musculo pectoral = new Musculo("Pectoral", ejercicioList);
        musculoList.add(pectoral);

        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Press inclinado con barra").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Press banca con mancuernas").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Aperturas inclinadas 15º").setValue(ejercicioList.get(2));
        rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child("Peck deck").setValue(ejercicioList.get(3));

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo("Hombro Frontal", ejercicioList);
        musculoList.add(hombroFrontal);

        rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child("Press Frontal en Maquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child("Elevaciones Laterales en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child("Press Tras Nuca").setValue(ejercicioList.get(2));

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo("Triceps", ejercicioList);
        musculoList.add(triceps);

        rutinas.child(rutina1.getKey()).child(triceps.getNombre()).child("Triceps en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(triceps.getNombre()).child("Press Frances en Polea").setValue(ejercicioList.get(1));

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

        Musculo dorsal = new Musculo("Dorsal", ejercicioList);
        musculoList.add(dorsal);


        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Remo Bajo en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho Invertido").setValue(ejercicioList.get(2));
        rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo("Hombros Posterior", ejercicioList);
        musculoList.add(hombrosPosterior);

        rutinas.child(rutina1.getKey()).child(hombrosPosterior.getNombre()).child("Pajaros en MAquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(hombrosPosterior.getNombre()).child("Encogimientos con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo("Biceps", ejercicioList);
        musculoList.add(biceps);

        rutinas.child(rutina1.getKey()).child(biceps.getNombre()).child("Curl con Barra en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(biceps.getNombre()).child("Curl Alterno con Mancuernas").setValue(ejercicioList.get(1));

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

        Musculo cuadriceps = new Musculo("Cuadriceps", ejercicioList);
        musculoList.add(cuadriceps);

        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Extension de Cuadriceps").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Sentadillas").setValue(ejercicioList.get(1));
        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Prensa").setValue(ejercicioList.get(2));
        rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo("Femoral", ejercicioList);
        musculoList.add(femoral);

        rutinas.child(rutina1.getKey()).child(femoral.getNombre()).child("Femoral Tumbado").setValue(ejercicioList.get(0));

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo("Gemelos", ejercicioList);
        musculoList.add(gemelos);

        rutinas.child(rutina1.getKey()).child(gemelos.getNombre()).child("Gemelos sentado en Maquina").setValue(ejercicioList.get(0));

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo("Abdominales", ejercicioList);
        musculoList.add(abdominales);

        rutinas.child(rutina1.getKey()).child(abdominales.getNombre()).child("Elevaciones de Tronco").setValue(ejercicioList.get(0));
        rutinas.child(rutina1.getKey()).child(abdominales.getNombre()).child("Elevaciones de Piernas").setValue(ejercicioList.get(1));

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

        Musculo pectoral = new Musculo("Pectoral", ejercicioList);
        musculoList.add(pectoral);

        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Press inclinado con barra").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Press banca con mancuernas").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Aperturas inclinadas 15º").setValue(ejercicioList.get(2));
        rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child("Peck deck").setValue(ejercicioList.get(3));

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo("Hombro Frontal", ejercicioList);
        musculoList.add(hombroFrontal);

        rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child("Press Frontal en Maquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child("Elevaciones Laterales en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child("Press Tras Nuca").setValue(ejercicioList.get(2));

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo("Triceps", ejercicioList);
        musculoList.add(triceps);

        rutinas.child(rutina2.getKey()).child(triceps.getNombre()).child("Triceps en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(triceps.getNombre()).child("Press Frances en Polea").setValue(ejercicioList.get(1));

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

        Musculo dorsal = new Musculo("Dorsal", ejercicioList);
        musculoList.add(dorsal);


        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Remo Bajo en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho Invertido").setValue(ejercicioList.get(2));
        rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo("Hombros Posterior", ejercicioList);
        musculoList.add(hombrosPosterior);

        rutinas.child(rutina2.getKey()).child(hombrosPosterior.getNombre()).child("Pajaros en MAquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(hombrosPosterior.getNombre()).child("Encogimientos con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo("Biceps", ejercicioList);
        musculoList.add(biceps);

        rutinas.child(rutina2.getKey()).child(biceps.getNombre()).child("Curl con Barra en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(biceps.getNombre()).child("Curl Alterno con Mancuernas").setValue(ejercicioList.get(1));

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

        Musculo cuadriceps = new Musculo("Cuadriceps", ejercicioList);
        musculoList.add(cuadriceps);

        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Extension de Cuadriceps").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Sentadillas").setValue(ejercicioList.get(1));
        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Prensa").setValue(ejercicioList.get(2));
        rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo("Femoral", ejercicioList);
        musculoList.add(femoral);

        rutinas.child(rutina2.getKey()).child(femoral.getNombre()).child("Femoral Tumbado").setValue(ejercicioList.get(0));

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo("Gemelos", ejercicioList);
        musculoList.add(gemelos);

        rutinas.child(rutina2.getKey()).child(gemelos.getNombre()).child("Gemelos sentado en Maquina").setValue(ejercicioList.get(0));

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo("Abdominales", ejercicioList);
        musculoList.add(abdominales);

        rutinas.child(rutina2.getKey()).child(abdominales.getNombre()).child("Elevaciones de Tronco").setValue(ejercicioList.get(0));
        rutinas.child(rutina2.getKey()).child(abdominales.getNombre()).child("Elevaciones de Piernas").setValue(ejercicioList.get(1));

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

        Musculo pectoral = new Musculo("Pectoral", ejercicioList);
        musculoList.add(pectoral);

        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Press inclinado con barra").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Press banca con mancuernas").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Aperturas inclinadas 15º").setValue(ejercicioList.get(2));
        rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child("Peck deck").setValue(ejercicioList.get(3));

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio(4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio(4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio(3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo("Hombro Frontal", ejercicioList);
        musculoList.add(hombroFrontal);

        rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child("Press Frontal en Maquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child("Elevaciones Laterales en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child("Press Tras Nuca").setValue(ejercicioList.get(2));

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo("Triceps", ejercicioList);
        musculoList.add(triceps);

        rutinas.child(rutina3.getKey()).child(triceps.getNombre()).child("Triceps en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(triceps.getNombre()).child("Press Frances en Polea").setValue(ejercicioList.get(1));

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

        Musculo dorsal = new Musculo("Dorsal", ejercicioList);
        musculoList.add(dorsal);


        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Remo Bajo en Polea").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Jalon al Pecho Invertido").setValue(ejercicioList.get(2));
        rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio(4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio(3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo("Hombros Posterior", ejercicioList);
        musculoList.add(hombrosPosterior);

        rutinas.child(rutina3.getKey()).child(hombrosPosterior.getNombre()).child("Pajaros en MAquina").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(hombrosPosterior.getNombre()).child("Encogimientos con Mancuernas").setValue(ejercicioList.get(1));

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio(4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo("Biceps", ejercicioList);
        musculoList.add(biceps);

        rutinas.child(rutina3.getKey()).child(biceps.getNombre()).child("Curl con Barra en Polea").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(biceps.getNombre()).child("Curl Alterno con Mancuernas").setValue(ejercicioList.get(1));

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

        Musculo cuadriceps = new Musculo("Cuadriceps", ejercicioList);
        musculoList.add(cuadriceps);

        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Extension de Cuadriceps").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Sentadillas").setValue(ejercicioList.get(1));
        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Prensa").setValue(ejercicioList.get(2));
        rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child("Jalon tras Nuca").setValue(ejercicioList.get(3));

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio(5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo("Femoral", ejercicioList);
        musculoList.add(femoral);

        rutinas.child(rutina3.getKey()).child(femoral.getNombre()).child("Femoral Tumbado").setValue(ejercicioList.get(0));

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio(5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo("Gemelos", ejercicioList);
        musculoList.add(gemelos);

        rutinas.child(rutina3.getKey()).child(gemelos.getNombre()).child("Gemelos sentado en Maquina").setValue(ejercicioList.get(0));

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio(4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo("Abdominales", ejercicioList);
        musculoList.add(abdominales);

        rutinas.child(rutina3.getKey()).child(abdominales.getNombre()).child("Elevaciones de Tronco").setValue(ejercicioList.get(0));
        rutinas.child(rutina3.getKey()).child(abdominales.getNombre()).child("Elevaciones de Piernas").setValue(ejercicioList.get(1));

        return musculoList;
    }
}
