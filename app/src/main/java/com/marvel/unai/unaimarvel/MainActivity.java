package com.marvel.unai.unaimarvel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marvel.unai.unaimarvel.Logic.HeroFactory;
import com.marvel.unai.unaimarvel.Models.AppModels.HeroModel;
import com.marvel.unai.unaimarvel.Models.AppModels.ItemList;
import com.marvel.unai.unaimarvel.ServerConnection.InterfaceConnection;
import com.marvel.unai.unaimarvel.Views.ItemListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private View view;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private ImageView profileImageView;
    private TextView profileTextView,descriptionTextView;
    private Button detalleButton,wikiButton,comicButton;
    private LinearLayout linearLayout;
    private ProgressBar progressBar,progressBarRecyclerView;
    private HeroModel heroModel;
    private ItemListAdapter itemListAdapter;

    @Inject InterfaceConnection connectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        this.view = findViewById(R.id.contentView);
        ((MyApplication) getApplication())
                .getMyComponent()
                .inject(this);
        this.tabLayout = findViewById(R.id.tabLayout);
        this.recyclerView = findViewById(R.id.recyclerView);
        this.profileImageView = findViewById(R.id.profileImageView);
        this.profileTextView = findViewById(R.id.profileTextView);
        this.detalleButton = findViewById(R.id.detalleButton);
        this.wikiButton = findViewById(R.id.wikiButton);
        this.comicButton = findViewById(R.id.comicButton);
        this.linearLayout=findViewById(R.id.linearLayout);
        this.descriptionTextView=findViewById(R.id.descriptionTextView);
        this.progressBar=findViewById(R.id.progressBar);
        this.progressBarRecyclerView=findViewById(R.id.progressBarRecyclerView);

        this.initTabs();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(mLayoutManager);

        this.initServerCalls();


        //I hide all the view to start timer to show splash two seconds
        this.view.setVisibility(View.INVISIBLE);
        //After two seconds I will show all the View Again
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               setVisibleView();
            }
        }, 2000);

    }

    public void UpdateInformationHero(HeroModel heroModel)
    {
        this.linearLayout.setVisibility(View.VISIBLE);
        this.progressBar.setVisibility(View.GONE);
        this.heroModel = heroModel;
        this.profileTextView.setText(this.heroModel.getName());
        this.descriptionTextView.setText(this.heroModel.getDescription());
        Glide.with(this).load(this.heroModel.getImageProfile()).into((this.profileImageView));
        if(this.tabLayout.getSelectedTabPosition()==0)
        {
            if(heroModel.getItemsComicsList()!=null) {
                this.itemListAdapter = new ItemListAdapter(this.heroModel.getItemsComicsList(),this);
                recyclerView.setAdapter(this.itemListAdapter);
                this.progressBarRecyclerView.setVisibility(View.GONE);
            }
            else
            {
                itemListAdapter = new ItemListAdapter(new ArrayList<ItemList>(),this);
                recyclerView.setAdapter(itemListAdapter);
                this.progressBarRecyclerView.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            if(heroModel.getItemsEvenotsList()!=null) {
                this.itemListAdapter = new ItemListAdapter(this.heroModel.getItemsEvenotsList(),this);
                recyclerView.setAdapter(this.itemListAdapter);
                this.progressBarRecyclerView.setVisibility(View.GONE);
            }
            else
            {
                itemListAdapter = new ItemListAdapter(new ArrayList<ItemList>(),this);
                recyclerView.setAdapter(itemListAdapter);
                this.progressBarRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onClickButton(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.detalleButton:
                initWebView(this.heroModel.getDetalleLink());
                break;
            case R.id.wikiButton:
                initWebView(this.heroModel.getWikiLink());
                break;
            case R.id.comicButton:
                initWebView(this.heroModel.getComicLink());
                break;
        }
    }

    private void initWebView(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        MainActivity.this.startActivity(intent);
    }

    private void initTabs()
    {
        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("COMICS");

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("EVENTOS");

        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:
                        if(heroModel!=null&&heroModel.getItemsComicsList()!=null) {
                            itemListAdapter = new ItemListAdapter(heroModel.getItemsComicsList(),getApplicationContext());
                            recyclerView.setAdapter(itemListAdapter);
                            progressBarRecyclerView.setVisibility(View.GONE);
                        }
                        else
                        {
                            itemListAdapter = new ItemListAdapter(new ArrayList<ItemList>(),getApplicationContext());
                            recyclerView.setAdapter(itemListAdapter);
                            progressBarRecyclerView.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 1:
                        if(heroModel.getItemsEvenotsList()!=null) {
                            itemListAdapter = new ItemListAdapter(heroModel.getItemsEvenotsList(),getApplicationContext());
                            recyclerView.setAdapter(itemListAdapter);
                            progressBarRecyclerView.setVisibility(View.GONE);
                        }
                        else
                        {
                            itemListAdapter = new ItemListAdapter(new ArrayList<ItemList>(),getApplicationContext());
                            recyclerView.setAdapter(itemListAdapter);
                            progressBarRecyclerView.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initServerCalls()
    {
        HeroFactory.getInstance().setMainActivity(this);
        //HeroFactory.getInstance().setConnectionManager(this.connectionManager);
        this.connectionManager.GetHeroData();
    }

    //Show all the view
    private void setVisibleView()
    {
        this.view.setVisibility(View.VISIBLE);
        if(this.heroModel!=null)
        {
            this.linearLayout.setVisibility(View.VISIBLE);
            this.progressBar.setVisibility(View.GONE);
            this.progressBarRecyclerView.setVisibility(View.VISIBLE);
        }
        else
        {
            this.linearLayout.setVisibility(View.GONE);
            this.progressBar.setVisibility(View.VISIBLE);
            this.progressBarRecyclerView.setVisibility(View.GONE);
        }
    }
}
