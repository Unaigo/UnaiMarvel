package com.marvel.unai.unaimarvel.Logic;

import com.marvel.unai.unaimarvel.Logic.MyModule;
import com.marvel.unai.unaimarvel.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {

    void inject(MainActivity data);

    void inject(HeroFactory data);

}
