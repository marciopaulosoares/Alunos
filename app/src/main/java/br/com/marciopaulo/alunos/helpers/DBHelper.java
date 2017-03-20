package br.com.marciopaulo.alunos.helpers;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.marciopaulo.alunos.App;
import br.com.marciopaulo.alunos.dao.AlunoDao;
import br.com.marciopaulo.alunos.dao.UsuarioDao;
import br.com.marciopaulo.alunos.model.Usuario;

/**
 * Created by marci on 19/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String NOME_BANCO="alunos.db";
    public static int VERSAO_BANCO=1;
    private static App instance;

    public DBHelper() {

        super(App.getContext(), NOME_BANCO, null, VERSAO_BANCO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UsuarioDao.createTable());
        db.execSQL(AlunoDao.createTable());

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
