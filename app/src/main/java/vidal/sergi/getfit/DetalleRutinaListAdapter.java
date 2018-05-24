package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import vidal.sergi.getfit.Objetos.Rutina;

public class DetalleRutinaListAdapter extends RecyclerView.Adapter<DetalleRutinaListAdapter.ViewHolder> {

    private List<String> musculosList;
    private List<Integer> colorList;


    DetalleRutinaListAdapter(List<String> musculosList, List<Integer> colorList){
        super();
        this.musculosList = musculosList;
        this.colorList = colorList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreRutina;
        FrameLayout frameLayout;

        public ViewHolder(View itemVIew) {
            super(itemVIew);
            tvNombreRutina = itemVIew.findViewById(R.id.tvNombreRutina);
            frameLayout = itemVIew.findViewById(R.id.frameLayoutRutinas);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleRutinaActivity.class);
                    intent.putExtra("nombreRutina", tvNombreRutina.getText());
                    context.startActivity(intent);
                }
            });
        }
    }

    //Al crear el ViewHolder, inflar el layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle_rutina, parent, false);
        return new ViewHolder(view);
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreRutina.setText(musculosList.get(position));
        holder.frameLayout.setBackgroundColor(colorList.get(position));
    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return musculosList.size();
    }
}
