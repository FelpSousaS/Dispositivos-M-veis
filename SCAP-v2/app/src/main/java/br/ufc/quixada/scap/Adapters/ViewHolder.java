package br.ufc.quixada.scap.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.ufc.quixada.scap.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView nome ,autor;
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v , getAbsoluteAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAbsoluteAdapterPosition());
                return true;
            }
        });
        nome = itemView.findViewById(R.id.txtAtvNome);
        autor = itemView.findViewById(R.id.txtAtvAutor);
    }
    private ViewHolder.ClickListener mClickListener;
    public  interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
         mClickListener = clickListener;
    }
}
