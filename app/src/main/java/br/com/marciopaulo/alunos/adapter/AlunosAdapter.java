package br.com.marciopaulo.alunos.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.List;

import br.com.marciopaulo.alunos.App;
import br.com.marciopaulo.alunos.CadastroFragment;
import br.com.marciopaulo.alunos.R;
import br.com.marciopaulo.alunos.dao.AlunoDao;
import br.com.marciopaulo.alunos.model.Aluno;

/**
 * Created by marci on 19/03/2017.
 */

public class AlunosAdapter extends RecyclerView.Adapter<AlunosAdapter.AlunoViewHolder> {

    private List<Aluno> alunosList;
    private Context context;
    public AlunosAdapter(List<Aluno> alunosList) {
        this.alunosList = alunosList;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View itemView=LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.aluno_list_row,parent,false);
        return new AlunoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AlunoViewHolder holder, final int position) {
        Aluno aluno= alunosList.get(position);
        holder.tvNome.setText(aluno.getNome());

        holder.tvNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno=  alunosList.get(position);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                CadastroFragment cadastroFragment= new CadastroFragment(aluno);

                manager.beginTransaction()
                        .replace(R.id.content_main,cadastroFragment, cadastroFragment.getTag())
                        .commit();

            }
        });

        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno=  alunosList.get(position);
                String nome = aluno.getNome();
                AlunoDao dao = new AlunoDao();
                dao.excluir(aluno.getId());

                alunosList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,alunosList.size());
                Toast.makeText(App.getContext(),"Removido : " + nome, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return alunosList.size();
    }

    public class AlunoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNome;
        public Button btRemove;

        public AlunoViewHolder(View itemView) {
            super(itemView);
            tvNome =(TextView) itemView.findViewById(R.id.tvNome);
            btRemove=(Button) itemView.findViewById(R.id.btRemove);
        }
    }
}
