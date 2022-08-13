package com.example.zerozlauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerviewNumbers;
    private RecyclerView recyclerviewCharacters;
    private RecyclerView recyclerViewApps;
    private KeyboardRecyclerViewAdapter recyclerviewNumbersAdapter;
    private KeyboardRecyclerViewAdapter recyclerviewCharactersAdapter;
    private AppsRecyclerViewAdapter recyclerviewAppsAdapter;
    private RelativeLayout recyclerViewItem;
    private final String[] recyclerviewItemsNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",};
    private final String[] recyclerViewItemCharacters = {"#","A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R","S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int screenWidth;
    String TAG ="jithu";
    private List<ResolveInfo> pkgAppsList = new ArrayList<>();
    private List<SingleApp> installedAppsList = new ArrayList<>();
    private List<SingleApp> filteredAppsList = new ArrayList<>();
    private ConstraintLayout timeWidgetLayout;
    private TextView tvTimeWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIdsToViews();
        setTimeToTimeWidget();
        getScreenWidth();
        setUpRecyclerViews();
        getInstalledAppDetails();

    }

    private void setTimeToTimeWidget() {
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        Log.i(TAG, "setTimeToTimeWidget: "+currentTime);
        tvTimeWidget.setText(currentTime);

    }

    private void setUpRecyclerViews() {

        recyclerviewAppsAdapter = new AppsRecyclerViewAdapter(filteredAppsList);
        recyclerViewApps.setLayoutManager(new GridLayoutManager(this,5));
        recyclerViewApps.setAdapter(recyclerviewAppsAdapter);

        recyclerviewNumbersAdapter = new KeyboardRecyclerViewAdapter(installedAppsList,filteredAppsList,recyclerviewAppsAdapter,recyclerviewItemsNumbers, screenWidth/10);
        recyclerviewNumbers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerviewNumbers.setAdapter(recyclerviewNumbersAdapter);

        recyclerviewCharactersAdapter = new KeyboardRecyclerViewAdapter(installedAppsList,filteredAppsList,recyclerviewAppsAdapter,recyclerViewItemCharacters, screenWidth/9);
        recyclerviewCharacters.setLayoutManager(new GridLayoutManager(this,9));
        recyclerviewCharacters.setAdapter(recyclerviewCharactersAdapter);

    }

    private void getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

    private void setIdsToViews() {
        recyclerviewNumbers = findViewById(R.id.recyclerviewNumbers);
        recyclerviewCharacters = findViewById(R.id.recyclervieCharacters);
        recyclerViewApps = findViewById(R.id.recyclerViewApps);
        recyclerViewItem = findViewById(R.id.recyclerViewItem);
        timeWidgetLayout = findViewById(R.id.timeWidgetLayout);
        tvTimeWidget = findViewById(R.id.tvTimeWidget);
    }

    private void getInstalledAppDetails() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppsList = getApplicationContext().getPackageManager().queryIntentActivities( mainIntent, 0);
        for (ResolveInfo resolveInfo : pkgAppsList){
            installedAppsList.add(new SingleApp(resolveInfo.loadLabel(this.getPackageManager()).toString(),
                    resolveInfo.loadIcon(getPackageManager()),
                    resolveInfo.activityInfo.packageName));
        }
        recyclerviewNumbersAdapter.notifyDataSetChanged();
        recyclerviewCharactersAdapter.notifyDataSetChanged();
        recyclerviewAppsAdapter.notifyDataSetChanged();
    }

}