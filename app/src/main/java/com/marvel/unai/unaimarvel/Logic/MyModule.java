package com.marvel.unai.unaimarvel.Logic;

import com.marvel.unai.unaimarvel.ServerConnection.InterfaceConnection;
import com.marvel.unai.unaimarvel.ServerConnection.ConnectionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    @Provides
    @Singleton
    static InterfaceConnection provideInterfaceConnection() {
        return new ConnectionManager();
    }

}