package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder>  {
    private static final String TAG ="RecyclerViewAdapter";
    Intent intent;

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mcontext;
    List<Integer> diasSemanas;
    public int si;


    public AdapterHome(ArrayList<String> mImageNames, ArrayList<String> mImages, Context context) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mcontext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent. getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        diasSemanas = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        database.getReference().child("Totales/Semana2/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: VALOR DIASADAPT "+snapshot.getValue().toString());
                    String e=snapshot.getValue().toString();
                    Pattern pattern = Pattern.compile("true");
                    Matcher matcher = pattern.matcher(e);

                    while (matcher.find()){
                        count++;

                    }
                    switch (position){
                        case 1: if (count>=5 && holder.imageName.getText().equals("Semana2")){
                            holder.imageName.setClickable(false);
                            holder.image.setClickable(false);
                            holder.imageName.setText("Completado");
                        }break;

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference().child("Totales/Semana1/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: VALOR DIASADAPT "+snapshot.getValue().toString());
                    String e=snapshot.getValue().toString();
                    Pattern pattern = Pattern.compile("true");
                    Matcher matcher = pattern.matcher(e);

                    while (matcher.find()){
                        count++;
                    }
                    switch (position){

                        case 0: if (count>=5 && holder.imageName.getText().equals("Semana1")){
                            holder.imageName.setText("Completado");
                        }else{
                            holder.imageName.setText("Semana1");

                        }break;

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference().child("Totales/Semana3/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: VALOR DIASADAPT "+snapshot.getValue().toString());
                    String e=snapshot.getValue().toString();
                    Pattern pattern = Pattern.compile("true");
                    Matcher matcher = pattern.matcher(e);

                    while (matcher.find()){
                        count++;
                    }
                    switch (position){

                        case 2: if (count>=5 && holder.imageName.getText().equals("Semana3")){
                            holder.imageName.setText("Completado");
                        }break;

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference().child("Totales/Semana4/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String e=snapshot.getValue().toString();
                    Pattern pattern = Pattern.compile("true");
                    Matcher matcher = pattern.matcher(e);

                    while (matcher.find()){
                        count++;
                    }
                    switch (position){
                        case 3: if (count>=5 && holder.imageName.getText().equals("Semana4")){
                            holder.imageName.setText("Completado");
                        }break;

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d(TAG, "onBindViewHolder: called");

        Glide.with(mcontext).asBitmap().load(mImages.get(position)).into(holder.image);
        holder.imageName.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        Intent intent = new Intent(mcontext,DiasActivity.class);
                        intent.putExtra("semana1", mImageNames.get(position));
                        mcontext.startActivity(intent);
                    case 1:
                        Intent intent2 = new Intent(mcontext,DiasActivity.class);
                        intent2.putExtra("semana1", mImageNames.get(position));
                        mcontext.startActivity(intent2);

                    case 2:
                        Intent intent3 = new Intent(mcontext,DiasActivity.class);
                        intent3.putExtra("semana1", mImageNames.get(position));
                        mcontext.startActivity(intent3);

                    case 3:
                        Intent intent4 = new Intent(mcontext,DiasActivity.class);
                        intent4.putExtra("semana1", mImageNames.get(position));
                        mcontext.startActivity(intent4);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
