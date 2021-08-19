package com.example.serendipitydonationapp.money;

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
import com.example.serendipitydonationapp.MainActivity;
import com.example.serendipitydonationapp.R;
import com.example.serendipitydonationapp.cloth.ClothActivity;
import com.example.serendipitydonationapp.cloth.Cloth_org;
import com.example.serendipitydonationapp.cloth.Cloth_rvAdapter;

import java.util.ArrayList;

public class Money_rvAdapter extends RecyclerView.Adapter<Money_rvAdapter.MoneyViewHolder> {
    ArrayList<Money_org> money_org = new ArrayList<>();
    private Context money_context;

    public Money_rvAdapter (Context money_context){
        this.money_context = money_context;
    }

    public Money_rvAdapter() {

    }

    @NonNull
    @Override
    public Money_rvAdapter.MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.money_org_list, parent, false);
        Money_rvAdapter.MoneyViewHolder holder = new Money_rvAdapter.MoneyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Money_rvAdapter.MoneyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.money_org_cause_tv.setText(money_org.get(position).getName());
        holder.money_org_list_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(money_context, money_org.get(position).getName() + " selected", Toast.LENGTH_SHORT).show();

                //gotoUrl(money_org.get(position).getImgURL());
            }
        });

        Glide.with(money_context)
                .asBitmap()
                .load(money_org.get(position).getImgURL())
                .into(holder.money_org_cause_img);
    }

    /*private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        money_context.startActivity(i);
    }*/

    @Override
    public int getItemCount() {
        return money_org.size();
    }

    public void setMoney_org(ArrayList<Money_org> money_org) {
        this.money_org = money_org;
        notifyDataSetChanged();
    }

    public class MoneyViewHolder extends RecyclerView.ViewHolder{

        private TextView money_org_cause_tv;
        private ImageView money_org_cause_img;
        private CardView money_org_list_parent;

        public MoneyViewHolder(@NonNull View itemView) {
            super(itemView);

            money_org_cause_tv = itemView.findViewById(R.id.money_org_cause_tv);
            money_org_cause_img = itemView.findViewById(R.id.money_org_cause_img);
            money_org_list_parent = itemView.findViewById(R.id.money_org_list_parent);
        }
    }
}
