package br.com.marciopaulo.alunos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.marciopaulo.alunos.api.UsuarioAPI;
import br.com.marciopaulo.alunos.dao.UsuarioDao;
import br.com.marciopaulo.alunos.helpers.RetrofitHelper;
import br.com.marciopaulo.alunos.model.Usuario;
import br.com.marciopaulo.alunos.util.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashScreenActivity extends AppCompatActivity implements Callback<Usuario> {

    private final int SPLASH_DISPLAY_LENGTH = 3500;
    ProgressDialog progressDialog;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        retrofit= RetrofitHelper.Get();
        UsuarioAPI api = retrofit.create(UsuarioAPI.class);
        Call<Usuario> call = api.buscar(Constantes.END_POINT_USUARIO);
        call.enqueue(this);
        carregar();
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacao);
        anim.reset();

        ImageView iv = (ImageView) findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }



    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        UsuarioDao usuarioDao= new UsuarioDao();
        if(!usuarioDao.buscar(response.body().getLogin(),response.body().getSenha())){
            usuarioDao.adicionar(response.body());
        }
    }

    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {

    }
}
