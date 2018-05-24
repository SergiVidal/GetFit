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
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.Alimento;
import vidal.sergi.getfit.Objetos.Comida;
import vidal.sergi.getfit.Objetos.Dieta;
import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Musculo;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class DietasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnMostrarRutina;
    TextView tvNombreDieta;
    Intent intent;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);
    DatabaseReference dieta1 = database.getReference(FirebaseReferences.DIETA1);
    DatabaseReference dieta2 = database.getReference(FirebaseReferences.DIETA2);
    DatabaseReference dieta3 = database.getReference(FirebaseReferences.DIETA3);
    List<Comida> comidaList;
    List<Alimento> alimentoList;
    List<Dieta> dietaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);

        recyclerView = findViewById(R.id.rvDieta);
        tvNombreDieta = findViewById(R.id.tvNombreDieta);

        final String username = getIntent().getExtras().getString("user");
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_dietas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(DietasActivity.this, HomeActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DietasActivity.this, RutinasActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DietasActivity.this, DietasActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);;
                        break;
                    case R.id.action_ajustes:
                        intent = new Intent(DietasActivity.this, AjustesActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        final Dieta ganarMusculo = new Dieta(R.drawable.dieta3, "Ganar Musculo", crearDieta1());
        final Dieta subirPeso = new Dieta(R.drawable.dieta1, "Subir Peso", crearDieta2());
        final Dieta bajarPeso = new Dieta(R.drawable.dieta2, "Bajar Peso", crearDieta3());
        dietaList = new ArrayList<>();
        dietaList.add(ganarMusculo);
        dietaList.add(subirPeso);
        dietaList.add(bajarPeso);

        DietasListAdapter adapter = new DietasListAdapter(dietaList);
        recyclerView.setLayoutManager (new LinearLayoutManager(DietasActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private List<Comida> crearDieta1(){
        comidaList = new ArrayList<>();
        alimentoList = new ArrayList<>();

        //Comida 1
        Alimento tortilla = new Alimento("Tortilla - 100g", 250);
        alimentoList.add(tortilla);

        Comida desayuno = new Comida("Desayuno", alimentoList);
        comidaList.add(desayuno);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(desayuno.getNombre()).child(alimento.getNombre()).setValue(alimento);

        //Comida 2
        alimentoList = new ArrayList<>();

        Alimento miniBocadillo = new Alimento("Bocadillo - 100g", 200);
        alimentoList.add(miniBocadillo);

        Alimento piezasFrutas = new Alimento("Fruta - 50g", 100);
        alimentoList.add(piezasFrutas);

        Comida almuerzo = new Comida("Almuerzo", alimentoList);
        comidaList.add(almuerzo);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(almuerzo.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 3
        alimentoList = new ArrayList<>();

        Alimento hidratos = new Alimento("Arroz o Pasta o Legumbres - 200g", 300);
        alimentoList.add(hidratos);

        Alimento proteinas = new Alimento("Pescado o Carne o Verduras - 150g", 100);
        alimentoList.add(proteinas);

        Comida comida = new Comida("Comida", alimentoList);
        comidaList.add(comida);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(comida.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 4
        alimentoList = new ArrayList<>();

        Alimento tortasDeArroz = new Alimento("Tortas de Arroz - 100g", 80);
        alimentoList.add(tortasDeArroz);

        Alimento pavo = new Alimento("Pavo - 120g", 100);
        alimentoList.add(pavo);

        Comida merienda = new Comida("Merienda", alimentoList);
        comidaList.add(merienda);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(merienda.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 5
        alimentoList = new ArrayList<>();

        Alimento arroz = new Alimento("Arroz - 70g", 120);
        alimentoList.add(arroz);

        Alimento pollo = new Alimento("Pollo - 250g", 250);
        alimentoList.add(pollo);

        Comida cena = new Comida("Cena", alimentoList);
        comidaList.add(cena);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(cena.getNombre()).child(alimento.getNombre()).setValue(alimento);

        return comidaList;
    }

    private List<Comida> crearDieta2(){
        comidaList = new ArrayList<>();
        alimentoList = new ArrayList<>();

        //Comida 1
        Alimento tortilla = new Alimento("Tortilla - 100g", 250);
        alimentoList.add(tortilla);

        Comida desayuno = new Comida("Desayuno", alimentoList);
        comidaList.add(desayuno);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(desayuno.getNombre()).child(alimento.getNombre()).setValue(alimento);

        //Comida 2
        alimentoList = new ArrayList<>();

        Alimento miniBocadillo = new Alimento("Bocadillo - 100g", 200);
        alimentoList.add(miniBocadillo);

        Alimento piezasFrutas = new Alimento("Fruta - 50g", 100);
        alimentoList.add(piezasFrutas);

        Comida almuerzo = new Comida("Almuerzo", alimentoList);
        comidaList.add(almuerzo);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(almuerzo.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 3
        alimentoList = new ArrayList<>();

        Alimento hidratos = new Alimento("Arroz o Pasta o Legumbres - 200g", 300);
        alimentoList.add(hidratos);

        Alimento proteinas = new Alimento("Pescado o Carne o Verduras - 150g", 100);
        alimentoList.add(proteinas);

        Comida comida = new Comida("Comida", alimentoList);
        comidaList.add(comida);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(comida.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 4
        alimentoList = new ArrayList<>();

        Alimento tortasDeArroz = new Alimento("Tortas de Arroz - 100g", 80);
        alimentoList.add(tortasDeArroz);

        Alimento pavo = new Alimento("Pavo - 120g", 100);
        alimentoList.add(pavo);

        Comida merienda = new Comida("Merienda", alimentoList);
        comidaList.add(merienda);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(merienda.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 5
        alimentoList = new ArrayList<>();

        Alimento arroz = new Alimento("Arroz - 70g", 120);
        alimentoList.add(arroz);

        Alimento pollo = new Alimento("Pollo - 250g", 250);
        alimentoList.add(pollo);

        Comida cena = new Comida("Cena", alimentoList);
        comidaList.add(cena);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(cena.getNombre()).child(alimento.getNombre()).setValue(alimento);

        return comidaList;
    }

    private List<Comida> crearDieta3(){
        comidaList = new ArrayList<>();
        alimentoList = new ArrayList<>();

        //Comida 1
        Alimento tortilla = new Alimento("Tortilla - 100g", 250);
        alimentoList.add(tortilla);

        Comida desayuno = new Comida("Desayuno", alimentoList);
        comidaList.add(desayuno);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(desayuno.getNombre()).child(alimento.getNombre()).setValue(alimento);

        //Comida 2
        alimentoList = new ArrayList<>();

        Alimento miniBocadillo = new Alimento("Bocadillo - 100g", 200);
        alimentoList.add(miniBocadillo);

        Alimento piezasFrutas = new Alimento("Fruta - 50g", 100);
        alimentoList.add(piezasFrutas);

        Comida almuerzo = new Comida("Almuerzo", alimentoList);
        comidaList.add(almuerzo);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(almuerzo.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 3
        alimentoList = new ArrayList<>();

        Alimento hidratos = new Alimento("Arroz o Pasta o Legumbres - 200g", 300);
        alimentoList.add(hidratos);

        Alimento proteinas = new Alimento("Pescado o Carne o Verduras - 150g", 100);
        alimentoList.add(proteinas);

        Comida comida = new Comida("Comida", alimentoList);
        comidaList.add(comida);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(comida.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 4
        alimentoList = new ArrayList<>();

        Alimento tortasDeArroz = new Alimento("Tortas de Arroz - 100g", 80);
        alimentoList.add(tortasDeArroz);

        Alimento pavo = new Alimento("Pavo - 120g", 100);
        alimentoList.add(pavo);

        Comida merienda = new Comida("Merienda", alimentoList);
        comidaList.add(merienda);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(merienda.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 5
        alimentoList = new ArrayList<>();

        Alimento arroz = new Alimento("Arroz - 70g", 120);
        alimentoList.add(arroz);

        Alimento pollo = new Alimento("Pollo - 250g", 250);
        alimentoList.add(pollo);

        Comida cena = new Comida("Cena", alimentoList);
        comidaList.add(cena);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(cena.getNombre()).child(alimento.getNombre()).setValue(alimento);

        return comidaList;
    }

    private int randomBgColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
