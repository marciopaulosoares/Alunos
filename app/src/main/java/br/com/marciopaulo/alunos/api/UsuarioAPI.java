package br.com.marciopaulo.alunos.api;

import br.com.marciopaulo.alunos.model.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marci on 19/03/2017.
 */

public interface UsuarioAPI {
    @GET("/v2/{parametro}")
    Call<Usuario> buscar(@Path("parametro") String parametro);
}
