package br.com.marciopaulo.alunos.helpers;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by marci on 19/03/2017.
 */

public class ProgressDialogHelper {
    public static ProgressDialog Show(Context context, String titulo, String mensagem){

        ProgressDialog progressDialog= new ProgressDialog(context);
        progressDialog.setTitle(titulo);
        progressDialog.setMessage(mensagem);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;

    }
}
