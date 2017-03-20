package br.com.marciopaulo.alunos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.marciopaulo.alunos.dao.AlunoDao;
import br.com.marciopaulo.alunos.model.Aluno;

import static br.com.marciopaulo.alunos.App.getContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {


    public Aluno getAluno() {
        return aluno;
    }

    private final Aluno aluno;
    EditText edtCadastroNome;
    Button btnCadastrar;
    View view;

    public CadastroFragment(Aluno aluno) {
        this.aluno=aluno;
    }

    public CadastroFragment(){
        this.aluno=new Aluno();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {

        
        view= inflater.inflate(R.layout.fragment_cadastro, container, false);

        edtCadastroNome =(EditText) view.findViewById(R.id.edtCadastroNome);
        btnCadastrar    =(Button) view.findViewById(R.id.btnCadastrar);
        if(aluno!=null){
            edtCadastroNome.setText(aluno.getNome());
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtCadastroNome.getText().toString().equals("")) {

                    AlunoDao alunoDao = new AlunoDao();
                    Aluno aluno = new Aluno();

                    if (getAluno().getId() > 0) {
                        aluno.setNome(edtCadastroNome.getText().toString());
                        aluno.setId(getAluno().getId());
                        alunoDao.atualizar(aluno);
                        aluno=null;
                    } else {
                        aluno.setNome(edtCadastroNome.getText().toString());
                        alunoDao.adicionar(aluno);
                    }

                   ListaFragment listaFragment = new ListaFragment();

                    FragmentManager manager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
                    manager
                            .beginTransaction()
                            .replace(R.id.content_main,listaFragment, listaFragment.getTag())
                            .commit();
                }
            }
        });

        return  view;
    }
}
