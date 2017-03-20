package br.com.marciopaulo.alunos.helpers;

import java.util.concurrent.TimeUnit;

import br.com.marciopaulo.alunos.util.Constantes;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marci on 19/03/2017.
 */

public class RetrofitHelper {

    public static Retrofit Get(){
        return new Retrofit
                .Builder()
                .baseUrl(Constantes.BASE_URL)
                .client(GetHTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient GetHTTPClient(){

        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
