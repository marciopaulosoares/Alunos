package br.com.marciopaulo.alunos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.marciopaulo.alunos.adapter.AlunosAdapter;
import br.com.marciopaulo.alunos.dao.AlunoDao;
import br.com.marciopaulo.alunos.listener.ClickListener;
import br.com.marciopaulo.alunos.listener.RecyclerTouchListener;
import br.com.marciopaulo.alunos.model.Aluno;
import br.com.marciopaulo.alunos.util.DividerItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    private List<Aluno> alunoList= new ArrayList<>();
    private RecyclerView rcvAluno;
    private AlunosAdapter alunosAdapter;
    private MainActivity main;

    public ListaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getAlunos();

        View view =inflater.inflate(R.layout.fragment_lista, container, false);

        rcvAluno=(RecyclerView)view.findViewById(R.id.rcvAlunos);
        rcvAluno.addItemDecoration(new DividerItemDecoration(App.getContext(), LinearLayoutManager.VERTICAL));
        alunosAdapter=new AlunosAdapter(alunoList);


        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));

        RecyclerView.LayoutManager manager= new LinearLayoutManager(App.getContext());

        rcvAluno.setLayoutManager(manager);
        rcvAluno.setItemAnimator(new DefaultItemAnimator());
        rcvAluno.setAdapter(alunosAdapter);






        return view;
        
    }

    private void getAlunos() {

        AlunoDao alunoDao = new AlunoDao();
        alunoList =alunoDao.listar();

    }




}
