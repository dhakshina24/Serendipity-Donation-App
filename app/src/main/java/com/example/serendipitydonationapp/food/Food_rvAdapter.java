package com.example.serendipitydonationapp.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.serendipitydonationapp.R;

import java.util.ArrayList;

public class Food_rvAdapter extends RecyclerView.Adapter<Food_rvAdapter.FoodViewHolder> {

    ArrayList<Food_org> food_org = new ArrayList<>();
    private Context food_context;

    public Food_rvAdapter (Context food_context){
        this.food_context = food_context;
    }

    public Food_rvAdapter() {

    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_org_list, parent, false);
        FoodViewHolder holder = new FoodViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.food_org_name_tv.setText(food_org.get(position).getName());
        holder.food_org_site_tv.setText(food_org.get(position).getSiteURL());
        holder.food_org_list_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(food_context, food_org.get(position).getName() + " selected", Toast.LENGTH_SHORT).show();

                gotoUrl(food_org.get(position).getSiteURL());
            }
        });

        Glide.with(food_context)
                .asBitmap()
                .load(food_org.get(position).getImgURL())
                .into(holder.food_org_img);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        food_context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return food_org.size();
    }

    public void setFood_org(ArrayList<Food_org> food_org) {
        this.food_org = food_org;
        notifyDataSetChanged();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private TextView food_org_name_tv, food_org_site_tv;
        private ImageView food_org_img;
        private CardView food_org_list_parent;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            food_org_name_tv = itemView.findViewById(R.id.food_org_name_tv);
            food_org_site_tv = itemView.findViewById(R.id.food_org_site_tv);
            food_org_img = itemView.findViewById(R.id.food_org_img);
            food_org_list_parent = itemView.findViewById(R.id.food_org_list_parent);
        }
    }
}
