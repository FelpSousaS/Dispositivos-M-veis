package br.ufc.quixada.scap.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.scap.Atividade;
import br.ufc.quixada.scap.Edit_atividade;
import br.ufc.quixada.scap.FormAddAtividade;
import br.ufc.quixada.scap.ListarAtividade;
import br.ufc.quixada.scap.MainActivity;
import br.ufc.quixada.scap.Model.Atividades;
import br.ufc.quixada.scap.R;

public class ListarAtividadesAdapter extends RecyclerView.Adapter<ViewHolder> {
    Atividade atividade;
    FormAddAtividade activity;
    ListarAtividade listarAtividade;
    ArrayList<Atividades> minhasAtividades;
    Edit_atividade edit_atividade;
    List<Atividades> atividadesList;
    MainActivity mainActivity;



    public ListarAtividadesAdapter(ListarAtividade listarAtividade, List<Atividades> atividadesList) {
        this.listarAtividade = listarAtividade;
        this.atividadesList = atividadesList;

    }
    public ListarAtividadesAdapter(MainActivity mainAtividade, List<Atividades> atividadesList) {
        this.mainActivity = mainAtividade;
        this.atividadesList = atividadesList;

    }


    public ListarAtividadesAdapter(Edit_atividade edit_atividade) {
        this.edit_atividade = edit_atividade;


    }

    public ListarAtividadesAdapter(Atividade atividade) {
        this.atividade = atividade;


    }

    public ListarAtividadesAdapter(FormAddAtividade activity, ArrayList<Atividades> atividades) {
        minhasAtividades = atividades;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_atividades, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String nome = atividadesList.get(position).getNome_da_atividade();
                String autor = atividadesList.get(position).getAutor();

                //Toast.makeText(listarAtividade, nome + "\n" + autor, Toast.LENGTH_SHORT).show();


                String id = atividadesList.get(position).getId();
                String tipo_da_atv = atividadesList.get(position).getTipo_de_atividade();
                String nome_da_atv = atividadesList.get(position).getNome_da_atividade();
                String descricao = atividadesList.get(position).getDescricao_da_atividade();
                String objetivo = atividadesList.get(position).getObjetivo_da_atividade();
                String metodologia = atividadesList.get(position).getMetodologia_da_atividade();
                String resultados = atividadesList.get(position).getResultados_da_atividade();
                String avaliacao = atividadesList.get(position).getAvaliacao_da_atividade();

                Intent intent = new Intent(listarAtividade, Atividade.class);




                //enviando os dados para a activity
                intent.putExtra("id", id);
                intent.putExtra("tipo_da_atv", tipo_da_atv);
                intent.putExtra("nome_da_atv", nome_da_atv);
                intent.putExtra("descricao", descricao);
                intent.putExtra("objetivo", objetivo);
                intent.putExtra("metodologia", metodologia);
                intent.putExtra("resultados", resultados);
                intent.putExtra("avaliacao", avaliacao);

                //start activity
                listarAtividade.startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {
                // criando o alerta
                AlertDialog.Builder builder = new AlertDialog.Builder(listarAtividade);

                //opçoes do display
                String[] options = {"Editar", "Excluir"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            //editar click
                            // get dados
                            String id = atividadesList.get(position).getId();
                            String tipo_da_atv = atividadesList.get(position).getTipo_de_atividade();
                            String nome_da_atv = atividadesList.get(position).getNome_da_atividade();
                            String descricao = atividadesList.get(position).getDescricao_da_atividade();
                            String objetivo = atividadesList.get(position).getObjetivo_da_atividade();
                            String metodologia = atividadesList.get(position).getMetodologia_da_atividade();
                            String resultados = atividadesList.get(position).getResultados_da_atividade();
                            String avaliacao = atividadesList.get(position).getAvaliacao_da_atividade();


                            Intent intent = new Intent(listarAtividade, Edit_atividade.class);
                            //enviando os dados para a activity
                            intent.putExtra("id", id);
                            intent.putExtra("tipo_da_atv", tipo_da_atv);
                            intent.putExtra("nome_da_atv", nome_da_atv);
                            intent.putExtra("descricao", descricao);
                            intent.putExtra("objetivo", objetivo);
                            intent.putExtra("metodologia", metodologia);
                            intent.putExtra("resultados", resultados);
                            intent.putExtra("avaliacao", avaliacao);

                            //start activity
                            listarAtividade.startActivity(intent);

                        }
                        if (which == 1) {
                            listarAtividade.deleteData(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nome.setText(atividadesList.get(position).getNome_da_atividade());
        holder.autor.setText(atividadesList.get(position).getAutor());
    }

    @Override
    public int getItemCount() {
        return atividadesList.size();
    }
}