package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Rutina;
import vidal.sergi.getfit.Objetos.Usuario;

public class RutinasListAdapter extends RecyclerView.Adapter<RutinasListAdapter.ViewHolder> {

    private List<Rutina> rutinaList;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.USERS);

    RutinasListAdapter (List<Rutina> rutinaList){
        super();
        this.rutinaList = rutinaList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreRutina;
        FrameLayout frameLayout;
        FloatingActionButton btnIdRutina;

        public ViewHolder(final View itemVIew) {
            super(itemVIew);
            tvNombreRutina = itemVIew.findViewById(R.id.tvNombreRutina);
            frameLayout = itemVIew.findViewById(R.id.frameLayoutRutinas);
            btnIdRutina = itemVIew.findViewById(R.id.btnIdRutina);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleRutinaActivity.class);
                    intent.putExtra("nombreRutina", tvNombreRutina.getText());
                    context.startActivity(intent);
                }
            });

            btnIdRutina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnIdRutina.setRippleColor(Color.RED);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String email = user.getEmail();
                    usersRef.child(email.split("@")[0]).child("idRutina").setValue(String.valueOf(btnIdRutina.getTag()));
                    Toast.makeText(itemVIew.getContext(), "Ahora sigues esta rutina.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //Al crear el ViewHolder, inflar el layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rutina, parent, false);
        return new ViewHolder(view);
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreRutina.setText(rutinaList.get(position).getNombre());
        holder.frameLayout.setBackgroundResource(rutinaList.get(position).getImg());
        holder.btnIdRutina.setTag(String.valueOf(rutinaList.get(position).getId()));
    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return rutinaList.size();
    }
}
