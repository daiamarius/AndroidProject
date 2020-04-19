package com.example.statusapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusapp.R;
import com.example.statusapp.db.modelROOM.ServiceWithTags;
import com.example.statusapp.modelAPI.service.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{
    private final String TAG = "ServicesAdapter";
    private List<ServiceWithTags> services;
    private Context context;

    public ServicesAdapter(Context context,List<ServiceWithTags> services) {
        this.services = services;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_serviceitem,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.name.setText(services.get(position).getService().getName());
        holder.tags.setText(services.get(position).getTags().toString());
        holder.healthchecks.setText(String.
                valueOf(services.get(position).getService().getPassing()));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void setServices(List<ServiceWithTags> services){
        this.services = services;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView tags;
        TextView healthchecks;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tags = itemView.findViewById(R.id.tags);
            healthchecks = itemView.findViewById(R.id.healthchecks);
        }
    }
}
