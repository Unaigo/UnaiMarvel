package com.marvel.unai.unaimarvel;

import android.app.Application;


import com.marvel.unai.unaimarvel.Logic.DaggerMyComponent;
import com.marvel.unai.unaimarvel.Logic.MyComponent;
import com.marvel.unai.unaimarvel.Logic.MyModule;

public class MyApplication extends Application {
    private MyComponent mMyComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyComponent = createMyComponent();
    }

    public MyComponent getMyComponent() {
        return mMyComponent;
    }

    private MyComponent createMyComponent() {
        return DaggerMyComponent
                .builder()
                .myModule(new MyModule())
                .build();
    }

}