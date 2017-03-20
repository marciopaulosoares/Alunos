package br.com.marciopaulo.alunos.model;

/**
 * Created by marci on 19/03/2017.
 */

public class Aluno {


    public final static String NOME_TABELA       ="alunos";
    public final static String COLUNA_ID         ="_id";
    public final static String COLUNA_NOME       ="nome";



    private long id;
    private String nome;

    public Aluno(){

    }

    public Aluno(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
