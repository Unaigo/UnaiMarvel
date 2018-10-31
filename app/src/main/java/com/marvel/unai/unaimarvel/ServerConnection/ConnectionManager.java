package com.marvel.unai.unaimarvel.ServerConnection;

import com.himanshurawat.hasher.Hasher;

public class ConnectionManager implements InterfaceConnection {

    public void GetHeroData()
    {
        Hasher ob = new Hasher();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash= ob.hash(timestamp+"978985e55e35edf030a37de670b4ea650cf2e580"+"a97e89847d934d0d551f6252cb4be16f",Hasher.MD5);
        String url = "https://gateway.marvel.com/v1/public/characters/1009368?"+"ts="+timestamp+"&apikey=a97e89847d934d0d551f6252cb4be16f&hash="+hash;
        new RequestTask().execute(url);
    }

    public void GetComicsList(String comicId)
    {
        Hasher ob = new Hasher();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash= ob.hash(timestamp+"978985e55e35edf030a37de670b4ea650cf2e580"+"a97e89847d934d0d551f6252cb4be16f",Hasher.MD5);
        String url = comicId+"?ts="+timestamp+"&apikey=a97e89847d934d0d551f6252cb4be16f&hash="+hash;
        new ComicTask().execute(url);
    }

    public void GetEventsList(String eventId)
    {
        Hasher ob = new Hasher();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash= ob.hash(timestamp+"978985e55e35edf030a37de670b4ea650cf2e580"+"a97e89847d934d0d551f6252cb4be16f",Hasher.MD5);
        String url = eventId+"?ts="+timestamp+"&apikey=a97e89847d934d0d551f6252cb4be16f&hash="+hash;
        new EventsTask().execute(url);
    }

}
