package vidal.sergi.getfit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vidal.sergi.getfit.Objetos.Rutina;

public class RutinasListAdapter extends RecyclerView.Adapter<RutinasListAdapter.ViewHolder> {

    private List<Rutina> nombreList;

    RutinasListAdapter (List<Rutina> nombreList){
        this.nombreList = nombreList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreRutina;

        public ViewHolder(View itemVIew){
            super(itemVIew);
            tvNombreRutina = itemVIew.findViewById(R.id.tvNombreRutina);
        }
    }

    //Al crear el ViewHolder, inflar el layout Player
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rutina, parent, false));
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreRutina.setText(nombreList.get(position).getNombre());
    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return nombreList.size();
    }
}
