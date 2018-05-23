package vidal.sergi.getfit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreRutina;
        FrameLayout mBackground;


        public ViewHolder(View itemVIew){
            super(itemVIew);
            tvNombreRutina = itemVIew.findViewById(R.id.tvNombreRutina);
            mBackground = itemVIew.findViewById(R.id.asd);

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
        holder.mBackground.setBackgroundColor(rutinaList.get(position).getBgColor());

    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return rutinaList.size();
    }
}
