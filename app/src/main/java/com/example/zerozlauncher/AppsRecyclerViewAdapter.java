package com.example.zerozlauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppsRecyclerViewAdapter extends RecyclerView.Adapter<AppsRecyclerViewAdapter.ViewHolder> {

    List<SingleApp> filteredAppsList;

    public AppsRecyclerViewAdapter(List<SingleApp> filteredAppsList) {
        this.filteredAppsList = filteredAppsList;
    }

    @NonNull
    @Override
    public AppsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View singleAppLayout = inflater.inflate(R.layout.recycler_view_item_installed_app,parent,false);
        return new ViewHolder(singleAppLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.ivAppIcon.setImageDrawable(filteredAppsList.get(position).appIcon);
//        holder.tvAppName.setText(filteredAppsList.get(position).appName);

        holder.layoutSingleApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.layoutSingleApp.getContext();
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(filteredAppsList.get(holder.getAdapterPosition()).appPackageName));
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredAppsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layoutSingleApp;
        public ImageView ivAppIcon;
//        public TextView tvAppName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layoutSingleApp = itemView.findViewById(R.id.layoutSingleApp);
            this.ivAppIcon = itemView.findViewById(R.id.ivAppIcon);
//            this.tvAppName = itemView.findViewById(R.id.tvAppName);
        }
    }
}
