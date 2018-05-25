package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import vidal.sergi.getfit.Objetos.Rutina;

public class RutinasListAdapter extends RecyclerView.Adapter<RutinasListAdapter.ViewHolder> {

    private List<Rutina> rutinaList;

    RutinasListAdapter (List<Rutina> rutinaList){
        super();
        this.rutinaList = rutinaList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreRutina;
        FrameLayout frameLayout;
        Button btnIdRutina;

        public ViewHolder(View itemVIew) {
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
//                    intent.putExtra("idRutina", String.valueOf(btnIdRutina.getText()));
                    context.startActivity(intent);
                }
            });

            btnIdRutina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("svm", String.valueOf(btnIdRutina.getText()));
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
        holder.btnIdRutina.setText(String.valueOf(rutinaList.get(position).getId()));

    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return rutinaList.size();
    }
}
