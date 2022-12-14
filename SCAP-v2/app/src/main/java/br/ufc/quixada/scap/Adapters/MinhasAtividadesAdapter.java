package br.ufc.quixada.scap.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import br.ufc.quixada.scap.FormAddAtividade;
import br.ufc.quixada.scap.Model.Atividades;
import br.ufc.quixada.scap.R;

public class MinhasAtividadesAdapter extends RecyclerView.Adapter<MinhasAtividadesAdapter.ViewHolder> {
    ArrayList<Atividades> minhasAtividades;
    FormAddAtividade activity;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomeAtividade, txtNomeAutor;
        ShapeableImageView titleImage;
        ImageView itemUpdate;
        ImageView itemDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleImage = itemView.findViewById(R.id.title_image);
            txtNomeAtividade = itemView.findViewById(R.id.txtAtvNome);
            txtNomeAutor = itemView.findViewById(R.id.txtAtvAutor);
        }

        public TextView getTextViewNome() {
            return txtNomeAtividade;
        }

        public TextView getTextViewAutor() {
            return txtNomeAutor;
        }

        public ImageView getItemUpdate() {
            return itemUpdate;
        }

        public ImageView getItemDelete() {
            return itemDelete;
        }

    }

    public MinhasAtividadesAdapter(FormAddAtividade activity, ArrayList<Atividades> atividades) {
        minhasAtividades = atividades;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_atividades, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhasAtividadesAdapter.ViewHolder holder, int position) {
        //Atividades atv = minhasAtividades.get(position);

        holder.getTextViewNome().setText(minhasAtividades.get(position).getNome_da_atividade());
        holder.getTextViewAutor().setText(minhasAtividades.get(position).getTipo_de_atividade());
        holder.getItemDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minhasAtividades.remove(holder.getAbsoluteAdapterPosition());
                MinhasAtividadesAdapter.this.notifyDataSetChanged();
            }
        });
        holder.getItemUpdate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minhasAtividades.get(holder.getAbsoluteAdapterPosition());
                MinhasAtividadesAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return minhasAtividades.size();
    }
}
