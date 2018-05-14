package vidal.sergi.getfit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class RutinasListAdapter extends RecyclerView.Adapter<RutinasListAdapter.ViewHolder> {

    public RutinasListAdapter() {
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder{
        Button btnMostrarRutinas;
        TextView tvNombreRutina, tvSeguidores;

        public ViewHolder(View itemVIew){
            super(itemVIew);
            btnMostrarRutinas = itemVIew.findViewById(R.id.btnMostrarRutina);
            tvNombreRutina = itemVIew.findViewById(R.id.tvNombreRutina);
            tvSeguidores = itemVIew.findViewById(R.id.tvSeguidores);
        }
    }

    //Al crear el ViewHolder, inflar el layout Player
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rutina, parent, false);
        return new ViewHolder(view);
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreRutina.setText("Rutina");
        holder.tvSeguidores.setText("Rutina");
//        holder.btnMostrarRutinas;

    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return 3;
    }
}
