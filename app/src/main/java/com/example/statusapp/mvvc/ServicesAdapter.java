package com.example.statusapp.mvvc;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusapp.R;
import com.example.statusapp.db.model.ServiceWithTags;
import com.example.statusapp.db.model.UserTagEntity;

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

        ServiceWithTags e = services.get(position);
        holder.name.setText(e.getService().getName());
        List<UserTagEntity> tags = e.getTags();

        int p = e.getService().getPassing();
        int c = e.getService().getFailing();
        int w = e.getService().getWarning();

        if(p>0){
            TextView passing = new TextView(holder.context);
            passing.setGravity(Gravity.RIGHT);
            passing.setTextSize(20);
            passing.setText(String.valueOf(p));
            passing.setCompoundDrawablesWithIntrinsicBounds(R.drawable.passing, 0, 0, 0);

            holder.layout_checks.addView(passing);
        }

        if(w>0){
            TextView warning = new TextView(holder.context);
            warning.setTextSize(20);
            warning.setGravity(Gravity.RIGHT);
            warning.setText(String.valueOf(w));
            warning.setCompoundDrawablesWithIntrinsicBounds(R.drawable.warning, 0, 0, 0);

            holder.layout_checks.addView(warning);
        }

        if(c>0){
            TextView failing = new TextView(holder.context);
            failing.setTextSize(20);
            failing.setGravity(Gravity.RIGHT);
            failing.setText(String.valueOf(c));
            failing.setCompoundDrawablesWithIntrinsicBounds(R.drawable.critical, 0, 0, 0);

            holder.layout_checks.addView(failing);
        }

        if(c==0 && p==0 && w==0){
            // no checks

            TextView nochecks = new TextView(holder.context);
            nochecks.setText(R.string.nochecks);
            nochecks.setTextSize(20);
            nochecks.setGravity(Gravity.RIGHT);
            nochecks.setBackgroundResource(R.color.cardview_dark_background);
            nochecks.setPadding(5,5,5,5);
            nochecks.setTextColor(Color.parseColor("#ffffff"));

            holder.layout_checks.addView(nochecks);
        }

        for(UserTagEntity t: tags){
            TextView tv = new TextView(holder.context);
            tv.setText(t.getName());
            tv.setTextSize(20);
            tv.setGravity(Gravity.RIGHT);
            tv.setBackgroundColor(Color.parseColor("#17a2b8"));
            tv.setPadding(5,5,5,5);
            tv.setTextColor(Color.parseColor("#ffffff"));

            holder.layout_tags.addView(tv);
        }
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
        LinearLayout layout_tags;
        LinearLayout layout_checks;
        Context context;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            context =itemView.getContext();

            name = itemView.findViewById(R.id.name);
            layout_checks = itemView.findViewById(R.id.layout_checks);
            layout_tags = itemView.findViewById(R.id.layout_tags);
        }
    }
}
