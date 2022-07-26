package com.example.zerozlauncher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class KeyboardRecyclerViewAdapter extends RecyclerView.Adapter<KeyboardRecyclerViewAdapter.ViewHolder> {
    private List<SingleApp> installedApps = new ArrayList<>();
    private List<SingleApp> filteredAppsList = new ArrayList<>();
    private List<String> characterList = new ArrayList<>();
    private AppsRecyclerViewAdapter recyclerviewAppsAdapter;
    private int itemSize;

    public KeyboardRecyclerViewAdapter(List<SingleApp> installedApps,List<SingleApp> filteredAppsList,AppsRecyclerViewAdapter recyclerviewAppsAdapter,String[] characterList, int itemSize) {
        this.installedApps = installedApps;
        this.filteredAppsList = filteredAppsList;
        this.recyclerviewAppsAdapter = recyclerviewAppsAdapter;
        this.characterList = Arrays.asList(characterList);
        this.itemSize = itemSize;
    }

    @NonNull
    @Override
    public KeyboardRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.recycler_view_item_keyboard_key,parent,false);
        listItem.getLayoutParams().width = itemSize;
        listItem.getLayoutParams().height = itemSize;
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyboardRecyclerViewAdapter.ViewHolder holder, int position) {
        String currentItem = characterList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filteredAppsList.clear();
                for (SingleApp singleApp : installedApps){
//                 if (singleApp.appName.contains(currentItem)){
                 if ( Pattern.compile(Pattern.quote(currentItem), Pattern.CASE_INSENSITIVE).matcher(singleApp.appName).find()){
                    filteredAppsList.add(singleApp);
                 }
                }
                recyclerviewAppsAdapter.notifyDataSetChanged();
            }
        });
        holder.tvCharacter.setText(currentItem);

    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCharacter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCharacter = itemView.findViewById(R.id.tvCharater);
        }
    }
}
