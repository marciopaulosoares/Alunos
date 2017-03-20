package br.com.marciopaulo.alunos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.marciopaulo.alunos.dao.UsuarioDao;
import br.com.marciopaulo.alunos.util.Constantes;

public class LoginActivity extends AppCompatActivity {


    EditText edtLogin;
    EditText edtSenha;
    CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin=(EditText) this.findViewById(R.id.edtLogin);
        edtSenha=(EditText) this.findViewById(R.id.edtSenha);
        cbManterConectado=(CheckBox)this.findViewById(R.id.cbManterConectado);

       if(manterConected()){

           Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
       }
    }

    private boolean manterConected() {

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String usuario = sp.getString(Constantes.USUARIO_SP, null);

        return  usuario!=null;

    }

    private void gravarDadosUsuario(String usuario){

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putString(Constantes.USUARIO_SP, usuario);
        e.commit();
    }


    public void login(View view) {

        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if(login.equals("") || senha.equals("")){
            Toast.makeText(getApplicationContext(),"Login ou senha inválidos",Toast.LENGTH_LONG).show();
            return;
        }

        UsuarioDao usuarioDao = new UsuarioDao();
        if(usuarioDao.buscar(login,senha)){

            if(cbManterConectado.isChecked()){

                gravarDadosUsuario(login);
            }
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);


        }
        else{

            Toast.makeText(getApplicationContext(),"Login ou senha inválidos",Toast.LENGTH_LONG).show();
        }
    }
}
