package com.neyogiry.android.sample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DateActivity extends AppCompatActivity {

    private View progressBar;
    private RecyclerView rvDates;
    private FloatingActionButton fab;

    private DateViewModel mViewModel;
    private DateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        progressBar = findViewById(R.id.progressBar);
        rvDates = findViewById(R.id.rv_dates);
        fab = findViewById(R.id.fab_add_date);

        adapter = new DateAdapter(new ArrayList<String>());
        rvDates.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvDates.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addDate();
            }
        });

        mViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        mViewModel.getLoadingStatus().observe(this, new LoadingObserver());
        mViewModel.getMessage().observe(this, messageObserver);
        mViewModel.getDateList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> list) {
                if(list == null) return;
                adapter.updateList((ArrayList<String>) list);
            }
        });
    }

    //Observer
    private class LoadingObserver implements Observer<Boolean>{

        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if(isLoading == null) return;
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }

    }

    private Observer<String> messageObserver = new Observer<String>(){
        @Override
        public void onChanged(@Nullable String message) {
            if(message == null) return;
            Toast.makeText(DateActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

}
