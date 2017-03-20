package br.com.marciopaulo.alunos.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.marciopaulo.alunos.model.Usuario;

/**
 * Created by marci on 19/03/2017.
 */

public class UsuarioDao {

    private SQLiteDatabase db;
    public static String createTable(){
        return  "CREATE TABLE "+ Usuario.NOME_TABELA +" ("
                + Usuario.COLUNA_ID +" INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + Usuario.COLUNA_LOGIN + " TEXT, "
                + Usuario.COLUNA_SENHA + " TEXT); ";
    }

    public void adicionar(Usuario usuario){
        db = DatabaseManager.getInstance().openDatabase(true);
        ContentValues dados = new ContentValues();
        dados.put(Usuario.COLUNA_LOGIN,usuario.getLogin());
        dados.put(Usuario.COLUNA_SENHA,usuario.getSenha());
        db.insert(usuario.NOME_TABELA,null, dados);
        DatabaseManager.getInstance().closeDatabase();
    }

    public boolean buscar(String usuario, String senha){
        Boolean logado= false;
        Cursor cursor;
        String[] campos = {Usuario.COLUNA_LOGIN,Usuario.COLUNA_SENHA};
        String where = Usuario.COLUNA_LOGIN + " = '" +usuario +"' AND "+ Usuario.COLUNA_SENHA + " = '" +senha +"'";

        db = DatabaseManager.getInstance().openDatabase(true);
        cursor = db.query(Usuario.NOME_TABELA, campos, where, null, null, null, null, null);

        logado = cursor.getCount() > 0;

        DatabaseManager.getInstance().closeDatabase();

        return  logado;
    }


}
