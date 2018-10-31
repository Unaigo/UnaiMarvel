package com.marvel.unai.unaimarvel.Models.AppModels;

import java.util.List;

public class HeroModel {

    private String name,imageProfile,detalleLink,wikiLink, comicLink,description;

    private List<ItemList> itemsComicsList = null,itemsEvenotsList = null;

    public void setName(String name){this.name = name;};

    public String getName(){return this.name;}

    public String getImageProfile(){return this.imageProfile;};

    public  String setImageProfile(String imageProfile){return this.imageProfile=imageProfile;};

    public String getDetalleLink() {
        return detalleLink;
    }

    public void setDetalleLink(String detalleLink) {
        this.detalleLink = detalleLink;
    }

    public String getComicLink() {
        return comicLink;
    }

    public void setComicLink(String comicLink) {
        this.comicLink = comicLink;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<ItemList> getItemsEvenotsList() {
        return itemsEvenotsList;
    }

    public void setItemsEvenotsList(List<ItemList> itemsEvenotsList) {
        this.itemsEvenotsList = itemsEvenotsList;
    }

    public List<ItemList> getItemsComicsList() {
        return itemsComicsList;
    }

    public void setItemsComicsList(List<ItemList> itemsComicsList) {
        this.itemsComicsList = itemsComicsList;
    }
}
