package br.com.marciopaulo.alunos.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.marciopaulo.alunos.model.Aluno;

/**
 * Created by marci on 19/03/2017.
 */

public class AlunoDao {



    private SQLiteDatabase db;
    public  AlunoDao(){
        db = DatabaseManager.getInstance().openDatabase(true);
    }


    public static String createTable(){
        return  "CREATE TABLE "+ Aluno.NOME_TABELA +" ("
                + Aluno.COLUNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Aluno.COLUNA_NOME + " TEXT); ";
    }

    public void atualizar(Aluno aluno){
        ContentValues dados = new ContentValues();
        dados.put(Aluno.COLUNA_NOME,aluno.getNome());

        String where=Aluno.COLUNA_ID + "=?";
        String[] whereAgs =new String[]{String.valueOf(aluno.getId())};
        db.update(Aluno.NOME_TABELA,dados,where,whereAgs);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void adicionar(Aluno aluno){
        ContentValues dados = new ContentValues();
        dados.put(Aluno.COLUNA_NOME,aluno.getNome());
        db.insert(Aluno.NOME_TABELA,null,dados);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void excluir(long id){
        String where=Aluno.COLUNA_ID + "=?";
        String[] whereAgs =new String[]{String.valueOf(id)};
        db.delete(Aluno.NOME_TABELA,where,whereAgs);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();

        Cursor cursor;
        String[] campos = {Aluno.COLUNA_ID, Aluno.COLUNA_NOME};
        String orderBy= Aluno.COLUNA_NOME + " ASC ";

        cursor = db.query(Aluno.NOME_TABELA, campos, null, null, null, null, orderBy, null);

        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    Aluno aluno = new Aluno();

                    aluno.setId(cursor.getInt(cursor.getColumnIndex(Aluno.COLUNA_ID)));
                    aluno.setNome(cursor.getString(cursor.getColumnIndex(Aluno.COLUNA_NOME)));
                    alunos.add(aluno);
                } while(cursor.moveToNext());
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return alunos;
    }
}
