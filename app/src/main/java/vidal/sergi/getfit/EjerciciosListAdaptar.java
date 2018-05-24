package vidal.sergi.getfit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import vidal.sergi.getfit.Objetos.Ejercicio;

public class EjerciciosListAdaptar extends BaseAdapter {

    ValueEventListener context;
    List<Ejercicio> ejerciciosList;
    TextView tvNombreEjercicio, tvSeries, tvRepeticiones, tvDescanso;

    public EjerciciosListAdaptar(ValueEventListener context, List<Ejercicio> ejerciciosList) {
        this.context = context;
        this.ejerciciosList = ejerciciosList;
    }

    @Override
    public int getCount() {
        return ejerciciosList.size();
    }

    @Override
    public Ejercicio getItem(int position) {
        return ejerciciosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_rutinas, parent, false);

        }

        tvNombreEjercicio = view.findViewById(R.id.tvNombreEjercicio);
        tvSeries = view.findViewById(R.id.tvSeries);
        tvRepeticiones = view.findViewById(R.id.tvRepeticiones);
        tvDescanso = view.findViewById(R.id.tvDescanso);

        Ejercicio ejercicio = getItem(position);
        tvNombreEjercicio.setText(ejercicio.getNombre());
        tvSeries.setText("Series: " + String.valueOf(ejercicio.getSeries()));
        tvRepeticiones.setText("Repeticiones: " + String.valueOf(ejercicio.getRepeticiones()));
        tvDescanso.setText("Descanso: " + String.valueOf(ejercicio.getDescanso()+"\""));


        return view;
    }
}

