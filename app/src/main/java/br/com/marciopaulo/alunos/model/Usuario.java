package br.com.marciopaulo.alunos.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marci on 19/03/2017.
 */

public class Usuario {


    public final static String NOME_TABELA       ="usuarios";
    public final static String COLUNA_ID         ="_id";
    public final static String COLUNA_LOGIN      ="login";
    public final static String COLUNA_SENHA      ="senha";


    @SerializedName("usuario")
    String login;
    String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
