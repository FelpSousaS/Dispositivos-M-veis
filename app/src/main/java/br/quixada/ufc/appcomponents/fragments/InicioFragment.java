package br.quixada.ufc.appcomponents.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.quixada.ufc.appcomponents.R;

public class InicioFragment extends Fragment {

  public InicioFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        String[] musica = {"Sertanejo","Rock","Forró","Eletrônica","Rap","MPB"};
        int[] imagens = {R.drawable.music, R.drawable.music, R.drawable.music, R.drawable.music,R.drawable.music,R.drawable.music};
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        gridView.setAdapter(new GridAdapter(getContext(),musica,imagens));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Você selecionou " + musica[i], Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public class GridAdapter extends BaseAdapter{
      Context context;
      String[] musica;
      int[] imagens;

        public GridAdapter(Context context, String[] musica, int[] imagens) {
            this.context = context;
            this.musica = musica;
            this.imagens = imagens;
        }

        LayoutInflater layoutInflater;


        @Override
        public int getCount() {
            return musica.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(layoutInflater == null){
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            if(view == null){
                view = layoutInflater.inflate(R.layout.grid_item, null);
            }
            ImageView imageView = view.findViewById(R.id.grid_image);
            TextView textView = view.findViewById(R.id.item_name);
            imageView.setImageResource(imagens[i]);
            textView.setText(musica[i]);

            return view;
        }
    }
}