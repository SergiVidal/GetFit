package vidal.sergi.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LunesActivity extends AppCompatActivity {
    public final String TAG ="LunesActivity";
    RecyclerView rv;
    List<String> diasSemanas;
    LunesAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lunes2);
        Bundle extras2 = getIntent().getExtras();
            String value = extras2.getString("c");
        String value2 = extras2.getString("d");
        LunesAdapter ls= new LunesAdapter();
            rv = findViewById(R.id.recycler);
            rv. setLayoutManager(new LinearLayoutManager(this));
            diasSemanas = new ArrayList<String>();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            adapter = new LunesAdapter(diasSemanas,value,value2);
            adapter.setValor(value);

            adapter.setDia(value2);
            rv.setAdapter(adapter);
            database.getReference().child("Seguimiento/Semana1/Lunes").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                     diasSemanas.removeAll(diasSemanas);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Log.d(TAG, "onDataChange: VALOR SNAPSHOT "+snapshot.getKey());
                        diasSemanas.add(snapshot.getKey());
                    }
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation2);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(LunesActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(LunesActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(LunesActivity.this, DietasActivity.class);
                        startActivity(intent);;
                        break;
                }
                return true;
            }
        });

        }


    }


