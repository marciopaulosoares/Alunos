package br.com.marciopaulo.alunos.listener;

import android.view.View;

/**
 * Created by marci on 19/03/2017.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
