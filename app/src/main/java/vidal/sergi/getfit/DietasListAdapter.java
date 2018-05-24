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

import vidal.sergi.getfit.Objetos.Dieta;

public class DietasListAdapter extends RecyclerView.Adapter<DietasListAdapter.ViewHolder> {

    private List<Dieta> dietaList;


    DietasListAdapter(List<Dieta> dietaList){
        super();
        this.dietaList = dietaList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreDieta;
        FrameLayout frameLayout;


        public ViewHolder(View itemVIew){
            super(itemVIew);
            tvNombreDieta = itemVIew.findViewById(R.id.tvNombreDieta);
            frameLayout = itemVIew.findViewById(R.id.frameLayoutDietas);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleDietaActivity.class);
                    intent.putExtra("nombreDieta", tvNombreDieta.getText());
                    context.startActivity(intent);
                }
            });

        }
    }

    //Al crear el ViewHolder, inflar el layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dieta, parent, false);
        return new ViewHolder(view);
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreDieta.setText(dietaList.get(position).getNombre());
        holder.frameLayout.setBackgroundResource(dietaList.get(position).getImg());

    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return dietaList.size();
    }
}
