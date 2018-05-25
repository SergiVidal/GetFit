package vidal.sergi.getfit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import vidal.sergi.getfit.Objetos.Alimento;

public class AlimentosListAdaptar extends BaseAdapter {

    ValueEventListener context;
    List<Alimento> alimentoList;
    TextView tvNombreAlimento;

    public AlimentosListAdaptar(ValueEventListener context, List<Alimento> alimentoList) {
        this.context = context;
        this.alimentoList = alimentoList;
    }

    @Override
    public int getCount() {
        return alimentoList.size();
    }

    @Override
    public Alimento getItem(int position) {
        return alimentoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_dietas, parent, false);

        }

        tvNombreAlimento = view.findViewById(R.id.tvNombreAlimento);


        Alimento alimento = getItem(position);
        tvNombreAlimento.setText(alimento.getNombre());

        return view;
    }
}

