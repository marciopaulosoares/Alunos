package br.com.marciopaulo.alunos;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import br.com.marciopaulo.alunos.dao.DatabaseManager;
import br.com.marciopaulo.alunos.helpers.DBHelper;

/**
 * Created by marci on 19/03/2017.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        DatabaseManager.initializeInstance(new DBHelper());
    }

    public  static Context getContext(){
        return  instance;
    }

    public  App(){
        instance = this;
    }
}
