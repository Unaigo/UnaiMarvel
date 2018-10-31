package com.marvel.unai.unaimarvel.Logic;

import com.marvel.unai.unaimarvel.MainActivity;
import com.marvel.unai.unaimarvel.Models.AppModels.HeroModel;
import com.marvel.unai.unaimarvel.Models.AppModels.ItemList;
import com.marvel.unai.unaimarvel.Models.ServerModels.Result;
import com.marvel.unai.unaimarvel.Models.ServerModels.ServerDataModel;
import com.marvel.unai.unaimarvel.MyApplication;
import com.marvel.unai.unaimarvel.ServerConnection.InterfaceConnection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HeroFactory
{

    @Inject InterfaceConnection connectionManager;

    private static final HeroFactory instance = new HeroFactory();

    //private constructor to avoid client applications to use constructor
    private HeroFactory(){}

    public static HeroFactory getInstance(){
        return instance;
    }

    private HeroModel heroModel;

    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
    }

    public void FactoryServerModelToAppModel(ServerDataModel serverModel)
    {
        ((MyApplication) mainActivity.getApplication())
                .getMyComponent()
                .inject(this);
        if (serverModel != null) {
            this.heroModel = new HeroModel();
            Result result = serverModel.getData().getResults().get(0);
            this.heroModel.setName(result.getName());
            this.heroModel.setImageProfile(result.getThumbnail().getPath() + "." + result.getThumbnail().getExtension());
            this.heroModel.setComicLink(result.getUrls().get(2).getUrl());
            this.heroModel.setWikiLink(result.getUrls().get(1).getUrl());
            this.heroModel.setDetalleLink(result.getUrls().get(0).getUrl());
            this.heroModel.setDescription(result.getDescription());



            connectionManager.GetComicsList(result.getComics().getCollectionURI());
            connectionManager.GetEventsList(result.getEvents().getCollectionURI());

            this.mainActivity.UpdateInformationHero(this.heroModel);
        }
    }

        public void FactoryComicsServerModelToAppModel(ServerDataModel serverModel)
        {
            List<ItemList> itemsComicsList = new ArrayList<>();

            for (Result item : serverModel.getData().getResults()) {
                ItemList itemList = new ItemList();
                itemList.setTitle(item.getTitle());
                itemList.setImageUrl(item.getThumbnail().getPath()+"."+item.getThumbnail().getExtension());
                itemList.setDescription(item.getDescription());
                itemsComicsList.add(itemList);
            }
            this.heroModel.setItemsComicsList(itemsComicsList);
            this.mainActivity.UpdateInformationHero(this.heroModel);
        }

        public void FactoryEventsServerModelToAppModel(ServerDataModel serverModel)
        {
            List<ItemList> itemsEventosList = new ArrayList<>();
            for (Result item : serverModel.getData().getResults()) {
                ItemList itemList = new ItemList();
                itemList.setTitle(item.getTitle());
                itemList.setImageUrl(item.getThumbnail().getPath()+"."+item.getThumbnail().getExtension());
                itemsEventosList.add(itemList);
            }
            this.heroModel.setItemsEvenotsList(itemsEventosList);
            this.mainActivity.UpdateInformationHero(this.heroModel);
        }
}
